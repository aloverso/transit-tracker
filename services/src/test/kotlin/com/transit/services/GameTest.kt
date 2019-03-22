package com.transit.services

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GameTest {

    @Test
    fun `a new game should have a score of zero`() {
        assertThat(Game().score()).isEqualTo(0)
    }

    @Test
    fun `the score is affected by one roll`() {
        val game = Game()
        game.roll(1)
        assertThat(game.score()).isEqualTo(1)
    }

    @Test
    fun `the score is affected by a different one roll`() {
        val game = Game()
        game.roll(5)
        assertThat(game.score()).isEqualTo(5)
    }

    @Test
    fun `second roll is added to the first rolls score`() {
        val game = Game()
        game.roll(5)
        game.roll(4)
        assertThat(game.score()).isEqualTo(9)
    }

    @Test
    fun `when two rolls in a frame add up to 10, the next roll is counted as bonus`() {
        val game = Game()
        game.roll(1)
        game.roll(0)
        assertThat(game.score()).isEqualTo(1)

        game.roll(6)
        game.roll(4)
        assertThat(game.score()).isEqualTo(11)

        game.roll(2)
        assertThat(game.score()).isEqualTo(15)
    }

    @Test
    fun `when the first roll of a frame is 10, the next two rolls are counted as bonus`() {
        val game = Game()
        game.roll(10)
        assertThat(game.score()).isEqualTo(10)

        game.roll(3)
        game.roll(5)
        assertThat(game.score()).isEqualTo(26)
    }

}