plugins {
    id("java")
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    id("TestConvention")
}

group = "org.example"
version = "1.0-SNAPSHOT"


dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(libs.coroutine)
    implementation(libs.retrofit)
    implementation(libs.serialization.converter)
    implementation(libs.okHttp)
    implementation(libs.okHttp.logging)
    implementation(libs.gson)
    implementation(libs.serialization)
    testImplementation(libs.mockwebserver)
    implementation(libs.koin)

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(20)
}