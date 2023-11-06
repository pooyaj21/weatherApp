plugins {
    id("java")
    kotlin("jvm") version "1.9.0"
    id("TestConvention")
}

group = "org.example"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":model"))
    implementation(project(":data"))
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}