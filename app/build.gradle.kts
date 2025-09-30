plugins {
    // Apply the shared build logic from a convention plugin.
    // The shared code is located in `buildSrc/src/main/kotlin/kotlin-jvm.gradle.kts`.
    id("buildsrc.convention.kotlin-jvm")

    // Apply the Application plugin to add support for building an executable JVM application.
    application
}

dependencies {
    // Project "app" depends on project "utils". (Project paths are separated with ":", so ":utils" refers to the top-level "utils" project.)
    implementation(project(":utils"))

    // RxJava Core
    implementation("io.reactivex.rxjava3:rxjava:3.1.12")
    implementation("io.reactivex.rxjava3:rxkotlin:3.0.1")

    // Http Client
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("com.squareup.okhttp3:okhttp:5.1.0")

    //JSON processing
    implementation("com.google.code.gson:gson:2.13.2")

    // Logging
    implementation("ch.qos.logback:logback-classic:1.5.18")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")

    //Testing
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:5.20.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:6.0.0")

    //For coroutines interop
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-rx3:1.10.2")

}

application {
    // Define the Fully Qualified Name for the application main class
    // (Note that Kotlin compiles `App.kt` to a class with FQN `com.example.app.AppKt`.)
    mainClass = "org.rx.app.AppKt"
}

// === Numbered scaffold for Rx learning path ===
tasks.register("scaffoldRxPackages") {
    // No base package → everything goes directly under src/main/kotlin
    val basePackage = ""
    val srcRootDir = layout.projectDirectory.dir("src/main/kotlin").asFile
    val basePath = if (basePackage.isBlank()) "" else basePackage.replace('.', '/') + "/"

    val packages = listOf(
        "01_observables","01_observables.01_basics","01_observables.02_subjects","01_observables.03_advanced",
        "02_flowables","02_flowables.01_basics","02_flowables.02_strategies","02_flowables.03_advanced",
        "03_transformers","03_transformers.01_mapping","03_transformers.02_grouping","03_transformers.03_advanced",
        "04_filters","04_filters.01_basic","04_filters.02_timing","04_filters.03_conditional",
        "05_merging","05_merging.01_combining","05_merging.02_concatenation","05_merging.03_switching",
        "06_aggregators","06_aggregators.01_mathematical","06_aggregators.02_collection","06_aggregators.03_advanced",
        "07_errorhandling","07_errorhandling.01_recovery","07_errorhandling.02_propagation","07_errorhandling.03_patterns",
        "08_schedulers","08_schedulers.01_basics","08_schedulers.02_advanced","08_schedulers.03_performance",
        "09_lifecycles","09_lifecycles.01_disposal","09_lifecycles.02_resources","09_lifecycles.03_patterns",
        "10_utilities","10_utilities.01_sideeffects","10_utilities.02_materialization","10_utilities.03_conversion",
        "11_testing","11_testing.01_basics","11_testing.02_patterns","11_testing.03_advanced",
        "12_coroutines","12_coroutines.01_interop","12_coroutines.02_patterns","12_coroutines.03_advanced",
        "13_geolocation","13_geolocation.01_basics","13_geolocation.02_monitoring","13_geolocation.03_optimization",
        "14_projects","14_projects.01_beginner","14_projects.02_intermediate","14_projects.03_advanced","14_projects.04_capstone",
        "15_utils"
    )

    val srcRootPath = srcRootDir.toPath()

    doLast {
        packages.forEach { p ->
            val dir = srcRootDir.resolve(basePath + p.replace('.', '/'))
            if (dir.mkdirs()) {
                val rel = srcRootPath.relativize(dir.toPath()).toString()
                println("Created: src\\main\\kotlin\\$rel")
            }
            dir.resolve(".gitkeep").apply { if (!exists()) createNewFile() }
        }
        println("✅ Done. Numbered folders created under: ${srcRootDir.absolutePath}")
    }
}
