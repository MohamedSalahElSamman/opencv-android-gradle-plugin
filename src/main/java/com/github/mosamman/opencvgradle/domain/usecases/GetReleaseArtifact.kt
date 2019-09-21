package com.github.mosamman.opencvgradle.domain.usecases

import com.github.mosamman.opencvgradle.data.repository.RepositoryAccess
import com.github.mosamman.opencvgradle.domain.model.Release
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetReleaseArtifact(private val repo: RepositoryAccess) {
    suspend fun execute(): List<Release> = withContext(Dispatchers.Default) {
        try {
            repo.getReleasesList()
        } catch (e :Exception){
            println(e.message)
            println(e::class.java.canonicalName)
            emptyList<Release>()
        }
    }
}