plugins {
    id("java")
    kotlin("jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(project(":service"))
    implementation(project(":model"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1") // Use the latest version of kotlinx-coroutines-test

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}