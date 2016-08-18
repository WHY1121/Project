package com.why.server;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

import com.why.pojo.WeatherModel;
@WebService(
	targetNamespace="http://weather.why.com",
	name="weathwrInterface",
	portName="WeatherInterfacePort",
	serviceName="WeatherService"
		
		)
@BindingType(javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public interface WeatherInterface {
	
	public @WebResult(name="result")List<WeatherModel> queryWeather(@WebParam(name="cityName")String cityName);
}
