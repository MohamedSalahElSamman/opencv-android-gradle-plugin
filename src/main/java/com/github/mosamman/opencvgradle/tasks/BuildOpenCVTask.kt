package com.github.mosamman.opencvgradle.tasks


import com.github.mosamman.opencvgradle.dependencies.AndroidOpenCvKoin
import com.github.mosamman.opencvgradle.domain.usecases.GetReleaseArtifact
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider

import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.inject

@Suppress("MemberVisibilityCanBePrivate", "UnstableApiUsage")
open class BuildOpenCVTask : DefaultTask(), KoinComponent {

    init {
        loadKoinModules(AndroidOpenCvKoin.modules)
    }


    @Input
    val opencvVersion: Property<String> = project.objects.property(String::class.java)

    // Read-only property calculated from the greeting
    @Internal
    val version: Provider<String> = opencvVersion.map { "v$it" }


    @TaskAction
    fun checkVersion()  {

        val getReleases: GetReleaseArtifact by inject()

        if (version.get() == "v4.1.1")
            println("version is correct")
        else
            println("wrong version number")

        val path = project.rootDir.absoluteFile

        println(path)

        runBlocking {
            getReleases.execute().forEach {
                println(it.tagName)
            }
        }

    }


}