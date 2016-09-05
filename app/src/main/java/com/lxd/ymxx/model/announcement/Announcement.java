package com.lxd.ymxx.model.announcement;

import java.io.Serializable;
import java.util.ArrayList;

public class Announcement implements Serializable{

    /**
     * code : 1
     * msg :
     * data : [{"AdvanceID":2,"AdvanceTitle":"1111","AdvanceContent":"<p>是多少多少<br/><\/p>","AdvanceTime":"2016-06-24T16:21:43.65","AdvanceImage":"","CompanyId":1,"AdvanceType":0},{"AdvanceID":1,"AdvanceTitle":"1111111","AdvanceContent":"<p>22222222222222222222<br/><\/p>","AdvanceTime":"2016-06-24T16:20:21.713","AdvanceImage":"","CompanyId":1,"AdvanceType":0}]
     */

    private String code;
    private String msg;
    /**
     * AdvanceID : 2
     * AdvanceTitle : 1111
     * AdvanceContent : <p>是多少多少<br/></p>
     * AdvanceTime : 2016-06-24T16:21:43.65
     * AdvanceImage :
     * CompanyId : 1
     * AdvanceType : 0
     */

    private ArrayList<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        private int AdvanceID;
        private String AdvanceTitle;
        private String AdvanceContent;
        private String AdvanceTime;
        private String AdvanceImage;
        private int CompanyId;
        private int AdvanceType;

        public int getAdvanceID() {
            return AdvanceID;
        }

        public void setAdvanceID(int AdvanceID) {
            this.AdvanceID = AdvanceID;
        }

        public String getAdvanceTitle() {
            return AdvanceTitle;
        }

        public void setAdvanceTitle(String AdvanceTitle) {
            this.AdvanceTitle = AdvanceTitle;
        }

        public String getAdvanceContent() {
            return AdvanceContent;
        }

        public void setAdvanceContent(String AdvanceContent) {
            this.AdvanceContent = AdvanceContent;
        }

        public String getAdvanceTime() {
            return AdvanceTime;
        }

        public void setAdvanceTime(String AdvanceTime) {
            this.AdvanceTime = AdvanceTime;
        }

        public String getAdvanceImage() {
            return AdvanceImage;
        }

        public void setAdvanceImage(String AdvanceImage) {
            this.AdvanceImage = AdvanceImage;
        }

        public int getCompanyId() {
            return CompanyId;
        }

        public void setCompanyId(int CompanyId) {
            this.CompanyId = CompanyId;
        }

        public int getAdvanceType() {
            return AdvanceType;
        }

        public void setAdvanceType(int AdvanceType) {
            this.AdvanceType = AdvanceType;
        }
    }
}
