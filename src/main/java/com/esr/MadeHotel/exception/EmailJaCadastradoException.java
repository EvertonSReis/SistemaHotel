package com.esr.MadeHotel.exception;

import com.esr.MadeHotel.modelo.enums.EValidacao;

public class EmailJaCadastradoException extends ExceptionAbstract{

    public EmailJaCadastradoException(EValidacao EValidacao){
        super(EValidacao);
    }
}
