package com.zk.sfservice.model;

/**
 * @purpose:查询时效
 * @author: zk
 * @date: 2019年8月16日
 */
public class DeliverTime {

	//快件产品：可以为空，为空时查询顺丰支持的所有业务类型。
	private String business_type;

	//货物总重量
	private String weight;
	
	//货物的总长，宽，高，以半角逗号分隔，单位CM，精确到小数点后2位，包含子母件。
	private String volumn;
	
	//寄件时间
	private String consigned_time;
	
	//1：表示查询含价格的接口 0：表示查询不含价格的接口
	private String search_price;
	
	//原寄地所在省份
	private String province_j;
	
	//原寄地所在县/区
	private String city_j;
	
	//原寄地详细地址
	private String address_j;
	
	//原寄地区域代码
	private String code_j;
	
	// 目的地所在省份
	private String province_d;
	
	//目的地所在城市
	private String city_d;
	
	//目的地所在县/区
	private String district_d;
	
	//目的地详细地址
	private String address_d;
	
	//目的地区域代码
	private String code_d;

	public String getBusiness_type() {
		return business_type;
	}

	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getVolumn() {
		return volumn;
	}

	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}

	public String getConsigned_time() {
		return consigned_time;
	}

	public void setConsigned_time(String consigned_time) {
		this.consigned_time = consigned_time;
	}

	public String getSearch_price() {
		return search_price;
	}

	public void setSearch_price(String search_price) {
		this.search_price = search_price;
	}

	public String getProvince_j() {
		return province_j;
	}

	public void setProvince_j(String province_j) {
		this.province_j = province_j;
	}

	public String getCity_j() {
		return city_j;
	}

	public void setCity_j(String city_j) {
		this.city_j = city_j;
	}

	public String getAddress_j() {
		return address_j;
	}

	public void setAddress_j(String address_j) {
		this.address_j = address_j;
	}

	public String getCode_j() {
		return code_j;
	}

	public void setCode_j(String code_j) {
		this.code_j = code_j;
	}

	public String getProvince_d() {
		return province_d;
	}

	public void setProvince_d(String province_d) {
		this.province_d = province_d;
	}

	public String getCity_d() {
		return city_d;
	}

	public void setCity_d(String city_d) {
		this.city_d = city_d;
	}

	public String getDistrict_d() {
		return district_d;
	}

	public void setDistrict_d(String district_d) {
		this.district_d = district_d;
	}

	public String getAddress_d() {
		return address_d;
	}

	public void setAddress_d(String address_d) {
		this.address_d = address_d;
	}

	public String getCode_d() {
		return code_d;
	}

	public void setCode_d(String code_d) {
		this.code_d = code_d;
	}
}
