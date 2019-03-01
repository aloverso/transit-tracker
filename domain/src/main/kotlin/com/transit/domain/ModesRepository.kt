package com.transit.domain

interface ModesRepository {
    fun getModes(): List<TransitMode>
    fun save(name: String): TransitMode
    fun update(mode: TransitMode): TransitMode
    fun find(id: Int): TransitMode?
}
