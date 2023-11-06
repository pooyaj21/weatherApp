package com

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class VersionUpdatePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val versionFile = project.file("version.txt")
        val currentVersion = readVersionFromFile(versionFile) ?: 1.0
        val updatedVersion = String.format("%.1f",currentVersion+0.1)

        project.version = updatedVersion

        writeVersionToFile(updatedVersion, versionFile)
    }

    private fun readVersionFromFile(file: File): Double? {
        return if (file.exists()) {
            file.readText().toDoubleOrNull()
        } else {
            null
        }
    }

    private fun writeVersionToFile(version: String, file: File) {
        file.writeText(version)
    }
}
