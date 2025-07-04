plugins {
    id("java")
}

group = "com.jit"
version = "5.3.39-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
	api(project(":spring-context"))
	api(project(":spring-core"))
}

tasks.test {
    useJUnitPlatform()
}