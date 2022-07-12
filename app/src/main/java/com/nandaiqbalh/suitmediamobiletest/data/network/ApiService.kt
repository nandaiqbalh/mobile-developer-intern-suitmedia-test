package com.nandaiqbalh.suitmediamobiletest.data.network

import com.nandaiqbalh.suitmediamobiletest.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    fun getUserList() : Call<UserResponse>
}