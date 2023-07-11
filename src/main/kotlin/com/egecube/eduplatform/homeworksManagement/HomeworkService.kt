package com.egecube.eduplatform.homeworksManagement

import com.egecube.eduplatform.homeworksManagement.internal.HomeworkAnswerRepository
import com.egecube.eduplatform.homeworksManagement.internal.HomeworkRepository
import com.egecube.eduplatform.homeworksManagement.internal.domain.HomeWork
import com.egecube.eduplatform.homeworksManagement.internal.domain.HomeworkAnswer
import org.springframework.stereotype.Service

@Service
class HomeworkService(
    private val homeworkRepository: HomeworkRepository,
    private val homeworkAnswerRepository: HomeworkAnswerRepository

) {
    fun getAllHomeworks(): List<HomeWork> {
        return homeworkRepository.findAll()
    }

    fun createHomework(homework: HomeWork): HomeWork {
        return homeworkRepository.save(homework)
    }

    fun createAnswerOnHomework(homeworkAnswer: HomeworkAnswer):HomeworkAnswer {
        return homeworkAnswerRepository.save(homeworkAnswer)
    }
}