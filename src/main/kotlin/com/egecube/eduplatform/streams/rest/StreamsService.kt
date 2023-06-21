package com.egecube.eduplatform.streams.rest

import com.egecube.eduplatform.schedules.EventsRepository
import com.egecube.eduplatform.streams.StreamsRepository
import org.springframework.stereotype.Service

@Service
class StreamsService(
    private val eventsRepository: EventsRepository,
    private val streamsRepository: StreamsRepository
) {
    fun getEventDescriptionFromStreamId(streamId: Long): Long {
        return streamsRepository.findById(streamId).get().webinarId
    }
}