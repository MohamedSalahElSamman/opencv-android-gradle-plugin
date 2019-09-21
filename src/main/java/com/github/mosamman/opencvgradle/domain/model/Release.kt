package com.github.mosamman.opencvgradle.domain.model

data class Release(
        val id: Int,
        val tagName: String,
        val commitish: String,
        val name: String,
        val description: String,
        val assets: List<Asset>
)