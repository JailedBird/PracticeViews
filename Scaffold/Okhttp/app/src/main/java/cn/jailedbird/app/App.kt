package cn.jailedbird.app

import android.app.Application
import cn.jailedbird.core.common.utils.log
import cn.jailedbird.core.settings.Settings
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        "App create".log()
        Settings.init(this)
    }
    
}