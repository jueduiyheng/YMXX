package com.lxd.ymxx.model.technicianschedule;

import com.google.gson.annotations.Expose;

public class Datum {

	@Expose
	private Integer TechnicianScheduleID;
	@Expose
	private String Workedate;
	@Expose
	private Integer IsWork;
	@Expose
	private String Createtime;
	@Expose
	private Integer CompanyID;
	@Expose
	private String TechnicianName;

	/**
	 * 
	 * @return The TechnicianScheduleID
	 */
	public Integer getTechnicianScheduleID() {
		return TechnicianScheduleID;
	}

	/**
	 * 
	 * @param TechnicianScheduleID
	 *            The TechnicianScheduleID
	 */
	public void setTechnicianScheduleID(Integer TechnicianScheduleID) {
		this.TechnicianScheduleID = TechnicianScheduleID;
	}

	/**
	 * 
	 * @return The Workedate
	 */
	public String getWorkedate() {
		return Workedate;
	}

	/**
	 * 
	 * @param Workedate
	 *            The Workedate
	 */
	public void setWorkedate(String Workedate) {
		this.Workedate = Workedate;
	}

	/**
	 * 
	 * @return The IsWork
	 */
	public Integer getIsWork() {
		return IsWork;
	}

	/**
	 * 
	 * @param IsWork
	 *            The IsWork
	 */
	public void setIsWork(Integer IsWork) {
		this.IsWork = IsWork;
	}

	/**
	 * 
	 * @return The Createtime
	 */
	public String getCreatetime() {
		return Createtime;
	}

	/**
	 * 
	 * @param Createtime
	 *            The Createtime
	 */
	public void setCreatetime(String Createtime) {
		this.Createtime = Createtime;
	}

	/**
	 * 
	 * @return The CompanyID
	 */
	public Integer getCompanyID() {
		return CompanyID;
	}

	/**
	 * 
	 * @param CompanyID
	 *            The CompanyID
	 */
	public void setCompanyID(Integer CompanyID) {
		this.CompanyID = CompanyID;
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

}
