import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        jcenter()
        mavenCentral()
    }
}

plugins {
    java
    idea
    id("com.github.johnrengelman.shadow") version Versions.gradleShadowPlugin apply false
    id("io.micronaut.application") version Versions.gradleMicronautPlugin apply false
    id("io.micronaut.library") version Versions.gradleMicronautPlugin apply false
    kotlin("jvm") version Versions.kotlin apply false
    kotlin("kapt") version Versions.kotlin apply false
    kotlin("plugin.allopen") version Versions.kotlin apply false
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

idea {
    module {
        isDownloadJavadoc = false
        isDownloadSources = false
    }
}

allprojects {
    repositories {
        mavenLocal()
        jcenter()
        mavenCentral()
        maven { url = uri("https://oss.jfrog.org/oss-snapshot-local") }
    }
}

subprojects {
    apply(plugin = "java")

    version = "0.1-SNAPSHOT"
    group = "com.polusharie"

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
        testCompileOnly {
            extendsFrom(configurations.testAnnotationProcessor.get())
        }
    }

    dependencies {
        // MapStruct
        annotationProcessor("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
        testAnnotationProcessor("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
    }

    tasks.withType<JavaCompile> {
        // FIX:
        // For queries with named parameters you need to use provide names for method parameters.
        // Use @Param for query method parameters, or when on Java 8+ use the javac flag -parameters.
        options.compilerArgs.add("-parameters")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform {
            includeEngines = setOf("junit-jupiter")
            excludeEngines = setOf("junit-vintage")
        }
    }
}
