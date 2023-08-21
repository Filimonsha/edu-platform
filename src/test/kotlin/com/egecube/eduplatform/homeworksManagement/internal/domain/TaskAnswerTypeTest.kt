package com.egecube.eduplatform.homeworksManagement.internal.domain

import com.egecube.eduplatform.homeworksManagement.tasks.domain.TaskAnswerType
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TaskAnswerTypeTest {

    @Test
    fun b() {
        val objectMapper = ObjectMapper()
        val ans = TaskAnswerType.TaskRightAnswer.TextAnswer( "dsda")
        //val res = ByteArray()
        val res = objectMapper.writeValueAsString(ans)
        println(res)
    }
    @Test
    fun a () {
        val objectMapper = ObjectMapper()

        val jsonString =  """{"type":"text","answer":"dsda"}""";

val res =         objectMapper.readValue(jsonString.toByteArray(), TaskAnswerType.TaskRightAnswer::class.java)
        println(res)
            /*
        try (InputStream inputStream = new ByteArrayInputStream(
            jsonString.getBytes("utf-8"))) {
            Object object = objectMapper.readValue(inputStream, Monster.class);
            assertTrue(object instanceof Skeleton);
        }*/
    }
}