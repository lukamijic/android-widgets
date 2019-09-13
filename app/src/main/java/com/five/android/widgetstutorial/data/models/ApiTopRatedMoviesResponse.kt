package com.five.android.widgetstutorial.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiTopRatedMoviesResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "results")
    val results: List<ApiTopRatedMovie>,
    @Json(name = "total_results")
    val totalResults: Int,
    @Json(name = "total_pages")
    val totalPages: Int
)