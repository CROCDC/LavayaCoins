import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.cr.o.cdc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
}
plugins {
    id("org.springframework.boot") version "2.5.0-M1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.21-2"
    kotlin("plugin.spring") version "1.4.21-2"
    kotlin("jpa")
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")


    // start spring libraries
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // end spring libraries


    // start graphql libraries
    implementation("com.graphql-java:graphql-spring-boot-starter:5.0.2")
    implementation("com.graphql-java:graphiql-spring-boot-starter:5.0.2")
    implementation("com.graphql-java:graphql-java-tools:5.2.4")
    // end graphql libraries

    implementation("mysql:mysql-connector-java")
    implementation("org.postgresql:postgresql:42.2.2")

    implementation("com.auth0:java-jwt:3.4.0")
    implementation("io.jsonwebtoken:jjwt-api:0.11.2")


}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
