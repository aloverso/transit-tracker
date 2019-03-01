package com.transit.domain.stubs

import com.transit.domain.ModesRepository
import com.transit.domain.TransitMode

class StubModesRepository : ModesRepository {
    lateinit var stubbedGetModes: List<TransitMode>
    var save_nameCalledWith: String? = null

    override fun getModes(): List<TransitMode> {
        return stubbedGetModes
    }

    override fun save(name: String): TransitMode {
        save_nameCalledWith = name
        return TransitMode(name, 1)
    }
}