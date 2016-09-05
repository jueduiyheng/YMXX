package com.lxd.ymxx.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.lxd.ymxx.model.Address.Newaddress;
import com.lxd.ymxx.utlis.ApiClient;
import com.lxd.ymxx.utlis.Constants;
import com.lxd.ymxx.utlis.LoadingDialog;
import com.lxd.ymxx.utlis.TitleBuilder;
import com.lxd.ymxx.utlis.ToastUtils;
import com.lxd.ymxx.utlis.Utlis;
import com.xinbo.utils.GsonUtils;
import com.xinbo.utils.VolleyListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class AddressActivity extends Activity implements View.OnClickListener {
    private List<Newaddress.DataBean> myListAddress = new ArrayList<>();
    private ListAdapter adapter;
    private ListView lv_address;
    private List<Newaddress.DataBean> addrlist = new ArrayList<>();
    private String userid;
    private String oldid = "";
    private LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        dialog = new LoadingDialog(AddressActivity.this, "数据加载中...");
        dialog.show();
        userid = Utlis.getcredential(this).getUserID().toString();
        initui();
        initdata();

    }

    private void initdata() {
        Map<String, String> params = new HashMap<>();
        params.put("userid", userid);
        ApiClient.getAddressList(this, params, new VolleyListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtils.showToast(AddressActivity.this, "请求失败");
            }

            @Override
            public void onResponse(String json) {
                dialog.close();
                addrlist.clear();
                json = Utlis.cutout(json);
                Newaddress address = GsonUtils.parseJSON(json, Newaddress.class);
                addrlist.addAll(address.getData());
                initraank();
                adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 把默认的放在第一行
     */
    private void initraank() {
        for (int i = 0; i < addrlist.size(); i++) {
            if (addrlist.get(i).getDefaultAddress() == 1) {
                myListAddress.add(addrlist.get(i));
                addrlist.remove(i);
                addrlist.addAll(0, myListAddress);
                myListAddress.clear();
            }
        }
    }

    private void initui() {
        new TitleBuilder(this).setTitleText("收货地址管理").setLeftOnClickListener(this);
        findViewById(R.id.but_new_address).setOnClickListener(this);
        lv_address = (ListView) findViewById(R.id.lv_address);
        adapter = new ListAdapter();
        lv_address.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_img_back:
                finish();
                break;
            case R.id.but_new_address:
                //新增地址
                Intent intent = new Intent(AddressActivity.this, EditaddressActivity.class);
                intent.putExtra(Constants.Code.KEY_ADDRESS, 2);// 添加的请求码
                startActivity(intent);
                break;
        }
    }

    /**
     * 删除
     */
    private void initdelete(String addressid) {
        Map<String, String> params = new HashMap<>();
        params.put("addressid", addressid);
        params.put("userid", userid);
        ApiClient.getaddressdelete(this, params, new VolleyListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtils.showToast(AddressActivity.this, "请求失败");
            }

            @Override
            public void onResponse(String json) {
                ToastUtils.showToast(AddressActivity.this, json);
                json = Utlis.cutout(json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    if ("1".equals(jsonObject.getString("code"))) {
                        ToastUtils.showToast(AddressActivity.this, "删除成功");
                        initdata();
                    } else {
                        ToastUtils.showToast(AddressActivity.this, "删除失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    /**
     * 设置默认地址
     */
    private void initdefault(String address_id, final LoadingDialog dialog) {
        Map<String, String> params = new HashMap<>();
        params.put("userid", userid);
        params.put("oldid", oldid);
        params.put("newid", address_id);
        ApiClient.getSetDefaultAddress(this, params, new VolleyListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                ToastUtils.showToast(AddressActivity.this, "请求失败");
            }

            @Override
            public void onResponse(String json) {
//                Log.e("onResponse: ", json);
                dialog.close();
                initdata();
            }
        });
    }

    class ListAdapter extends BaseAdapter {
        // 标记用户当前选择的那一个默认地址
        private String index = "1";

        @Override
        public int getCount() {
            return addrlist.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Newaddress.DataBean dataBean = addrlist.get(position);
            final String name = dataBean.getReceiver();
            final String phone = dataBean.getPhone();
            final String address1 = dataBean.getAddress1();
            final String address_id = dataBean.getAddressID() + "";

            final viewHolder holder;
            if (convertView == null) {
                holder = new viewHolder();
                convertView = LayoutInflater.from(AddressActivity.this).inflate(R.layout.item_address, null);
                holder.tv_address_name = (TextView) convertView.findViewById(R.id.tv_address_name);
                holder.tv_address_phone = (TextView) convertView.findViewById(R.id.tv_address_phone);
                holder.tv_address_address = (TextView) convertView.findViewById(R.id.tv_address_address);
                holder.tv_address_editor = (TextView) convertView.findViewById(R.id.tv_address_editor);
                holder.tv_address_delete = (TextView) convertView.findViewById(R.id.tv_address_delete);
                holder.rb_address_default = (RadioButton) convertView.findViewById(R.id.rb_address_default);
                convertView.setTag(holder);
            } else {
                holder = (viewHolder) convertView.getTag();
            }

            holder.tv_address_name.setText(name);
            holder.tv_address_phone.setText(phone);
            holder.tv_address_address.setText(dataBean.getAddress1());
            if (dataBean.getDefaultAddress() == 1) {
                holder.rb_address_default.setChecked(true);
                oldid = address_id;
            } else {
                holder.rb_address_default.setChecked(false);
            }
            /**
             * 设置默认地址
             */
            holder.rb_address_default.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                    if (isChecked) {
                        final LoadingDialog dialog = new LoadingDialog(AddressActivity.this, "数据加载中...");
                        dialog.show();
                        holder.rb_address_default.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                initdefault(address_id, dialog);
                            }
                        }, 1000);
                    }
                }
            });
            /**
             * 删除地址
             */
            holder.tv_address_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new SweetAlertDialog(AddressActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("你确定删除吗?").setCancelText("取消").setConfirmText("确定").showCancelButton(true)
                            .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismiss();
                                }
                            }).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            initdelete(address_id);
                            sDialog.dismiss();
                        }
                    }).show();
                }
            });
            /**
             * 修改地址
             */
            holder.tv_address_editor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AddressActivity.this, EditaddressActivity.class);
                    intent.putExtra(Constants.Code.KEY_ADDRESS, 1);// 修改的请求码
                    intent.putExtra("name", name);
                    intent.putExtra("phone", phone);
                    intent.putExtra("address1", address1);
                    intent.putExtra("address_id", address_id);
                    startActivity(intent);
                }
            });
            return convertView;
        }
    }

    public class viewHolder {
        public TextView tv_address_name;
        public TextView tv_address_phone;
        public TextView tv_address_address;
        public TextView tv_address_editor;
        public RadioButton rb_address_default;
        public TextView tv_address_delete;
    }
}
