package com.why.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.why.pojo.WeatherModel;

public class WeatherInterfaceImpl implements WeatherInterface{

	@Override
	public List<WeatherModel> queryWeather(String cityName) {

		
		
		List<WeatherModel> list=new ArrayList<WeatherModel>();
		Calendar calendar=Calendar.getInstance();
		int day=calendar.get(Calendar.DATE);
		
		WeatherModel weatherModel_1=new WeatherModel();
		weatherModel_1.setDetail("«ÁÃÏ");
		weatherModel_1.setData(new Date());
		weatherModel_1.setTemperature_max(5);
		weatherModel_1.setTemperature_min(-2);
		WeatherModel weatherModel_2=new WeatherModel();
		weatherModel_2.setDetail("“ıÃÏ");
		weatherModel_2.setData(new Date());
		weatherModel_2.setTemperature_max(10);
		weatherModel_2.setTemperature_min(2);
		list.add(weatherModel_1);
		list.add(weatherModel_2);
		
		return list;
	}

}
