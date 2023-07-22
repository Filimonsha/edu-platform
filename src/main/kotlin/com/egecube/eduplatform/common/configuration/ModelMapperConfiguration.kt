package com.egecube.eduplatform.common.configuration

import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.modelmapper.convention.NameTokenizers
import org.modelmapper.jackson.JsonNodeValueReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ModelMapperConfiguration {
    @Bean
    fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper()
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STRICT
        modelMapper.configuration.valueReaders.add(JsonNodeValueReader())
        modelMapper.configuration.sourceNameTokenizer = NameTokenizers.UNDERSCORE
        return modelMapper
    }
}