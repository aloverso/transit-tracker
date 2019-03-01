package com.transit

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TransitTrackerApplication

fun main(args: Array<String>) {
    runApplication<TransitTrackerApplication>(*args)
}
