package com.egecube.eduplatform.subjectsManagement.courses

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CourseRepository : JpaRepository<Course, Long> {
    fun getAllBySubjectId(courseId: Long): List<Course>
    fun getBySubjectIdAndId(subjectId: Long, id: Long): Optional<Course>

}