package com.lxd.ymxx.model.announcement;

import com.google.gson.annotations.Expose;

public class Data {

	@Expose
	private Integer AdvanceID;
	@Expose
	private String AdvanceTitle;
	@Expose
	private String AdvanceContent;
	@Expose
	private String AdvanceTime;
	@Expose
	private String AdvanceImage;
	@Expose
	private Integer CompanyId;
	@Expose
	private Integer AdvanceType;

	/**
	 * 
	 * @return The AdvanceID
	 */
	public Integer getAdvanceID() {
		return AdvanceID;
	}

	/**
	 * 
	 * @param AdvanceID
	 *            The AdvanceID
	 */
	public void setAdvanceID(Integer AdvanceID) {
		this.AdvanceID = AdvanceID;
	}

	/**
	 * 
	 * @return The AdvanceTitle
	 */
	public String getAdvanceTitle() {
		return AdvanceTitle;
	}

	/**
	 * 
	 * @param AdvanceTitle
	 *            The AdvanceTitle
	 */
	public void setAdvanceTitle(String AdvanceTitle) {
		this.AdvanceTitle = AdvanceTitle;
	}

	/**
	 * 
	 * @return The AdvanceContent
	 */
	public String getAdvanceContent() {
		return AdvanceContent;
	}

	/**
	 * 
	 * @param AdvanceContent
	 *            The AdvanceContent
	 */
	public void setAdvanceContent(String AdvanceContent) {
		this.AdvanceContent = AdvanceContent;
	}

	/**
	 * 
	 * @return The AdvanceTime
	 */
	public String getAdvanceTime() {
		return AdvanceTime;
	}

	/**
	 * 
	 * @param AdvanceTime
	 *            The AdvanceTime
	 */
	public void setAdvanceTime(String AdvanceTime) {
		this.AdvanceTime = AdvanceTime;
	}

	/**
	 * 
	 * @return The AdvanceImage
	 */
	public String getAdvanceImage() {
		return AdvanceImage;
	}

	/**
	 * 
	 * @param AdvanceImage
	 *            The AdvanceImage
	 */
	public void setAdvanceImage(String AdvanceImage) {
		this.AdvanceImage = AdvanceImage;
	}

	/**
	 * 
	 * @return The CompanyId
	 */
	public Integer getCompanyId() {
		return CompanyId;
	}

	/**
	 * 
	 * @param CompanyId
	 *            The CompanyId
	 */
	public void setCompanyId(Integer CompanyId) {
		this.CompanyId = CompanyId;
	}

	/**
	 * 
	 * @return The AdvanceType
	 */
	public Integer getAdvanceType() {
		return AdvanceType;
	}

	/**
	 * 
	 * @param AdvanceType
	 *            The AdvanceType
	 */
	public void setAdvanceType(Integer AdvanceType) {
		this.AdvanceType = AdvanceType;
	}

}
