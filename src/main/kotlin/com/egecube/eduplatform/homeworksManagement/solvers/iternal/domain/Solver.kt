package com.egecube.eduplatform.homeworksManagement.solvers.iternal.domain

import com.egecube.eduplatform._security_.accounts.domain.UserRole
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Id
import net.minidev.json.annotate.JsonIgnore
import org.springframework.data.mongodb.core.mapping.Document

@Document("solvers")
data class Solver(
    @Id
    @JsonIgnore
    val _id: Long? = null,

    @JsonProperty("name")
    val name: String,
    val secondName: String,
    val email: String,
    val solverRole: UserRole,
    //            TODO или хранить ток айдишники ?
    val homeworks: MutableList<String> = mutableListOf(),
    val homeworkAnswers: MutableList<String> = mutableListOf(),

//    @DBRef
//    val evaluatedHomeworks: MutableList<HomeworkAnswer> = mutableListOf(),
//
//    @DBRef
//    val unevaluatedHomeworks: MutableList<Homework> = mutableListOf(),
//
//    @DBRef
//    val unresolvedHomeworks: MutableList<Homework>? = mutableListOf(),
)