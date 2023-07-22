package com.egecube.eduplatform.quizGame.active_games.utils

import kotlin.math.floor
import kotlin.math.sqrt

class FieldUtils {
    fun makeEmptyFieldWithElements(count: Int, tasks: List<Long>): ArrayList<ArrayList<Pair<Long?, Boolean?>>> {
        // get square matrix and count values to append on sides
        val dimension: Int = floor(sqrt(count.toDouble())).toInt()
        var removing: Int = count - dimension*dimension
        if (removing > 0) {
            if (dimension % 2 == 0) {
                removing /= 2
                removing *= 2
            } else {
                if (removing % 2 != 1) {
                    removing -= 1
                }
            }
        }
//        println("dimension $dimension")
//        println("removing $removing")
        // fill the lists
        val field: ArrayList<ArrayList<Pair<Long?, Boolean?>>> = ArrayList(dimension)
        var taskCount = 0
        for (rowN in 0 until dimension) {
            val row: ArrayList<Pair<Long?, Boolean?>> = ArrayList()
            for (colN in 0 until dimension) {
//                println("adding row $rowN $colN el $taskCount")
                row.add(Pair(tasks[taskCount], false))
                taskCount += 1
            }
            val rangeAccept = (dimension - removing) / 2
//            println("range accept $rangeAccept")
            if (rowN in rangeAccept until dimension - rangeAccept ) {
//                println("adding row $rowN add el $taskCount")
                row.add(Pair(tasks[taskCount], false))
                taskCount += 1
            }
            field.add(row)
        }
        return field
    }
}