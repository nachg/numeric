plugins {
    kotlin("js") version "1.5.31"
}

group = "me.nikitachegodaev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers/")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-html:0.7.3")
}

kotlin {
    js {
        binaries.executable()
        browser {
            commonWebpackConfig {
                //cssSupport.enabled = true
            }
        }
    }
}