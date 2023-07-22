package com.egecube.eduplatform.homeworksManagement.tasks.domain

//enum class TaskType(
//    //private val str: String
//) {
//    SINGLE_CHOICE("SelectAnswer"),
//
//    //MULTIPLE_CHOICE(  ""),
//    TEXT_INPUT("TextAnswer");
//    // OPEN_ENDED;
////     override fun toString(): String {
////         return str
////     }
//}

object TaskType {
    const val TEXT_INPUT = "TEXT_INPUT"
    const val SINGLE_CHOICE = "SINGLE_CHOICE"
    const val MULTIPLE_ANSWER = "MULTIPLE_ANSWER"
    const val ANSWER_WITH_ATTACHMENT = "ANSWER_WITH_ATTACHMENT"
}
