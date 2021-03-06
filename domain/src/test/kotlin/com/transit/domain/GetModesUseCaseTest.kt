package com.transit.domain

import com.transit.domain.stubs.StubModesRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetModesUseCaseTest {

    @Test
    fun `execute() gets transit modes`() {
        val stubModesRepository = StubModesRepository()
        stubModesRepository.stubbedGetModes = listOf(
                TransitMode(name="train", counter=1, id=1)
        )

        val getModesUseCase = GetModesUseCase(stubModesRepository)

        assertThat(getModesUseCase.execute()).isEqualTo(listOf(
                TransitMode(name="train", counter=1, id=1)
        ))
    }
}