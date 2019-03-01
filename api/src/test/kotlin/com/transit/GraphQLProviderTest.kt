package com.transit

import com.graphql.spring.boot.test.GraphQLTest
import com.graphql.spring.boot.test.GraphQLTestTemplate
import com.transit.domain.ModesRepository
import com.transit.domain.TransitMode
import com.transit.domain.stubs.StubModesRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@GraphQLTest
@ActiveProfiles("test")
@RunWith(SpringRunner::class)
class GraphQLProviderTest {

    @Configuration
    class TestConfig {

        companion object {
            lateinit var stubModesRepository: StubModesRepository
        }

        @Bean
        @Primary
        fun stubModesRepository(): ModesRepository {
            stubModesRepository = StubModesRepository()
            return stubModesRepository
        }
    }

    @Autowired
    private lateinit var graphQLTestTemplate: GraphQLTestTemplate

    @Test
    fun `should return list of transit modes`() {
        TestConfig.stubModesRepository.stubbedGetModes = listOf(
                TransitMode(
                        name = "some name",
                        counter = 5
                ),
                TransitMode(
                        name = "some other name",
                        counter = 10
                )
        )

        val response = graphQLTestTemplate.postForResource("getModes.graphql")

        assertThat(response.isOk).isTrue()
        assertThat(response.readTree()["data"]["modes"][0]["name"].asText()).isEqualTo("some name")
        assertThat(response.readTree()["data"]["modes"][0]["counter"].asInt()).isEqualTo(5)
        assertThat(response.readTree()["data"]["modes"][1]["name"].asText()).isEqualTo("some other name")
        assertThat(response.readTree()["data"]["modes"][1]["counter"].asInt()).isEqualTo(10)
    }

    @Test
    fun `should add a transit mode`() {

        val response = graphQLTestTemplate.postForResource("addMode.graphql")

        assertThat(TestConfig.stubModesRepository.save_nameCalledWith).isEqualTo("LIRR")

        assertThat(response.isOk).isTrue()
        assertThat(response.readTree()["data"]["addMode"]["name"].asText()).isEqualTo("LIRR")
        assertThat(response.readTree()["data"]["addMode"]["counter"].asInt()).isEqualTo(1)
    }

    @Test
    fun `should increment a transit mode`() {

        TestConfig.stubModesRepository.stubbedGetModes = listOf(
                TransitMode(
                        name = "LIRR",
                        counter = 5
                )
        )

        val response = graphQLTestTemplate.postForResource("incrementMode.graphql")

        assertThat(TestConfig.stubModesRepository.update_nameCalledWith).isEqualTo("LIRR")

        assertThat(response.isOk).isTrue()
        assertThat(response.readTree()["data"]["incrementMode"]["name"].asText()).isEqualTo("LIRR")
        assertThat(response.readTree()["data"]["incrementMode"]["counter"].asInt()).isEqualTo(6)
    }
}