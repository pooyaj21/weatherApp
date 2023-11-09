plugins {
    kotlin("jvm") version "1.9.0"
    application
    id("VersionUpdatePlugin")
}

group = "org.example"


dependencies {
    implementation(project(":model"))
    implementation(project(":domain"))
    testImplementation(kotlin("test"))
    implementation(kotlin("reflect"))
    implementation(libs.coroutine)
    implementation(libs.zxing)
    implementation(libs.koin)
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