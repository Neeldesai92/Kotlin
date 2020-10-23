package com.neel.desai.workdemo.retrofit

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestClient {


    companion object {
        var BaseUrl: String = "https://randomuser.me"
        var retrofitApp: Retrofit? = null

        var builderApp: Retrofit.Builder? =
            Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create())

        var httpClientApp: OkHttpClient.Builder =
            OkHttpClient.Builder().callTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        fun <S> createServiceApp(
            serviceClass: Class<S>?
        ): S? {
            httpClientApp.addInterceptor(logging)

            builderApp?.client(httpClientApp.build())
            if (retrofitApp == null) retrofitApp = builderApp?.build()

            return retrofitApp?.create(serviceClass)
        }

        private val logging =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                var message = message
                if (message.contains("{\"") || message.contains("FAILED") || message.contains(
                        BaseUrl
                    )
                ) {
                    if (message.startsWith("-->")) {
                        Log.i("API", "------------------------------------\n")

                        message = message.replace("--> POST", "-- INPUT : \n")
                    } else if (message.startsWith("<-- 200 OK")) {
                        message = "-- OUTPUT : "
                    }
                    Log.i("API", message)
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY)
    }


}