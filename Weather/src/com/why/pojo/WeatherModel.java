package com.why.pojo;

import java.util.Date;

/**
 * 
 * <p>Title: WeatherModel</p>
 * <p>Description:天气信息模型</p>
 * <p>Company: www.itcast.com</p> 
 * @author	传智.燕青
 * @date	2014-12-31上午9:03:59
 * @version 1.0
 */
public class WeatherModel {
	
	//天气概况
	private String detail;
	
	//日期
	private Date data;
	
	//�?高温�?
	private int temperature_max;
	
	//�?低温�?
	private int temperature_min;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getTemperature_max() {
		return temperature_max;
	}

	public void setTemperature_max(int temperature_max) {
		this.temperature_max = temperature_max;
	}

	public int getTemperature_min() {
		return temperature_min;
	}

	public void setTemperature_min(int temperature_min) {
		this.temperature_min = temperature_min;
	}
	
	

}
