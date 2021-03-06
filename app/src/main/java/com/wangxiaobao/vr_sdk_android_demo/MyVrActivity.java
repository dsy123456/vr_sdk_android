package com.wangxiaobao.vr_sdk_android_demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wangxiaobao.vr_sdk_android.WxbVrActivity;
import com.wangxiaobao.vr_sdk_android.WxbVrParams;

public class MyVrActivity extends WxbVrActivity {
    /**
     * 微信邀请好友回调
     * */
    @Override
    public void onVrInviteShareToWeChat() {
        // TODO:  此处进行二维码分享处理
    }
    /**
     * VR语音房加入成功 在vr带看中能和用户正常聊天带看
     * */
    @Override
    public void onEnterVoiceRoomSuccess() {

    }
    /**
     * VR语音房加入失败 不能在vr带看中能和用户聊天带看
     * */
    @Override
    public void onEnterVoiceRoomFail() {
        Toast.makeText(MyVrActivity.this,"VR语音房加入失败,不能与客户进行语音聊天",Toast.LENGTH_SHORT).show();
    }
    /**
     * 房间进入成功  加载成功进入vr界面能进行同屏带看
     * */
    @Override
    public void onEnterRoomSuccess() {

    }
    /**
     * 房间进入失败  不能进行同屏带看
     * */
    @Override
    public void onEnterRoomFail() {
        Toast.makeText(MyVrActivity.this,"房间进入失败",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void resetNav(RelativeLayout nav_layout, ImageButton backBtn, TextView vr_title) {
        super.resetNav(nav_layout, backBtn, vr_title);
    }

    public static void go(Context mContext, Class<?> clasz, WxbVrParams vrParams) {
        if (vrParams == null) {
            Toast.makeText(mContext, "跳转失败,缺少必要参数", Toast.LENGTH_SHORT).show();
            return;
        }
        //跳转前需要进行动态相关动态权限申请
        Intent intent = new Intent(mContext, clasz);
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", vrParams);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }
}
