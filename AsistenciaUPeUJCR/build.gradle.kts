// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript{
    dependencies{

        classpath("com.google.dagger:hilt-android-gradle-plugin:2.47") //cambiado 2.47 old 2.45
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    id("com.android.library") version "8.1.1" apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}