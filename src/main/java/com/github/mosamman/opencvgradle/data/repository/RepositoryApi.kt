package com.github.mosamman.opencvgradle.data.repository

import com.github.mosamman.opencvgradle.data.repository.model.ReleaseResponse
import okhttp3.ResponseBody
import retrofit2.http.*


interface RepositoryApi {

    @Streaming
    @Headers("Accept: application/octet-stream")
    @GET
    suspend fun downloadArtifact(@Url url: String): ResponseBody


    @GET(GithubURL.RELEASES_LIST)
    suspend fun getReleasesList(): List<ReleaseResponse>
}