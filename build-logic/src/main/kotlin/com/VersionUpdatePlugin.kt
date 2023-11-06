package com

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class VersionUpdatePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val versionFile = project.file("assets/version.txt")
        val currentVersion = readVersionFromFile(versionFile) ?: 1.0
        val updatedVersion = currentVersion + 0.1

        project.version = updatedVersion.toString()

        writeVersionToFile(updatedVersion, versionFile)
    }

    private fun readVersionFromFile(file: File): Double? {
        return if (file.exists()) {
            file.readText().toDoubleOrNull()
        } else {
            null
        }
    }

    private fun writeVersionToFile(version: Double, file: File) {
        file.writeText(version.toString())
    }
}
