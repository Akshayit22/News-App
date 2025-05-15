import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "1.9.10"
    id("kotlin-kapt")
}

android {
    namespace = "com.aks.hotnews"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.aks.hotnews"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField("String", "API_BASE_URL", properties.getProperty("API_BASE_URL"))
        buildConfigField("String", "API_KEY", properties.getProperty("API_KEY"))
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
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

    val compose_version = "1.8.0"
    implementation("androidx.compose.material3:material3:1.3.2")

    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")


    val kotlin_redux_ver = "0.5.5"

    implementation("org.reduxkotlin:redux-kotlin-threadsafe-jvm:$kotlin_redux_ver")
    implementation("org.reduxkotlin:redux-kotlin-thunk:$kotlin_redux_ver")


    val ktorVersion = "2.3.7"
    val serializationVersion = "1.8.0"

    dependencies {
        implementation("io.ktor:ktor-client-core:$ktorVersion")
        implementation("io.ktor:ktor-client-cio:$ktorVersion")
        implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
        implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
    }

    val nav_version = "2.7.4"
    implementation("androidx.navigation:navigation-compose:$nav_version")

    val retrofir_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofir_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofir_version")

    val lifecycle_version = "2.8.7"
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

    val coil_version = "3.0.4"
    implementation("io.coil-kt.coil3:coil-compose:$coil_version")
    implementation("io.coil-kt.coil3:coil-network-okhttp:$coil_version")
    implementation("androidx.compose.runtime:runtime-livedata:1.7.6")

    val room = "2.7.0-alpha01"

    // Room
    implementation("androidx.room:room-runtime:$room")
    implementation("androidx.room:room-ktx:$room")
    kapt("androidx.room:room-compiler:$room")

}