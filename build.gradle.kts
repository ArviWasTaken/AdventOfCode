plugins {
    kotlin("jvm") version "1.9.23"
}

group = "nl.arviwastaken"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
    implementation("org.apache.httpcomponents:httpclient:4.5.14")
    implementation("org.jsoup:jsoup:1.17.2")

}

kotlin {
    jvmToolchain(21)
}