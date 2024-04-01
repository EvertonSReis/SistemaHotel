package com.esr.MadeHotel.exception.handler;

import com.esr.MadeHotel.exception.ExceptionAbstract;
import com.esr.MadeHotel.modelo.dtos.erro.ErroDTO;
import com.esr.MadeHotel.modelo.dtos.erro.ErrosDTO;
import com.esr.MadeHotel.modelo.enums.EValidacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ValidationHandler {

    @ExceptionHandler(ExceptionAbstract.class)
    public ResponseEntity<Object> validacaoHandle(ExceptionAbstract ex) {
        if (ex.getCodigo().equals(EValidacao.NAO_IDENTIFICADO.getCodigo())) {
            log.error("Ocorreu um erro interno", ex);
            return new ResponseEntity<>(
                    ErroDTO.builder().codigo(ex.getCodigo()).mensagem(ex.getMensagem()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            List<ErroDTO> erros = new ArrayList<>();

            erros.add(ErroDTO.builder().codigo(ex.getCodigo()).mensagem(ex.getMensagem()).build());
            log.warn(ex.getMensagem());
            return new ResponseEntity<>(
                    ErrosDTO.builder().erros(erros).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
