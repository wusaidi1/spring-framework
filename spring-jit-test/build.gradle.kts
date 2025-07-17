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
	implementation("org.mybatis:mybatis:3.4.5")
	implementation("mysql:mysql-connector-java:8.0.33")
	implementation("org.mybatis:mybatis-spring:1.3.2")
//	implementation("org.mybatis:mybatis-spring:2.0.6")
}

tasks.test {
    useJUnitPlatform()
}