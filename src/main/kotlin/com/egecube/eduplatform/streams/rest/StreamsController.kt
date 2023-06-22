package com.egecube.eduplatform.streams.rest

import com.egecube.eduplatform.streams.consts.StreamsRoutes
import com.egecube.eduplatform.streams.domain.Stream
import com.egecube.eduplatform.streams.dto.StreamDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class StreamsController(
    private val streamsService: StreamsService
) {

    @GetMapping(StreamsRoutes.STREAMS_ROUTE)
    fun getAvailableStreams(
        principal: Principal
    ): ResponseEntity<List<Stream>> {
        println(principal.name)
        val streams = streamsService.getTranslationsForUser()
        return if (streams.isNotEmpty()) {
            ResponseEntity.ok().body(streams)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping(StreamsRoutes.STREAMS_ROUTE)
    fun addNewStream(
        @RequestBody streamDto: StreamDto
    ): ResponseEntity<Long> {
        println(streamDto.common.assignedPersonIds)
        val newStreamId = streamsService.createTranslation(streamDto)
        return if (newStreamId != null) {
            ResponseEntity.ok().body(newStreamId)
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping(StreamsRoutes.STREAM_ROUTE)
    fun getStream(
        @PathVariable("id") streamId: Long
    ): ResponseEntity<Stream> {
        val stream = streamsService.getTranslationById(streamId)
        return if (stream != null) {
            ResponseEntity.ok().body(stream)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}