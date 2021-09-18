plugins {
    id("com.github.johnrengelman.shadow") version ("7.0.0")
    id("kr.entree.spigradle") version ("2.2.4")
    kotlin("jvm") version ("1.5.30")
}

group = "io.github.awesomemoder316.heartssmp"
version = "1.0.0"

repositories {
    maven { url = uri("https://nexus.sirblobman.xyz/repository/public/") }
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
    mavenCentral()
}


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.30")
    compileOnly("com.SirBlobman.combatlogx:CombatLogX-API:10.0.0.0-SNAPSHOT")
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
}
tasks.compileKotlin {
    kotlinOptions.jvmTarget = "16"
}

tasks.compileJava {
    sourceCompatibility = "16"
    targetCompatibility = "16"
}

artifacts.archives(tasks.shadowJar)

tasks.shadowJar {
    archiveFileName.set(rootProject.name + "-" + rootProject.version + ".jar")
    relocate("kotlin", "io.github.awesomemoder316.heartssmp.dependencies")
}

spigot {
    authors = listOf("Awesomemoder316")
    apiVersion = "1.17"
    description = "A remake of HeartsSmp SMP's plugin."

    commands {
        create("withdraw") {
            description = "Withdraw hearts."
            usage = "/withdraw (number of hearts)"
        }

        create("restore") {
            description = "Restore back to 10 hearts"
            usage = "/restore (name of player)"
        }
    }

    permissions {
        create("heartssmp.admin") {
            description = "Gives admin permission for heartssmp."
            defaults = "op"
        }

        create("heartssmp.withdraw") {
            description = "Gives permission to withdraw hearts."
            defaults = "true"
        }
    }
}