package com.mainiway.cloudcut.util;

public class DbOperaCode {

	//一.基本操作
	public static final String SELECT = "_SELECT";//查询
	public static final String INSERT = "_INSERT";//新增
	public static final String UPDATE = "_UPDATE";//修改
	public static final String DEL = "_DEL";//删除
	public static final String SELECT_ROWS = "_SELECT_ROWS";//行数
	public static final String SELECT_BIIDMAX = "_SELECT_BIIDMAX";//最大业务ID


	//二.表名
	//1 非业务表
	public static final String ERRORCODE = "IDB_ERRORCODE";//1.1 错误返回值信息表
	public static final String PROVINCE = "IDB_PROVINCE";//1.2 省
	public static final String CITY = "IDB_CITY";//1.3 市
	public static final String AREA = "IDB_AREA";//1.4 区
	//2 权限管理
	public static final String RESOURCE = "IDB_RESOURCE";//2.1 用户权限资源表
	public static final String ROLE = "IDB_ROLE";//2.2 用户角色表
	public static final String ROLE_RIGHT = "IDB_ROLERIGHT";//2.3 角色权限表
	public static final String USER_ROLE = "IDB_USERROLE";//2.4 用户角色表
	public static final String USER_GROUP = "IDB_USERGROUP";//2.5 用户组表
	public static final String USER_GROUP_REL = "IDB_USERGROUPREL";//2.6 用户组角色表
	public static final String USER_GROUP_ROLE = "IDB_USERGROUPROLE";//2.7 用户组关系表
	//3 个人中心
	public static final String USER = "IDB_USER";//3.1 用户信息表
	public static final String ADDRESS = "IDB_ADDRESS";//3.2 收货地址表
	public static final String GOODS_ASSESS = "IDB_GOODSASSESS";//3.3 商品评价表
	public static final String FOOTMARK = "IDB_FOOTMARK";//3.4 足迹表
	//4 工厂中心
	//4.1 基础管理
	public static final String FACTORY = "IDB_FACTORY";//4.1.1 工厂入驻表
	public static final String FACTORY_PIC = "IDB_FACTORYPIC";//4.1.2 工厂入驻附加图片表
	public static final String FACTORY_USER = "IDB_FACTORYUSER";//4.1.3 工厂用户信息表
	public static final String FACTORY_APPROVAL = "IDB_FACTORYAPPROVAL";//4.1.4 工厂入驻审核表
	public static final String FACTORY_SHOP = "IDB_FACTORYSHOP";//4.1.5 工厂店铺关系表
	public static final String SHOP_ONLINE_INFO = "IDB_SHOPONLINEINFO";//4.1.6 店铺信息表
	public static final String SHOP_PAY_TYPE = "IDB_SHOPPAYTYPE";//4.1.7 店铺支付类型表
	public static final String SHOP_PAY_METHOD = "IDB_SHOPPAYMETHOD";//4.1.8 店铺支付方式表
	public static final String SHOP_DELIVERY_METHOD = "IDB_SHOPDELIVERYMETHOD";//4.1.9 店铺配送方式表
	public static final String SHOP_ENTITY_INFO = "IDB_SHOPENTITYINFO";//4.1.10 线下门店表
	//4.2 商品管理
	public static final String SHOP_COMMODITY = "IDB_SHOPCOMMODITY";//4.2.1 店铺商品关系表
	public static final String FACTORY_COMMODITY = "IDB_FACTORYCOMMODITY";//4.2.2工厂 商品关系表
	public static final String COMMODITY = "IDB_COMMODITY";//4.2.3 商品表
	public static final String COMMODITY_APPEND = "IDB_COMMODITYAPPEND";//4.2.4 商品附加信息表
	public static final String COMMODITY_ATTR = "IDB_COMMODITYATTR";//4.2.5商品属性表
	public static final String COMMODITY_BOM = "IDB_COMMODITYBOM";//4.2.6 商品BOM值关系表
	public static final String COMMODITY_BOM_DETAILS = "IDB_COMMODITYBOMDETAILS";//4.2.7 商品BOM明细关系表
	public static final String COMMODITY_SUPPORT  = "IDB_COMMODITYSUPPORT";//商品辅助信息表
	public static final String COMMODITY_PAY_TYPE  = "IDB_COMMODITYPAYTYPE";//4.1.4 商品支付类型表
	public static final String COMMODITY_PAY_METHOD  = "IDB_COMMODITYPAYMETHOD";//4.1.5 商品支付方式表
	public static final String COMMODITY_DELIVERY_METHOD  = "IDB_COMMODITYDELIVERYMETHOD";//4.1.6 商品配送方式表
	public static final String STOCK_COMMODITY  = "IDB_STOCKCOMMODITY";//库存商品信息表
	public static final String STOCK_COMMODITY_PRICE  = "IDB_STOCKCOMMODITYPRICE";//库存商品价格信息表
	public static final String STOCK_COMMODITY_BOM  = "IDB_STOCKCOMMODITYBOM";//库存商品BOM明细表
	public static final String STOCK_COMMODITY_AREA_PRICE  = "IDB_STOCKCOMMODITYAREAPRICE";//库存商品地区价格表

	//4.3 BOM管理
	public static final String BOM = "IDB_BOM";//4.3.1 BOM表
	public static final String BOM_DETAIL = "IDB_BOMDETAIL";//4.3.2 BOM明细表
	//4.4  经销商表管理
	public static final String DISTRIBUTOR = "IDB_DISTRIBUTOR";//4.4.1 经销商表
	//5.文件管理
	//6.设计师管理
	public static final String DESIGNER = "IDB_DESIGNER";//6.1.1 设计师信息表
	public static final String DESIGNER_APPROVAL = "IDB_DESIGNERAPPROVAL";//6.1.2 设计师审核表
	//7.设计管理
	public static final String WORKS = "IDB_WORKS";//7.1.1 设计作品表
	public static final String WORKS_SIGN = "IDB_WORKSSIGN";//7.1.2 设计作品签约表
	// 8运营维护
	//8.1 基本资料
	public static final String OPERATE_USER = "IDB_OPERATEUSER";//8.1.1 运营用户信息表
	public static final String BRAND = "IDB_BRAND";//8.1.2品牌表
	public static final String GOODS_KIND = "IDB_GOODSKIND";//8.1.3 品类表
	public static final String GOODS_KIND_ATTR = "IDB_GOODSKINDATTR";//8.1.4 品类属性表
	public static final String GOODS_KIND_ATTR_DETAIL = "IDB_GOODSKINDATTRDETAIL";//8.1.5 品类属性明细表

	//8.2 广告管理
	public static final String AD = "IDB_AD";//8.2.1 广告表
	public static final String AD_POSITION = "IDB_ADPOSITION";//8.2.2 广告位表
	// 8.3 资讯管理
	public static final String INFO = "IDB_INFO";//8.3.1 资讯表
	public static final String INFO_COMMENT = "IDB_INFOCOMMENT";//8.3.2 资讯评论关系表
	public static final String COMMENT = "IDB_COMMENT";//8.3.3 评论表
	public static final String CLICKLIKES = "IDB_CLICKLIKES";//8.3.3点赞表
	//8.4 话题管理
	public static final String TOPIC_COMMENT = "IDB_TOPICCOMMENT";//8.4.1 话题评论关系表
	public static final String TOPIC = "IDB_TOPIC";//8.4.2 话题表
	//8.5 活动管理
	public static final String ACTIVITY = "IDB_ACTIVITY";//8.5.1 活动表
	public static final String ACTIVITY_USER = "IDB_ACTIVITYUSER";//8.5.2 活动用户表

	//8.6其他
	public static final String FW_SCHEME = "IDB_FWSCHEME";//8.6.1 过滤词方案表
	public static final String FW_SCHEME_DETAILS = "IDB_FWSCHEMEDETAILS";//8.6.2 过滤词方案明细表
	public static final String MODULE_FW_SCHEME = "IDB_MODULEFWSCHEME";//8.6.3 版块过滤词方案关系表
	public static final String FILTER_WORDS = "IDB_FILTERWORDS";//8.6.3 过滤词组表
	public static final String NOTICE = "IDB_NOTICE";//8.6.4 公告表
	public static final String TRADE = "IDB_TRADE";//8.6.5 行业表
	public static final String CONSULT = "IDB_CONSULT";//8.6.6 咨询表

	//8.7发现管理
	public static final String DISCOVER = "IDB_DISCOVER";//8.7.1 发现表
	//8.8卡券管理
	public static final String COUPON = "IDB_COUPON";//8.8.1 卡券表
	public static final String COUPON_COMMODITY = "IDB_COUPONCOMMODITY";//8.8.2 卡券适用商品表
	public static final String USER_COUPON = "IDB_USERCOUPON";//8.8.3 用户卡券信息表
	//8.9试乘试驾
	public static final String TESTDRIVE_APPLY = "IDB_TESTDRIVEAPPLY";//8.9.1 试乘试驾申请表
	//8.10订单管理
	public static final String ORDER = "IDB_ORDER";//8.10.1 订单主表
	public static final String ORDER_COMMODITY_BOM = "IDB_ORDERCOMMODITYBOM";//订单商品BOM表(t_order_commodity_bom)
	public static final String ORDER_DETAIL = "IDB_ORDERDETAIL";//8.10.2 订单明细表
	public static final String ORDER_COUPON = "IDB_ORDERCOUPON";//8.10.3 订单明细卡券使用表
	public static final String ORDER_PRD_TRACK = "IDB_ORDERPRDTRACK";//8.10.4 生产追踪信息表
	public static final String ORDERSTATUS_TRANSFER = "IDB_ORDERSTATUSTRANSFER";//8.10.5 订单状态流转表
	public static final String ORDER_PAYMENT = "IDB_ORDERPAYMENT";//8.10.6 订单支付信息表
	public static final String PAYORDER = "IDB_PAYORDER";//支付订单表
	public static final String REFUNDORDER = "IDB_REFUNDORDER";//退款表

	public static final String ORDER_LOGISTICS = "IDB_ORDERLOGISTICS";//8.10.7 订单物流信息表
	public static final String LOGISTICS_COMPANY = "IDB_LOGISTICSCOMPANY";//8.10.8 物流公司表
	public static final String PICKUP = "IDB_PICKUP";// 用户上门取货表
    //8.11 充电桩管理chargingpile_apply
	public static final String CHARGINGPILE_APPLY = "IDB_CHARGINGPILEAPPLY";//8.11.1 充电桩申请表
	public static final String CHARGINGPILE_APPLY_CHECK = "IDB_CHARGINGPILEAPPLYCHECK";//8.11.2 充电桩申请审核表

	//8.12 购物车管理
	public static final String CART_COMMODITY = "IDB_CARTCOMMODITY";//8.12.1 购物车商品信息表
	public static final String CART_COMMODITY_BOM = "IDB_CARTCOMMODITYBOM";//8.12.2 购物车商品BOM表

	//8.13退货管理
	public static final String RETURN_GOODS = "IDB_RETURNGOODS";//8.13.1 退货表
	public static final String RETURNSTATUS_TRANSFER = "IDB_RETURNSTATUSTRANSFER";//8.13.2 退货状态流转表
	public static final String RETURN_LOGISTICS = "IDB_RETURNLOGISTICS";//8.13.2 退货状态流转表

	//三.事务操作TRANS_BEGIN
	public static final String TRANS_BEGIN = "TRANS_BEGIN";
	public static final String TRANS_COMMIT = "TRANS_COMMIT";


	//四、视图
	//商品视图
	public static final String COMMODITY_VIEW = "VIEW_ITERCOMMODITY_SELECT";
	public static final String COMMODITY_VIEW_ROWS = "VIEW_ITERCOMMODITY_SELECT_ROWS";
	public static final String COMMODITY_BOM_VIEW = "VIEW_ITERCOMMODITYBOM_SELECT";
	public static final String COMMODITY_BOM_VIEW_ROWS = "VIEW_ITERCOMMODITYBOM_SELECT_ROWS";
	public static final String COMMODITY_ATTR_VIEW = "VIEW_ITERCOMMODITYATTR_SELECT";
	public static final String COMMODITY_ATTR_VIEW_ROWS = "VIEW_ITERCOMMODITYATTR_ROWS";

	//工厂视图
	public static final String FACTORY_VIEW = "VIEW_ITERFACTORY_SELECT";
	public static final String FACTORY_VIEW_ROWS = "VIEW_ITERFACTORY_SELECT_ROWS";
	//店铺视图
	public static final String SHOP_ONLINE_INFO_VIEW = "VIEW_ITERSHOPONLINEINFO_SELECT";
	public static final String SHOP_ONLINE_INFO_VIEW_ROWS = "VIEW_ITERSHOPONLINEINFO_SELECT_ROWS";
	//设计品视图
	public static final String WORKS_VIEW = "VIEW_ITERWORKS_SELECT";
	public static final String WORKS_VIEW_ROWS = "VIEW_ITERWORKS_SELECT_ROWS";
	//评论视图
	public static final String COMMENT_VIEW = "VIEW_ITERCOMMENT_SELECT";
	public static final String COMMENT_VIEW_ROWS = "VIEW_ITERCOMMENT_SELECT_ROWS";
	//查询评论数量视图
	public static final String COMMENT_COUNT_VIEW = "VIEW_ITERCOMMENTCOUNT_SELECT";
	public static final String COMMENT_COUNT_VIEW_ROWS = "VIEW_ITERCOMMENTCOUNT_SELECT_ROWS";
	//订单视图
	public static final String ORDER_VIEW = "VIEW_ITERORDER_SELECT";
	public static final String ORDER_VIEW_ROWS = "VIEW_ITERORDER_SELECT_ROWS";
	//订单-评论视图
	public static final String ORDER_ASSESS_VIEW = "VIEW_ITERORDERASSESS_SELECT";
	public static final String ORDER_ASSESS_VIEW_ROWS = "VIEW_ITERORDERASSESS_SELECT_ROWS";
	//商品-评论视图
	public static final String COMMODITY_ASSESS_VIEW = "VIEW_ITERCOMMODITYASSESS_SELECT";
	public static final String COMMODITY_ASSESS_VIEW_ROWS = "VIEW_ITERCOMMODITYASSESS_SELECT_ROWS";
	//经销商视图
	public static final String DISTRIBUTOR_VIEW = "VIEW_ITERDISTRIBUTOR_SELECT";
	public static final String DISTRIBUTOR_VIEW_ROWS = "VIEW_ITERDISTRIBUTOR_SELECT_ROWS";
	//充电桩视图
	public static final String CHARGINGPOLE_VIEW = "VIEW_ITERCHARGINGPILE_SELECT";
	public static final String CHARGINGPOLE_VIEW_ROWS = "VIEW_ITERCHARGINGPILE_SELECT_ROWS";
	//发现视图
	public static final String DISCOVER_VIEW = "VIEW_ITERDISCOVER_SELECT";
	public static final String DISCOVER_VIEW_ROWS = "VIEW_ITERDISCOVER_SELECT_ROWS";
	//过滤词方案视图
	public static final String FW_SCHEME_VIEW = "VIEW_ITERFWSCHEME_SELECT";
	public static final String FW_SCHEME_VIEW_ROWS = "VIEW_ITERFWSCHEME_SELECT_ROWS";
	//退货视图
	public static final String RETURN_GOODS_VIEW = "VIEW_ITERRETURNGOODS_SELECT";
	public static final String RETURN_GOODS_VIEW_ROWS = "VIEW_ITERRETURNGOODS_SELECT_ROWS";
	//购物车视图
	public static final String CART_VIEW = "VIEW_ITERCART_SELECT";
	public static final String CART_VIEW_ROWS = "VIEW_ITERCART_SELECT_ROWS";
	//试乘试驾视图
	public static final String TESTDRIVE_VIEW = "VIEW_ITERTESTDRIVE_SELECT";
	public static final String TESTDRIVE_VIEW_ROWS = "VIEW_ITERTESTDRIVE_SELECT_ROWS";
	//库存SKU商品视图
	public static final String STOCK_COMMODITY_B_VIEW = "VIEW_ITERSTOCKCOMMODITYB_SELECT";
	public static final String STOCK_COMMODITY_B_VIEW_ROWS = "VIEW_ITERSTOCKCOMMODITYB_SELECT_ROWS";
	//库存SKU商品价格视图
	public static final String STOCK_COMMODITY_P_VIEW = "VIEW_ITERSTOCKCOMMODITYP_SELECT";
	public static final String STOCK_COMMODITY_P_VIEW_ROWS = "VIEW_ITERSTOCKCOMMODITYP_SELECT_ROWS";

	//表  业务ID定义
	public static final String t_errorcode = "errorcode_id";
	public static final String t_province = "province_id";
	public static final String t_city = "city_id";
	public static final String t_area = "area_id";
	public static final String t_resource = "resource_id";
	public static final String t_role = "role_id";
	public static final String t_role_right = "rel_id";
	public static final String t_user_role = "rel_id";
	public static final String t_user_group = "user_group_id";
	public static final String t_user_group_rel = "rel_id";
	public static final String t_user_group_role = "rel_id";
	public static final String t_user = "user_id";
	public static final String t_address = "addr_id";
	public static final String t_goods_assess = "goods_assess_id";
	public static final String t_footmark = "footmark_id";
	public static final String t_factory = "factory_id";
	public static final String t_factory_pic = "factory_pic_id";
	public static final String t_factory_user = "factory_user_id";
	public static final String t_factory_approval = "approval_id";
	public static final String t_factory_shop = "rel_id";
	public static final String t_shop_online_info = "shop_id";
	public static final String t_shop_pay_type = "spt_id";
	public static final String t_shop_pay_method = "spm_id";
	public static final String t_shop_delivery_method = "delivery_method_id";
	public static final String t_shop_entity_info = "shop_id";
	public static final String t_shop_commodity = "rel_id";

	public static final String t_factory_commodity = "factory_commodity_id";

	public static final String t_commodity = "commodity_id";
	public static final String t_commodity_append = "commodity_info_id";
	public static final String t_commodity_attr = "commodity_attr_id";
	public static final String t_commodity_bom = "commodity_bom_id";
	public static final String t_commodity_bom_details = "commodity_bom_details_id";
	public static final String t_commodity_support = "commodity_support_id";
	public static final String t_commodity_pay_type = "cpt_id";
	public static final String t_commodity_pay_method = "spm_id";
	public static final String t_commodity_delivery_method = "delivery_method_id";
	public static final String t_stock_commodity = "stock_commodity_id";
	public static final String t_stock_commodity_price = "stock_commodity_price_id";
	public static final String t_stock_commodity_bom = "stock_commodity_bom_id";
	public static final String t_stock_commodity_area_price = "area_price_id";
	public static final String t_bom = "bom_id";
	public static final String t_bom_detail = "bom_detail_id";
	public static final String t_distributor = "distributor_id";
	public static final String t_designer = "designer_id";
	public static final String t_designer_approval = "approval_id";
	public static final String t_works = "works_id";
	public static final String t_works_sign = "sign_id";
	public static final String t_operate_user = "operate_user_id";
	public static final String t_brand = "brand_id";
	public static final String t_goods_kind = "kind_id";
	public static final String t_goods_kind_attr = "attr_id";
	public static final String t_goods_kind_attr_detail = "attr_detail_id";
	public static final String t_ad = "ad_id";
	public static final String t_ad_position = "ad_pos_id";
	public static final String t_info = "info_id";

	public static final String t_info_comment = "info_comment_id";

	public static final String t_comment = "comment_id";
	public static final String t_clicklikes = "clicklike_id";

	public static final String t_topic_comment = "topic_comment_id";

	public static final String t_topic = "topic_id";
	public static final String t_activity = "activity_id";
	public static final String t_activity_user = "rel_id";
	public static final String t_fw_scheme = "fw_scheme_id";
	public static final String t_fw_scheme_details = "details_id";

	public static final String t_module_fw_scheme = "module_fw_scheme_id";

	public static final String t_filter_words = "fw_id";
	public static final String t_notice = "notice_id";
	public static final String t_trade = "trade_id";
	public static final String t_consult = "consult_id";
	public static final String t_discover = "discover_id";
	public static final String t_coupon = "coupon_id";
	public static final String t_coupon_commodity = "coupon_commodity_id";
	public static final String t_user_coupon = "user_coupon_id";
	public static final String t_testdrive_apply = "apply_id";
	public static final String t_order = "order_id";
	public static final String t_order_commodity_bom = "commodity_bom_id";
	public static final String t_order_detail = "order_detail_id";
	public static final String t_order_coupon = "rel_id";
	public static final String t_order_prd_track = "prd_track_id";
	public static final String t_orderstatus_transfer = "transfer_id";
	public static final String t_order_payment = "payment_id";
	public static final String t_payorder = "payorder_id";
	public static final String t_order_logistics = "logistics_id";
	public static final String t_logistics_company = "lc_id";
	public static final String t_pickup = "pickup_id";
	public static final String t_chargingpile_apply = "apply_id";
	public static final String t_chargingpile_apply_check = "check_id";
	public static final String t_cart_commodity = "cart_commodity_id";
	public static final String t_cart_commodity_bom = "cart_commodity_bom_id";
	public static final String t_return_goods = "return_goods_id";
	public static final String t_returnstatus_transfer = "transfer_id";
	public static final String t_return_logistics = "logistics_id";

}
