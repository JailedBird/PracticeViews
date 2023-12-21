package cn.jailedbird.app

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import cn.jailedbird.core.common.utils.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    @Suppress("PrivatePropertyName")
    private val JSON: MediaType = "application/json".toMediaType()

    fun post(url: String, json: String): String? {
        val body: RequestBody = RequestBody.create(JSON, json)
        val request: Request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        client.newCall(request).execute().use { response ->
            return response.body?.string()
        }
    }

    private fun run(@Suppress("SameParameterValue") url: String): String {
        val request: Request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).execute().use { response ->
            return response.body!!.string()
        }
    }

    private fun runSync(@Suppress("SameParameterValue") url: String) {
        val request: Request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                return response.body!!.string().log()
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main)

        findViewById<View>(R.id.btLaunch).setOnClickListener {
            launch()
        }

    }

    private fun launch() {
        lifecycleScope.launch(Dispatchers.IO) {
            run("https://www.baidu.com").log()
        }
    }
}