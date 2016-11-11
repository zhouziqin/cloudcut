package com.mainiway.cloudcut.common.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mainiway.cloudcut.common.constant.Constants;

/**
 * 存储返回结果
 * 旗下所有子模块所需要的返回结构体都必须继承该类
 * 			返回数据结构可以做特别的扩展
 */
@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
		getterVisibility = JsonAutoDetect.Visibility.NONE)
public class RetObject implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public int reCode;// 返回结果代码，标识错误编号
	public String reDescr;// 返回结果描述
	public String jsonRes;// 返回json结果
	public String xmlRes;// 返回xml结果
	public String total;//总页数
	public Object obj;// 返回结果数据存储

	/**
	 * 默认是5000 EXCEPTION_SYSTEM错误 描述异常
	 */
	public RetObject() {
		this.reDescr = "异常";
	}

	public RetObject(int reCode) {
		this.reCode = reCode;
	}

	public RetObject(int reCode, String reDescr) {
		this.reCode = reCode;
		this.reDescr = reDescr;
	}

	public RetObject(int reCode, String reDescr, Object obj) {
		this.reCode = reCode;
		this.reDescr = reDescr;
		this.obj = obj;
	}

	public int getReCode() {
		return reCode;
	}

	public void setReCode(int reCode) {
		this.reCode = reCode;
	}

	public String getReDescr() {
		return reDescr;
	}

	public void setReDescr(String reDescr) {
		this.reDescr = reDescr;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getJsonRes() {
		return jsonRes;
	}

	public void setJsonRes(String jsonRes) {
		this.jsonRes = jsonRes;
	}

	public String getXmlRes() {
		return xmlRes;
	}

	public void setXmlRes(String xmlRes) {
		this.xmlRes = xmlRes;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setDescr()
	{
		switch ( reCode )
		{
			case Constants.SUCCESS:
				this.reDescr = Constants.SUCCESSDES;
				break;

			case Constants.LOGINVERIFY:
				this.reDescr = Constants.LOGINVERIFYDES;
				break;

			case Constants.USERREGISTERED:
				this.reDescr = Constants.USERREGISTEREDDES;
				break;

			case Constants.USERNOTREGISTERED:
				this.reDescr = Constants.USERNOTREGISTEREDDES;
				break;

			case Constants.VERCODEERROR:
				this.reDescr = Constants.VERCODEERRORDES;
				break;

			case Constants.PWDORCODEERROR:
				this.reDescr = Constants.PWDORCODEERRORDES;
				break;

			case Constants.ADDFAILURE:
				this.reDescr = Constants.ADDFAILUREDES;
				break;

			case Constants.UPDATEFAILURE:
				this.reDescr = Constants.UPDATEFAILUREDES;
				break;

			case Constants.DELFAILURE:
				this.reDescr = Constants.DELFAILUREDES;
				break;

			case Constants.RESULTNULL:
				this.reDescr = Constants.RESULTNULLDES;
				break;

			case Constants.PARAMERROR:
				this.reDescr = Constants.PARAMERRORDES;
				break;

			case Constants.PARAMNOTNULL:
				this.reDescr = Constants.PARAMNOTNULLDES;
				break;

			case Constants.PAGEPARAMFALSE:
				this.reDescr = Constants.PAGEPARAMFALSEDES;
				break;

			case Constants.ADDRESSFAILURE:
				this.reDescr = Constants.ADDRESSREDES;
				break;

			case Constants.CANNOTDEL:
				this.reDescr = Constants.CANNOTDELDES;
				break;

			case Constants.COUPONAMOUNTLIMIT:
				this.reDescr = Constants.COUPONAMOUNTLIMITDES;
				break;

			case Constants.COUPONPUBAMOUNTNOTHAVE:
				this.reDescr = Constants.COUPONPUBAMOUNTNOTHAVEDES;
				break;

			case Constants.EXCEPTIONSYSTEM:
				this.reDescr = Constants.EXCEPTIONSYSTEMDES;
				break;

			case Constants.EXCEPTIONLOSESESSION:
				this.reDescr = Constants.EXCEPTIONLOSESESSIONDES;
				break;

			default:
				this.reDescr = "未知错误";
				break;
		}
	}
}
