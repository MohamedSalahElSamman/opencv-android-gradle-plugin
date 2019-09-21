package com.github.mosamman.opencvgradle

import com.github.mosamman.opencvgradle.dependencies.AndroidOpenCvKoin
import com.github.mosamman.opencvgradle.tasks.BuildOpenCVTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AndroidOpenCV : Plugin<Project> {

    companion object {
        init {
            startKoin {
                module {
                    AndroidOpenCvKoin.modules
                }
            }
        }
    }

    override fun apply(project: Project) {


        project.tasks.create("checkopencv", BuildOpenCVTask::class.java)
    }
}