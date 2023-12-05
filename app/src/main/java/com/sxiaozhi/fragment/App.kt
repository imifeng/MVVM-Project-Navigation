package com.sxiaozhi.fragment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {

    companion object {
        private const val TAG = "App"

        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

//    fun initAnySDK(isReview: Boolean) {
//        initTTAdSDK(isReview)
//        initRegToWx()
//
//        initUmeng()
//    }

    // 穿山甲SDK初始化
//    private fun initTTAdSDK(isReview: Boolean) {
//        // 强烈建议在应用对应的Application#onCreate()方法中调用，避免出现content为null的异常
//        TTAdManagerHolder.init(this, isReview)
//    }
//
//    private fun initRegToWx() {
//        val api: IWXAPI = WXAPIFactory.createWXAPI(this, Constant.Weixin_AppID, true)
//        // 建议动态监听微信启动广播进行注册到微信
//        registerReceiver(object : BroadcastReceiver() {
//            override fun onReceive(context: Context?, intent: Intent?) {
//                // 将该app注册到微信
//                api.registerApp(Constant.Weixin_AppID)
//            }
//        }, IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP))
//    }
//
//    fun initUmeng() {
//        UMConfigure.setLogEnabled(BuildConfig.DEBUG)
//        val bundle = Bundle().apply {
//            putBoolean(UMCrash.KEY_ENABLE_CRASH_JAVA, true)
//            putBoolean(UMCrash.KEY_ENABLE_CRASH_NATIVE, true)
//            putBoolean(UMCrash.KEY_ENABLE_ANR, true)
//            putBoolean(UMCrash.KEY_ENABLE_PA, false)
//            putBoolean(UMCrash.KEY_ENABLE_LAUNCH, false)
//            putBoolean(UMCrash.KEY_ENABLE_MEM, false)
//        }
//        UMCrash.initConfig(bundle)
//
//        UMConfigure.init(
//            this,
//            UMConstant.UM_APP_KEY,
//            this.getApplicationMetaValue("UMENG_CHANNEL"),
//            UMConfigure.DEVICE_TYPE_PHONE,
//            null
//        )
//        // 选用AUTO页面采集模式
//        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
//        //
//        UMCrash.registerUMCrashCallback { "Crashed in App(${getString(R.string.app_name)})" }
//    }

}