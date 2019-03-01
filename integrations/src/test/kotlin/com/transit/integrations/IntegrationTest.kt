package com.transit.integrations

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class IntegrationTest {

    @Autowired
    lateinit var jpaModeRepository: JpaModeRepository

    lateinit var modesRepository: DefaultModesRepository

    @Before
    fun setUp() {
        modesRepository = DefaultModesRepository(jpaModeRepository)
    }

    @Test
    fun canSaveAndGetModes() {
        modesRepository.save("F train")
        modesRepository.save("Q train")

        val modes = modesRepository.getModes()
        assertThat(modes.size).isEqualTo(2)
        assertThat(modes[0].name).isEqualTo("F train")
        assertThat(modes[0].counter).isEqualTo(1)
        assertThat(modes[1].name).isEqualTo("Q train")
        assertThat(modes[1].counter).isEqualTo(1)
    }
}