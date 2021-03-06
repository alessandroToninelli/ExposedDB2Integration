@file:Suppress("UnstableApiUsage")

package org.jetbrains.exposed.gradle

import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPom
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.plugins.signing.SigningExtension
import org.gradle.api.plugins.JavaPluginExtension


infix fun <T> Property<T>.by(value: T) {
    set(value)
}

fun MavenPom.configureMavenCentralMetadata(project: Project) {
    name by project.name
    description by "Exposed, an ORM framework for Kotlin"
    url by "https://github.com/JetBrains/Exposed"

    licenses {
        license {
            name by "The Apache Software License, Version 2.0"
            url by "https://www.apache.org/licenses/LICENSE-2.0.txt"
            distribution by "repo"
        }
    }

    developers {
        developer {
            id by "alessandroToninelli"
            name by "Alessandro"
            email by "alessandro.toninelli@toninelli.it"
        }
    }

}

fun MavenPublication.signPublicationIfKeyPresent(project: Project) {
    val keyId = System.getenv("exposed.sign.key.id")
    val signingKey = System.getenv("exposed.sign.key.private")
    val signingKeyPassphrase = System.getenv("exposed.sign.passphrase")
    if (!signingKey.isNullOrBlank()) {
        project.extensions.configure<SigningExtension>("signing") {
            useInMemoryPgpKeys(keyId, signingKey.replace(" ", "\r\n"), signingKeyPassphrase)
            sign(this@signPublicationIfKeyPresent)
        }
    }
}

fun Project._publishingLocalTon(configure: PublishingExtension.() -> Unit) {
    extensions.configure("publishing", configure)
}

fun Project._java(configure: JavaPluginExtension.() -> Unit) {
    extensions.configure("java", configure)
}
