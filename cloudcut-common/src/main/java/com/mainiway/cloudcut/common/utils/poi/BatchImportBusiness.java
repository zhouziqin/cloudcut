package com.mainiway.cloudcut.common.utils.poi;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public interface BatchImportBusiness {
	
	/**批量导入
	 * @param inputStream
	 * @param adminId
	 * @return
	 * @throws BusinessException
	 */
	public List<String> batchImport(InputStream inputStream, String adminId,String  bigRoleId,String batchNo,Date fileDate) throws Exception;
}
