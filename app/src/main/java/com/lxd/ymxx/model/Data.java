package com.lxd.ymxx.model;

import com.google.gson.annotations.Expose;

public class Data {

    @Expose
    private Integer UserID;
    @Expose
    private String UserName;
    @Expose
    private String UserAccount;
    @Expose
    private String UserAdvancePassword;
    @Expose
    private Integer UserSex;
    @Expose
    private Integer UserScores;
    @Expose
    private String UserPhone;
    @Expose
    private String UserBirthYear;
    @Expose
    private String UserBirthMonth;
    @Expose
    private String UserEmail;
    @Expose
    private String UserImage;
    @Expose
    private String UserCreateTime;
    @Expose
    private String UserPassword;
    @Expose
    private Integer CompanyID;

    /**
     * @return The getCompanyID
     */
    public Integer getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(Integer companyID) {
        this.CompanyID = companyID;
    }

    /**
     * @return The UserID
     */
    public Integer getUserID() {
        return UserID;
    }

    /**
     * @param UserID The UserID
     */
    public void setUserID(Integer UserID) {
        this.UserID = UserID;
    }

    /**
     * @return The UserName
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * @param UserName The UserName
     */
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     * @return The UserAccount
     */
    public String getUserAccount() {
        return UserAccount;
    }

    /**
     * @param UserAccount The UserAccount
     */
    public void setUserAccount(String UserAccount) {
        this.UserAccount = UserAccount;
    }

    /**
     * @return The UserAdvancePassword
     */
    public String getUserAdvancePassword() {
        return UserAdvancePassword;
    }

    /**
     * @param UserAdvancePassword The UserAdvancePassword
     */
    public void setUserAdvancePassword(String UserAdvancePassword) {
        this.UserAdvancePassword = UserAdvancePassword;
    }

    /**
     * @return The UserSex
     */
    public Integer getUserSex() {
        return UserSex;
    }

    /**
     * @param UserSex The UserSex
     */
    public void setUserSex(Integer UserSex) {
        this.UserSex = UserSex;
    }

    /**
     * @return The UserScores
     */
    public Integer getUserScores() {
        return UserScores;
    }

    /**
     * @param UserScores The UserScores
     */
    public void setUserScores(Integer UserScores) {
        this.UserScores = UserScores;
    }

    /**
     * @return The UserPhone
     */
    public String getUserPhone() {
        return UserPhone;
    }

    /**
     * @param UserPhone The UserPhone
     */
    public void setUserPhone(String UserPhone) {
        this.UserPhone = UserPhone;
    }

    /**
     * @return The UserBirthYear
     */
    public String getUserBirthYear() {
        return UserBirthYear;
    }

    /**
     * @param UserBirthYear The UserBirthYear
     */
    public void setUserBirthYear(String UserBirthYear) {
        this.UserBirthYear = UserBirthYear;
    }

    /**
     * @return The UserBirthMonth
     */
    public String getUserBirthMonth() {
        return UserBirthMonth;
    }

    /**
     * @param UserBirthMonth The UserBirthMonth
     */
    public void setUserBirthMonth(String UserBirthMonth) {
        this.UserBirthMonth = UserBirthMonth;
    }

    /**
     * @return The UserEmail
     */
    public String getUserEmail() {
        return UserEmail;
    }

    /**
     * @param UserEmail The UserEmail
     */
    public void setUserEmail(String UserEmail) {
        this.UserEmail = UserEmail;
    }

    /**
     * @return The UserImage
     */
    public String getUserImage() {
        return UserImage;
    }

    /**
     * @param UserImage The UserImage
     */
    public void setUserImage(String UserImage) {
        this.UserImage = UserImage;
    }

    /**
     * @return The UserCreateTime
     */
    public String getUserCreateTime() {
        return UserCreateTime;
    }

    /**
     * @param UserCreateTime The UserCreateTime
     */
    public void setUserCreateTime(String UserCreateTime) {
        this.UserCreateTime = UserCreateTime;
    }

    /**
     * @return The UserPassword
     */
    public String getUserPassword() {
        return UserPassword;
    }

    /**
     * @param UserPassword The UserPassword
     */
    public void setUserPassword(String UserPassword) {
        this.UserPassword = UserPassword;
    }

}
