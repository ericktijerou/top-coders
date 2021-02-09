plugins {
    id("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("org.jlleitschuh.gradle.ktlint")
    id("com.apollographql.apollo").version("2.5.3")
    kotlin("kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId = "erick.tijerou.topcoders"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions {
            annotationProcessorOptions {
                arguments.plusAssign(
                        hashMapOf(
                                "room.schemaLocation" to "$projectDir/schemas",
                                "room.incremental" to "true",
                                "room.expandProjection" to "true"
                        )
                )
            }
        }
        val githubOauthKey = Globals.buildProperties["githubOauthKey"]
        buildConfigField("String", "GITHUB_OAUTH_TOKEN", "\"$githubOauthKey\"")
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            isTestCoverageEnabled = true

            buildConfigField(
                "String",
                "API_URL",
                "\"https://api.github.com/graphql\""
            )
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            isZipAlignEnabled = true
            proguardFile(getDefaultProguardFile("proguard-android.txt"))
            proguardFile(file("proguard-rules.pro"))

            buildConfigField(
                "String",
                "API_URL",
                "\"https://api.github.com/graphql\""
            )
        }
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    }

    buildFeatures.dataBinding = true
}

ktlint {
    android.set(true)
    outputColorName.set("RED")
}

apollo {
    generateKotlinModels.set(true)
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Kotlin
    implementation(Dependencies.kotlin)

    // Coroutines
    implementation(Coroutines.core)
    implementation(Coroutines.android)

    // Android
    implementation(Android.appcompat)
    implementation(Android.activityKtx)
    implementation(Android.coreKtx)
    implementation(Android.constraintLayout)
    implementation(Android.refreshLayout)

    // Architecture Components
    implementation(Lifecycle.viewModel)
    implementation(Lifecycle.liveData)

    // Room components
    implementation(Room.runtime)
    implementation(Room.ktx)
    kapt(Room.compiler)

    // Material Design
    implementation(Dependencies.materialDesign)

    // Coil-kt
    implementation(Dependencies.coil)

    // Apollo
    implementation(Apollo.apollo)
    implementation(Apollo.apolloCoroutines)

    // Paging
    implementation(Paging.paging)

    // OkHttp
    implementation(OkHttp.okHttpInterceptor)

    // Moshi
    implementation(Moshi.moshi)
    implementation(Moshi.codeGen)
    kapt(Moshi.codeGen)

    // Navigation
    implementation(Navigation.navigation)
    implementation(Navigation.navigationKtx)

    // Hilt + Dagger
    implementation(Hilt.hiltAndroid)
    implementation(Hilt.hiltViewModel)
    kapt(Hilt.daggerCompiler)
    kapt(Hilt.hiltCompiler)
}