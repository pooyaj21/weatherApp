package com

import org.gradle.api.Plugin
import org.gradle.api.Project

 class TestConvention: Plugin<Project>{
    override fun apply(project: Project) {
        project.dependencies.apply {
            add("implementation", "org.jetbrains.kotlin:kotlin-test:1.5.21")
            add("testImplementation",project.dependencies.platform("org.junit:junit-bom:5.9.2"))
            add("testImplementation", "org.junit.jupiter:junit-jupiter:5.9.2")
            add("testImplementation", "io.mockk:mockk:1.13.4")
            add("testImplementation", "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
        }
    }

}
