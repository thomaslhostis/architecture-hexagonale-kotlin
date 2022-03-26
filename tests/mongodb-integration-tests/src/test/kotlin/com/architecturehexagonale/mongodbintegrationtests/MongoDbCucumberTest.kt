package com.architecturehexagonale.mongodbintegrationtests

import com.architecturehexagonale.ArchitectureHexagonale
import com.architecturehexagonale.functionaltests.configuration.TestContextCleaner
import com.architecturehexagonale.functionaltests.configuration.TestDatabaseCleaner
import io.cucumber.java.Before
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@RunWith(Cucumber::class)
@CucumberOptions(
    plugin = ["pretty"],
    features = ["../functional/src/main/resources"],
    glue = [
        "classpath:com.architecturehexagonale.functionaltests.glue",
        "classpath:com.architecturehexagonale.mongodbintegrationtests"
    ]
)
class MongoDbCucumberTest {

    @SpringBootTest(classes = [ArchitectureHexagonale::class], webEnvironment = RANDOM_PORT)
    @CucumberContextConfiguration
    class CucumberSpringContextConfiguration(
        private val testContextCleaner: TestContextCleaner,
        private val testDatabaseCleaner: TestDatabaseCleaner
    ) {
        @Before
        fun cleanUp() {
            testContextCleaner.reset()
            testDatabaseCleaner.dropCollections()
        }
    }
}
