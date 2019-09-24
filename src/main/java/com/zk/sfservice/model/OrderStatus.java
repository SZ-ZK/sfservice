package com.zk.sfservice.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @purpose: 订单状态
 * @author: zk
 * @date: 2019年8月4日
 */
public class OrderStatus {
	
	private String sendorder;//订单号
	
	private String hzorder;//运单号
	
	private String sendName;//寄件人姓名
	
	private String sendMobile;//寄件人电话
	
	private String sendAddress;//寄件人地址
	
	private String sendDetailAddress;//寄件人详情地址
	
	private String sendCom;//寄件人归属部门
	
	private String receiveName;//收件人姓名
	
	private String receiveMobile;//收件人电话
	
	private String receiveAdress;//收件人地址
	
	private String receiveDetailAdress;//收件人详情地址
	
	private String sendtype;//寄件类型
	
	private String producttype;//产品类型
	
	private String expressid;//快递公司id
	
	private String relationtype;//1.寄件人 2.收件人
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date cometime;//选择上门时间
	
	private String remark;//备注
	
	private String carriage;//运费
	
	private String type;//类型 1-下单接口，2-路由查询接口，3-取消订单接口

	public String getSendorder() {
		return sendorder;
	}

	public void setSendorder(String sendorder) {
		this.sendorder = sendorder;
	}

	public String getHzorder() {
		return hzorder;
	}

	public void setHzorder(String hzorder) {
		this.hzorder = hzorder;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getSendMobile() {
		return sendMobile;
	}

	public void setSendMobile(String sendMobile) {
		this.sendMobile = sendMobile;
	}

	public String getSendAddress() {
		return sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	public String getSendDetailAddress() {
		return sendDetailAddress;
	}

	public void setSendDetailAddress(String sendDetailAddress) {
		this.sendDetailAddress = sendDetailAddress;
	}

	public String getSendCom() {
		return sendCom;
	}

	public void setSendCom(String sendCom) {
		this.sendCom = sendCom;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getReceiveMobile() {
		return receiveMobile;
	}

	public void setReceiveMobile(String receiveMobile) {
		this.receiveMobile = receiveMobile;
	}

	public String getReceiveAdress() {
		return receiveAdress;
	}

	public void setReceiveAdress(String receiveAdress) {
		this.receiveAdress = receiveAdress;
	}

	public String getReceiveDetailAdress() {
		return receiveDetailAdress;
	}

	public void setReceiveDetailAdress(String receiveDetailAdress) {
		this.receiveDetailAdress = receiveDetailAdress;
	}

	public String getSendtype() {
		return sendtype;
	}

	public void setSendtype(String sendtype) {
		this.sendtype = sendtype;
	}

	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}

	public String getExpressid() {
		return expressid;
	}

	public void setExpressid(String expressid) {
		this.expressid = expressid;
	}

	public String getRelationtype() {
		return relationtype;
	}

	public void setRelationtype(String relationtype) {
		this.relationtype = relationtype;
	}

	public Date getCometime() {
		return cometime;
	}

	public void setCometime(Date cometime) {
		this.cometime = cometime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCarriage() {
		return carriage;
	}

	public void setCarriage(String carriage) {
		this.carriage = carriage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}