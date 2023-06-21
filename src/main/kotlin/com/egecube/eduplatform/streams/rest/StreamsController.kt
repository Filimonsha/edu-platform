package com.egecube.eduplatform.streams.rest

import com.egecube.eduplatform.streams.consts.StreamsRoutes
import com.egecube.eduplatform.streams.domain.Stream
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class StreamsController(
    private val streamsService: StreamsService
) {

    @GetMapping(StreamsRoutes.STREAMS_ROUTE)
    fun getAvailableStreams(): ResponseEntity<ArrayList<Stream>> {
        val streams = streamsService.getTranslationsForUser()
        return if (streams.isNotEmpty()) {
            ResponseEntity.ok().body(streams)
        } else {
            ResponseEntity.notFound().build()
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