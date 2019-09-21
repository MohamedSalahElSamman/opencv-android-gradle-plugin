package com.github.mosamman.opencvgradle.data.repository

import com.github.mosamman.opencvgradle.data.repository.model.AssetResponse
import com.github.mosamman.opencvgradle.data.repository.model.ReleaseResponse
import com.github.mosamman.opencvgradle.domain.model.Asset
import com.github.mosamman.opencvgradle.domain.model.Release

object RepositoryMapper {

    fun fromResponse(response: ReleaseResponse) = Release(
			id = response.id,
			name = response.name,
			tagName = response.tagName,
			commitish = response.commitish,
			description = response.description,
			assets = response.assets.map { mapAsset(it) }
	)


    private fun mapAsset(assetResponse: AssetResponse) = Asset(
			url = assetResponse.url,
			id = assetResponse.id,
			name = assetResponse.name,
			label = assetResponse.label,
			contentType = assetResponse.contentType,
			size = assetResponse.size,
			progress = 0
	)
}