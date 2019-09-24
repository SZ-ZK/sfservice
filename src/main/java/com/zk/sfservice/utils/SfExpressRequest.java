package com.zk.sfservice.utils;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Request")
public class SfExpressRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	// 响应结果
	@XmlElement(name = "Body")
	private Body Body;

	@XmlAccessorType(XmlAccessType.NONE)
	public static class Body {
		
		@XmlElement(name = "WaybillRoute")
		private WaybillRoute WaybillRoute;

		public WaybillRoute getWaybillRoute() {
			return WaybillRoute;
		}

		public void setWaybillRoute(WaybillRoute waybillRoute) {
			WaybillRoute = waybillRoute;
		}
	}
	
	@XmlRootElement(name = "WaybillRoute")
	@XmlAccessorType(XmlAccessType.NONE)
	public static class WaybillRoute {
		
		// 路由节点信息编号,每一个id代表一条不同的路由节点信息。
		@XmlAttribute(name = "id")
		private String id;
		
		//顺丰运单号
		@XmlAttribute(name = "mailno")
		private String mailno;
		
		//客户订单号
		@XmlAttribute(name = "orderid")
		private String orderid;
		
		//路由节点产生的时间
		@XmlAttribute(name = "acceptTime")
		private String acceptTime;
		
		//路由节点发生的城市
		@XmlAttribute(name = "acceptAddress")
		private String acceptAddress;
		
		//路由节点具体描述
		@XmlAttribute(name = "remark")
		private String remark;
		
		//路由节点操作码
		@XmlAttribute(name = "opCode")
		private String 	opCode;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getMailno() {
			return mailno;
		}

		public void setMailno(String mailno) {
			this.mailno = mailno;
		}

		public String getOrderid() {
			return orderid;
		}

		public void setOrderid(String orderid) {
			this.orderid = orderid;
		}

		public String getAcceptTime() {
			return acceptTime;
		}

		public void setAcceptTime(String acceptTime) {
			this.acceptTime = acceptTime;
		}

		public String getAcceptAddress() {
			return acceptAddress;
		}

		public void setAcceptAddress(String acceptAddress) {
			this.acceptAddress = acceptAddress;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getOpCode() {
			return opCode;
		}

		public void setOpCode(String opCode) {
			this.opCode = opCode;
		}
	}

	public SfExpressRequest.Body getBody() {
		return Body;
	}

	public void setBody(SfExpressRequest.Body body) {
		Body = body;
	}
}
