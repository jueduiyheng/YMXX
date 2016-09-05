package com.lxd.ymxx.model.technicianpersonal;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Data {

    @Expose
    private Integer ID;
    @Expose
    private String TechnicianName;
    @Expose
    private Object Introduction;
    @Expose
    private String TechnicianLogo;
    @Expose
    private Integer TechnicianSex;
    @Expose
    private String TechnicianCategoryName;
    @Expose
    private List<Image> images = new ArrayList<Image>();

    /**
     * 
     * @return
     *     The ID
     */
    public Integer getID() {
        return ID;
    }

    /**
     * 
     * @param ID
     *     The ID
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * 
     * @return
     *     The TechnicianName
     */
    public String getTechnicianName() {
        return TechnicianName;
    }

    /**
     * 
     * @param TechnicianName
     *     The TechnicianName
     */
    public void setTechnicianName(String TechnicianName) {
        this.TechnicianName = TechnicianName;
    }

    /**
     * 
     * @return
     *     The Introduction
     */
    public Object getIntroduction() {
        return Introduction;
    }

    /**
     * 
     * @param Introduction
     *     The Introduction
     */
    public void setIntroduction(Object Introduction) {
        this.Introduction = Introduction;
    }

    /**
     * 
     * @return
     *     The TechnicianLogo
     */
    public String getTechnicianLogo() {
        return TechnicianLogo;
    }

    /**
     * 
     * @param TechnicianLogo
     *     The TechnicianLogo
     */
    public void setTechnicianLogo(String TechnicianLogo) {
        this.TechnicianLogo = TechnicianLogo;
    }

    /**
     * 
     * @return
     *     The TechnicianSex
     */
    public Integer getTechnicianSex() {
        return TechnicianSex;
    }

    /**
     * 
     * @param TechnicianSex
     *     The TechnicianSex
     */
    public void setTechnicianSex(Integer TechnicianSex) {
        this.TechnicianSex = TechnicianSex;
    }

    /**
     * 
     * @return
     *     The TechnicianCategoryName
     */
    public String getTechnicianCategoryName() {
        return TechnicianCategoryName;
    }

    /**
     * 
     * @param TechnicianCategoryName
     *     The TechnicianCategoryName
     */
    public void setTechnicianCategoryName(String TechnicianCategoryName) {
        this.TechnicianCategoryName = TechnicianCategoryName;
    }

    /**
     * 
     * @return
     *     The images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

}
