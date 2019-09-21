package com.github.mosamman.opencvgradle.data.repository.model

import com.google.gson.annotations.SerializedName

data class ReleaseResponse(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("tag_name")
        val tagName: String,
        @SerializedName("target_commitish")
        val commitish: String,
        @SerializedName("body")
        val description: String,
        @SerializedName("assets")
        val assets: List<AssetResponse>
)