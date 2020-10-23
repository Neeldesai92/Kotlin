package com.neel.desai.workdemo.retrofit


import com.neel.desai.workdemo.model.Matches
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {

    @GET("/api/")
    fun getMatch(@Query("results") page: String): Call<Matches>
}