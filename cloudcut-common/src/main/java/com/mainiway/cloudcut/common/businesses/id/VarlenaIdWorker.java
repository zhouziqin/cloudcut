package com.mainiway.cloudcut.common.businesses.id;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

/**
 * ***************************************************************************
 *模块名 : VarlenaIdWorker
 *文件名 : VarlenaIdWorker.java
 *创建时间 : Aug 10, 2016
 *实现功能 :  根据Snowflake算法修改，实现可变长ID。和原算法一样在多线程环境下可能发生碰撞
 *作者 : justin
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *Aug 10, 2016 v0.0.1 justin 创建
 ****************************************************************************
 */
public class VarlenaIdWorker implements IdWorker {

	// 用于重设时间基准，为0时以1970为基准
	private final long twepoch = 0; // 1288834974657L; //2010-11-04 09:42:54

	// 设备ID所占位数
	private long workerIdBits = 2L;

	// 序列数所占位数
	private long sequenceBits = 16L;

	// 服务器设备ID，可以用数据中心+机器号构成
	private long MAX_WORKER_ID;

	// 最大序列数，占16位时最大数为65535，占8位时最大数为255
	private int SEQUENCE_MASK;

	private long workerId;
	private boolean bShortTime = false;
	private long lastTimestamp = -1L;

	private long sequence = 0L;

	public VarlenaIdWorker(int deviceId) {

		init(deviceId);
	}

	public VarlenaIdWorker(int workerId_bits, int workerId, int sequence_bits, boolean shortTime) {

		this.workerIdBits = workerId_bits;
		this.sequenceBits = sequence_bits;
		this.bShortTime = shortTime;

		init(workerId);
	}

	@Override
	public long nextId(boolean bMutliThread) {
		long newId = 0;

		if (bMutliThread) {
			synchronized (VarlenaIdWorker.class) {
				newId = generateId();
			}
		} else {
			newId = generateId();
		}

		return newId;
	}
	
	/**
	 * ====================================================================
	 *函 数 名： generateId
	 *功 能：计算唯一ID，由时间戳+设备ID+序列数三部分组成
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *Aug 9, 2016 v0.0.1 justin 创建
	====================================================================
	 */
	private long generateId() {

		long timestamp = timeGen();

		// System.out.println("Thread-"+ Thread.currentThread().getId() +
		// "Entry...");

		// 如果上次计算ID的时间戳大于当前时间戳，抛出异常
		if (timestamp < lastTimestamp) {
			throw new IllegalArgumentException(String.format(
					"Clock moved backwards.  Refusing to generate id for %d milliseconds",
					lastTimestamp - timestamp));
		}

		// 如果上次计算ID的时间戳与当前相同，说明是在同一时间产生ID，增加原子递增数为序列数
		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & SEQUENCE_MASK;

			if (sequence == 0) {
				// 如果序列数达到最大值，延迟到下一个不同的时间戳再计算ID
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			// 序列数重置为0，但会出现大量碰撞
			// sequence = 0;
		}

		lastTimestamp = timestamp;
		return (timestamp - twepoch << (sequenceBits + workerIdBits)) | (workerId << sequenceBits)
						| sequence;
	
	}

	private long tilNextMillis(final long lastTimestamp) {

		long timestamp = this.timeGen();

		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		// System.out.println("waitNextTime..");
		return timestamp;
	}

	private long timeGen() {
		return (bShortTime ? (long) (System.currentTimeMillis() / 1000) : System
				.currentTimeMillis());
	}

	private void init(int workerId) {

		// 计算最大WORKER_ID和最大序列数
		MAX_WORKER_ID = -1L ^ -1L << workerIdBits;
		SEQUENCE_MASK = (int) (-1L ^ -1L << sequenceBits);

		if (workerId > MAX_WORKER_ID || workerId < 0) {
			throw new IllegalArgumentException(String.format(
					"worker Id can't be greater than %d or less than %d", this.MAX_WORKER_ID));
		}

		this.workerId = workerId;
	}

	private static void idWorkerTest(IdWorker worker, HashSet<Long> idTable) {

		System.out.println("Thread-" + Thread.currentThread().getId() + " IdWorker ....");

		long start = System.currentTimeMillis();
		int idNum = 5000000; // 5百万

		for (int i = 0; i < idNum; i++) {
			try {
				long newId = worker.nextId(false);

				if (idTable != null) {
					if (idTable.size() > 5000000)
						idTable.clear();

					if (!idTable.add(newId)) {
						System.out.println("Thread-" + Thread.currentThread().getId()
								+ " ERROR: Id duplicate " + newId);
						System.exit(0);
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		long duration = System.currentTimeMillis() - start;
		System.out.println("Thread-" + Thread.currentThread().getId() + ": Generated Id total: "
				+ duration + "ms, " + idNum / duration + "/ms");
	}
//
//	public static void main(String[] args) {
//
//		final HashSet<Long> idTable = new HashSet<Long>();
//
//		// 单线程测试
//		IdWorker worker1 = new VarlenaIdWorker(0, 0, 8, true);
//		idWorkerTest(worker1, null);
//
//		try {
//			System.out.println("press any key continue...");
//			System.in.read();
//		} catch (IOException e) {
//		}
//
//		int threadNum = 10;
//		final VarlenaIdWorker worker2 = new VarlenaIdWorker(0, 0, 8, false);
//
//		for (int i = 0; i < threadNum; i++) {
//			Thread th = new Thread(new Runnable() {
//
//				@Override
//				public void run() {
//					idWorkerTest(worker2, idTable);
//				}
//			});
//
//			th.start();
//		}
//
//		try {
//			System.out.println("press any key continue...");
//			System.in.read();
//		} catch (IOException e) {
//		}
//	}
}
