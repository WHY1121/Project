
package com.why.weather;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>weatherModel complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="weatherModel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="data" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="detail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="temperature_max" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="temperature_min" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "weatherModel", propOrder = {
    "data",
    "detail",
    "temperatureMax",
    "temperatureMin"
})
public class WeatherModel {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar data;
    protected String detail;
    @XmlElement(name = "temperature_max")
    protected int temperatureMax;
    @XmlElement(name = "temperature_min")
    protected int temperatureMin;

    /**
     * 获取data属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getData() {
        return data;
    }

    /**
     * 设置data属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setData(XMLGregorianCalendar value) {
        this.data = value;
    }

    /**
     * 获取detail属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置detail属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetail(String value) {
        this.detail = value;
    }

    /**
     * 获取temperatureMax属性的值。
     * 
     */
    public int getTemperatureMax() {
        return temperatureMax;
    }

    /**
     * 设置temperatureMax属性的值。
     * 
     */
    public void setTemperatureMax(int value) {
        this.temperatureMax = value;
    }

    /**
     * 获取temperatureMin属性的值。
     * 
     */
    public int getTemperatureMin() {
        return temperatureMin;
    }

    /**
     * 设置temperatureMin属性的值。
     * 
     */
    public void setTemperatureMin(int value) {
        this.temperatureMin = value;
    }

}
