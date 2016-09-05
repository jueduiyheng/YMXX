package com.lxd.ymxx.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Company {

	@Expose
	private String code;
	@Expose
	private String msg;
	@Expose
	private List<CompanyDatum> data = new ArrayList<CompanyDatum>();

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
	public List<CompanyDatum> getData() {
		return data;
	}

	/**
	 * 
	 * @param data
	 *            The data
	 */
	public void setData(List<CompanyDatum> data) {
		this.data = data;
	}

}
