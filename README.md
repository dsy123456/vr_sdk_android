# android 对接VR文档
**一.添加依赖：**

1.在project的build.gradle中添加jitpack仓库支持
```java
allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
  }

```
2.在module的build.gradle中添加依赖的引用
```java
dependencies {
    implementation 'com.github.2628748861:vr_sdk_android:1.0.0'
  }
```

**二.集成步骤：**

 1.添加权限
 ```java
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.RECORD_AUDIO" />
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />
    <uses-permission android:name="android.permission.BLUETOOTH" />
    <uses-feature android:name="android.hardware.camera" />
    <uses-feature android:name="android.hardware.camera.autofocus" />

 ```
 
 2.在defaultConfig中,指定App使用的CPU架构.
  ```java
  defaultConfig {
        ndk {
        abiFilters "armeabi-v7a"
      }
    }
  ```
  
3.新建Activity 继承 VrActivity
```java
public class MyVrActivity extends WxbVrActivity {
        /**
        * 微信邀请好友回调
        * */
        @Override
        public void onVrInviteShareToWeChat() {

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

            }
        /**
         * 进入房间进入成功  加载成功进入vr界面能进行同屏带看
        * */
        @Override
        public void onEnterRoomSuccess() {

            }
        /**
        * 进入房间进入失败  不能进行同屏带看
        * */
        @Override
        public void onEnterRoomFail() {

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
            Intent intent = new Intent(mContext, clasz);
            Bundle bundle = new Bundle();
            bundle.putParcelable("data", vrParams);
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        }
    }

```

4.注册AndroidManifest.xml
```java
<application>
        <activity android:name=".MyVrActivity">

        </activity>
      </aplication>
```


 5.代码混淆
 ```java
 #vr配置
    -keep class com.just.agentweb.** {
    *;
    }
    -dontwarn com.just.agentweb.**
    -keepclassmembers class com.wangxiaobao.vr_sdk_android.WxbVrActivity.AndroidInterface{ *; }
    #语音配置
    -keep class com.tencent.** { *; }
    # 保留Parcelable序列化类不被混淆
    -keep class * implements android.os.Parcelable {
      public static final android.os.Parcelable$Creator *;
    }
 ```