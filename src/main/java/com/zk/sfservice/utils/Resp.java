package com.zk.sfservice.utils;

import java.io.Serializable;

public class Resp<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int SUCCESS = 200;

	public static final int FAIL = 400;

	public static final int ERROR = 500;

	public static <T> Resp<T> response(int code, String msg, T data) {
		Resp<T> resp = new Resp<T>();
		resp.setCode(code);
		resp.setMsg(msg);
		resp.setData(data);
		return resp;
	}

	private int code;

	private String msg;

	private T data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
