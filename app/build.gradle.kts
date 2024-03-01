import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.clipboarder.clipboarder"
    compileSdk = 34

    // Get security values from local.properties
    val localProperties = Properties()
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localProperties.load(localPropertiesFile.inputStream())
    }

    defaultConfig {
        applicationId = "com.clipboarder.clipboarder"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        localProperties["clipboarderBaseUrl"]?.let {value ->
            buildConfigField("String", "clipboarderBaseUrl", "\"$value\"")
        }
        localProperties["clipboarderServerClientId"]?.let { value ->
            buildConfigField("String", "clipboarderServerClientId", "\"$value\"")
        }
    }

    signingConfigs {
        create("release") {
            storeFile = localProperties["storeFile"]?.let { file(localProperties["storeFile"]!!) }
            storePassword = localProperties["storePassword"]?.let { localProperties["storePassword"]!! as String }
            keyAlias = localProperties["keyAlias"]?.let { localProperties["keyAlias"]!! as String}
            keyPassword = localProperties["keyPassword"]?.let { localProperties["keyPassword"]!! as String}
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    viewBinding {
        enable = true
    }
}

dependencies {
    // Default dependencies
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-process:2.7.0")

    // Additional dependencies
    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")             // Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")       // Retrofit Converter
    implementation("com.google.dagger:hilt-android:2.50")
    implementation("androidx.lifecycle:lifecycle-service:2.7.0")               // Dagger-Hilt
    ksp("com.google.dagger:hilt-android-compiler:2.50")                 // Hilt Compiler
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")       // Hilt Navigation
    implementation("com.google.android.gms:play-services-auth:21.0.0")  // Google Sign-In
    implementation("androidx.security:security-crypto:1.1.0-alpha06")   // Encrypted Shared Preferences
    implementation("com.squareup.okhttp3:okhttp:4.9.0")                 // OkHttp

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}