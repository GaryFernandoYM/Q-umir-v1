plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt") //Agregado
    id("dagger.hilt.android.plugin") //Agregado
}

android {
    namespace = "pe.edu.upeu.asistenciaupeujcr"
    compileSdk = 34

    defaultConfig {
        applicationId = "pe.edu.upeu.asistenciaupeujcr"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            // arguments += ["room.schemaLocation":
            //"$projectDir/schemas".toString()]
            }
        }

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
        sourceCompatibility = JavaVersion.VERSION_17 //1_8
        targetCompatibility = JavaVersion.VERSION_17 //1_8
    }
    kotlinOptions {
        jvmTarget = "17" //1.8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("androidx.compose.material3:material3-window-size-class") //Agregado
    val appcompat_version = "1.6.1"
    implementation("androidx.appcompat:appcompat:$appcompat_version")//Agregado recien
    //agregado Navigation
    val navCompose = "2.7.7" //old 2.7.0-beta01
    implementation("androidx.navigation:navigation-compose:$navCompose")
    implementation ("com.google.accompanist:accompanist-insets:0.31.4-beta")//Agregado //0.17.0
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.31.4-beta") //0.16.0
    implementation ("com.google.accompanist:accompanist-navigation-animation:0.31.4-beta") //0.16.0
//Agregados
            implementation ("com.google.dagger:hilt-android:2.47")
            kapt ("com.google.dagger:hilt-compiler:2.47")
//Agregado Dagger - Hilt
            implementation ("com.google.dagger:hilt-android:2.47")
            kapt ("com.google.dagger:hilt-compiler:2.47")
            implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
            implementation("com.valentinilk.shimmer:compose-shimmer:1.0.5")
            implementation ("io.coil-kt:coil-compose:2.4.0")
//Otros adicionales compose
            implementation ("androidx.compose.ui:ui-tooling")
            implementation ("androidx.compose.foundation:foundation")
            implementation ("androidx.compose.runtime:runtime-livedata")
//Para trabajar con contraint layout
            implementation ("androidx.constraintlayout:constraintlayout:2.2.0-alpha10")
            implementation ("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha10")
//Para trabajar con camara
        val camerax_version = "1.2.3" //1.2.3
    implementation ("androidx.camera:camera-core:${camerax_version}")
    implementation ("androidx.camera:camera-camera2:${camerax_version}")
    implementation ("androidx.camera:camera-lifecycle:${camerax_version}")
    implementation ("androidx.camera:camera-view:${camerax_version}")

//Barcode
    implementation ("com.google.mlkit:barcode-scanning:17.1.0")
//Location
    implementation ("com.google.android.gms:play-services-location:21.0.1")
//Manager permissions
    implementation ("com.google.accompanist:accompanist-permissions:0.30.1")
//Moshi
    implementation ("com.squareup.moshi:moshi-kotlin:1.14.0")
    kapt ("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")
//Formularios
    implementation ("com.github.k0shk0sh:compose-easyforms:0.2.0")
// Room https://developer.android.com/jetpack/androidx/releases/room
    val room_version = "2.6.1"
    implementation ("androidx.room:room-runtime:$room_version")
    annotationProcessor ("androidx.room:room-compiler:$room_version")
// To use Kotlin annotation processing tool (kapt)
    kapt ("androidx.room:room-compiler:$room_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
// optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")
// Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
}