package com.egecube.eduplatform.lecturesManagement.lectures.internal

import org.springframework.data.jpa.repository.JpaRepository


interface LectureRepository:JpaRepository<Lecture,Long>