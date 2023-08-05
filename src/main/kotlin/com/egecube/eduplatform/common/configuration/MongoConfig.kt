package com.egecube.eduplatform.common.configuration

import com.egecube.eduplatform.common.mongodb.converters.ZonedDateTimeReadConverter
import com.egecube.eduplatform.common.mongodb.converters.ZonedDateTimeWriteConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.convert.MongoCustomConversions


@Configuration
class MongoConfig(
    @Value("\${spring.data.mongodb.database}")
    private val databaseName: String,
) : AbstractMongoClientConfiguration() {

    override fun getDatabaseName(): String {
        return databaseName
    }


    override fun customConversions(): MongoCustomConversions =
        MongoCustomConversions(
            listOf(
                ZonedDateTimeReadConverter(),
                ZonedDateTimeWriteConverter()
            )
        )

}