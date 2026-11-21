plugins {
    java
    id("com.gradleup.shadow") version "8.3.3"
}

group = "com.github.nutt1101"
version = "2.0.1"
description = "CatchBall"
java.toolchain.languageVersion = JavaLanguageVersion.of(25)

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }
    maven {
        url = uri("https://repo.codemc.io/repository/maven-public/")
    }
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    mavenCentral()
}

dependencies {
    implementation("de.tr7zw:item-nbt-api:2.15.7")
    compileOnly("io.papermc.paper:paper-api:26.3.build.249-stable")
    compileOnly("me.clip:placeholderapi:2.11.6")
    compileOnly(files("./libs/Residence5.1.4.3.jar"))
}

listOf("compileClasspath", "runtimeClasspath").forEach { name ->
    configurations.named(name) {
        attributes {
            attribute(TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE, 25)
        }
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(21)
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.shadowJar {
    archiveFileName.set("CatchBall-${project.version}.jar")
    minimize()
}

tasks.jar {
    archiveFileName.set("CatchBall-${version}-original.jar")
}
