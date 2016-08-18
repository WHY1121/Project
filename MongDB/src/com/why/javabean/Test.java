package com.why.javabean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;


public class Test {

	public static void main(String[] args) {
		User user=new User();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
		    
			//��ȡuser������Ϣ
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		    //����������Ϣ
			for(PropertyDescriptor pd:propertyDescriptors){
				//�жϵ�ǰ�������Ƿ���name����
				 Method write = pd.getWriteMethod();
				if(pd.getName().equals("name")){
				  
				   write.invoke(user, "tom");
				}else if(pd.getName().equals("password")){
					write.invoke(user, "1234");
				}
			}
		     System.out.println(user.toString());
		     
		     
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
