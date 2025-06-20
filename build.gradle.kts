import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.testing.Test
import org.gradle.jvm.toolchain.JvmVendorSpec

plugins {
    java
}

allprojects {
    group = "net.codesapien.callerble"
    version = "1.0.0-SNAPSHOT"
}

subprojects {
    
    apply(plugin = "java")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
            vendor.set(JvmVendorSpec.ADOPTIUM)
        }
    }

    tasks.withType<JavaCompile> {
        options.release.set(21)
        options.compilerArgs.addAll(listOf("--enable-preview"))
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        jvmArgs("--enable-preview")
    }
} 