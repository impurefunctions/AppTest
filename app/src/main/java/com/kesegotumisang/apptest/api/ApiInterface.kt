package com.kesegotumisang.apptest.api

import com.kesegotumisang.apptest.models.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {
    @GET("mostpopular/v2/mostviewed/{section}/{period}.json")
    fun getNewsDetails(
        @Path("section") section: String?, @Path("period") period: String?,
        @Query("api-key") apiKey: String?
    ): Call<ApiResponse?>?
}
