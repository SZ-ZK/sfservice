package com.zk.sfservice.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.zk.sfservice.model.DeliverTime;
import com.zk.sfservice.model.OrderStatus;
import com.zk.sfservice.model.PushOrderStatus;
import com.zk.sfservice.utils.ConvertToJavaBean;
import com.zk.sfservice.utils.FileStringUtil;
import com.zk.sfservice.utils.Md5AndBase64Util;
import com.zk.sfservice.utils.Resp;
import com.zk.sfservice.utils.SfExpressRequest;
import com.zk.sfservice.utils.SfExpressResponse;
import io.swagger.annotations.ApiOperation;

/**
 * @purpose:调用丰桥API接口
 * @author: zk
 * @date: 2019年9月24日
 */
@Controller
@RequestMapping("/sf")
public class CallServiceController {

	private static final Logger logger = LoggerFactory.getLogger(CallServiceController.class);

	// 调用丰桥平台API接口路径
	private static final String reqURL = "https://bsp-oisp.sf-express.com/bsp-oisp/sfexpressService";
	// 此处替换为您在丰桥平台获取的顾客编码
	private static final String clientCode = "";
	// 此处替换为您在丰桥平台获取的校验码
	private static final String checkword = "";
	// 此处替换为您在丰桥平台获取的月结卡
	private static final String monthCard = "";

	SimpleDateFormat sdfMilH = new SimpleDateFormat("yyMMdd");

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@SuppressWarnings("static-access")
	@ResponseBody
	@PostMapping("/call")
	@ApiOperation("调用顺丰API接口:下单，订单路由，订单取消")
	public Resp<Object> call(OrderStatus orderStatusVO, HttpServletRequest request, HttpServletResponse response)
			throws HttpException, IOException {

		logger.info("进入顺丰接口:params={},type={}", JSONObject.toJSONString(orderStatusVO), orderStatusVO.getType());

		String myReqXML = "";
		String methodName = "";
		if ("1".equals(orderStatusVO.getType())) {
			// 1.获取下单接口报文
			methodName = "orderSerivce";
			myReqXML = getOrderServiceRequestXml(orderStatusVO);
		} else if ("2".equals(orderStatusVO.getType())) {
			// 1.获取订单路由接口报文
			methodName = "routeService";
			myReqXML = getRouteServiceRequestXml(orderStatusVO);
		} else if ("3".equals(orderStatusVO.getType())) {
			// 1.获取订单取消接口报文
			methodName = "cancelService";
			myReqXML = getCancelServiceRequestXml(orderStatusVO);
		} else {
			logger.warn("调用接口类型传错");
			return Resp.response(Resp.FAIL, "调用接口类型传错", null);
		}

		logger.info("请求报文：" + myReqXML);
		// 2.xml+checkword
		String verifyCode = myReqXML + checkword;
		// 3.MD5加密 + Base64编码
		verifyCode = Md5AndBase64Util.md5EncryptAndBase64(verifyCode);
		// 4.post 请求
		Map<String, Object> toHttpParams = new HashMap<>();
		toHttpParams.put("xml", myReqXML);
		toHttpParams.put("verifyCode", verifyCode);
		logger.info("请求顺丰接口" + methodName, toHttpParams.toString());
		HttpClient httpclient = new HttpClient();
		PostMethod post = new PostMethod(reqURL.toString());
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		post.setParameter("xml", myReqXML);
		post.setParameter("verifyCode", verifyCode);
		int httpCode = httpclient.executeMethod(post);
		String respXml = new String(post.getResponseBody(), "UTF-8");
		logger.info("返回报文：" + httpCode + "," + respXml);
		// 5.XML转JavaBean
		SfExpressResponse result = (SfExpressResponse) ConvertToJavaBean.convertToJavaBean(respXml,
				SfExpressResponse.class);
		// 6.执行业务逻辑操作
		
		return Resp.response(Resp.SUCCESS, "成功", result);
	}

	@ResponseBody
	@PostMapping("/callback")
	@ApiOperation("路由推送接口")
	public void Callback(String content, HttpServletRequest request, HttpServletResponse response) {

		logger.info("路由推送接口请求参数 callback  content:{}", content);

		String respXml = "";
		try {
			// 1. URL解码
			respXml = URLDecoder.decode(content, "UTF-8");
			// 2. Xml转javabean
			SfExpressRequest result = (SfExpressRequest) ConvertToJavaBean.convertToJavaBean(respXml,
					SfExpressRequest.class);
			if (result == null) {
				String reqXml = requestXml("callExpressRequest/9.responseERR.txt");
				responseXml(reqXml, response);
			}
			// 3. 执行业务逻辑操作
			String remark = result.getBody().getWaybillRoute().getOpCode();
			logger.info("=========remark=========:" + remark);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			String reqXml = requestXml("callExpressRequest/9.responseERR.txt");
			logger.info("业务逻辑错误");
			responseXml(reqXml, response);
		}
		String reqXml = requestXml("callExpressRequest/8.responseOK.txt");
		logger.info("路由推送接口响应数据：" + reqXml);
		//顺丰丰桥调用路由推送接口，返回响应报文给对方
		responseXml(reqXml, response);
	}

	@ResponseBody
	@PostMapping("/pushOrderState")
	@ApiOperation("订单状态推送接口")
	public void pushOrderState(String content, HttpServletRequest request, HttpServletResponse response) {

		logger.info("顺丰订单状态推送接口响应参数 pushOrderState  content:{}", content);

		String respXml = "";
		try {
			// 1. URL解码
			respXml = URLDecoder.decode(content, "UTF-8");
			// 2. Xml转javabean
			PushOrderStatus result = (PushOrderStatus) ConvertToJavaBean.convertToJavaBean(respXml,
					PushOrderStatus.class);
			if (result == null) {
				String reqXml = requestXml("callExpressRequest/11.responseERR.txt");
				responseXml(reqXml, response);
			}
			// 3. 执行业务逻辑操作
			logger.info("=========result=========:" + result.getOrderStateDesc());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			String reqXml = requestXml("callExpressRequest/11.responseERR.txt");
			responseXml(reqXml, response);
		}
		String reqXml = requestXml("callExpressRequest/10.responseOK.txt");
		//顺丰丰桥调用订单状态推送接口，返回响应报文给对方
		responseXml(reqXml, response);
	}

	@SuppressWarnings("static-access")
	@ResponseBody
	@PostMapping("/deliverTm")
	@ApiOperation("时效查询接口")
	public Resp<Object> deliverTm(DeliverTime deliverTimeVO, HttpServletRequest request, HttpServletResponse response)
			throws HttpException, IOException {

		logger.info("进入时效查询接口:params={},type={}", JSONObject.toJSONString(deliverTimeVO), deliverTimeVO);

		String myReqXML = "";
		String methodName = "";
		// 1.获取时效查询接口报文
		methodName = "DeliverTmService";
		myReqXML = getDeliverTmServiceRequestXml(deliverTimeVO);

		logger.info("请求报文：" + myReqXML);
		// 2.xml+checkword
		String verifyCode = myReqXML + checkword;
		// 3.MD5加密 + Base64编码
		verifyCode = Md5AndBase64Util.md5EncryptAndBase64(verifyCode);
		// 4.post 请求
		Map<String, Object> toHttpParams = new HashMap<>();
		toHttpParams.put("xml", myReqXML);
		toHttpParams.put("verifyCode", verifyCode);
		logger.info("请求顺丰接口" + methodName, toHttpParams.toString());
		HttpClient httpclient = new HttpClient();
		PostMethod post = new PostMethod(reqURL.toString());
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		post.setParameter("xml", myReqXML);
		post.setParameter("verifyCode", verifyCode);
		int httpCode = httpclient.executeMethod(post);
		String respXml = new String(post.getResponseBody(), "UTF-8");
		logger.info("返回报文：" + httpCode + "," + respXml);
		// 5.XML转JavaBean
		SfExpressResponse result = (SfExpressResponse) ConvertToJavaBean.convertToJavaBean(respXml,
				SfExpressResponse.class);
		// 6.执行业务逻辑操作
		return Resp.response(Resp.SUCCESS, "成功", result);
	}
	
	@SuppressWarnings("unused")
	private String getOrderServiceRequestXml(OrderStatus orderStatusVO) {

		String reqXml = requestXml("callExpressRequest/1.order.txt");

		reqXml = reqXml.replace("SLKJ2019", clientCode); // 此处替换为顾客编码
		reqXml = reqXml.replace("TB1207300000001", orderStatusVO.getSendorder()); // 此处替换为订单号
		reqXml = reqXml.replace("ZHANGSAN", orderStatusVO.getSendName()); // 此处替换为寄件人姓名
		reqXml = reqXml.replace("15012345678", orderStatusVO.getSendMobile()); // 此处替换为寄件人电话
		reqXml = reqXml.replace("广东省深圳市罗湖区深港大厦35楼0805室", orderStatusVO.getSendDetailAddress()); // 此处替换寄件人详细地址
		reqXml = reqXml.replace("LISI", orderStatusVO.getReceiveName()); // 此处替换为收件人姓名
		reqXml = reqXml.replace("33992159", orderStatusVO.getReceiveMobile()); // 此处替换为收件人电话
		reqXml = reqXml.replace("广东省广州市海珠区宝芝林大厦701室", orderStatusVO.getReceiveDetailAdress()); // 此处替换收件人详细地址
		reqXml = reqXml.replace("支付方式", orderStatusVO.getSendtype()); // 此处替换付款方式:1-寄方付，2-收方付，3-第三方付
		reqXml = reqXml.replace("月结卡号", monthCard); // 此处替换月结卡
		reqXml = reqXml.replace("上门取件时间", sdf.format(orderStatusVO.getCometime()));// 此处替换为上门取件时间

		try {
			reqXml = new String(reqXml.getBytes(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		return reqXml;
	}

	@SuppressWarnings("unused")
	private String getRouteServiceRequestXml(OrderStatus orderStatusVO) {

		String reqXml = requestXml("callExpressRequest/5.route_queryByOrderNo.txt");

		reqXml = reqXml.replace("SLKJ2019", clientCode); // 此处替换顾客编码
		reqXml = reqXml.replace("QIAO-20180104001", orderStatusVO.getHzorder());// 此处替换运单号

		return reqXml;
	}

	@SuppressWarnings("unused")
	private String getCancelServiceRequestXml(OrderStatus orderStatusVO) {

		String reqXml = requestXml("callExpressRequest/3.order_confirm.txt");

		// 1.配置参数
		reqXml = reqXml.replace("SLKJ2019", clientCode); // 此处替换顾客编码
		reqXml = reqXml.replace("QIAO-20180104001", orderStatusVO.getSendorder()); // 此处替换订单号

		return reqXml;
	}

	@SuppressWarnings("unused")
	private String getDeliverTmServiceRequestXml(DeliverTime deliverTimeVO) {

		String reqXml = requestXml("callExpressRequest/4.deliverTimeRequest.txt");

		reqXml = reqXml.replace("BSPdevelop", clientCode); // 此处替换为顾客编码
		reqXml = reqXml.replace("寄件时间", deliverTimeVO.getConsigned_time());//寄件时间
		reqXml = reqXml.replace("新洲十一街万基商务大厦", deliverTimeVO.getAddress_j()); //原寄地详细地址
		reqXml = reqXml.replace("广州白云山", deliverTimeVO.getAddress_d()); //目的地详细地址

		try {
			reqXml = new String(reqXml.getBytes(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		return reqXml;
	}
	
	@SuppressWarnings("unused")
	private void responseXml(String returnResponse, HttpServletResponse response) {

		logger.info("顺丰接口响应操作");
		try {
			response.setContentType("text/xml; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(returnResponse);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private String requestXml(String str) {

		String reqXml = "";
		try {
			InputStream in = CallServiceController.class.getClassLoader().getResourceAsStream(str);
			reqXml = FileStringUtil.readFileByIn(in).toString();
		} catch (Exception e) {
			logger.info("执行模板异常");
			e.printStackTrace();
		}
		return reqXml;
	}

}
