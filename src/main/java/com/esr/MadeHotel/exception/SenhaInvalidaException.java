package com.esr.MadeHotel.exception;

import com.esr.MadeHotel.modelo.enums.EValidacao;

public class SenhaInvalidaException extends ExceptionAbstract{

    public SenhaInvalidaException(EValidacao EValidacao){
        super(EValidacao);
    }

    public SenhaInvalidaException(EValidacao EValidacao, String... params){super(EValidacao,params);}
}
