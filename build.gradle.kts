plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"


dependencies {
    implementation(project(":model"))
    implementation(project(":domain"))
    testImplementation(kotlin("test"))
    implementation(kotlin("reflect"))
    implementation(Deps.coroutine)
    implementation(Deps.zxing)
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