package com.mainiway.cloudcut.common.utils.poi;

/**
 *	
 */
public interface ErrorCodeEnumConstants {

	public String getCode();

	public String getDesc();

	/**
	 * 
	 */
	public enum SystemCode implements ErrorCodeEnumConstants {
		SUCCESS("00000", "成功")
		//..............
		;


		private String code;
		private String desc;

		private SystemCode(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public String getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}
	}
	
	
	/**
	 * 
	 */
	public enum BatchImportCode implements ErrorCodeEnumConstants {
		OVER_MAX_ROWS_LIMIT("09001","超过最大行数限制"),
		MISS_TITLE_NAME("09002","缺少标题名"),
		UPLOAD_DATA_IS_EMPTY("0903","上传数据为空");
		

		private String code;
		private String desc;

		private BatchImportCode(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}

		public String getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}
	}
	
}
