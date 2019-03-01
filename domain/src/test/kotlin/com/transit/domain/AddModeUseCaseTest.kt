package com.transit.domain

import com.transit.domain.stubs.StubModesRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AddModeUseCaseTest {
    @Test
    fun `execute() adds transit modes`() {
        val stubModesRepository = StubModesRepository()

        val addModesUseCase = AddModeUseCase(stubModesRepository)
        val addedMode = addModesUseCase.execute("train")

        assertThat(addedMode.name).isEqualTo("train")
        assertThat(addedMode.counter).isEqualTo(1)

        assertThat(stubModesRepository.save_nameCalledWith).isEqualTo("train")
    }
}