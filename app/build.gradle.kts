plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.dagger.hilt.android")
    id ("kotlin-kapt")
    id ("io.realm.kotlin")
}

android {
    namespace = "com.android.mongodbcrud"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.android.mongodbcrud"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
    // for Local realm SDK
    implementation ("io.realm.kotlin:library-base:1.16.0")
    // hilt-setup
    implementation("com.google.dagger:hilt-android:2.52")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    // Dependencies for viewModels() delegate
    implementation("androidx.fragment:fragment-ktx:1.8.3")
    implementation("androidx.activity:activity-ktx:1.9.2")
}