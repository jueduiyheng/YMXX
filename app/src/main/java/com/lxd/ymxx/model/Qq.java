package com.lxd.ymxx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Qq {

	@SerializedName("is_yellow_vip")
	@Expose
	private String isYellowVip;
	@SerializedName("yellow_vip_level")
	@Expose
	private String yellowVipLevel;
	@SerializedName("profile_image_url")
	@Expose
	private String profileImageUrl;
	@SerializedName("screen_name")
	@Expose
	private String screenName;
	@Expose
	private String msg;
	@Expose
	private String vip;
	@Expose
	private String city;
	@Expose
	private String gender;
	@Expose
	private String province;
	@Expose
	private String level;
	@SerializedName("is_yellow_year_vip")
	@Expose
	private String isYellowYearVip;
	@Expose
	private String openid;

	/**
	 * 
	 * @return The isYellowVip
	 */
	public String getIsYellowVip() {
		return isYellowVip;
	}

	/**
	 * 
	 * @param isYellowVip
	 *            The is_yellow_vip
	 */
	public void setIsYellowVip(String isYellowVip) {
		this.isYellowVip = isYellowVip;
	}

	/**
	 * 
	 * @return The yellowVipLevel
	 */
	public String getYellowVipLevel() {
		return yellowVipLevel;
	}

	/**
	 * 
	 * @param yellowVipLevel
	 *            The yellow_vip_level
	 */
	public void setYellowVipLevel(String yellowVipLevel) {
		this.yellowVipLevel = yellowVipLevel;
	}

	/**
	 * 
	 * @return The profileImageUrl
	 */
	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	/**
	 * 
	 * @param profileImageUrl
	 *            The profile_image_url
	 */
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	/**
	 * 
	 * @return The screenName
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * 
	 * @param screenName
	 *            The screen_name
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
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
	 * @return The vip
	 */
	public String getVip() {
		return vip;
	}

	/**
	 * 
	 * @param vip
	 *            The vip
	 */
	public void setVip(String vip) {
		this.vip = vip;
	}

	/**
	 * 
	 * @return The city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @param city
	 *            The city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 
	 * @return The gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * 
	 * @param gender
	 *            The gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * 
	 * @return The province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * 
	 * @param province
	 *            The province
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 
	 * @return The level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * 
	 * @param level
	 *            The level
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * 
	 * @return The isYellowYearVip
	 */
	public String getIsYellowYearVip() {
		return isYellowYearVip;
	}

	/**
	 * 
	 * @param isYellowYearVip
	 *            The is_yellow_year_vip
	 */
	public void setIsYellowYearVip(String isYellowYearVip) {
		this.isYellowYearVip = isYellowYearVip;
	}

	/**
	 * 
	 * @return The openid
	 */
	public String getOpenid() {
		return openid;
	}

	/**
	 * 
	 * @param openid
	 *            The openid
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
