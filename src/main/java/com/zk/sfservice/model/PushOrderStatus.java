package com.zk.sfservice.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Request")
public class PushOrderStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	//客户订单号
	@XmlElement(name = "orderNo")
	private String orderNo;
	
	//顺丰运单号
	@XmlElement(name = "waybillNo")
	private String waybillNo;
	
	//订单状态
	@XmlElement(name = "orderStateCode")
	private String orderStateCode;
	
	//订单状态描述
	@XmlElement(name = "orderStateDesc")
	private String orderStateDesc;
	
	//收件员工工号
	@XmlElement(name = "empCode")
	private String empCode;
	
	//收件员手机号
	@XmlElement(name = "empPhone")
	private String empPhone;
	
	//网点
	@XmlElement(name = "netCode")
	private String netCode;
	
	//最晚上门时间
	@XmlElement(name = "lastTime")
	private Date lastTime;
	
	//客户预约时间
	@XmlElement(name = "bookTime")
	private Date bookTime;
	
	//承运商代码(SF)
	@XmlElement(name = "carrierCode")
	private String carrierCode;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public String getOrderStateCode() {
		return orderStateCode;
	}

	public void setOrderStateCode(String orderStateCode) {
		this.orderStateCode = orderStateCode;
	}

	public String getOrderStateDesc() {
		return orderStateDesc;
	}

	public void setOrderStateDesc(String orderStateDesc) {
		this.orderStateDesc = orderStateDesc;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getNetCode() {
		return netCode;
	}

	public void setNetCode(String netCode) {
		this.netCode = netCode;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Date getBookTime() {
		return bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
