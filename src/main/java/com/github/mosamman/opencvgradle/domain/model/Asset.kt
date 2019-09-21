package com.github.mosamman.opencvgradle.domain.model


data class Asset(
        val url: String,
        val id: Int,
        val name: String,
        val label: String,
        val contentType: String,
        val size: Long,
        var progress: Long
)