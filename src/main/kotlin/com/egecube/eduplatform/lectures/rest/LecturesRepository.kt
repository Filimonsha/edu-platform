package com.egecube.eduplatform.lectures.rest

import com.egecube.eduplatform.lectures.domain.Lecture
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LecturesRepository: CrudRepository<Lecture, Long> {
    fun findStreamByExternalLinkIsNotNull()
}