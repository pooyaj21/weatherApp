plugins {
    id("java")
    kotlin("jvm") version "1.9.0"
    id("TestConvention")
}

group = "org.example"
version = "1.0-SNAPSHOT"


dependencies {
    implementation(project(":service"))
    implementation(project(":model"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(libs.koin)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}