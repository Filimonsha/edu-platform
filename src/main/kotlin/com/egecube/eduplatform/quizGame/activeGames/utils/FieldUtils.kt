package com.egecube.eduplatform.quizGame.activeGames.utils

import com.egecube.eduplatform.quizGame.activeGames.domain.Game
import org.springframework.stereotype.Service
import kotlin.math.floor
import kotlin.math.sqrt

@Service
class FieldUtils {

    fun makeEmptyFieldWithElements(count: Int, tasks: List<String>): ArrayList<ArrayList<Pair<String?, Boolean?>>> {
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
        // fill the lists
        val field: ArrayList<ArrayList<Pair<String?, Boolean?>>> = ArrayList(dimension)
        var taskCount = 0
        for (rowN in 0 until dimension) {
            val row: ArrayList<Pair<String?, Boolean?>> = ArrayList()
            for (colN in 0 until dimension) {
                row.add(Pair(tasks[taskCount], false))
                taskCount += 1
            }
            val rangeAccept = (dimension - removing) / 2
            if (rowN in rangeAccept until dimension - rangeAccept ) {
                row.add(Pair(tasks[taskCount], false))
                taskCount += 1
            }
            field.add(row)
        }
        return field
    }

    fun countRightAnswersForUser(userId: Long, game: Game): Int {
        return game.postedAnswers.count{ it.second && it.first.userId == userId }
    }
}