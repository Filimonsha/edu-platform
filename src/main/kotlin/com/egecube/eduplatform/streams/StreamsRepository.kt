package com.egecube.eduplatform.streams

import com.egecube.eduplatform.streams.domain.Stream
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StreamsRepository: CrudRepository<Stream, Long> {
    fun findStreamByExternalLinkIsNotNull()
}