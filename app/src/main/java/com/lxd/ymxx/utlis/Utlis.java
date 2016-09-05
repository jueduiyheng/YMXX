package com.lxd.ymxx.utlis;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.lxd.ymxx.model.Data;

import java.util.Map;

public class Utlis {
    /**
     * json截取
     *
     * @param json
     * @return
     */
    public static String cutout(String json) {
        json = json.substring(json.indexOf("{\""), json.lastIndexOf("</string>"));
        return json;

    }

    /**
     * 保存商户ID
     */
    public static void savecompanyid(Context context, Integer integer) {
        SharedPreferences sp = context.getSharedPreferences("COMPANY", Context.MODE_PRIVATE);
        sp.edit().putInt("companyid", integer).commit();
    }

    /**
     * 获取商户ID
     */
    public static Integer getcompanyid(Context context) {
        SharedPreferences sp = context.getSharedPreferences("COMPANY", Context.MODE_PRIVATE);
        Integer id = sp.getInt("companyid", 1);
        return id;
    }


    /**
     * 保存个人简介
     **/
    public static void savecredential(Context context, Integer userID, String userName, String userAccount,
                                      String userAdvancePassword, Integer userSex, Integer userScores, String userPhone, String userBirthYear,
                                      String userBirthMonth, String userEmail, String userImage, String userCreateTime, String userPassword, Integer CompanyID) {
        SharedPreferences sp = context.getSharedPreferences("CREDENTIAL", Context.MODE_PRIVATE);
        sp.edit().putInt("userID", userID).putString("userName", userName).putString("userAccount", userAccount)
                .putString("userAdvancePassword", userAdvancePassword).putInt("userSex", userSex)
                .putInt("userScores", userScores).putString("userPhone", userPhone)
                .putString("userBirthYear", userBirthYear).putString("userBirthMonth", userBirthMonth)
                .putString("userEmail", userEmail).putString("userImage", userImage)
                .putString("userCreateTime", userCreateTime).putString("userPassword", userPassword).putInt("CompanyID", CompanyID).commit();
    }

    /**
     * 获取个人简介
     **/
    public static Data getcredential(Context context) {
        SharedPreferences sp = context.getSharedPreferences("CREDENTIAL", Context.MODE_PRIVATE);
        Data person = new Data();
        person.setUserID(sp.getInt("userID", 0));
        person.setUserName(sp.getString("userName", "未设置"));
        person.setUserAccount(sp.getString("userAccount", "未设置"));
        person.setUserAdvancePassword(sp.getString("userAdvancePassword", ""));
        person.setUserSex(sp.getInt("userSex", 2));
        person.setUserScores(sp.getInt("userScores", 0));
        person.setUserPhone(sp.getString("userPhone", "未设置"));
        person.setUserBirthYear(sp.getString("userBirthYear", ""));
        person.setUserBirthMonth(sp.getString("userBirthMonth", ""));
        person.setUserImage(sp.getString("userImage", ""));
        person.setUserCreateTime(sp.getString("userCreateTime", ""));
        person.setUserPassword(sp.getString("userPassword", ""));
        person.setCompanyID(sp.getInt("CompanyID", 0));
        return person;
    }

    /**
     * 清空个人简介
     **/
    public static void cleardential(Context context) {
        SharedPreferences sp = context.getSharedPreferences("CREDENTIAL", Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

    /**
     * Map转json
     *
     * @param map
     * @return
     */
    public static <T> String mapToJson(Map<String, T> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        return jsonStr;
    }
}
