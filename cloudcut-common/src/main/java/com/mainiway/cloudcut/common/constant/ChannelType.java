package com.mainiway.cloudcut.common.constant;
/**
 * ***************************************************************************
 *模块名 : TradeChannel
 *文件名 : TradeChannel.java
 *创建时间 : Jun 28, 2016
 *实现功能 :  渠道类型定义， 根据不同场景选择不同的支付方式
 *作者 : justin
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *Jun 28, 2016 v0.0.1 justin 创建
 ****************************************************************************
 */
public enum ChannelType {

	MW_TEST(0),          //0虚拟支付（仅供测试使用）
	WX_NATIVE(1),     // 1微信公众号二维码支付
	WX_JSAPI(2),        // 2微信公众号支付
	ALI_WEB(3),          // 3支付宝网页支付
	ALI_QRCODE(4),    // 4支付宝内嵌二维码支付
	ALI_WAP(5),         // 5支付宝移动网页支付
	UN_WEB(6),          // 6银联网页支付
	UN_WAP(7),         // 7银联移动网页支付
	JD_WEB(8),           // 8京东网页支付
	JD_WAP(9),          // 9京东移动网页支付
	YEE_WEB(10),        // 10易宝网页支付
	YEE_WAP(11),        // 11易宝移动网页支付
	YEE_NOBANKCARD(12),   // 12易宝点卡支付
	KUAIQIAN_WEB(13),        //13快钱网页支付
	KUAIQIAN_WAP(14),        // 14快钱移动网页支付
	BD_WEB(15),                    // 15百度网页支付
	BD_WAP(16),                    // 16百度移动网页支付
	ABC_WEB(17);                  // 17农行网页支付
	private int index;
	private ChannelType(int index){
		this.index = index;
	}
	public int getIndex(){
		return index;
	}
}

