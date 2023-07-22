package com.egecube.eduplatform.docs

import com.egecube.eduplatform.EduPlatformApplication
import org.junit.jupiter.api.Test
import org.springframework.modulith.core.ApplicationModules
import org.springframework.modulith.docs.Documenter

class ModuleCanvases {
    private val modules = ApplicationModules.of(EduPlatformApplication::class.java)
    private val docsPath = "modulith-docs"

    @Test
    fun writeCanvases() {
        Documenter(modules, docsPath)
            .writeModuleCanvases()
    }
}