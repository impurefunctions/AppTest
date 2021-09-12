package com.kesegotumisang.apptest.models

import com.google.gson.annotations.SerializedName


class ApiResponse(
    @field:SerializedName("status") private val status: String, @field:SerializedName(
        "num_results"
    ) private val num_results: Int, results: List<Article>
) {
    @SerializedName("results")
    private val results: List<Article> = results
    fun getResults(): List<Article> {
        return results
    }

}
