import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.8.21"
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
    implementation("org.jetbrains.kotlin:kotlin-reflect")
//  Spring web
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.6")
//  Spring security
    implementation("org.springframework.boot:spring-boot-starter-security:3.0.6")
//  Spring data jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.6")
//  Jwt
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
//  Postgres
    implementation("org.postgresql:postgresql:42.5.4")
//  Modulith
    implementation("org.springframework.experimental:spring-modulith-core:0.5.1")
    implementation("org.springframework.experimental:spring-modulith-docs:0.5.1")
//  Utils
    implementation("org.modelmapper:modelmapper:3.1.1")
//  Tests
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