plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}

buildscript {
    dependencies {
        classpath(libs.navigation.safe.args.gradle.plugin)
        classpath(libs.kotlin.gradle.plugin)
    }
}

