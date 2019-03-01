package com.transit

import com.google.common.io.Resources
import com.transit.domain.AddModeUseCase
import com.transit.domain.GetModesUseCase
import com.transit.domain.TransitMode
import com.transit.domain.IncrementModeUseCase
import graphql.GraphQL
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import graphql.schema.GraphQLSchema
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.io.IOException
import javax.annotation.PostConstruct
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.TypeRuntimeWiring.newTypeWiring
import org.springframework.beans.factory.annotation.Autowired

@Component
class GraphQLProvider {

    private var graphQL: GraphQL? = null

    private var graphQLSchema: GraphQLSchema? = null

    @Autowired
    lateinit var dataFetchers: DataFetchers

    @Bean
    fun graphQL(): GraphQL? {
        return graphQL
    }

    @Bean
    fun schema(): GraphQLSchema? {
        return graphQLSchema
    }

    @PostConstruct
    @Throws(IOException::class)
    fun init() {
        this.graphQLSchema = buildSchema()
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build()
    }

    private fun buildSchema(): GraphQLSchema {
        val url = Resources.getResource("schema.graphqls")
        val sdl = Resources.toString(url, Charsets.UTF_8)
        val typeRegistry = SchemaParser().parse(sdl)

        val runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("modes", dataFetchers.modes())
                )
                .type(newTypeWiring("Mutation")
                        .dataFetcher("addMode", dataFetchers.addMode())
                        .dataFetcher("incrementMode", dataFetchers.incrementMode())
                )
                .build()

        val schemaGenerator = SchemaGenerator()


        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring)
    }
}


@Component
class DataFetchers {

    @Autowired
    lateinit var getModesUseCase: GetModesUseCase

    @Autowired
    lateinit var addModeUseCase: AddModeUseCase

    @Autowired
    lateinit var incrementModeUseCase: IncrementModeUseCase

    fun modes(): DataFetcher<List<TransitMode>> {
        return toDataFetcher { getModesUseCase.execute() }
    }

    fun addMode(): DataFetcher<TransitMode> {
        return toDataFetcher { env -> addModeUseCase.execute(env!!.getArgument("name")) }
    }

    fun incrementMode(): DataFetcher<TransitMode> {
        return toDataFetcher { env -> incrementModeUseCase.execute(env!!.getArgument("id")) }
    }

    private fun <T> toDataFetcher(f: (environment: DataFetchingEnvironment?) -> T) : DataFetcher<T> =
            DataFetcher { environment -> f(environment) }
}
