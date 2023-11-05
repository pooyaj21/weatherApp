object Versions {
    val jdk = "1.8.20"
    val kotlin = "1.9.0"
    val coroutine = "1.7.3"
    val zxing = "3.5.1"
    val junit = "5.9.2"
    val okHttp = "4.11.0"
    val mockk = "1.13.4"
    val retrofit = "2.9.0"
    val serialization_converter = "1.0.0"
    val gson = "2.8.9"
    val serialization = "1.6.0"
}


object Deps {
    val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.jdk}"
    val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.jdk}"
    val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    val zxing = "com.google.zxing:core:${Versions.zxing}"
    val junit_api = "org.junit.jupiter:junit-jupiter-api:${Versions.junit}"
    val junit_engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
    val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
    val junit_bom = "org.junit:junit-bom:${Versions.junit}"
    val junit_jupiter = "org.junit.jupiter:junit-jupiter:${Versions.junit}"
    val mockk = "io.mockk:mockk:${Versions.mockk}"
    val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutine}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val serialization_converter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.serialization_converter}"
    val okHttp_logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    val okHttp ="com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    val gson = "com.google.code.gson:gson:${Versions.gson}"
    val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
}