package com.transit.domain.stubs

import com.transit.domain.ModesRepository
import com.transit.domain.TransitMode

class StubModesRepository : ModesRepository {
    lateinit var stubbedGetModes: List<TransitMode>
    var save_nameCalledWith: String? = null
    var update_nameCalledWith: String? = null
    var update_counterCalledWith: Int? = null

    override fun getModes(): List<TransitMode> {
        return stubbedGetModes
    }

    override fun save(name: String): TransitMode {
        save_nameCalledWith = name
        return TransitMode(name, 1, 1)
    }

    override fun update(mode: TransitMode): TransitMode {
        update_nameCalledWith = mode.name
        update_counterCalledWith = mode.counter
        return mode
    }

    override fun find(id: Int): TransitMode? {
        return stubbedGetModes.find { mode -> mode.id == id }
    }
}