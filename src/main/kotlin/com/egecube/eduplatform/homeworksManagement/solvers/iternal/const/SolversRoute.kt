package com.egecube.eduplatform.homeworksManagement.solvers.iternal.const

object SolversRoute {
    private const val BASE_ROUTE = "/api/homeworks-management"
    const val SOLVERS = "${BASE_ROUTE}/solvers"
    const val SOLVER = "${SOLVERS}/{solverId}"
    const val SOLVER_HOMEWORKS = "${SOLVER}/homeworks"
    const val SOLVER_HOMEWORK = "${SOLVER}/homeworks/{homeworkId}"
    const val SOLVER_ANSWERS = "${SOLVER}/answers"
    const val SOLVER_ANSWER = "${SOLVER_ANSWERS}/{answerId}"
}