package com.architecturehexagonale.mongodbintegrationtests

import com.architecturehexagonale.ArchitectureHexagonale
import com.architecturehexagonale.commons.Profiles.MONGO
import com.architecturehexagonale.commons.Profiles.TEST
import com.architecturehexagonale.functionaltests.configuration.TestContextCleaner
import com.architecturehexagonale.functionaltests.configuration.TestDatabaseCleaner
import io.cucumber.java.Before
import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.ActiveProfiles

@RunWith(Cucumber::class)
@CucumberOptions(
    plugin = ["pretty"],
    features = ["../functional-tests/src/test/resources"],
    glue = [
        "classpath:com.architecturehexagonale.functionaltests",
        "classpath:com.architecturehexagonale.mongodbintegrationtests"
    ]
)
class MongoDbCucumberTest {

    @SpringBootTest(classes = [ArchitectureHexagonale::class], webEnvironment = RANDOM_PORT)
    @CucumberContextConfiguration
    @ActiveProfiles(profiles = [MONGO, TEST])
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
