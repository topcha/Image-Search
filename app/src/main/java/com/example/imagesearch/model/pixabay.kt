package com.example.imagesearch.model


import com.google.gson.annotations.SerializedName

data class pixabay(
    @SerializedName("hits")
    val hits: List<Hit>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int
)