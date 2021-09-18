plugins {
    id("com.github.johnrengelman.shadow") version ("7.0.0")
    kotlin("jvm") version ("1.5.21")
    java
    `maven-publish`
}

group = "io.github.awesomemoder316.spigottemplate"
version = "1.17.1-2"

repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    compileOnly("com.github.awesomemoder316:ModersLib-Api:1.17.1-3")
}

tasks.compileKotlin {
    kotlinOptions.jvmTarget = "11"
}

tasks.compileJava {
    sourceCompatibility = "11"
    targetCompatibility = "11"
}

artifacts.archives(tasks.shadowJar)

tasks.shadowJar {
    archiveFileName.set(rootProject.name + "-" + rootProject.version + ".jar")

    relocate("dev.triumphteam", "io.github.awesomemoder316.lib.api")
    relocate("kotlin", "io.github.awesomemoder316.lib.dependencies")
    relocate("net.kyori", "io.github.awesomemoder316.lib.api")
    relocate("net.wesjd", "io.github.awesomemoder316.lib.api")
    relocate("org.bstats", "io.github.awesomemoder316.lib.api")
}
