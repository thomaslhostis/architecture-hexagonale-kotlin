package com.architecturehexagonale.infrastructure.mongo

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import de.bwaldvogel.mongo.MongoServer
import de.bwaldvogel.mongo.backend.memory.MemoryBackend
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory

@Configuration
class InMemoryMongoConfiguration {

    companion object {
        const val DATABASE_NAME = "test"
    }

    @Bean
    fun mongoUri(mongoServer: MongoServer): String {
        val serverAddress = mongoServer.localAddress
        return "mongodb://${serverAddress.hostName}:${serverAddress.port}/$DATABASE_NAME"
    }

    @Bean
    fun mongoDbFactory(mongoUri: String): MongoDatabaseFactory {
        return SimpleMongoClientDatabaseFactory(mongoUri)
    }

    @Bean
    fun mongoTemplate(mongoDatabaseFactory: MongoDatabaseFactory): MongoTemplate {
        return MongoTemplate(mongoDatabaseFactory)
    }

    @Bean(destroyMethod = "shutdown")
    fun mongoServer(): MongoServer {
        val mongoServer = MongoServer(MemoryBackend())
        mongoServer.bind()
        return mongoServer
    }

    @Bean
    fun mongoClient(mongoUri: String): MongoClient {
        val connectionString = ConnectionString(mongoUri)
        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString).build()
        return MongoClients.create(mongoClientSettings)
    }
}
