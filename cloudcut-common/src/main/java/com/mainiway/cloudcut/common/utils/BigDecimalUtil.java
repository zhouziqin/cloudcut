package com.mainiway.cloudcut.common.utils;

import java.math.BigDecimal;

/**
 * ***************************************************************************
 *模块名 : BigDecimalUtil
 *文件名 : BigDecimalUtil.java
 *创建时间 : 2016年7月8日
 *实现功能 : BigDecimal的计算
 *作者 : liliangjun
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年7月8日 v0.0.1 liliangjun 创建
 ****************************************************************************
 */
public class BigDecimalUtil {


	/**
	 *1的BigDecimal实例，用来进行四舍五入处理
	 */
	public static final BigDecimal  DECIMAL_1 = new BigDecimal(1);

	/**
	 * 平台默认的小数点位数
	 */
	public static final int  SCALE = 5;


	/**
	 * ====================================================================
	 *函 数 名： @param orialMoneySum 原来的商品总价
	 *函 数 名： @param orialCommodityNum 原来的商品数量
	 *函 数 名： @param updateCommodityNum  更新后的商品数量
	 *函 数 名： @return
	 *功 能： 计算更新之后的商品总价
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年7月8日 v0.0.1 liliangjun 创建
	====================================================================
	 */
	public static String calculateUpdateMoneySum(String orialMoneySum,String orialCommodityNum,String updateCommodityNum){
	    return  String.valueOf(
	    		new BigDecimal(orialMoneySum).divide(new BigDecimal(orialCommodityNum),SCALE,BigDecimal.ROUND_HALF_UP)// 原来的商品总价  / 原来的商品数量 = 商品单价(四舍五入处理之后)
	    		.multiply(new BigDecimal(updateCommodityNum))// 商品单价  * 更新后的商品数量 = 更新后的商品总价
				.divide(DECIMAL_1,SCALE,BigDecimal.ROUND_HALF_UP)// 更新后的商品总价 进行四舍五入处理
				);
	}


	/**
	 * ====================================================================
	 *函 数 名： @param orialMoneySum
	 *函 数 名： @param orialCommodityNum
	 *函 数 名： @return
	 *功 能： 计算商品单价
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年8月4日 v0.0.1 liliangjun 创建
	====================================================================
	 */
	public static String calculateUnitPrice(String orialMoneySum,String orialCommodityNum){
	    return  String.valueOf(
	    		new BigDecimal(orialMoneySum).divide(new BigDecimal(orialCommodityNum),SCALE,BigDecimal.ROUND_HALF_UP)// 原来的商品总价  / 原来的商品数量 = 商品单价(四舍五入处理之后)
			);
	}

//	public static void main(String[] args) {
//		System.out.println(BigDecimalUtil.calculateUnitPrice("30000.00020", "3"));
//	}



}
