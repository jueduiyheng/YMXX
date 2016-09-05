package com.lxd.ymxx.model.card;

import com.google.gson.annotations.Expose;

public class Datum {

    @Expose
    private Integer RechargeID;
    @Expose
    private Double RechargeMoney;
    @Expose
    private Double RechargeDiscount;
    @Expose
    private String RechargeImage;
    @Expose
    private String RechargeTime;
    @Expose
    private Integer CompanyID;

    /**
     * 
     * @return
     *     The RechargeID
     */
    public Integer getRechargeID() {
        return RechargeID;
    }

    /**
     * 
     * @param RechargeID
     *     The RechargeID
     */
    public void setRechargeID(Integer RechargeID) {
        this.RechargeID = RechargeID;
    }

    /**
     * 
     * @return
     *     The RechargeMoney
     */
    public Double getRechargeMoney() {
        return RechargeMoney;
    }

    /**
     * 
     * @param RechargeMoney
     *     The RechargeMoney
     */
    public void setRechargeMoney(Double RechargeMoney) {
        this.RechargeMoney = RechargeMoney;
    }

    /**
     * 
     * @return
     *     The RechargeDiscount
     */
    public Double getRechargeDiscount() {
        return RechargeDiscount;
    }

    /**
     * 
     * @param RechargeDiscount
     *     The RechargeDiscount
     */
    public void setRechargeDiscount(Double RechargeDiscount) {
        this.RechargeDiscount = RechargeDiscount;
    }

    /**
     * 
     * @return
     *     The RechargeImage
     */
    public String getRechargeImage() {
        return RechargeImage;
    }

    /**
     * 
     * @param RechargeImage
     *     The RechargeImage
     */
    public void setRechargeImage(String RechargeImage) {
        this.RechargeImage = RechargeImage;
    }

    /**
     * 
     * @return
     *     The RechargeTime
     */
    public String getRechargeTime() {
        return RechargeTime;
    }

    /**
     * 
     * @param RechargeTime
     *     The RechargeTime
     */
    public void setRechargeTime(String RechargeTime) {
        this.RechargeTime = RechargeTime;
    }

    /**
     * 
     * @return
     *     The CompanyID
     */
    public Integer getCompanyID() {
        return CompanyID;
    }

    /**
     * 
     * @param CompanyID
     *     The CompanyID
     */
    public void setCompanyID(Integer CompanyID) {
        this.CompanyID = CompanyID;
    }

}
