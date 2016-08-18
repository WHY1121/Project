package com.why.dom;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.Iterator;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 读取xml
 * @author fsdfsdsss
 *
 */
public class Dom4j2 {
      
  public static void main(String[] args) {
	  try{
    	  SAXReader reader=new SAXReader();
    	  Document document = reader.read(new File("src/students.xml"));
    	  
    	  
    	  //获得根元素
    	  Element rootElement = document.getRootElement();
    	  Element studentEle = rootElement.addElement("student").addAttribute("number", "001");
    	  studentEle.addElement("name").addText("王惠宇");	  
    	  studentEle.addElement("age").addText("20");	  
    	  studentEle.addElement("sex").addText("male");
    	  OutputFormat format=OutputFormat.createPrettyPrint();
    	  XMLWriter writer=new XMLWriter(new FileWriter("src/students_copy.xml"),format); 
    	  writer.write(document);
    	  writer.close();
			
		
      }catch(Exception e){
    	  e.printStackTrace();
      }
   
  
  }
    
      
      
       
}
