import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow")
    id("io.micronaut.application")
}

micronaut {
    version(Versions.micronaut)
    processing {
        incremental(true)
        module(project.name)
        group(project.group.toString())
        annotations("com.polusharie.*")
    }
}

dependencies {
    // Micronaut
    implementation("io.micronaut:micronaut-inject")
    implementation("io.micronaut:micronaut-validation")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut:micronaut-http-server-netty")
    implementation("io.micronaut:micronaut-management")
    implementation("io.micronaut.security:micronaut-security-jwt")
    implementation("io.micronaut.graphql:micronaut-graphql")

    // libraries
    implementation("javax.annotation:javax.annotation-api")
    runtimeOnly("ch.qos.logback:logback-classic")
}

application {
    mainClassName = "com.polusharie.graphql.GraphQlApplication"
    mainClass.set(mainClassName)
}

tasks.withType<ShadowJar> {
    mergeServiceFiles()
}

tasks.withType<JavaExec> {
    jvmArgs("-XX:TieredStopAtLevel=1", "-Dcom.sun.management.jmxremote")
    if (gradle.startParameter.isContinuous) {
        systemProperties(mapOf(
                "micronaut.io.watch.restart" to "true",
                "micronaut.io.watch.enabled" to "true",
                "micronaut.io.watch.paths" to "src/main"
        ))
    }
}
