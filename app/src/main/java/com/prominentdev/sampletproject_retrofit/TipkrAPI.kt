package com.prominentdev.sampletproject_retrofit

import android.content.Context
import android.util.Base64
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.io.IOException
import java.util.concurrent.TimeUnit

class TipkrAPI private constructor() {

    private var service: WeatherService? = null

    private var appContext: Context? = null

    private val authHeader = Interceptor { chain ->
        val newRequest: Request
        val original = chain.request()

        val requestBuilder = original.newBuilder()
        val userNamePass = "" + ":" + ""
        val encoded = String(Base64.encode(userNamePass.toByteArray(), Base64.NO_WRAP))

        requestBuilder.header("Authorization", "Basic $encoded")

        //if app has been logged in
        //  all the following apis will be attached user ref id as header
        //if not
        //  all the following apis will be attached client generated UUID as ref id as header
        /*String refId = UserManager.getUserRefId(appContext);
            if (!TextUtils.isEmpty(refId)) {
                // authenticated user
                requestBuilder.header("in-uref", refId);
            } else {
                //guest mode
                requestBuilder.header("in-guref", Utils.getUUID(appContext));
            }

            //in-app-version
            String inAppVersionHeader = "g." + Utils.getVersionName(appContext);
            requestBuilder.header("in-app-version", inAppVersionHeader);*/

        newRequest = requestBuilder.build()
        chain.proceed(newRequest)
    }

    fun init(context: Context, baseUrl: String, debug: Boolean) {

        this.appContext = context.applicationContext

        val builder = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(authHeader)

        if (debug) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(logging)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
        service = retrofit.create(WeatherService::class.java)
    }

    companion object {

        private var okHttpClient: OkHttpClient? = null

        fun cancel() {
            if (okHttpClient != null) {
                okHttpClient!!.dispatcher().cancelAll()
            }
        }
    }
}