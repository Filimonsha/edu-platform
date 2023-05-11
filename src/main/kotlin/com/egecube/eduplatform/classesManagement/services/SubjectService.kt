package com.egecube.eduplatform.classesManagement.services

import com.egecube.eduplatform.classesManagement.repositories.SubjectRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SubjectService (private val subjectRepository: SubjectRepository){
    fun get(id: Long): Optional<com.egecube.eduplatform.classesManagement.domain.Subject> = subjectRepository.findById(id)

    fun getAll() = subjectRepository.findAll()
}