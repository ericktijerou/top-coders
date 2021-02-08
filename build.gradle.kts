import java.io.FileInputStream

buildscript {
    repositories {
        google()
        jcenter()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(Dependencies.gradle)
        classpath(Dependencies.kotlinGradle)
        classpath(Dependencies.daggerHilt)
        classpath(Dependencies.ktLint)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

Globals.buildProperties.load(FileInputStream("build.properties"))