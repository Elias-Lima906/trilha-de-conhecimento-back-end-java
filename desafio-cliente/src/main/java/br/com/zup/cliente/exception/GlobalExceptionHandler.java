package br.com.zup.cliente.exception;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.zup.cliente.dto.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(GlobalException.class)
	public @ResponseBody ErrorDTO handleGenericException(GlobalException e) {
		e.printStackTrace();
		
		logger.error(e.getMensagemErro());
		logger.error(e.getStackTrace());
		
		return new ErrorDTO(e.getMensagemErro());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody List<ErrorDTO> handleValidationError(MethodArgumentNotValidException e) {

		List<ErrorDTO> errosDeValidacao = new ArrayList<>();

		for (ObjectError erro : e.getBindingResult().getAllErrors()) {

			if (nonNull(erro.getCodes())) {
				errosDeValidacao.add(new ErrorDTO(erro.getDefaultMessage().toString()));
			}
		}

		return errosDeValidacao;
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public @ResponseBody ErrorDTO handleGenericException(Exception e) {
		e.printStackTrace();
		
		logger.error(e.getLocalizedMessage());
		logger.error(e.getStackTrace());
		
		return new ErrorDTO(e.getLocalizedMessage());
	}

}
