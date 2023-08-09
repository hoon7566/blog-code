import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.14"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.spring") version "1.6.21"
}

group = "com.hoony"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {

    // webflux
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    // 코루틴
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    // serializer
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

    // r2dbc
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")

    // mysql r2dbc driver
    implementation("com.github.jasync-sql:jasync-r2dbc-mysql:2.2.1")



    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")

    // coroutines test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    // kotest test
    testImplementation("io.kotest:kotest-runner-junit5:5.3.2")
    testImplementation("io.kotest:kotest-assertions-core:5.3.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
