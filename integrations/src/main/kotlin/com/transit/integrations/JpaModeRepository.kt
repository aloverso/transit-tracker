package com.transit.integrations

import com.transit.domain.TransitMode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Repository
interface JpaModeRepository: JpaRepository<JpaTransitMode, Long>

@Entity
class JpaTransitMode(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        val name: String = "",
        val counter: Int = 0
) {
    fun toDomain(): TransitMode {
        return TransitMode(
                name = name,
                counter = counter
        )
    }
}