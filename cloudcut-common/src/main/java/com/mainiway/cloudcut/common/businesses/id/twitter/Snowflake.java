package com.mainiway.cloudcut.common.businesses.id.twitter;

import com.mainiway.cloudcut.common.businesses.id.IdWorker;
/**
 * An Implementation of Twitter Snowflake（雪片） ID Generator
 *
 * This package provides unique id in distribute system
 *  the algorithm is inspired by Twitter's famous snowflake
 *  its link is: https://github.com/twitter/snowflake/releases/tag/snowflake-2010
 *
 * 0                               41                             51                           64
 *+----------------------+--------------------+--------------------+
 * |           时间戳             |           设备ID         |      序列数               |
 *+----------------------+--------------------+--------------------+
 *
 * id  = timestamp | workerid | sequence (eg. 1451063443347648410)
 * An id is composed by three part: timestamp in millon second, worker id, and sequence. Sequence is zero default.
 * when timestamp is the same, we use sequence to avoid conflict
 *
 */

public class Snowflake implements IdWorker {

	private final long twepoch = 1288834974657L;
	private final long workerIdBits = 5L;
	private final long datacenterIdBits = 5L;
	private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
	private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	private final long sequenceBits = 12L;
	private final long workerIdShift = sequenceBits;
	private final long datacenterIdShift = sequenceBits + workerIdBits;
	private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
	private final long sequenceMask = -1L ^ (-1L << sequenceBits);

	private long workerId;
	private long datacenterId;
	private long sequence = 0L;
	private long lastTimestamp = -1L;
	
	public Snowflake(long workerId, long datacenterId) {
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format(
					"worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException(String.format(
					"datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}
		this.workerId = workerId;
		this.datacenterId = datacenterId;
	}

	@Override
	public synchronized long nextId(boolean bMutliThread) {
		long timestamp = timeGen();
		if (timestamp < lastTimestamp) {
			throw new RuntimeException(String.format(
					"Clock moved backwards.  Refusing to generate id for %d milliseconds",
					lastTimestamp - timestamp));
		}
		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0L;
		}

		lastTimestamp = timestamp;

		return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
				| (workerId << workerIdShift) | sequence;
	}

	protected long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	protected long timeGen() {
		return System.currentTimeMillis();
	}
}
