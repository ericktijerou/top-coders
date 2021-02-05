object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val daggerHilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
    const val ktLint = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktLint}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Lifecycle {
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
}

object Hilt {
    const val daggerCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.daggerHiltAndroid}"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.daggerHiltAndroid}"
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
}

object Moshi {
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val codeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val moshiRetrofitConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
}

object OkHttp {
    const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
}

object Coroutines {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object Android {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object Room {
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
}

object Versions {
    internal const val appcompat = "1.2.0"
    internal const val constraintLayout = "2.0.4"
    internal const val gradle = "4.1.1"
    internal const val daggerHilt = "2.31-alpha"
    internal const val daggerHiltAndroid = "1.0.0-alpha02"
    internal const val kotlin = "1.4.30"
    internal const val coroutines = "1.4.2"
    internal const val materialDesign = "1.2.1"
    internal const val ktLint = "9.2.1"
    internal const val coreKtx = "1.3.2"
    internal const val activityKtx = "1.1.0"
    internal const val coil = "1.1.1"
    internal const val lifecycle = "2.2.0"
    internal const val retrofit = "2.9.0"
    internal const val moshi = "1.11.0"
    internal const val room = "2.2.6"
    internal const val okhttp = "4.9.1"
}
