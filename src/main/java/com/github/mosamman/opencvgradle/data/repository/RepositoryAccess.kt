package com.github.mosamman.opencvgradle.data.repository

import com.github.mosamman.opencvgradle.domain.model.Asset
import com.github.mosamman.opencvgradle.domain.model.Release
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryAccess(private val api: RepositoryApi) {


    suspend fun getReleasesList(): List<Release> = withContext(Dispatchers.IO) {
        api.getReleasesList().map { RepositoryMapper.fromResponse(it) }
    }

    suspend fun downloadAsset(asset: Asset): Asset = withContext(Dispatchers.IO) {
        val url = "${GithubURL.ASSET_BASE}/${asset.id}"
        api.downloadArtifact(url)
        //TODO save to local file
        return@withContext asset
    }

}