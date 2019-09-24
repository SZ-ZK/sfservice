package com.zk.sfservice.utils;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Response")
public class SfExpressResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	// 响应状态
	@XmlElement(name = "Head")
	private String Head;
	// 响应失败原因
	@XmlElement(name = "ERROR")
	private ERROR ERROR;
	// 响应结果
	@XmlElement(name = "Body")
	private Body Body;

	@XmlAccessorType(XmlAccessType.NONE)
	public static class ERROR {
		@XmlAttribute(name = "code")
		private String code;
		@XmlValue
		private String text;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
	}

	@XmlAccessorType(XmlAccessType.NONE)
	public static class Body {
		@XmlElement(name = "OrderResponse")
		private OrderResponse OrderResponse;

		@XmlElement(name = "RouteResponse")
		private RouteResponse RouteResponse;
		
		@XmlElement(name = "OrderConfirmResponse")
		private OrderConfirmResponse OrderConfirmResponse;
		
		@XmlElement(name = "DeliverTmResponse")
		private DeliverTmResponse DeliverTmResponse;

		public SfExpressResponse.OrderResponse getOrderResponse() {
			return OrderResponse;
		}

		public void setOrderResponse(SfExpressResponse.OrderResponse orderResponse) {
			OrderResponse = orderResponse;
		}

		public SfExpressResponse.RouteResponse getRouteResponse() {
			return RouteResponse;
		}

		public void setRouteResponse(SfExpressResponse.RouteResponse routeResponse) {
			RouteResponse = routeResponse;
		}

		public OrderConfirmResponse getOrderConfirmResponse() {
			return OrderConfirmResponse;
		}

		public void setOrderConfirmResponse(OrderConfirmResponse orderConfirmResponse) {
			OrderConfirmResponse = orderConfirmResponse;
		}

		public DeliverTmResponse getDeliverTmResponse() {
			return DeliverTmResponse;
		}

		public void setDeliverTmResponse(DeliverTmResponse deliverTmResponse) {
			DeliverTmResponse = deliverTmResponse;
		}
	}

	@XmlRootElement(name = "OrderResponse")
	@XmlAccessorType(XmlAccessType.NONE)
	public static class OrderResponse {
		// 订单号
		@XmlAttribute(name = "orderid")
		private String orderId;
		// 运单号
		@XmlAttribute(name = "mailno")
		private String mailNo;
		// 原寄地区域代码(可用于顺丰电子运单标签打印)
		@XmlAttribute(name = "origincode")
		private String originCode;
		// 目的地区域代码(可用于顺丰电子运单标签打印)
		@XmlAttribute(name = "destcode")
		private String destCode;
		// 筛单结果：1：人工确认 2：可收派 3：不可以收派
		@XmlAttribute(name = "filter_result")
		private String filterResult;

		public String getOrderId() {
			return orderId;
		}

		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}

		public String getMailNo() {
			return mailNo;
		}

		public void setMailNo(String mailNo) {
			this.mailNo = mailNo;
		}

		public String getOriginCode() {
			return originCode;
		}

		public void setOriginCode(String originCode) {
			this.originCode = originCode;
		}

		public String getDestCode() {
			return destCode;
		}

		public void setDestCode(String destCode) {
			this.destCode = destCode;
		}

		public String getFilterResult() {
			return filterResult;
		}

		public void setFilterResult(String filterResult) {
			this.filterResult = filterResult;
		}
	}

	@XmlRootElement(name = "RouteResponse")
	@XmlAccessorType(XmlAccessType.NONE)
	public static class RouteResponse {
		// 运单号
		@XmlAttribute(name = "mailno")
		private String mailNo;
		// 路由
		@XmlElement(name = "Route")
		private List<Route> Route;

		public String getMailNo() {
			return mailNo;
		}

		public void setMailNo(String mailNo) {
			this.mailNo = mailNo;
		}

		public List<SfExpressResponse.Route> getRoute() {
			return Route;
		}

		public void setRoute(List<SfExpressResponse.Route> route) {
			Route = route;
		}
	}

	@XmlRootElement(name = "Route")
	@XmlAccessorType(XmlAccessType.NONE)
	public static class Route {
		// 路由节点发生的时间
		@XmlAttribute(name = "accept_time")
		private String acceptTime;

		// 路由节点具体描述
		@XmlAttribute(name = "remark")
		private String remark;

		// 路由节点操作码
		@XmlAttribute(name = "opcode")
		private String opcode;
		
		// 路由节点发生的地点
		@XmlAttribute(name = "accept_address")
		private String acceptAddress;

		public String getAcceptTime() {
			return acceptTime;
		}

		public void setAcceptTime(String acceptTime) {
			this.acceptTime = acceptTime;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getOpcode() {
			return opcode;
		}

		public void setOpcode(String opcode) {
			this.opcode = opcode;
		}

		public String getAcceptAddress() {
			return acceptAddress;
		}

		public void setAcceptAddress(String acceptAddress) {
			this.acceptAddress = acceptAddress;
		}		
	}
	
	@XmlRootElement(name = "OrderConfirmResponse")
	@XmlAccessorType(XmlAccessType.NONE)
	public static class OrderConfirmResponse {
		// 客户订单号
		@XmlAttribute(name = "orderid")
		private String orderid;
		
		// 备注 1-客户订单号与顺丰运单不匹配 2-操作成功
		@XmlAttribute(name = "res_status")
		private String resStatus;

		public String getOrderid() {
			return orderid;
		}

		public void setOrderid(String orderid) {
			this.orderid = orderid;
		}

		public String getResStatus() {
			return resStatus;
		}

		public void setResStatus(String resStatus) {
			this.resStatus = resStatus;
		}
	}
	
	@XmlRootElement(name = "DeliverTmResponse")
	@XmlAccessorType(XmlAccessType.NONE)
	public static class DeliverTmResponse {
		// 运单号
		@XmlElement(name = "DeliverTm")
		private List<DeliverTm> DeliverTm;

		public List<DeliverTm> getDeliverTm() {
			return DeliverTm;
		}

		public void setDeliverTm(List<DeliverTm> deliverTm) {
			DeliverTm = deliverTm;
		}
	}

	@XmlRootElement(name = "DeliverTm")
	@XmlAccessorType(XmlAccessType.NONE)
	public static class DeliverTm {
		// 业务类型描述
		@XmlAttribute(name = "business_type_desc")
		private String businessTypeDesc;
		
		// 预计派送时间或预计派送时间段
		@XmlAttribute(name = "deliver_time")
		private String deliverTime;
		
		// 代表所支持的快件产品
		@XmlAttribute(name = "business_type")
		private String businessType;
		
		// 费用
		@XmlAttribute(name = "price")
		private String price;

		public String getBusinessTypeDesc() {
			return businessTypeDesc;
		}

		public void setBusinessTypeDesc(String businessTypeDesc) {
			this.businessTypeDesc = businessTypeDesc;
		}

		public String getDeliverTime() {
			return deliverTime;
		}

		public void setDeliverTime(String deliverTime) {
			this.deliverTime = deliverTime;
		}

		public String getBusinessType() {
			return businessType;
		}

		public void setBusinessType(String businessType) {
			this.businessType = businessType;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}		
	}

	public String getHead() {
		return Head;
	}

	public void setHead(String head) {
		Head = head;
	}

	public SfExpressResponse.ERROR getERROR() {
		return ERROR;
	}

	public void setERROR(SfExpressResponse.ERROR ERROR) {
		this.ERROR = ERROR;
	}

	public SfExpressResponse.Body getBody() {
		return Body;
	}

	public void setBody(SfExpressResponse.Body body) {
		Body = body;
	}
}
