import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    id("com.github.johnrengelman.shadow") version "5.2.0"
    id("java")
}

group = "dev.kosztadani.unrandom"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.ow2.asm:asm:8.0.1")
    implementation("org.ow2.asm:asm-tree:8.0.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
}

tasks.jar {
    manifest {
        attributes(
                mapOf("Premain-Class" to "dev.kosztadani.unrandom.Premain")
        )
    }
}

tasks.shadowJar {
    mergeServiceFiles()
    manifest {
        attributes(
                mapOf("Premain-Class" to "dev.kosztadani.unrandom.Premain")
        )
    }
}

tasks.register("testWithoutAgent", Test::class) {
    dependsOn("jar")
    useJUnitPlatform {
        excludeTags("useAgentWithoutArguments")
    }
    testLogging {
        events(PASSED, SKIPPED, FAILED)
    }
}

tasks.register("testWithAgent", Test::class) {
    dependsOn("jar")
    useJUnitPlatform {
        includeTags("useAgentWithoutArguments")
    }
    jvmArgs(listOf("-javaagent:build/libs/unrandom-${version}.jar"))
    testLogging {
        events(PASSED, SKIPPED, FAILED)
    }
}

tasks.test {
    dependsOn("testWithoutAgent", "testWithAgent")
}
