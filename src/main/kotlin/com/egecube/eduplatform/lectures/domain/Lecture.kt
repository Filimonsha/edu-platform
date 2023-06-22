package com.egecube.eduplatform.lectures.domain

import com.egecube.eduplatform.schedules.domain.Event
import jakarta.persistence.*

@Entity
@Table(name = "lectures")
open class Lecture (
    open var externalLink: String,
    @Enumerated(EnumType.STRING)
    open var webinarStatus: LectureStatus = LectureStatus.PLANNED,
): Event() {

    companion object {
        fun build(): Lecture = Lecture::class.java.getConstructor().newInstance()
    }
}