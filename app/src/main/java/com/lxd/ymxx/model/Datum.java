package com.lxd.ymxx.model;

import com.google.gson.annotations.Expose;

public class Datum {

	@Expose
	private Integer ScoresID;
	@Expose
	private Integer UserID;
	@Expose
	private Integer Scores1;
	@Expose
	private String ScoresTime;
	@Expose
	private Integer ScoresCount;
	@Expose
	private Integer Type;
	@Expose
	private String Describe;

	/**
	 * 
	 * @return The ScoresID
	 */
	public Integer getScoresID() {
		return ScoresID;
	}

	/**
	 * 
	 * @param ScoresID
	 *            The ScoresID
	 */
	public void setScoresID(Integer ScoresID) {
		this.ScoresID = ScoresID;
	}

	/**
	 * 
	 * @return The UserID
	 */
	public Integer getUserID() {
		return UserID;
	}

	/**
	 * 
	 * @param UserID
	 *            The UserID
	 */
	public void setUserID(Integer UserID) {
		this.UserID = UserID;
	}

	/**
	 * 
	 * @return The Scores1
	 */
	public Integer getScores1() {
		return Scores1;
	}

	/**
	 * 
	 * @param Scores1
	 *            The Scores1
	 */
	public void setScores1(Integer Scores1) {
		this.Scores1 = Scores1;
	}

	/**
	 * 
	 * @return The ScoresTime
	 */
	public String getScoresTime() {
		return ScoresTime;
	}

	/**
	 * 
	 * @param ScoresTime
	 *            The ScoresTime
	 */
	public void setScoresTime(String ScoresTime) {
		this.ScoresTime = ScoresTime;
	}

	/**
	 * 
	 * @return The ScoresCount
	 */
	public Integer getScoresCount() {
		return ScoresCount;
	}

	/**
	 * 
	 * @param ScoresCount
	 *            The ScoresCount
	 */
	public void setScoresCount(Integer ScoresCount) {
		this.ScoresCount = ScoresCount;
	}

	/**
	 * 
	 * @return The Type
	 */
	public Integer getType() {
		return Type;
	}

	/**
	 * 
	 * @param Type
	 *            The Type
	 */
	public void setType(Integer Type) {
		this.Type = Type;
	}

	/**
	 * 
	 * @return The Describe
	 */
	public String getDescribe() {
		return Describe;
	}

	/**
	 * 
	 * @param Describe
	 *            The Describe
	 */
	public void setDescribe(String Describe) {
		this.Describe = Describe;
	}

}
