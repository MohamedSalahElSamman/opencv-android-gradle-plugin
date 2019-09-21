package com.github.mosamman.opencvgradle.data.repository.model

import com.google.gson.annotations.SerializedName

data class AssetResponse(
        @SerializedName("browser_download_url")
        val url: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("label")
        val label: String,
        @SerializedName("content_type")
        val contentType: String,
        @SerializedName("size")
        val size: Long
)