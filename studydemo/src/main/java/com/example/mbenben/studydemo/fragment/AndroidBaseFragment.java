package com.example.mbenben.studydemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mbenben.studydemo.App;
import com.example.mbenben.studydemo.R;
import com.example.mbenben.studydemo.base.CommonAdapter;
import com.example.mbenben.studydemo.base.OnItemClickListener;
import com.example.mbenben.studydemo.base.ViewHolder;
import com.example.mbenben.studydemo.basenote.broadcastreceiver.BroadcastReceiverActivity;
import com.example.mbenben.studydemo.basenote.contentprovider.ContentProviderActivity;
import com.example.mbenben.studydemo.basenote.intent.IntentActivity;
import com.example.mbenben.studydemo.basenote.plugin.PluginActivity;
import com.example.mbenben.studydemo.basenote.scaniamges.imageloader.ScanImageActivity;
import com.example.mbenben.studydemo.basenote.service.ServiceActivity;
import com.example.mbenben.studydemo.basenote.service.download.DownLoadServiceActivity;
import com.example.mbenben.studydemo.db.SearchBean;
import com.example.mbenben.studydemo.db.SearchDBManager;
import com.example.mbenben.studydemo.model.HashSetSearchBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MBENBEN on 2017/2/4.
 */

public class AndroidBaseFragment extends Fragment{
    @BindView(R.id.rlv_main)
    RecyclerView rlvMain;
    @BindView(R.id.tv_desc)
    TextView tvDesc;

    private static final String KEY="androidbase";
    private List<String> datas;
    private Map<String,String> mapTitle;

    private SearchDBManager manager;

    private CommonAdapter<String> adapter;
    public static AndroidBaseFragment newInstance(String desc) {


        Bundle args = new Bundle();
        args.putString(KEY,desc);
        AndroidBaseFragment fragment = new AndroidBaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frag_main_layout,container,false);
        manager=new SearchDBManager();
        initView(view);
        initRlv();
        addDB();
        return view;
    }

    private void initRlv() {
        adapter=new CommonAdapter<String>(App.getInstance().getContext(),R.layout.item_info,datas) {
            @Override
            public void convert(ViewHolder holder, String s) {
                holder.setText(R.id.id_info, mapTitle.get(s));
            }
        };
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                switch (o.toString()){
                    case "ContentProviderActivity":
                        Intent intentContentProvider=new Intent(App.getInstance().getContext(), ContentProviderActivity.class);
                        startActivity(intentContentProvider);
                        break;
                    case "BroadcastReceiverActivity":
                        Intent intentBroadcastReceiver=new Intent(App.getInstance().getContext(), BroadcastReceiverActivity.class);
                        startActivity(intentBroadcastReceiver);
                        break;
                    case "ServiceActivity":
                        Intent intentServiceActivity=new Intent(App.getInstance().getContext(), ServiceActivity.class);
                        startActivity(intentServiceActivity);
                        break;
                    case "DownLoadServiceActivity":
                        Intent intentDowdLoadActivity=new Intent(App.getInstance().getContext(), DownLoadServiceActivity.class);
                        startActivity(intentDowdLoadActivity);
                        break;
                    case "IntentActivity":
                        Intent intentActivity=new Intent(App.getInstance().getContext(), IntentActivity.class);
                        startActivity(intentActivity);
                        break;
                    case "ScanImageActivity":
                        Intent intentScanImage=new Intent(App.getInstance().getContext(), ScanImageActivity.class);
                        startActivity(intentScanImage);
                        break;

                    case "PluginActivity":
                        Intent intentPlugin=new Intent(App.getInstance().getContext(), PluginActivity.class);
                        startActivity(intentPlugin);
                        break;

                }
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
        rlvMain.setAdapter(adapter);
    }

    private void initView(View view) {
        ButterKnife.bind(this,view);

        rlvMain.setLayoutManager(new LinearLayoutManager(App.getInstance().getContext()));

        Bundle bundle = getArguments();
        String string = bundle.getString(KEY);
        tvDesc.setText(string);

        datas=new ArrayList<>();
        mapTitle =new HashMap<>();

        datas.add("ContentProviderActivity");
        mapTitle.put("ContentProviderActivity","ContentProvider获取手机通讯录");
        App.addData(new HashSetSearchBean("ContentProviderActivity",ContentProviderActivity.class));


        datas.add("BroadcastReceiverActivity");
        mapTitle.put("BroadcastReceiverActivity","BroadcastReceiver广播的基本应用");
        App.addData(new HashSetSearchBean("BroadcastReceiverActivity",BroadcastReceiverActivity.class));

        datas.add("ServiceActivity");
        mapTitle.put("ServiceActivity","Service服务的基本应用");
        App.addData(new HashSetSearchBean("ServiceActivity",ServiceActivity.class));

        datas.add("DownLoadServiceActivity");
        mapTitle.put("DownLoadServiceActivity","断点续传效果");
        App.addData(new HashSetSearchBean("IntentActivity",DownLoadServiceActivity.class));

        datas.add("IntentActivity");
        mapTitle.put("IntentActivity","Intent用法");
        App.addData(new HashSetSearchBean("IntentActivity",IntentActivity.class));

        datas.add("ScanImageActivity");
        mapTitle.put("ScanImageActivity","仿微信多图选择");
        App.addData(new HashSetSearchBean("ScanImageActivity",ScanImageActivity.class));

        datas.add("PluginActivity");
        mapTitle.put("PluginActivity","插件化加载资源");
        App.addData(new HashSetSearchBean("PluginActivity",PluginActivity.class));
    }

    private void addDB() {
        for (String name:datas){
            SearchBean searchBean=new SearchBean();
            searchBean.setActivity(name);
            searchBean.setTitle(mapTitle.get(name));
            manager.insert(searchBean);
        }
    }
}
