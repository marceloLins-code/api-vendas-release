package com.lins.comercial.exceptionhandler.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.lins.comercial.exceptionhandler.EntidadeEmUsoException;
import com.lins.comercial.exceptionhandler.EntidadeNaoEncontradaException;
import com.lins.comercial.exceptionhandler.NegocioException;

@ControllerAdvice
public class HandlerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e,
			WebRequest webRequest) {

		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);

	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> tratarEntidadeEmUso(EntidadeEmUsoException e, WebRequest webRequest) {

		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, webRequest);

	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(NegocioException e, WebRequest webRequest) {

		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);

	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null) {
			body = TratarErro.builder().dataHora(LocalDateTime.now()).descricaoDoProblema(status.getReasonPhrase())
					.build();
		} else if (body instanceof String) {
			body = TratarErro.builder().dataHora(LocalDateTime.now()).descricaoDoProblema((String) body).build();

		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

}
