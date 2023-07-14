package com.egecube.eduplatform.homeworksManagement.homeworks

import com.egecube.eduplatform.homeworksManagement.homeworks.internal.HomeworkAnswerRepository
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.HomeworkRepository
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.Homework
import com.egecube.eduplatform.homeworksManagement.homeworks.internal.domain.HomeworkAnswer
import com.egecube.eduplatform.homeworksManagement.tasks.dto.TaskRequestDTO
import com.egecube.eduplatform.homeworksManagement.tasks.internal.TaskRepository
import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.Task
import com.egecube.eduplatform.homeworksManagement.tasks.internal.domain.TaskAnswer
import org.springframework.stereotype.Service

@Service
class HomeworkService(
    private val homeworkRepository: HomeworkRepository,
    private val homeworkAnswerRepository: HomeworkAnswerRepository,
    private val taskRepository: TaskRepository
) {
    fun getAllHomeworks(): List<Homework> {
        return homeworkRepository.findAll()
    }

    fun createHomework(homework: Homework): Homework {
//        homework.tasks.forEach { task: Task -> taskRepository.save(task) }
        return homeworkRepository.save(homework)
    }

    fun getHomework(homeworkId: String): Homework {
        return homeworkRepository.findById(homeworkId).orElseThrow()
    }

    fun addTasksToHomework(homeworkId: String, dtoTasks: List<TaskRequestDTO>): Homework {
        val foundHomework = homeworkRepository.findById(homeworkId).orElseThrow()
        print(foundHomework._id.toString() + "AAAAAAAAAaaaaaa")
//            TODO Как проверять уникальность приритета ?
        val distinctTasks = dtoTasks.map {
            Task(
                null,
                homeworkId,
                it.title,
                it.description,
                it.priority,
                it.correctAnswer,
            )
        }.distinctBy { it.priority }

        distinctTasks.map { task: Task -> taskRepository.save(task) }
        foundHomework.tasks.plusAssign(distinctTasks.sortedBy { it.priority })


//        return
        return homeworkRepository.save(foundHomework)
    }

    fun getHomeworkTasks(homeworkId: String): List<Task> {
        return taskRepository.findAllByHomeworkId(homeworkId)
    }

    fun createAnswerOnHomework(homeworkAnswer: HomeworkAnswer): HomeworkAnswer {
        val homework = homeworkRepository.findById(homeworkAnswer.homeworkId).orElseThrow()
        val savedHomeworkAnswer = homeworkAnswerRepository.save(homeworkAnswer)
        //            TODO добавить кастомный эксепшен на не соотвествие размера тасок и ответов
        homework.tasks.size != homeworkAnswer.answers.size && throw Exception("Ne sovpadaet dlina )")
        homeworkAnswer.answers.forEach { taskAnswer: TaskAnswer ->
//            TODO добавить кастомный эксепшен на не соотвествие инутых тасок к существующей домашки
            homework.tasks.none { existingTasks: Task ->
                println(existingTasks._id)
                println(taskAnswer.taskId)
                existingTasks._id == taskAnswer.taskId
            } && throw Exception("Not found tasks with that id")
        }
        homework.answers.add(savedHomeworkAnswer)
        homeworkRepository.save(homework)
        return savedHomeworkAnswer
    }
}