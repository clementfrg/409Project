plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.a409project"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.a409project"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file(project.findProperty("RELEASE_STORE_FILE") as String)
            storePassword = project.findProperty("RELEASE_STORE_PASSWORD") as String
            keyAlias = project.findProperty("RELEASE_KEY_ALIAS") as String
            keyPassword = project.findProperty("RELEASE_KEY_PASSWORD") as String
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
