package com.egecube.eduplatform

import org.junit.jupiter.api.Test
import org.springframework.modulith.core.ApplicationModules

internal class ModularityTest {
    private var modules = ApplicationModules.of(EduPlatformApplication::class.java)

    @Test
    fun verifyModularity() {
        modules.forEach(::println)
        modules.verify()
    }
}
