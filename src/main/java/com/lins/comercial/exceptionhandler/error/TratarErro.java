package com.lins.comercial.exceptionhandler.error;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TratarErro {

	private LocalDateTime dataHora;
	private String descricaoDoProblema;
}
