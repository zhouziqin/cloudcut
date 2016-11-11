package com.mainiway.cloudcut.common.constant;


public class OperateRemark {

	//1.订单状态备注
	/**
	 *买家完成提交订单
	 */
	public static final String OrderStatus1 =   "买家完成提交订单" ;
	/**
	 *买家取消订单
	 */
	public static final String OrderStatus1_7_a =   "买家取消订单" ;
	/**
	 *付款超时，系统自动关闭订单
	 */
	public static final String OrderStatus1_7_b =   "待付款超时，系统自动关闭订单" ;

	/**
	 *待收货超时，系统自动确认收货
	 */
	public static final String OrderStatus5_6_c =   "待收货超时，系统自动确认收货" ;
	/**
	 *买家付款成功
	 */
	public static final String OrderStatus1_2_a =   "买家付款成功" ;
	/**
	 *买家确认收货
	 */
	public static final String OrderStatus5_6_a =   "买家确认收货" ;
	/**
	 *工厂确认订单
	 */
	public static final String OrderStatus2_3 =   "工厂确认订单" ;
	/**
	 *工厂已发货，目的地到经销商地址
	 */
	public static final String OrderStatus3_4 =   "工厂已发货，目的地到经销商地址" ;
	 /**
	 *工厂已发货，目的地到用户地址
	 */
	public static final String OrderStatus3_5 =   "工厂已发货，目的地到用户地址" ;

	 /**
	 *经销商确认到货
	 */
	public static final String OrderStatus4_5 =   "经销商确认到货" ;


	 /**
	 *经销商确认买家提货成功
	 */
	public static final String OrderStatus5_6_b =   "经销商确认买家提货成功" ;


	 /**
	 *经销商确认收款成功(预付款或全款
	 */
	public static final String OrderStatus1_2_b =   "经销商确认收款成功(预付款或全款 " ;


	//2.退货状态备注
	/**
	 *买家申请退货
	 */
	public static final String ReturnStatus1 =   "买家申请退货" ;
	/**
	 *买家自己关闭退货
	 */
	public static final String ReturnStatus1_4_a =   "买家自己关闭退货" ;
	/**
	 *买家已经开始物流发货
	 */
	public static final String ReturnStatus2_3 =   "买家已经开始物流发货" ;
	/**
	 *待退货超时，系统自动关闭退货
	 */
	public static final String ReturnStatus2_5 =   "待退货超时，系统自动关闭退货" ;
	/**
	 *工厂同意退货
	 */
	public static final String ReturnStatus1_2_a =   "工厂同意退货" ;
	/**
	 *待审核超时，系统自动默认工厂同意退货
	 */
	public static final String ReturnStatus1_2_b =   "待审核超时，系统自动默认工厂同意退货" ;
	/**
	 *工厂拒绝退货
	 */
	public static final String ReturnStatus1_4_b =   "工厂拒绝退货，关闭退货" ;
	 /**
	 *工厂确认收到退货
	 */
	public static final String ReturnStatus3_4_a =   "工厂确认收到退货" ;
	/**
	 *经销商确认收到退货
	 */
	public static final String ReturnStatus3_4_b =   "经销商确认收到退货" ;
	/**
	 *待收到退货超时，系统自动退货成功
	 */
	public static final String ReturnStatus3_4_c =   "待收到退货超时，系统自动退货成功" ;





}
