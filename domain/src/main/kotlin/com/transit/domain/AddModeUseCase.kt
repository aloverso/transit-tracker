package com.transit.domain

class AddModeUseCase(val modesRepository: ModesRepository) {
    fun execute(name: String): TransitMode {
        return modesRepository.save(name)
    }
}