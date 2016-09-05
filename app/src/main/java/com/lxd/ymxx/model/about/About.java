package com.lxd.ymxx.model.about;

import com.google.gson.annotations.Expose;

public class About {

	@Expose
	private String code;
	@Expose
	private String msg;
	@Expose
	private Data data;

	/**
	 * 
	 * @return The code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 *            The code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 
	 * @return The msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 
	 * @param msg
	 *            The msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 
	 * @return The data
	 */
	public Data getData() {
		return data;
	}

	/**
	 * 
	 * @param data
	 *            The data
	 */
	public void setData(Data data) {
		this.data = data;
	}

}
