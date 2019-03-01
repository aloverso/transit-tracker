package com.transit.domain

class GetModesUseCase(val modesRepository: ModesRepository) {
    fun execute(): List<TransitMode> {
        return modesRepository.getModes()
    }
}