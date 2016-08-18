package com.why.dom;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * ��ȡxml
 * @author fsdfsdsss
 *
 */
public class Dom4j {
      
  public static void main(String[] args) {
	  try{
    	  SAXReader reader=new SAXReader();
    	  Document document = reader.read(new File("src/students.xml"));
    	  
    	  
    	  //��ø�Ԫ��
    	  Element rootElement = document.getRootElement();
    	  for(Iterator<Element> iterator=rootElement.elementIterator("student");iterator.hasNext();){
    		  Element student=iterator.next();
    		  
    		  String number=student.attributeValue("number");
    		  
    		  
    		//ȡ��Ԫ��
    		  String name = student.elementText("name");
    		  String age = student.elementText("age");
    		  String sex = student.elementText("sex");
    		  System.out.println("��ǰѧ�����ϢΪ��"+name+" "+age+" "+sex);
    		  
    		  
    	  }
      }catch(Exception e){
    	  e.printStackTrace();
      }
	  System.out.print("Xmx=");
	  System.out.println(Runtime.getRuntime().maxMemory()/1024.0/1024+"M");

	  System.out.print("free mem=");
	  System.out.println(Runtime.getRuntime().freeMemory()/1024.0/1024+"M");

	  System.out.print("total mem=");
	  System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024+"M");
  
  }
    
      
      
       
}
