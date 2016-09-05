package com.lxd.ymxx.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.lxd.ymxx.app.R;
import com.lxd.ymxx.utlis.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreferentialFragment extends Fragment {


    private LayoutInflater inflater;

    public PreferentialFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.fragment_preferential, container, false);
        ListView lv_preferential = (ListView) view.findViewById(R.id.lv_preferential);
        ListaAdapter adapter = new ListaAdapter();
        lv_preferential.setAdapter(adapter);
        ToastUtils.showToast(getActivity(),"不是吧那");
        Log.e("onCreateView: ", "不是吧那");
        return view;
    }


    class ListaAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflater.inflate(R.layout.item_preferential,null);
            return view;
        }
    }


}
