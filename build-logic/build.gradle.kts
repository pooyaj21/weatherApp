plugins {
    `kotlin-dsl`
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_19
    sourceCompatibility = JavaVersion.VERSION_19
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
}

gradlePlugin{
    plugins{
        register("TestConvention"){
            id = "TestConvention"
            implementationClass ="com.TestConvention"
        }
    }
}

