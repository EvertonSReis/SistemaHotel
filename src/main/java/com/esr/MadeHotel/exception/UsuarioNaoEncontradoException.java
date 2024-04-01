package com.esr.MadeHotel.exception;

import com.esr.MadeHotel.modelo.enums.EValidacao;

public class UsuarioNaoEncontradoException extends ExceptionAbstract{

    public UsuarioNaoEncontradoException(EValidacao EValidacao){
        super(EValidacao);
    }

    public UsuarioNaoEncontradoException(EValidacao EValidacao, String... params){super(EValidacao,params);}
}
