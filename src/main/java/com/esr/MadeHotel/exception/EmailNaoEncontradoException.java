package com.esr.MadeHotel.exception;

import com.esr.MadeHotel.modelo.enums.EValidacao;

public class EmailNaoEncontradoException extends ExceptionAbstract{

    public EmailNaoEncontradoException(EValidacao EValidacao){
        super(EValidacao);
    }

    public EmailNaoEncontradoException(EValidacao EValidacao, String... params){super(EValidacao,params);}
}
