package com.mainiway.cloudcut.common.businesses.id;

import java.util.HashSet;

/**
 * ***************************************************************************
 *模块名 : IdGenerator
 *文件名 : IdGenerator.java
 *创建时间 : Aug 4, 2016
 *实现功能 : 业务ID生成器 
 *作者 : justin
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *Aug 4, 2016 v0.0.1 justin 创建
 ****************************************************************************
 */
final public class IdGenerator {

	final private VarlenaIdWorker idWorker = new VarlenaIdWorker(0, 0, 8, true);

	/**
	 * ====================================================================
	 *函 数 名： @param deviceId
	 *函 数 名： @return
	 *功 能： 生成订单ID，共16位，前12位为唯一UUID，后4位为用户ID的后4位，为以后分库分表使用
	 *
	 *            订单ID结构
	 *+---------------------------------------------------------+
	 * | 唯一ID | 用户ID尾数
	 *+----------------------------------------------------------+
	 *  
	 *         用户ID尾数
	 *+-----------------------+
	 * |    库信息  |  表信息      |
	 *+-----------------------+
	 *
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *Aug 4, 2016 v0.0.1 justin 创建
	====================================================================
	 */
	public long generateOrderId(long uid) {

		long UID_MASK = 10000;

		// 通过取模得到UID的后四位整数
		int uidTail = (int) (uid % UID_MASK);		
		long orderId = idWorker.nextId(true) * UID_MASK + uidTail;
		return orderId;
	}

	// 可用数据库自增长字段和Zookeeper序列节点
	// public long generateAccountId() {
	// return atomic.getAndIncrement();
	// }

	public long generateCommdityId() {
		return generateUUID();
	}

	public long generateUUID() {
		return idWorker.nextId(true);
	}

	public String formatId(long id, int width) {
		return String.format("%1$0" + width + "d", id);
	}

//	public static void main(String[] args) {
//
//		IdGenerator idGen = new IdGenerator();
//		HashSet<Long> idSet = new HashSet<Long>();
//		
//		for (int i = 0; i < 5000000; i++) {
//
//			long orderId = idGen.generateOrderId(i + 1);
//			//System.out.println();
//			//System.out.println("UID=" + idGen.formatId(i + 1, 12));
//			//System.out.println("ORDERID=" + idGen.generateOrderId(i + 1));
//			if(!idSet.add(orderId)){
//				System.out.println("ERROR: Id duplicate " + orderId);
//			}
//		}
//	}
}
