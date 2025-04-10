plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
//    id("com.google.devtools.ksp") version "2.1.10-1.0.31"
}

//ksp {
//    arg(RoomSchemaArgProvider(File(projectDir, "schemas")))
//}

android {
    namespace = "com.example.masterkit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.masterkit"
        minSdk = 24
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
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "2.0.0"
    }
}



//class RoomSchemaArgProvider(
//    @get:InputDirectory
//    @get:PathSensitive(PathSensitivity.RELATIVE)
//    val schemaDir: File
//) : CommandLineArgumentProvider {
//
//    override fun asArguments(): Iterable<String> {
//        // Note: If you're using KAPT and javac, change the line below to
//        // return listOf("-Aroom.schemaLocation=${schemaDir.path}").
//        return listOf("room.schemaLocation=${schemaDir.path}")
//    }
//}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    // 添加 KotlinX 协程依赖
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.activity:activity-ktx:1.6.1")
    implementation("com.google.accompanist:accompanist-permissions:0.34.0")

    val composeBom = platform("androidx.compose:compose-bom:2024.10.01")
    implementation(composeBom)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.animation)
    implementation(libs.androidx.activity.compose)

    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.runtime.livedata)
    // compose 预览功能
    implementation(libs.androidx.ui.tooling.preview)
    implementation(project(":MasterCode"))
    implementation(project(":base"))
    implementation(project(":db"))

//    implementation("androidx.room:room-ktx:2.6.1")
//    implementation("androidx.room:room-runtime:2.6.1")
//    ksp("androidx.room:room-compiler:2.6.1")
}