plugins {
    id("java")
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.9.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"


dependencies {

    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("test"))
    implementation(libs.coroutine)
    implementation(libs.retrofit)
    implementation(libs.serialization.converter)
    implementation(libs.okHttp)
    implementation(libs.okHttp.logging)
    implementation(libs.gson)
    implementation(libs.serialization)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.bundles.test)
    testImplementation(libs.mockwebserver)


}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}