package com.transit.domain

import com.transit.domain.stubs.StubModesRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AddModeUseCaseTest {
    @Test
    fun `execute() gets transit modes`() {
        val stubModesRepository = StubModesRepository()

        val addModesUseCase = AddModeUseCase(stubModesRepository)

        assertThat(addModesUseCase.execute("train")).isEqualTo(
                TransitMode("train", 1)
        )

        assertThat(stubModesRepository.save_nameCalledWith).isEqualTo("train")
    }
}