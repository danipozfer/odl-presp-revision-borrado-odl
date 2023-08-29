package com.santalucia.cdc.core.mappers.insurance;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * Offset DateTime mapper
 *
 */
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OffsetDateTimeMapper {

	/**
	 * Max position
	 *
	 */
	int DATE_MAX_POSITION = 19;
	/**
	 * Mapper de OffsetDateTime a String
	 *
	 * @param fecha
	 * @return String fecha convertida
	 */
	static String offsetDateTimeToString(OffsetDateTime fecha) {
		String result = null;
		if (fecha != null) {
			result = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
		return result;
	}

	/**
	 * Mapper de String a OffsetDateTime
	 *
	 * @param fecha
	 * @return OffsetDateTime fecha convertida
	 */

	static OffsetDateTime stringToOffsetDateTime(String fecha) {
		OffsetDateTime result = null;
		String nFecha = fecha;
		if (nFecha == null || nFecha.isBlank()) {
			return result;
		}
		ZoneId zone = ZoneId.systemDefault();
		DateTimeFormatter parser = null;
		if (nFecha.contains("T")) {
			if (nFecha.length() > 20) {
				nFecha = nFecha.substring(0, DATE_MAX_POSITION) + "Z";
				parser = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(zone);
				LocalDateTime dt = LocalDateTime.parse(nFecha, parser);
				return dt.atZone(zone).toOffsetDateTime();
			} else if (nFecha.contains("Z")) {
				parser = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").withZone(zone);
				LocalDateTime dt = LocalDateTime.parse(nFecha, parser);
				return dt.atZone(zone).toOffsetDateTime();
			}

			parser = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd'T'HH:mm:ss").toFormatter().withZone(zone);
			LocalDateTime dt = null;
			if (nFecha.contains(".")) {
				dt = LocalDateTime.parse(nFecha.substring(0, nFecha.lastIndexOf(".")), parser);
			} else {
				dt = LocalDateTime.parse(nFecha, parser);
			}
			return dt.atZone(zone).toOffsetDateTime();
		} else if (nFecha.length() == 7) {
			YearMonth dt = YearMonth.parse(nFecha);
			return dt.atDay(1).atStartOfDay(zone).toOffsetDateTime();
		}
		parser = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(zone);
		LocalDate dt = LocalDate.parse(nFecha, parser);
		return dt.atStartOfDay(zone).toOffsetDateTime();
	}
}
