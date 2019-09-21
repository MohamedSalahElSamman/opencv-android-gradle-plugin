package com.github.mosamman.opencvgradle.dependencies

import com.github.mosamman.opencvgradle.domain.usecases.GetReleaseArtifact
import org.koin.dsl.module

object UseCasesModule {
    val get = module {
        factory(override = true) { GetReleaseArtifact(get()) }
    }
}