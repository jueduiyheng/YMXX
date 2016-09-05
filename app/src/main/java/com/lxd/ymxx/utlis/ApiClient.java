package com.lxd.ymxx.utlis;

import android.content.Context;

import com.xinbo.utils.HTTPUtils;
import com.xinbo.utils.VolleyListener;

import java.util.Map;

public class ApiClient {
    /**
     * 登录
     *
     * @param context
     * @param params
     * @param listener
     */
    public static void getlogin(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.post(context, Constants.URL.LOGIN, params, listener);
    }

    /**
     * 注册
     *
     * @param context
     * @param params
     * @param listener
     */
    public static void getRegister(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.post(context, Constants.URL.REGISTER, params, listener);
    }

    /**
     * 验证码
     *
     * @param context
     * @param params
     * @param listener
     */
    public static void getsendsms(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.post(context, Constants.URL.SENDSMS, params, listener);
    }

    /**
     * 修改用户登录密码和支付密码
     *
     * @param context
     * @param params
     * @param listener
     */
    public static void getUpdatePwd(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.post(context, Constants.URL.UPDATEPWD, params, listener);
    }

    /**
     * 获取用户积分明细
     * <p/>
     * 传userid
     */
    public static void getScoreByUser(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.get(context, Constants.URL.GETSCOREBYUSER, params, listener);
    }

    /**
     * 获取关于美约秀秀信息（平台信息） GET
     * <p/>
     * 传userid
     */
    public static void getAbout(Context context, VolleyListener listener) {
        HTTPUtils.get(context, Constants.URL.GETABOUT, listener);
    }

    /**
     * 根据用户ID 获取用户的收获地址
     * <p/>
     * 传userid
     */
    public static void getAddressList(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.get(context, Constants.URL.GETADDRESSLIST, params, listener);
    }

    /**
     * 用户修改默认地址
     * <p/>
     * 传oldid,newid POST
     */
    public static void getSetDefaultAddress(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.post(context, Constants.URL.SETDEFAULTADDRESS, params, listener);
    }

    /**
     * 获取商城产品
     * <p/>
     * 传userid,category,orderother
     */
    public static void getProduct(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.get(context, Constants.URL.GETPRODUCT, params, listener);
    }

    /**
     * 获取商户信息 根据商户ID或者商户名称获取商户信息，如果都为空获取所有商户信息 companyname,companyid
     */
    public static void getcompanyt(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.get(context, Constants.URL.GETCOMPANY, params, listener);
    }

    /**
     * 用户绑定商户或者更改商户绑定 根据商户ID或者商户名称获取商户信息，如果都为空获取所有商户信息 传userid,companyid
     */
    public static void getbindingcompany(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.get(context, Constants.URL.BINDINGCOMPANY, params, listener);
    }

    /**
     * 3.2 根据商户ID获取其绑定人数
     * <p/>
     * 传companyid
     */
    public static void getcompanyusercout(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.get(context, Constants.URL.GETCOMPANYUSERCOUT, params, listener);
    }

    /**
     * 3.4 获取用户绑定商户余额
     * <p/>
     * 传companyid,userid
     */
    public static void getcompanyusermoney(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.get(context, Constants.URL.GETCOMPANYUSERMONEY, params, listener);
    }

    /**
     * 3.3 根据商户ID获取充值金额档次
     * <p/>
     * 传companyid,userid
     */
    public static void getrecharge(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.get(context, Constants.URL.GETRECHARGE, params, listener);
    }

    /**
     * 3.6 获取商户最近第一条公告 GET
     * <p/>
     * 传companyid
     */
    public static void getfirstadvance(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.get(context, Constants.URL.GETFIRSTADVANCE, params, listener);
    }

    /**
     * 3.8 获取商户技师 GET
     * <p/>
     * 传companyid
     */
    public static void gettechnician(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.get(context, Constants.URL.GETTECHNICIAN, params, listener);
    }

    /**
     * 3.12 根据技师ID获取技师作品
     * <p/>
     * 传techniciaid
     */
    public static void gettechnicianbyid(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.get(context, Constants.URL.GETTECHNICIANBYID, params, listener);
    }

    /**
     * 根据商户ID获取所有公告信息
     * <p/>
     * 传companyid
     */
    public static void getadvance(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.get(context, Constants.URL.GETADVANCE, params, listener);
    }

    /**
     * 根据商户ID获取某天的排班情况
     * <p/>
     * 传companyid
     */
    public static void gettechnicianschedule(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.get(context, Constants.URL.GETTECHNICIANSCHEDULE, params, listener);
    }

    /**
     * 2.3	用户第三方登录注册
     * <p/>
     * openid,name,sex,companyid,userlogo
     */
    public static void getuserregister(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.post(context, Constants.URL.USERREGISTER, params, listener);
    }

    /**
     * 2.14	用户新增收货地址
     */
    public static void getaddress(Context context, Map<String, String> params, int is, VolleyListener listener) {
        if (is == 2) {
            //新增地址
            HTTPUtils.post(context, Constants.URL.ADDRESSADD, params, listener);
        } else if (is == 1) {
            //修改地址
            HTTPUtils.post(context, Constants.URL.ADDRESSUPDATE, params, listener);
        }
    }

    /**
     * 2.15	用户删除收货地址
     */
    public static void getaddressdelete(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.post(context, Constants.URL.ADDRESSDELETE, params, listener);
    }
    /**
     * 2.6	修改用户信息
     */
    public static void getupdateuser(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.post(context, Constants.URL.UPDATEUSER, params, listener);
    }
     /**
     * 2.5	获取用户信息
     */
    public static void getgetuser(Context context, Map<String, String> params, VolleyListener listener) {
        HTTPUtils.get(context, Constants.URL.GETUSER, params, listener);
    }

}
