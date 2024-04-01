package com.esr.MadeHotel.exception;

import com.esr.MadeHotel.modelo.enums.EValidacao;
import lombok.Getter;

public class ExceptionAbstract extends RuntimeException {

    private EValidacao validacao;

    @Getter private String[] params;

    public Integer getCodigo() {
        return this.validacao.getCodigo();
    }

    public String getMensagem() {
        return this.validacao.getDescricao(this.params);
    }

    public ExceptionAbstract(EValidacao validacao) {
        super(validacao.getDescricao());
        this.validacao = validacao;
    }

    public ExceptionAbstract(EValidacao validacao, String... params) {
        super(validacao.getDescricao());
        this.validacao = validacao;
        this.params = params;
    }

    public ExceptionAbstract(EValidacao validacao, Throwable throwable) {
        super(validacao.getDescricao(), throwable);
        this.validacao = validacao;
    }
}
