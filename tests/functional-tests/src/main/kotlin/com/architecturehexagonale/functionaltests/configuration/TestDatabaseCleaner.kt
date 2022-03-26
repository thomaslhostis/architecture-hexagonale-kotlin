package com.architecturehexagonale.functionaltests.configuration

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component

@Component
class TestDatabaseCleaner(private val mongoTemplate: MongoTemplate) {

    fun dropCollections() {
        for (collection in mongoTemplate.collectionNames) {
            mongoTemplate.getCollection(collection).drop()
        }
    }
}
