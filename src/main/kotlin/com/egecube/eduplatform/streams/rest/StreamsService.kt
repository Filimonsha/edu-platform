package com.egecube.eduplatform.streams.rest

import com.egecube.eduplatform.schedules.CommonEvent
import com.egecube.eduplatform.streams.StreamsRepository
import com.egecube.eduplatform.streams.domain.Stream
import com.egecube.eduplatform.streams.dto.StreamDto
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class StreamsService(
    private val streamsRepository: StreamsRepository
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

//    fun getEventDescriptionFromStreamId(streamId: Long): CommonEvent? {
//        return try {
//            val eventId = streamsRepository.findById(streamId).get().webinarId
//            eventsRepository.findById(eventId).get()
//        } catch (e: NoSuchElementException) {
//            null
//        }
//    }

    fun getTranslationsForUser(): ArrayList<Stream> {
        return streamsRepository.findAll() as ArrayList<Stream>
    }

    fun getTranslationsForGroupList() {}

    fun getTranslationById(eventId: Long): Stream? {
        return try {
            streamsRepository.findById(eventId).get()
        } catch (e: NoSuchElementException) {
            null
        }
    }

    fun createTranslation(streamDto: StreamDto): Long? {
        val newTranslation = Stream.build()
            .initCommonWithDto<Stream>(streamDto.common).also {
                it!!.externalLink = streamDto.externalLink
                it.webinarStatus = streamDto.webinarStatus
            }
        return try {
            val stream = streamsRepository.save(newTranslation!!)
            logger.info("Created new translation: ${stream.eventName}")
            stream.id
        } catch (e: IllegalArgumentException) {
            logger.warn("Unable to register stream")
            null
        }
    }
}