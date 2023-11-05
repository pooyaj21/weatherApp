plugins {
    id("java")
    kotlin("jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"

dependencies {

    implementation(project(":model"))
    implementation(project(":data"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("test"))

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