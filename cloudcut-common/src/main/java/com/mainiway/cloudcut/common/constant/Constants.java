package com.mainiway.cloudcut.common.constant;

public class Constants {

	//json参数格式错误
	public static final int JSONFORMATERROR = 1000;
	public static final String JSONFORMATERRORDES = "json参数格式错误,请检查!";
	//参数值含敏感信息
	public static final int SENSITIVEVALUE = 1100;
	public static final String SENSITIVEVALUEDES = "参数值含敏感信息";
	//请求成功
	public static final int SUCCESS = 2000;
	public static final String SUCCESSDES = "请求成功";
	//登录校验失败或用户未登录
	public static final int LOGINVERIFY = 2010;
	public static final String LOGINVERIFYDES = "登录失败或用户未登录";
	//该用户已经注册
	public static final int USERREGISTERED = 2020;
	public static final String USERREGISTEREDDES = "该用户已注册";
	//该用户不存在
	public static final int USERNOTREGISTERED = 2030;
	public static final String USERNOTREGISTEREDDES = "该用户未注册";
	//验证码错误或者失效
	public static final int VERCODEERROR = 2040;
	public static final String VERCODEERRORDES = "验证码错误或者失效";
	//用户名或密码错误
	public static final int PWDORCODEERROR = 2050;
	public static final String PWDORCODEERRORDES = "用户名或密码错误";
	//用户被锁定
	public static final int USERLOCKS = 2060;
	public static final String USERLOCKSRORDES = "用户被锁定";
	//用户待审核
	public static final int NOTAPPROVAL = 2070;
	public static final String NOTAPPROVALDES = "用户待审核";
	//用户审核未通过
	public static final int  APPROVALNOTPASSED = 2080;
	public static final String APPROVALNOTPASSEDDES = "用户审核未通过";

	//用户无该访问权限（经销商）
	public static final int  ACCESSFORBIDDEN = 2090;
	public static final String ACCESSFORBIDDENDES = "用户无该访问权限";

	//用户新密码不能为空
	public static final int  PWDNOTBLANK = 2100;
	public static final String PWDNOTBLANKDES = "用户新密码不能为空";

	//用户新旧密码相同
	public static final int  PWDSAME = 2110;
	public static final String PWDSAMEDES = "用户新密码与原密码相同";

	//订单已被删除
	public static final int ORDERMISSING = 3000;
	public static final String ORDERMISSINGDES ="订单已被删除";

	//订单状态校验失败
	public static final int ORDERSTATUSERROR = 3010;
	public static final String ORDERSTATUSERRORDES ="校验订单状态失败，无法执行当前操作";
	//支付失败
	public static final int PAYFAILURE = 3020;
	public static final String PAYFAILUREDES ="支付失败，请重新支付";
	//未完成生产，不可以发货
	public static final int PRDSTATUSERROR = 3030;
	public static final String PRDSTATUSERRORDES ="商品未入库，不可以发货";
	//支付类型错误，只能选择用户已确认的支付类型
	public static final int PAYTYPERROR = 3040;
	public static final String PAYTYPERRORDES ="支付类型错误,请选择正确的支付类型";
	//此订单已选择了在线支付，不能门店支付
	public static final int PAYMODENOTSTORE = 3050;
	public static final String PAYMODENOTSTOREDES ="此订单已选择了在线支付，不能门店支付";
	//此订单未付预付款
	public static final int IMPRESTNOTPAY = 3060;
	public static final String IMPRESTNOTPAYDES ="此订单预付款未完成支付";
	//此订单未付尾款
	public static final int FINALNOTPAY = 3070;
	public static final String FINALNOTPAYDES ="此订单尾款未完成支付";
	//此订单付款已经完成
	public static final int PAYSUCCESS = 3080;
	public static final String PAYSUCCESSDES ="此订单付款已经完成";
	//取货码错误或者失效
	public static final int PICKUPCODEERROR = 3090;
	public static final String PICKUPCODEERRORDES ="取货码错误或者失效";
	//校验退货状态失败，无法执行当前操作
	public static final int RETURNSTATUSERROR = 3100;
	public static final String RETURNSTATUSERRORDES ="校验退货状态失败，无法执行当前操作";
	//退货方式选择错误
	public static final int RETURNTYPEERROR = 3110;
	public static final String RETURNTYPEERRORDES ="退货方式选择错误";
	//所选的BOM库存不足
	public static final int STOCKERROR = 3120;
	public static final String STOCKERRORDES ="所选的BOM库存不足";
	//订单提交超时
	public static final int ORDERTIMEOUT = 3130;
	public static final String ORDERTIMEOUTDES ="订单提交超时或已提交";

	//所选商品来自不同店铺，请选择同一店铺下商品
	public static final int ORDERNOTSUPPORT = 3140;
	public static final String ORDERNOTSUPPORTDES ="所选商品来自不同店铺，请选择同一店铺下商品";

	//付款金额与实际不符
	public static final int MONEYWARNING = 3150;
	public static final String MONEYWARNINGDES ="付款金额与实际不符";

	//此订单已付尾款
	public static final int FINALPAID = 3160;
	public static final String FINALPAIDDES ="此订单尾款已完成支付";

	//新增操作失败
	public static final int DBOPERATEFAILURE = 4000;
	public static final String DBOPERATEFAILUREDES = "数据库操作失败";
	//新增操作失败
	public static final int ADDFAILURE = 4010;
	public static final String ADDFAILUREDES = "新增操作失败";
	//更新操作失败
	public static final int UPDATEFAILURE = 4020;
	public static final String UPDATEFAILUREDES = "更新操作失败";
	//删除操作失败
	public static final int DELFAILURE = 4030;
	public static final String DELFAILUREDES = "删除操作失败";
	//"查询数据为空
	public static final int RESULTNULL = 4040;
	public static final String RESULTNULLDES = "查询数据为空";
	//请求参数错误
	public static final int PARAMERROR = 4050;
	public static final String PARAMERRORDES = "请求参数错误";
	//请求参数param不可为空
	public static final int PARAMNOTNULL = 4060;
	public static final String PARAMNOTNULLDES = "请求参数param不可为空";
	//分页参数错误
	public static final int PAGEPARAMFALSE = 4070;
	public static final String PAGEPARAMFALSEDES = "分页参数错误";
	//收货地址大于默认值
	public static final int ADDRESSFAILURE = 4080;
	public static final String ADDRESSREDES = "收货地址大于10条";
    //有关联子表的信息无法删除
	public static final int CANNOTDEL = 4090;
	public static final String CANNOTDELDES = "存在关联信息无法删除";
	//达到卡券数量领取上限
	public static final int COUPONAMOUNTLIMIT = 4100;
	public static final String COUPONAMOUNTLIMITDES =	"达到卡券数量领取上限";
	//卡券可领取的数量为0
	public static final int COUPONPUBAMOUNTNOTHAVE = 4110;
	public static final String COUPONPUBAMOUNTNOTHAVEDES =	"该卡券可领取的数量为0";
	//返回系统异常 一般为bug，是捕获到的异常处理
	public static final int EXCEPTIONSYSTEM = 5000;
	public static final String EXCEPTIONSYSTEMDES = "接口执行异常";
	//session会话丢失
	public static final int EXCEPTIONLOSESESSION = 5100;
	public static final String EXCEPTIONLOSESESSIONDES = "session会话丢失";

	//无匹配短信模板
	public static final int NOMESSAGETEMPLATE = 6000;
	public static final String NOMESSAGETEMPLATEDES = "模板不存在";

	//短信发送失败
	public static final int MESSAGEFAILURE = 6010;
	public static final String MESSAGEFAILUREDES = "短信发送失败";

	//邮件发送失败
	public static final int MAILFAILURE = 6020;
	public static final String MAILFAILUREDES = "邮件发送失败";
	//密码强度
	public static final int PWDWEAK = 6030;
	public static final String PWDWEAKDES = "弱等强度";
	public static final int PWDIN = 6040;
	public static final String PWDINDES = "中等强度";
	public static final int PWDSTRONG = 6050;
	public static final String PWDSTRONGDES = "高等强强度";

	//自定义属性值
	public static final String CUSTOM="custom"; //>
	//平台公共属性值
	public static final String COMMON="common"; //>
	//个人用户
	public static final  String PERSONAL = "personal";
	//工厂用户
	public static final String FACTORY ="factory";
	//运营用户
	public static final String OPERATE ="operate";
	//经销商用户
	public static final String DISTRIBUTOR ="distributor";
	//通用日期格式
	public static final String DTAEFORMAT ="yyyy-MM-dd HH:mm:ss";
	//日期格式 只获取到天
	public static final String DTAEFORMAT_DD ="yyyy-MM-dd";
	//sessionUser
	public static final String SESSION_USER = "sessionUser";
	public static final String COOKIE_NAME = "cookieUser";// 用户Cookie
	public static final String CODE_REPEAT_USER = "邮箱手机重复注册";

	public static final String INSERT="insert"; //>
	public static final String SELECT="select"; //>
	public static final String UPDATE="update"; //>
	public static final String DELETE="delete"; //>
	public static final String MORE="more"; //>
	public static final String LESS="less"; //<
	public static final String EQUAL ="equal"; //=
	public static final String MOREEQUAL ="moreEqual"; //>=
	public static final String LESSEQUAL ="lessEqual"; //<=
	public static final String INCLUDE ="include"; //包含

}
