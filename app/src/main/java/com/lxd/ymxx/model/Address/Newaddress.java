package com.lxd.ymxx.model.Address;

import java.util.List;

/**
 * Created by 小林 on 2016/8/22.
 */
public class Newaddress {

    /**
     * code : 1
     * msg :
     * data : [{"AddressID":2,"UserID":1,"Province":"福建","City":"厦门","Area":"湖里区","Address1":"厦门市湖里区软件园","Code":"10011 ","Phone":"13215917990","DefaultAddress":null,"Receiver":"小明"}]
     */

    private String code;
    private String msg;
    /**
     * AddressID : 2
     * UserID : 1
     * Province : 福建
     * City : 厦门
     * Area : 湖里区
     * Address1 : 厦门市湖里区软件园
     * Code : 10011
     * Phone : 13215917990
     * DefaultAddress : null
     * Receiver : 小明
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int AddressID;
        private int UserID;
        private String Province;
        private String City;
        private String Area;
        private String Address1;
        private String Code;
        private String Phone;
        private int DefaultAddress;
        private String Receiver;

        public int getAddressID() {
            return AddressID;
        }

        public void setAddressID(int AddressID) {
            this.AddressID = AddressID;
        }

        public int getUserID() {
            return UserID;
        }

        public void setUserID(int UserID) {
            this.UserID = UserID;
        }

        public String getProvince() {
            return Province;
        }

        public void setProvince(String Province) {
            this.Province = Province;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getArea() {
            return Area;
        }

        public void setArea(String Area) {
            this.Area = Area;
        }

        public String getAddress1() {
            return Address1;
        }

        public void setAddress1(String Address1) {
            this.Address1 = Address1;
        }

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public int getDefaultAddress() {
            return DefaultAddress;
        }

        public void setDefaultAddress(int DefaultAddress) {
            this.DefaultAddress = DefaultAddress;
        }

        public String getReceiver() {
            return Receiver;
        }

        public void setReceiver(String Receiver) {
            this.Receiver = Receiver;
        }
    }
}
