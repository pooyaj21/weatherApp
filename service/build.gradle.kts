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
    implementation(Deps.coroutine)
    implementation(Deps.retrofit)
    implementation(Deps.serialization_converter)
    implementation(Deps.okHttp)
    implementation(Deps.okHttp_logging)
    implementation(Deps.gson)
    implementation(Deps.serialization)
    testImplementation(platform(Deps.junit_bom))
    testImplementation(Deps.junit_jupiter)
    testImplementation(Deps.mockwebserver)
    testImplementation(Deps.mockk)
    testImplementation(Deps.coroutines_test)

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}