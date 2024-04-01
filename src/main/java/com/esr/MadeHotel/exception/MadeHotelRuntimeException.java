package com.esr.MadeHotel.exception;

import com.esr.MadeHotel.modelo.enums.EValidacao;
import lombok.Getter;

@Getter
public class MadeHotelRuntimeException extends RuntimeException{

    @Getter
    private String descricao;

    public MadeHotelRuntimeException(String msg) {
        super(msg);
        this.descricao = msg;
    }

    public MadeHotelRuntimeException(EValidacao validacao) {
        super(validacao.toString());
        this.descricao = validacao.toString();
    }

    public MadeHotelRuntimeException(Throwable causa) {
        super(causa);

        this.descricao = causa.getMessage();

        if (causa instanceof MadeHotelRuntimeException) {
            this.descricao = ((MadeHotelRuntimeException) causa).getDescricao();
        }
    }

    public MadeHotelRuntimeException(String msg, Throwable causa) {
        super(msg, causa);
        this.descricao = msg;
    }
}
