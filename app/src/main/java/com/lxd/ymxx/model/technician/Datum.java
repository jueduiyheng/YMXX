package com.lxd.ymxx.model.technician;

import com.google.gson.annotations.Expose;

public class Datum {

	@Expose
	private Integer ID;
	@Expose
	private String TechnicianName;
	@Expose
	private Integer TechnicianCategoryID;
	@Expose
	private Object TechnicianLogo;
	@Expose
	private Object ServiceID;
	@Expose
	private Object IsDoorService;
	@Expose
	private Object TechnicianSex;
	@Expose
	private Object TechnicianIndexImage;
	@Expose
	private Object TechnicianIsIndex;
	@Expose
	private Integer CompanyId;
	@Expose
	private Object Introduction;

	/**
	 * 
	 * @return The ID
	 */
	public Integer getID() {
		return ID;
	}

	/**
	 * 
	 * @param ID
	 *            The ID
	 */
	public void setID(Integer ID) {
		this.ID = ID;
	}

	/**
	 * 
	 * @return The TechnicianName
	 */
	public String getTechnicianName() {
		return TechnicianName;
	}

	/**
	 * 
	 * @param TechnicianName
	 *            The TechnicianName
	 */
	public void setTechnicianName(String TechnicianName) {
		this.TechnicianName = TechnicianName;
	}

	/**
	 * 
	 * @return The TechnicianCategoryID
	 */
	public Integer getTechnicianCategoryID() {
		return TechnicianCategoryID;
	}

	/**
	 * 
	 * @param TechnicianCategoryID
	 *            The TechnicianCategoryID
	 */
	public void setTechnicianCategoryID(Integer TechnicianCategoryID) {
		this.TechnicianCategoryID = TechnicianCategoryID;
	}

	/**
	 * 
	 * @return The TechnicianLogo
	 */
	public Object getTechnicianLogo() {
		return TechnicianLogo;
	}

	/**
	 * 
	 * @param TechnicianLogo
	 *            The TechnicianLogo
	 */
	public void setTechnicianLogo(Object TechnicianLogo) {
		this.TechnicianLogo = TechnicianLogo;
	}

	/**
	 * 
	 * @return The ServiceID
	 */
	public Object getServiceID() {
		return ServiceID;
	}

	/**
	 * 
	 * @param ServiceID
	 *            The ServiceID
	 */
	public void setServiceID(Object ServiceID) {
		this.ServiceID = ServiceID;
	}

	/**
	 * 
	 * @return The IsDoorService
	 */
	public Object getIsDoorService() {
		return IsDoorService;
	}

	/**
	 * 
	 * @param IsDoorService
	 *            The IsDoorService
	 */
	public void setIsDoorService(Object IsDoorService) {
		this.IsDoorService = IsDoorService;
	}

	/**
	 * 
	 * @return The TechnicianSex
	 */
	public Object getTechnicianSex() {
		return TechnicianSex;
	}

	/**
	 * 
	 * @param TechnicianSex
	 *            The TechnicianSex
	 */
	public void setTechnicianSex(Object TechnicianSex) {
		this.TechnicianSex = TechnicianSex;
	}

	/**
	 * 
	 * @return The TechnicianIndexImage
	 */
	public Object getTechnicianIndexImage() {
		return TechnicianIndexImage;
	}

	/**
	 * 
	 * @param TechnicianIndexImage
	 *            The TechnicianIndexImage
	 */
	public void setTechnicianIndexImage(Object TechnicianIndexImage) {
		this.TechnicianIndexImage = TechnicianIndexImage;
	}

	/**
	 * 
	 * @return The TechnicianIsIndex
	 */
	public Object getTechnicianIsIndex() {
		return TechnicianIsIndex;
	}

	/**
	 * 
	 * @param TechnicianIsIndex
	 *            The TechnicianIsIndex
	 */
	public void setTechnicianIsIndex(Object TechnicianIsIndex) {
		this.TechnicianIsIndex = TechnicianIsIndex;
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
	 * @return The Introduction
	 */
	public Object getIntroduction() {
		return Introduction;
	}

	/**
	 * 
	 * @param Introduction
	 *            The Introduction
	 */
	public void setIntroduction(Object Introduction) {
		this.Introduction = Introduction;
	}

}
