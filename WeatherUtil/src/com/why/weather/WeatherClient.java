package com.why.weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class WeatherClient {

	
	
	public static void main(String[] args) {
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean=new JaxWsProxyFactoryBean();
		jaxWsProxyFactoryBean.setAddress("http://127.0.0.1:12345/weather?wsdl");
		jaxWsProxyFactoryBean.setServiceClass(WeathwrInterface.class);
		WeathwrInterface weathwrInterface = (WeathwrInterface) jaxWsProxyFactoryBean.create();
	
	    List<WeatherModel> list=weathwrInterface.queryWeather("±±¾©");
	    for(WeatherModel weatherModel:list){
	    	System.out.println(weatherModel.getDetail());
	    	Date date=weatherModel.getData().toGregorianCalendar().getTime();
	    	System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(date));
	    	System.out.println(weatherModel.getTemperatureMax());
	    	System.out.println(weatherModel.getTemperatureMin());
	    }
	    
	}
}
