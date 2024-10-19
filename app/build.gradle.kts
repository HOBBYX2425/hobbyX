plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.hobbyx"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.hobbyx"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation("com.airbnb.android:lottie:4.2.2")
    implementation(libs.constraintlayout)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.appcompat.v170)
    implementation(libs.material.v1120)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.firestore.v2500)
    implementation(libs.firebase.storage)
    implementation(libs.room.common)
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation(libs.firebase.messaging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.v121)
    androidTestImplementation(libs.espresso.core.v361)
    implementation(libs.ccp)
    implementation(libs.okhttp)
    implementation(platform(libs.firebase.bom))
    implementation(libs.google.firebase.auth)
    implementation(libs.firebase.ui.firestore)
    implementation(libs.circleimageview)


    //dependencies {
    //    // FirebaseUI for Firebase Realtime Database
    //    implementation(libs.firebase.ui.database)
//
    //    // FirebaseUI for Cloud Firestore
    //    implementation(libs.firebase.ui.firestore)
//
    //    // FirebaseUI for Firebase Auth
    //    implementation(libs.firebase.ui.auth)
//
    //    // FirebaseUI for Cloud Storage
    //    implementation(libs.firebase.ui.storage)
    //    implementation(libs.imagepicker)
    //    implementation(libs.imagepicker)
        implementation(libs.glide)
//
    //    implementation(libs.firebase.ui.firestore.v802)
//
//
    //    implementation(libs.circleimageview)
//
    //    implementation(libs.dexter)
    //    implementation(libs.room.runtime)
    //    annotationProcessor(libs.room.compiler)
/////////////////
    //    implementation(libs.ucrop)
    //    //////////
    //    implementation(libs.ucrop.v228native)
    //    ///
    //    //implementation("com.github.krokyze:ucropnedit:2.2.6-2")
    //    /////////map
    //    implementation(libs.play.services.maps.v1820)
//
    //    implementation(libs.play.services.location.v2120)
//
    //    implementation(libs.firebase.database)
//
    //    implementation(platform(libs.firebase.bom.v3281))
//
    //    implementation(libs.imageslideshow)
//
    //    implementation(libs.lottie)
    //    implementation(libs.okhttp)
        implementation(libs.sdp.android)
    //    implementation(libs.motiontoast)
     // Замените на последнюю версию
    annotationProcessor(libs.compiler) // Для обработки аннотаций (если требуется)

//
    //}
}