package com.santalucia.cdc.core.mappers.budget;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import javax.annotation.Nullable;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OffsetDateTimeMapper {

  /**
   * Mapper de Instant a OffsetDateTime
   *
   * @param date
   * @return OffsetDateTime fecha convertida
   */
  @Nullable
  static OffsetDateTime instantToOffsetDateTime(Instant date) {
    OffsetDateTime offsetDateTime = null;
    if (date != null) {
      offsetDateTime = OffsetDateTime.ofInstant(date, ZoneId.systemDefault());
    }
    return offsetDateTime;
  }

  /**
   * Mapper de OffsetDateTime a Instant
   *
   * @param date
   * @return OffsetDateTime fecha convertida
   */
  @Nullable
  static Instant instantoffsetDateTimeToInstant(OffsetDateTime date) {
    Instant instant = null;
    if (date != null) {
      instant = date.toInstant();
    }
    return instant;
  }


}
