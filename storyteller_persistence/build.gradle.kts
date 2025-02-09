plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}


rootProject.extra.apply {
    set("PUBLISH_GROUP_ID", "com.github.leandroborgesferreira")
    set("PUBLISH_ARTIFACT_ID", "storyteller-persistence")
    set("PUBLISH_VERSION", "0.1.1")
}

apply(from = "${rootDir}/scripts/publish-module.gradle")

android {
    namespace = "com.github.leandroborgesferreira.storyteller.persistence"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    publishing {
        singleVariant("release")
    }
}

kotlin{
    sourceSets.all {
        languageSettings {
            languageVersion = "2.0"
        }
    }
}

dependencies {
    implementation(project(":storyteller"))

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.room.testing)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.kotlinx.coroutines.test)
}
