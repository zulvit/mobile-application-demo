plugins {
    alias(libs.plugins.android.application)
    id("androidx.navigation.safeargs")
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "ru.zulvit"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.zulvit"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.gson)
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation(libs.preference.ktx)
    runtimeOnly("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation(libs.navigation.fragment.ktx.v260)
    implementation(libs.navigation.ui.ktx.v260)
    implementation(libs.retrofit.kotlinx.serialization.converter)
    implementation(libs.kotlinx.serialization.json.v160)
    implementation(libs.recyclerview.v131)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    // kotlinx.serialization
    implementation(libs.kotlinx.serialization.json)
    //OkHttp
    implementation(libs.okhttp)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    // Kotlinx Serialization
    implementation(libs.kotlinx.serialization.json)
    // RecyclerView
    implementation(libs.recyclerview)
    implementation(libs.cardview)
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.fragment)
    implementation(libs.fragment.v155)
    implementation(libs.lifecycle.extensions)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
