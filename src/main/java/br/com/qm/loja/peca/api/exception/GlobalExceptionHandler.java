package br.com.qm.loja.peca.api.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.qm.loja.peca.api.dto.ResponseDTO;


// Essa anotação serve para dizer que ele está olhando pra exceptions jogadas
// no controller.
@ControllerAdvice
public class GlobalExceptionHandler {

	private static final int POSICAO_ERRO = 0;

	// Aqui nós dizemos qual status http deverá ser retornado.
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	// Aqui específicamos qual exception tratar
    @ExceptionHandler({ErroDeNegocioException.class})
    public @ResponseBody ResponseDTO handleBusinessErrors(Exception e) {
        return new ResponseDTO(e.getMessage());
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public @ResponseBody List<ResponseDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
		List<ResponseDTO> resposta = new ArrayList<ResponseDTO>();

		for (ObjectError erro : e.getBindingResult().getAllErrors()) {

			String erroInteiro = erro.getCodes()[POSICAO_ERRO];
			String nomeCampo = erroInteiro.substring(erroInteiro.lastIndexOf(".") + 1, erroInteiro.length());
			
			resposta.add(new ResponseDTO(nomeCampo + " " + erro.getDefaultMessage()));
		}
		
		
		return resposta;
	}
	
	
}