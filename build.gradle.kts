import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
}

group = "com.egecube"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin libs
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Spring Framework
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework:spring-webmvc:6.0.8")

    // Spring Data
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("org.springframework.data:spring-data-jpa:3.0.3")

    // Modulith
    implementation("org.springframework.experimental:spring-modulith-core:0.5.1")
    implementation("org.springframework.experimental:spring-modulith-docs:0.5.1")

    // Test libs
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
