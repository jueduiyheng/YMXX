package com.lxd.ymxx.model.about;

import com.google.gson.annotations.Expose;

public class Data {
	@Expose
	private Integer AboutID;
	@Expose
	private Object LOGO;
	@Expose
	private String CompanayName;
	@Expose
	private String CompanayAddress;
	@Expose
	private String CompanayTEL;
	@Expose
	private String CompanayFAX;
	@Expose
	private String CompanayEmail;
	@Expose
	private String CompanayContent;

	/**
	 * 
	 * @return The AboutID
	 */
	public Integer getAboutID() {
		return AboutID;
	}

	/**
	 * 
	 * @param AboutID
	 *            The AboutID
	 */
	public void setAboutID(Integer AboutID) {
		this.AboutID = AboutID;
	}

	/**
	 * 
	 * @return The LOGO
	 */
	public Object getLOGO() {
		return LOGO;
	}

	/**
	 * 
	 * @param LOGO
	 *            The LOGO
	 */
	public void setLOGO(Object LOGO) {
		this.LOGO = LOGO;
	}

	/**
	 * 
	 * @return The CompanayName
	 */
	public String getCompanayName() {
		return CompanayName;
	}

	/**
	 * 
	 * @param CompanayName
	 *            The CompanayName
	 */
	public void setCompanayName(String CompanayName) {
		this.CompanayName = CompanayName;
	}

	/**
	 * 
	 * @return The CompanayAddress
	 */
	public String getCompanayAddress() {
		return CompanayAddress;
	}

	/**
	 * 
	 * @param CompanayAddress
	 *            The CompanayAddress
	 */
	public void setCompanayAddress(String CompanayAddress) {
		this.CompanayAddress = CompanayAddress;
	}

	/**
	 * 
	 * @return The CompanayTEL
	 */
	public String getCompanayTEL() {
		return CompanayTEL;
	}

	/**
	 * 
	 * @param CompanayTEL
	 *            The CompanayTEL
	 */
	public void setCompanayTEL(String CompanayTEL) {
		this.CompanayTEL = CompanayTEL;
	}

	/**
	 * 
	 * @return The CompanayFAX
	 */
	public String getCompanayFAX() {
		return CompanayFAX;
	}

	/**
	 * 
	 * @param CompanayFAX
	 *            The CompanayFAX
	 */
	public void setCompanayFAX(String CompanayFAX) {
		this.CompanayFAX = CompanayFAX;
	}

	/**
	 * 
	 * @return The CompanayEmail
	 */
	public String getCompanayEmail() {
		return CompanayEmail;
	}

	/**
	 * 
	 * @param CompanayEmail
	 *            The CompanayEmail
	 */
	public void setCompanayEmail(String CompanayEmail) {
		this.CompanayEmail = CompanayEmail;
	}

	/**
	 * 
	 * @return The CompanayContent
	 */
	public String getCompanayContent() {
		return CompanayContent;
	}

	/**
	 * 
	 * @param CompanayContent
	 *            The CompanayContent
	 */
	public void setCompanayContent(String CompanayContent) {
		this.CompanayContent = CompanayContent;
	}

}
