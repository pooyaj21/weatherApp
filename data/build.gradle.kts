plugins {
    id("java")
    kotlin("jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"


dependencies {
    implementation(project(":service"))
    implementation(project(":model"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation(kotlin("test"))
    testImplementation(platform(Deps.junit_bom))
    testImplementation(Deps.junit_jupiter)
    testImplementation(Deps.mockk)
    testImplementation(Deps.coroutines_test)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(20)
}