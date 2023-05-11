package com.egecube.eduplatform.classesManagement.repositories

import com.egecube.eduplatform.classesManagement.domain.Subject
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectRepository : JpaRepository<Subject,Long>