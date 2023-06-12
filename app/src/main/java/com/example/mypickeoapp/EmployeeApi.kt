package com.example.mypickeoapp


import retrofit2.Response
import retrofit2.http.GET

interface EmployeeApi {

    @GET("getEmployees.php")
    suspend fun getQues():Response<MaterialListResponse>

}