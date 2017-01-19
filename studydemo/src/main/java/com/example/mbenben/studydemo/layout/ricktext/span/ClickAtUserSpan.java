package com.example.mbenben.studydemo.layout.ricktext.span;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.example.mbenben.studydemo.layout.ricktext.listener.SpanAtUserCallBack;
import com.example.mbenben.studydemo.layout.ricktext.model.UserModel;

/**
 * 显示可点击的@某人
 * Created by shuyu on 2016/11/10.
 *
 * 原作者项目GitHub:https://github.com/CarGuo/RickText
 */
public class ClickAtUserSpan extends ClickableSpan {

    private Context context;
    private UserModel userModel;
    private SpanAtUserCallBack spanClickCallBack;
    private int color;

    public ClickAtUserSpan(Context context, UserModel userModel, int color, SpanAtUserCallBack spanClickCallBack) {
        this.context = context;
        this.userModel = userModel;
        this.spanClickCallBack = spanClickCallBack;
        this.color = color;
    }

    @Override
    public void onClick(View view) {
        if (spanClickCallBack != null) {
            spanClickCallBack.onClick(userModel);
        }
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        /** 给文字染色 **/
        ds.setColor(color);
        /** 去掉下划线 ， 默认自带下划线 **/
        ds.setUnderlineText(false);
    }

}