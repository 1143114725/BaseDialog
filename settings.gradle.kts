pluginManagement {
    repositories {
        maven(url = "https://maven.aliyun.com/repository/google")
        maven(url = "https://maven.aliyun.com/repository/jcenter")
        maven(url = "https://maven.aliyun.com/repository/central")
        maven(url = "https://maven.aliyun.com/repository/public")
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        id("com.android.application") version "7.2.1"
        id("org.jetbrains.kotlin.android") version "1.7.10"
        // 其他你需要的插件
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/jcenter") }
        maven { url = uri("https://maven.aliyun.com/repository/central") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        gradlePluginPortal()
    }
}
enableFeaturePreview("VERSION_CATALOGS")
rootProject.name = "BaseDialog"
include(":app")
