package com.egecube.eduplatform

import org.junit.jupiter.api.Test
import org.springframework.modulith.core.ApplicationModules
import org.springframework.modulith.docs.Documenter

class RenderDocumentation {
    private var modules = ApplicationModules.of(EduPlatformApplication::class.java)

    @Test
    fun renderDocumentation() {
        val canvasOptions: Documenter.CanvasOptions = Documenter.CanvasOptions.defaults()
        val docOptions: Documenter.DiagramOptions = Documenter.DiagramOptions.defaults()
            .withStyle(Documenter.DiagramOptions.DiagramStyle.UML)
        Documenter(modules)
            .writeDocumentation(docOptions, canvasOptions)
    }
}