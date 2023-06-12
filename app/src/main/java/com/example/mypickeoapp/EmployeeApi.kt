package com.example.mypickeoapp


import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EmployeeApi {

    @FormUrlEncoded
    @POST("getEmployees.php")
    suspend fun getQues(
        @Field("username") userName: String
    ):Response<MaterialListResponse>

    @FormUrlEncoded
    @POST("login.php")
    suspend fun login(
        @Field("username") userName: String
    ): Response<String>

}