package com.transit.domain

class IncrementModeUseCase(val modesRepository: ModesRepository) {

    fun execute(id: Int): TransitMode {
        val foundMode = modesRepository.find(id)!!

        foundMode.counter += 1
        modesRepository.update(foundMode)
        return foundMode
    }
}