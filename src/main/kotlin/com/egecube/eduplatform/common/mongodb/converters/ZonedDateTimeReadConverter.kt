package com.egecube.eduplatform.common.mongodb.converters

import org.springframework.core.convert.converter.Converter
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.Date

class ZonedDateTimeReadConverter:Converter<Date,ZonedDateTime> {
    override fun convert(source: Date): ZonedDateTime? {
        return source.toInstant().atZone(ZoneOffset.UTC);
    }
}