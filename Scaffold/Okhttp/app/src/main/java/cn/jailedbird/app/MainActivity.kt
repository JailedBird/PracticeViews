package cn.jailedbird.app

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import cn.jailedbird.core.common.utils.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.Socket
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.cert.X509Certificate


class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    @Suppress("PrivatePropertyName")
    private val JSON: MediaType = "application/json".toMediaType()

    interface CategoryService {
        @POST("category/{cat}/")
        fun categoryList(@Path("cat") a: String?, @Query("page") b: Int): Call<String>
    }

    fun handlerTest() {
        @Suppress("ObjectLiteralToLambda")
        val handler = Handler(Looper.getMainLooper(), object : Handler.Callback {
            override fun handleMessage(msg: Message): Boolean {
                println("Message is $msg")
                return true
            }

        })
        handler.sendMessage(Message.obtain())

    }

    fun retrofitTest() {
        val retrofit =
            Retrofit.Builder().baseUrl("https://www.baidu.com").addConverterFactory(null).build()
        val service = retrofit.create(CategoryService::class.java)
        val call = service.categoryList("a", 1)
        call.enqueue(object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>, response: retrofit2.Response<String>) {
                // 默认会切换到主线程
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // 默认会切换到主线程
            }

        })
    }

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

    fun main() {
        try {
            // 获取系统信任的证书
            val trustManagerFactory = javax.net.ssl.TrustManagerFactory.getInstance(
                javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm()
            )
//            trustManagerFactory.init(null)

            val systemTrustedCerts =
                (trustManagerFactory.trustManagers[0] as javax.net.ssl.X509TrustManager).acceptedIssuers

            val trustManager = object : javax.net.ssl.X509TrustManager {
                override fun checkClientTrusted(
                    x509Certificates: Array<X509Certificate>,
                    s: String
                ) {
                    // Do nothing, just accept any client certificates
                }

                @RequiresApi(Build.VERSION_CODES.O)
                override fun checkServerTrusted(
                    x509Certificates: Array<X509Certificate>,
                    s: String
                ) {
                    try {
                        // 在这里进行自定义的证书验证逻辑
                        val expectedCertHash = "sha256/expected_hash_value"

                        val serverCert = x509Certificates[0] // 假设服务器只返回一个证书
                        val sha256 = MessageDigest.getInstance("SHA-256")
                        val encodedCert = serverCert.encoded
                        val hashedCert = sha256.digest(encodedCert)
                        val certHash =
                            "sha256/" + java.util.Base64.getEncoder().encodeToString(hashedCert)

                        // 检查服务器证书的哈希值是否与预期值匹配
                        if (serverCert != null && expectedCertHash != certHash) {
                            println("Server certificate does not match expected hash.")
                        }
                    } catch (e: NoSuchAlgorithmException) {
                        throw RuntimeException(e)
                    }
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return systemTrustedCerts
                }
            }

            // 创建普通Socket连接
            val socket = Socket("example.com", 443)

            // 创建SSLSocket
            val sslContext = javax.net.ssl.SSLContext.getInstance("TLS")
            sslContext.init(null, arrayOf(trustManager), null)

            val sslSocketFactory =
                javax.net.ssl.SSLSocketFactory.getDefault() as javax.net.ssl.SSLSocketFactory

            val sslSocket = sslSocketFactory.createSocket(
                socket, "example.com", 443, true
            ) as javax.net.ssl.SSLSocket

            // 构建GET请求
            val request = "GET /path/to/resource HTTP/1.1\r\n" +
                    "Host: example.com\r\n" +
                    "Connection: close\r\n\r\n"

            // 发送请求
            val outputStream: OutputStream = sslSocket.getOutputStream()
            outputStream.write(request.toByteArray())
            outputStream.flush()

            // 读取响应
            val reader = BufferedReader(InputStreamReader(sslSocket.getInputStream()))
            val response = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line).append("\n")
            }
            // 输出响应体
            println(response.toString())
            // 关闭连接
            sslSocket.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}