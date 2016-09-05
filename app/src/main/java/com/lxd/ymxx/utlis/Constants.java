package com.lxd.ymxx.utlis;

public class Constants {
    public static final class URL {
        // http://119.29.80.31:83/User.asmx/GetUser?userid=2
        public static final String HOST = "http://119.29.80.31:83/";
        public static final String SERVICE = "User.asmx/";
        public static final String MERCHANT = "Company.asmx/";
        /**
         * 用户登入接口 post
         */
        public static final String LOGIN = HOST + SERVICE + "Login";
        /**
         * 用户注册接口 post
         */
        public static final String REGISTER = HOST + SERVICE + "Register";
        /**
         * 验证码 post
         */
        public static final String SENDSMS = HOST + SERVICE + "SendSms";
        /**
         * @修改用户登录密码和支付密码
         * @post
         * @修改类型：登陆密码 ：支付密码
         */
        public static final String UPDATEPWD = HOST + SERVICE + "UpdatePwd";
        /**
         * 获取用户积分明细 GET
         */
        public static final String GETSCOREBYUSER = HOST + SERVICE + "GetScoreByUser";
        /**
         * 获取关于美约秀秀信息（平台信息） GET
         */
        public static final String GETABOUT = HOST + SERVICE + "GetAbout";
        /**
         * 根据用户ID 获取用户的收获地址 GET
         */
        public static final String GETADDRESSLIST = HOST + SERVICE + "GetAddressList";
        /**
         * 用户修改默认地址 POST
         */
        public static final String SETDEFAULTADDRESS = HOST + SERVICE + "SetDefaultAddress";
        /**
         * 获取商城产品 GET
         */
        public static final String GETPRODUCT = HOST + SERVICE + "GetProduct";
        /**
         * 用户绑定商户或者更改商户绑定 POST
         */
        public static final String BINDINGCOMPANY = HOST + SERVICE + "BindingCompany";
        /**
         * 2.12 线上会员充值 POST
         */
        public static final String RECHARGE = HOST + SERVICE + "Recharge";

        /**
         * 商户模块
         * <p/>
         * 3.1 获取商户信息 GET
         */
        public static final String GETCOMPANY = HOST + MERCHANT + "GetCompany";
        /**
         * 3.2 根据商户ID获取其绑定人数 POST
         */
        public static final String GETCOMPANYUSERCOUT = HOST + MERCHANT + "GetCompanyUserCout";
        /**
         * 3.3 获取用户绑定商户余额 GET
         */
        public static final String GETCOMPANYUSERMONEY = HOST + MERCHANT + "GetCompanyUserMoney";
        /**
         * 3.3 根据商户ID获取充值金额档次
         */
        public static final String GETRECHARGE = HOST + MERCHANT + "GetRecharge";

        /**
         * 3.4 获取商户首页底部广告信息 GET
         */
        public static final String GETADVERTFORINDEX = HOST + MERCHANT + "GetAdvertForIndex";
        /**
         * 3.5 获取商户公告 GET
         */
        public static final String GETADVANCE = HOST + MERCHANT + "GetAdvance";
        /**
         * 3.6 获取商户最近第一条公告 GET
         */
        public static final String GETFIRSTADVANCE = HOST + MERCHANT + "GetFirstAdvance";
        /**
         * 3.7 获取商户具体某天的排班情况 GET
         */
        public static final String GETTECHNICIANSCHEDULE = HOST + MERCHANT + "GetTechnicianSchedule";
        /**
         * 3.8 获取商户技师 GET
         */
        public static final String GETTECHNICIAN = HOST + MERCHANT + "GetTechnician";

        /**
         * 3.9 获取热门发型分类 GET
         */
        public static final String GETSERVICEPROPERTY = HOST + MERCHANT + "GetServiceProperty";

        /**
         * 3.12 根据技师ID获取技师作品
         */
        public static final String GETTECHNICIANBYID = HOST + MERCHANT + "GetTechnicianByID";

        /**
         * 2.3	用户第三方登录注册
         */
        public static final String USERREGISTER = HOST + SERVICE + "UserRegister";
        /**
         * 2.12	用户新增收货地址
         */
        public static final String ADDRESSADD = HOST + SERVICE + "AddressAdd";
        /**
         * 2.13	用户修改收货地址
         */
        public static final String ADDRESSUPDATE = HOST + SERVICE + "AddressUpdate";
        /**
         * 2.15	用户删除收货地址
         */
        public static final String ADDRESSDELETE = HOST + SERVICE + "AddressDelete";
        /**
         * 2.6	修改用户信息
         */
        public static final String UPDATEUSER = HOST + SERVICE + "UpdateUser";
        /**
         * 2.5	获取用户信息
         */
        public static final String GETUSER = HOST + SERVICE + "GetUser";

    }

    /**
     * K值
     */
    public static final class Code {
        public static final String KEY_ADDRESS = "ADDRESS";
    }

}
