package com.egecube.eduplatform.common.mongodb.converters

import org.springframework.core.convert.converter.Converter
import java.time.ZonedDateTime
import java.util.Date

class ZonedDateTimeWriteConverter : Converter<ZonedDateTime, Date> {
    override fun convert(source: ZonedDateTime): Date {
        return Date.from(source.toInstant());
    }
}