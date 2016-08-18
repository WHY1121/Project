package com.why.weather;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.18
 * 2016-06-15T13:58:42.028+08:00
 * Generated source version: 2.7.18
 * 
 */
@WebService(targetNamespace = "http://weather.why.com", name = "weathwrInterface")
@XmlSeeAlso({ObjectFactory.class})
public interface WeathwrInterface {

    @WebResult(name = "result", targetNamespace = "")
    @RequestWrapper(localName = "queryWeather", targetNamespace = "http://weather.why.com", className = "com.why.weather.QueryWeather")
    @WebMethod
    @ResponseWrapper(localName = "queryWeatherResponse", targetNamespace = "http://weather.why.com", className = "com.why.weather.QueryWeatherResponse")
    public java.util.List<com.why.weather.WeatherModel> queryWeather(
        @WebParam(name = "cityName", targetNamespace = "")
        java.lang.String cityName
    );
}
