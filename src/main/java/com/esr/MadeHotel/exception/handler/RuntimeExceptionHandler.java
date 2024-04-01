package com.esr.MadeHotel.exception.handler;

import com.esr.MadeHotel.exception.MadeHotelRuntimeException;
import com.esr.MadeHotel.modelo.dtos.erro.ErroDTO;
import com.esr.MadeHotel.modelo.dtos.erro.ErrosDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class RuntimeExceptionHandler {

    @ExceptionHandler(MadeHotelRuntimeException.class)
    public ResponseEntity<Object> validacaoHandle(MadeHotelRuntimeException ex) {
        List<ErroDTO> erros = new ArrayList<>();

        erros.add(ErroDTO.builder().codigo(-999).mensagem(ex.getDescricao()).build());
        log.error("Ocorreu um erro interno", ex);

        return new ResponseEntity<>(
                ErrosDTO.builder().erros(erros).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
