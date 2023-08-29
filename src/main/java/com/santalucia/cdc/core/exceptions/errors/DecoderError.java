package com.santalucia.cdc.core.exceptions.errors;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Decoder errores API
 *
 */
@Slf4j
public class DecoderError implements ErrorDecoder {

	private final ErrorDecoder defaultErrorDecoder;
	/**
	 * Constructor de la clase DecodeError
	 */
	public DecoderError() {
		this.defaultErrorDecoder = new Default();
	}

	/**
	 * Gestion de los errores devueltos por la API
	 *
	 * @param s        String
	 * @param response Response
	 * @return exception
	 */
	@Override
	public Exception decode(String s, Response response) {
		log.info("Decode entry");
		Exception exception = defaultErrorDecoder.decode(s, response);

		if (!(exception instanceof RetryableException)) {
			int status = response.status();
			switch(status) {
			case 504:
				log.info("Error 504 - ERROR 504");
				exception = new RetryableException(0, "504 error", response.request().httpMethod(), Timestamp.valueOf(LocalDateTime.now()),
						response.request());
				break;
			case 400:
				log.info("Error 400 - BAD REQUEST");
				exception =  new RetryableException(0, "400 error", response.request().httpMethod(), Timestamp.valueOf(LocalDateTime.now()),
						response.request());
				break;
			case 401:
				log.info("Error 401 - Solicitud no autenticada debido a un token ausente, no válido o caducado");
				exception =  new RetryableException(0, "401 error", response.request().httpMethod(), Timestamp.valueOf(LocalDateTime.now()),
						response.request());
				break;
			case 403:
				log.info("Error 403 - Credenciales no correctas para poder ejecutar la operación solicitada");
				exception = new RetryableException(0, "403 error", response.request().httpMethod(), Timestamp.valueOf(LocalDateTime.now()),
						response.request());
				break;
			case 404:
				log.info("Error 404 - No se han encontrado coincidencias");
				exception = new RetryableException(0, "404 error", response.request().httpMethod(), Timestamp.valueOf(LocalDateTime.now()),
						response.request());
				break;
			case 500:
				log.info("Error 500 - Error de servidor");
				exception = new RetryableException(0, "500 error", response.request().httpMethod(), Timestamp.valueOf(LocalDateTime.now()),
						response.request());
				break;
			case 409:
				log.info("Error 409 - Poliza ya existente");
				exception = new RetryableException(0, "409 error", response.request().httpMethod(), Timestamp.valueOf(LocalDateTime.now()),
						response.request());
				break;
			default:
				exception = defaultErrorDecoder.decode(s, response);
				break;
			}
		}
		return exception;
	}
}
