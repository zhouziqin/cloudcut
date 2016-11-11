package com.mainiway.cloudcut.common.businesses.country.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mainiway.cloudcut.beans.jsonbean.PageBean;
import com.mainiway.cloudcut.common.beans.JsonEnvEntity;
import com.mainiway.cloudcut.common.beans.RetObject;
import com.mainiway.cloudcut.common.businesses.base.service.BaseService;
import com.mainiway.cloudcut.common.businesses.country.service.CountryManageService;
import com.mainiway.cloudcut.common.constant.Constants;
import com.mainiway.cloudcut.db.model.entity.AreaEntity;
import com.mainiway.cloudcut.db.model.entity.CityEntity;
import com.mainiway.cloudcut.db.model.entity.ProvinceEntity;
import com.mainiway.cloudcut.db.model.ui.CityUI;
import com.mainiway.cloudcut.db.model.ui.ProvinceUI;
import com.mainiway.cloudcut.util.DBConstants;
import com.mainiway.cloudcut.util.DbOperaCode;
import com.mainiway.cloudcut.util.Jackson;


/**
 * ***************************************************************************
 *模块名 : CountryManageServiceImpl
 *文件名 : CountryManageServiceImpl.java
 *创建时间 : 2016年5月31日
 *实现功能 :
 *作者 : Administrator
 *版本 : v0.0.1
-----------------------------------------------------------------------------
 *修改记录:
 *日 期 版本 修改人 修改内容
 *2016年5月31日 v0.0.1 Administrator 创建
 ****************************************************************************
 */
@Service("countryManageService")
public class CountryManageServiceImpl extends BaseService implements CountryManageService {
	private Map<String, String> provinceMap = new HashMap<String, String>();//省份
	private Map<String, String> cityMap = new HashMap<String, String>();//城市
	private Map<String, String> districtMap = new HashMap<String, String>();//区域
	/**
	 * ====================================================================
	 *函 数 名： @param request
	 *函 数 名： @param jsonEnv
	 *函 数 名： @return
	 *函 数 名： @throws Exception
	 *功 能：     查询省
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年5月31日 v0.0.1 Administrator 创建
	====================================================================
	 */
	@Override
	public String queryProvince(HttpServletRequest request, JsonEnvEntity jsonEnv) throws Exception {
		//http://localhost:8804/idinbaopersonal/countryManage/queryProvince
		RetObject ro = new RetObject();
		String jsonStr = "";
		try {
			// 获取默认的where条件
			StringBuffer where = new StringBuffer();
			// 获取默认的page条件
			PageBean page = new PageBean(DBConstants.PAGESTART, DBConstants.PAGEEND);
			// 检查param参数
			if (StringUtils.isNotBlank(jsonEnv.getParam())) {
				// 解析param参数
				Map<String, Object> paramMap = Jackson.json2map(jsonEnv.getParam());
				// 拼接where条件PROVINCE_ID
				String PROVINCE_ID = (String) paramMap.get("PROVINCE_ID");
				if (StringUtils.isNotBlank(PROVINCE_ID)) {
					where.append("PROVINCE_ID = '" + PROVINCE_ID + "' ");
				}
				// 解析并封装page条件
				page =setPageParam(ro, paramMap, page, DbOperaCode.PROVINCE+ DbOperaCode.SELECT_ROWS, where.toString());
			}
			// 基础查询方法
			List<ProvinceEntity> brands =baseSelectObject(where.toString(), page, ProvinceEntity.class, DbOperaCode.PROVINCE
									+ DbOperaCode.SELECT, "queryProvince");
			if (brands.size() > 0) {
				ro.setReCode(Constants.SUCCESS);
				ro.setReDescr(Constants.SUCCESSDES);
				ro.setObj(brands);
			} else {
				ro.setReCode(Constants.RESULTNULL);
				ro.setReDescr(Constants.RESULTNULLDES);
			}
		} catch (Exception e) {
			baseHandleException(e, ro, "CountryManageServiceImpl", "queryProvince()");
		}
		return baseHandleJsonStr(jsonStr, ro, request);
	}

	/**
	 * ====================================================================
	 *函 数 名： @param request
	 *函 数 名： @param jsonEnv
	 *函 数 名： @return
	 *函 数 名： @throws Exception
	 *功 能： 查询市
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年5月31日 v0.0.1 Administrator 创建
	====================================================================
	 */
	@Override
	public String queryCity(HttpServletRequest request, JsonEnvEntity jsonEnv) throws Exception {
		//{"FATHER_ID":"1500"}
		RetObject ro = new RetObject();
		String jsonStr = "";
		try {
			// 获取默认的where条件
			StringBuffer where = new StringBuffer(DBConstants.SELECTALL);
			// 获取默认的page条件
			PageBean page = new PageBean(DBConstants.PAGESTART, DBConstants.PAGEEND);
			// 检查param参数
			if (StringUtils.isNotBlank(jsonEnv.getParam())) {
				// 解析param参数
				Map<String, Object> paramMap = Jackson.json2map(jsonEnv.getParam());
				// 拼接where条件
				String FATHER_ID = (String) paramMap.get("FATHER_ID");
				String CITY_ID = (String) paramMap.get("CITY_ID");
				if (StringUtils.isNotBlank(FATHER_ID)) {
					where.append(" and FATHER_ID = '" + FATHER_ID +"'");
				}
				if (StringUtils.isNotBlank(CITY_ID)) {
					where.append(" and CITY_ID = '" + CITY_ID +"'");
				}
				// 解析并封装page条件
				page =setPageParam(ro, paramMap, page, DbOperaCode.CITY + DbOperaCode.SELECT_ROWS,where.toString());
			}
			// 基础查询方法
			List<CityEntity> citys =
					baseSelectObject(where.toString(), page, CityEntity.class, DbOperaCode.CITY
							+ DbOperaCode.SELECT, "queryCity");
			if (citys.size() > 0) {
				ro.setReCode(Constants.SUCCESS);
				ro.setReDescr(Constants.SUCCESSDES);
				ro.setObj(citys);
			} else {
				ro.setReCode(Constants.RESULTNULL);
				ro.setReDescr(Constants.RESULTNULLDES);
			}
		} catch (Exception e) {
			baseHandleException(e, ro, "CountryManageServiceImpl", "queryCity()");
		}
		return baseHandleJsonStr(jsonStr, ro, request);
	}

	/**
	 * ====================================================================
	 *函 数 名： @param request
	 *函 数 名： @param jsonEnv
	 *函 数 名： @return
	 *函 数 名： @throws Exception
	 *功 能：      查询区
	----------------------------------------------------------------------
	 *修改记录 ：
	 *日 期  版本 修改人 修改内容
	 *2016年5月31日 v0.0.1 Administrator 创建
	====================================================================
	 */
	@Override
	public String queryArea(HttpServletRequest request, JsonEnvEntity jsonEnv) throws Exception {
		// http://localhost:8804/idinbaopersonal/countryManage/queryProvince?param={}
		RetObject ro = new RetObject();
		String jsonStr = "";
		try {
			// 获取默认的where条件
			StringBuffer where = new StringBuffer(DBConstants.SELECTALL);
			// 获取默认的page条件
			PageBean page = new PageBean(DBConstants.PAGESTART, DBConstants.PAGEEND);
			// 检查param参数
			if (StringUtils.isNotBlank(jsonEnv.getParam())) {
				// 解析param参数
				Map<String, Object> paramMap = Jackson.json2map(jsonEnv.getParam());
				// 拼接where条件
				String AREA_ID = (String) paramMap.get("AREA_ID");
				String CITY_ID = (String) paramMap.get("CITY_ID");
				if (StringUtils.isNotBlank(AREA_ID)) {
					where.append(" and AREA_ID = '" + AREA_ID + "'");
				}
				if (StringUtils.isNotBlank(CITY_ID)) {
					where.append(" and FATHER_ID = '" + CITY_ID + "'");
				}
				// 解析并封装page条件
				page =setPageParam(ro, paramMap, page, DbOperaCode.AREA + DbOperaCode.SELECT_ROWS,where.toString());
			}
			// 基础查询方法
			List<AreaEntity> areas =
					baseSelectObject(where.toString(), page, AreaEntity.class, DbOperaCode.AREA
							+ DbOperaCode.SELECT, "queryArea");
			if (areas.size() > 0) {
				ro.setReCode(Constants.SUCCESS);
				ro.setReDescr(Constants.SUCCESSDES);
				ro.setObj(areas);
			} else {
				ro.setReCode(Constants.RESULTNULL);
				ro.setReDescr(Constants.RESULTNULLDES);
			}
		} catch (Exception e) {
			baseHandleException(e, ro, "CountryManageServiceImpl", "queryArea()");
		}
		return baseHandleJsonStr(jsonStr, ro, request);
	}
	//根据ID返回省名称
    public  String ProvinceName(String provinceID)throws Exception {
    	if (provinceMap.containsKey(provinceID)) {
			return provinceMap.get(provinceID);
		}
    	// 获取默认的where条件
    	StringBuffer where = new StringBuffer();
    	if (StringUtils.isNotBlank(provinceID)) {
			where.append("PROVINCE_ID = '" + provinceID + "' ");
		}
    	// 基础查询方法
    	List<ProvinceEntity> provinces =baseSelectObject(where.toString(), null, ProvinceEntity.class, DbOperaCode.PROVINCE
    										+ DbOperaCode.SELECT, "ProvinceName");
    	String provinceName ="";
    	if(provinces.size()>0){
    		provinceName = provinces.get(0).getPROVINCE();
    	}
    	if (provinceName != null && !"".equals(provinceName)) {
    		provinceMap.put(provinceID, provinceName);
		}
    	return provinceName;
    }
    //根据ID返回市名称
    public String CityName(String cityID)throws Exception {
    	if (cityMap.containsKey(cityID)) {
			return cityMap.get(cityID);
		}
    	// 获取默认的where条件
    	StringBuffer where = new StringBuffer();
    	if (StringUtils.isNotBlank(cityID)) {
			where.append("CITY_ID = '" + cityID +"'");
		}
    	// 基础查询方法
    	List<CityEntity> citys =baseSelectObject(where.toString(), null, CityEntity.class, DbOperaCode.CITY
    								+ DbOperaCode.SELECT, "CityName");
    	String cityName = "";
    	if(citys.size()>0){
    		 cityName = citys.get(0).getCITY();
    	}
    	if (cityName != null && !"".equals(cityName)) {
    		cityMap.put(cityID, cityName);
		}
    	return cityName;
    }
    //根据ID返回区名称
    public String AreaName(String areaID)throws Exception {
    	if (districtMap.containsKey(areaID)) {
			return districtMap.get(areaID);
		}
    	// 获取默认的where条件
    	StringBuffer where = new StringBuffer();
    	if (StringUtils.isNotBlank(areaID)) {
    		where.append("AREA_ID = '" + areaID + "'");
		}
    	// 基础查询方法
    	List<AreaEntity> areas =baseSelectObject(where.toString(), null, AreaEntity.class, DbOperaCode.AREA
    								+ DbOperaCode.SELECT, "AreaName");
    	String areaName = "";
    	if(areas.size()>0){
    		areaName= areas.get(0).getAREA();
    	}
    	if (areaName != null && !"".equals(areaName)) {
			districtMap.put(areaID, areaName);
		}
    	return areaName;
    }
    //查询所有省市区的信息
	@Override
	public String queryAll(HttpServletRequest request, JsonEnvEntity jsonEnv) throws Exception {
		RetObject ro = new RetObject();
		String jsonStr = "";
		try {
			// 获取默认的where条件
			StringBuffer where = new StringBuffer(DBConstants.SELECTALL);
			// 获取默认的page条件
			PageBean page = new PageBean(DBConstants.PAGESTART, DBConstants.PAGEEND);
			// 基础查询方法

			List<ProvinceEntity> provincelist =baseSelectObject(where.toString(), page, ProvinceEntity.class, DbOperaCode.PROVINCE
												+ DbOperaCode.SELECT, "queryAll");
			List<CityEntity> citylist =baseSelectObject(where.toString(), page, CityEntity.class, DbOperaCode.CITY
									+ DbOperaCode.SELECT, "queryAll");
			List<AreaEntity> arealist =baseSelectObject(where.toString(), page, AreaEntity.class, DbOperaCode.AREA
											+ DbOperaCode.SELECT, "queryAll");

			List<ProvinceUI> provinceUI = new ArrayList<ProvinceUI>();

			if (provincelist.size() > 0&&citylist.size() > 0&&arealist.size() > 0) {
				for (Iterator<ProvinceEntity> iterator = provincelist.iterator(); iterator.hasNext();) {
					ProvinceEntity provinces = iterator.next();
					ProvinceUI province = new ProvinceUI();
					province.setPROVINCE(provinces.getPROVINCE());
					province.setPROVINCE_ID(provinces.getPROVINCE_ID());

					List<CityUI> cityUI = new ArrayList<CityUI>(); //

					for (Iterator<CityEntity> iterator2 = citylist.iterator(); iterator2.hasNext();) {
						CityEntity citys = iterator2.next();
						if(citys.getFATHER_ID().equals(provinces.getPROVINCE_ID())){
							CityUI city = new CityUI();
							city.setCITY(citys.getCITY());
							city.setCITY_ID(citys.getCITY_ID());

							List<AreaEntity> areaUI = new ArrayList<AreaEntity>();

							for (Iterator<AreaEntity> iterator3 = arealist.iterator(); iterator3.hasNext();) {
								AreaEntity areaEntity = iterator3.next();
								if(areaEntity.getFATHER_ID().equals(citys.getCITY_ID())){
									areaUI.add(areaEntity);
									iterator3.remove();
								}
							}
							city.setAreaList(areaUI);
							cityUI.add(city);
							iterator2.remove();
						}
					}
					province.setCityList(cityUI);
					provinceUI.add(province);
				}
			/*for (int i = 0; i < provinces.size(); i++) {
				ProvinceUI province = new ProvinceUI();
				province.setPROVINCE(provinces.get(i).getPROVINCE());
				province.setPROVINCE_ID(provinces.get(i).getPROVINCE_ID());
				for (int j = 0; j < citys.size(); j++) {
					if(citys.get(j).getFATHER_ID().equals(provinces.get(i).getPROVINCE_ID())){
						CityUI city = new CityUI();
						city.setCITY(citys.get(j).getCITY());
						city.setCITY_ID(citys.get(j).getCITY_ID());
						for (int k = 0; k < areas.size(); k++) {
							if(areas.get(k).getFATHER_ID().equals(citys.get(j).getCITY_ID())){
								areaUI.add(areas.get(k));
							}
						}
						city.setAreaList(areaUI);
						cityUI.add(city);
					}
				}
				province.setCityList(cityUI);
				provinceUI.add(province);
			}*/
			ro.setReCode(Constants.SUCCESS);
			ro.setReDescr(Constants.SUCCESSDES);
			ro.setObj(provinceUI);
			}else{
				ro.setReCode(Constants.RESULTNULL);
				ro.setReDescr(Constants.RESULTNULLDES);
			}
		} catch (Exception e) {
			baseHandleException(e, ro, "CountryManageServiceImpl", "queryAll()");
		}
		return baseHandleJsonStr(jsonStr, ro, request);
	}

}
