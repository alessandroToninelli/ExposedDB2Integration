import org.jetbrains.exposed.gradle.*
import java.io.FileInputStream
import java.util.*

apply(plugin = "java-library")
apply(plugin = "maven-publish")
apply(plugin = "signing")

_java {
    withJavadocJar()
    withSourcesJar()
}

//_publishing {
//    publications {
//        create<MavenPublication>("ExposedJars") {
//            artifactId = project.name
//            from(project.components["java"])
//            pom {
//                configureMavenCentralMetadata(project)
//            }
//            signPublicationIfKeyPresent(project)
//        }
//    }
//}

_publishingLocalTon {
    publications {
        create<MavenPublication>("ToninelliLibJars") {
            artifactId = project.name
            groupId = "toninelli"
            from(project.components["java"])
            pom {
                configureMavenCentralMetadata(project)
            }
        }
    }
    repositories {
        maven {
            // change URLs to point to your repos, e.g. http://my.org/repo
            val releasesRepoUrl = uri("http://repo.toninelli.it/repository/maven-releases/")
            isAllowInsecureProtocol = true
            url = releasesRepoUrl

            val prop = Properties().apply {
                load(FileInputStream(File(rootProject.rootDir, "local.properties")))
            }

            credentials {
                username = prop.getProperty("REPO.USERNAME")
                password = prop.getProperty("REPO.PASSWORD")
            }
        }
    }
}
