plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":model"))
    implementation(project(":domain"))
    testImplementation(kotlin("test"))
    implementation(kotlin("reflect"))
    implementation(Deps.coroutine)
    implementation(Deps.zxing)
//    testImplementation(Deps.junit_api)
//    testRuntimeOnly(Deps.junit_engine)
//    testImplementation(Deps.mockwebserver)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(20)
}

application {
    mainClass.set("MainKt")
}