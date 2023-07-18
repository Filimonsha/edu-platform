package com.egecube.eduplatform.modulith

import com.egecube.eduplatform.EduPlatformApplication
import org.junit.jupiter.api.Test
import org.springframework.modulith.core.ApplicationModules

class ModularityTest {
    private var modules = ApplicationModules.of(EduPlatformApplication::class.java)

    @Test
    fun verifyModularity() {
        modules.forEach(::println)
        modules.verify()
    }
}
