package com.transit.services

class Game {

    var rollCount = 0
    var score = 0
    var firstRoll = 0
    var secondRoll = 0

    fun roll(pinsKnocked: Int) {
        score += pinsKnocked

        if (isFirstRollOfFrame()) {
            rollCount++

            if(pinsKnocked == 10) {
                roll(0)
            }

            if(firstRoll + secondRoll == 10) {
                score += pinsKnocked
            }

            firstRoll = pinsKnocked

        } else {
            rollCount++
            secondRoll = pinsKnocked
        }

    }

    private fun isFirstRollOfFrame() = rollCount % 2 == 0

    fun score(): Int {
        return score
    }
}