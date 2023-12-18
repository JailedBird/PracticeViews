package cn.jailedbird.template.utils

import android.util.Log


fun String?.log(){
    Log.d("DEBUG", this?:"null")
}