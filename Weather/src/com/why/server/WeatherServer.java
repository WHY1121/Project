package com.why.server;


import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

/**
 * 
 * <p>Title: WeatherServer</p>
 * <p>Description: 鍙戝竷澶╂皵鏈嶅姟</p>
 * <p>Company: www.itcast.com</p> 
 * @author	浼犳櫤.鐕曢潚
 * @date	2014-12-31涓婂崍9:10:47
 * @version 1.0
 */
public class WeatherServer {
	
	public static void main(String[] args) {
		
		//浣跨敤jaxWs鍙戝竷soap鍗忚鐨剋ebservice
		JaxWsServerFactoryBean jaxWsServerFactoryBean = new JaxWsServerFactoryBean();
		//鎸囧畾 webservice鍦板潃
		jaxWsServerFactoryBean.setAddress("http://127.0.0.1:12345/weather");
		//鎸囧畾 porttype
		jaxWsServerFactoryBean.setServiceClass(WeatherInterface.class);
		
		//鎸囧畾鏈嶅姟绫诲璞�
		jaxWsServerFactoryBean.setServiceBean(new WeatherInterfaceImpl());
		
		//鍙戝竷鏈嶅姟
		jaxWsServerFactoryBean.create();
		
	}

}
