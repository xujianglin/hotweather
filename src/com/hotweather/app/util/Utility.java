package com.hotweather.app.util;

import android.text.TextUtils;

import com.hotweather.app.db.CoolWeatherDB;
import com.hotweather.app.model.City;
import com.hotweather.app.model.County;
import com.hotweather.app.model.Province;

public class Utility {
/*
 * �����ʹ�����������ص�ʡ������
 */
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response){
		if(!TextUtils.isEmpty(response)){
			String[] allProvinces=response.split(",");
			if(allProvinces!=null&&allProvinces.length>0){
				for(String p:allProvinces){
					String [] array=p.split("\\|");
					Province province=new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					//���������������ݴ洢��Province��
					coolWeatherDB.savaProvince(province);
				}
				return true;
			}
		}
		return false;}
	/*
	 * �����ʹ�����������������м�����
	 */
	public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId){
		if(!TextUtils.isEmpty(response)){
			String[] allCities=response.split(",");
			if(allCities!=null&&allCities.length>0){}
			for(String c:allCities){
				String[] array=c.split("\\|");
				City city=new City();
				city.setCityCode(array[0]);
				city.setCityName(array[1]);
				city.setProvinceId(provinceId);
				//���������������ݴ洢��City��
			}
			return true;
		}
		return false;}
	/*
	 * �����ʹ�����������������ؼ�����
	 */
	public static boolean handleCouniesResponse(CoolWeatherDB coolWeatherDB,String response,int cityId){
		if(!TextUtils.isEmpty(response)){
			String[]allCounties=response.split(",");
			if(allCounties!=null&&allCounties.length>0){
				for(String c:allCounties){
					String[]array=c.split(",");
					County county=new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					//���������������ݴ洢��County��
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;}
}
