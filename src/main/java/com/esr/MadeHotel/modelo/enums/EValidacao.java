package com.esr.MadeHotel.modelo.enums;

import com.esr.MadeHotel.interfaces.IEnumLabel;
import lombok.Getter;

@Getter
public enum EValidacao implements IEnumLabel<EValidacao> {

    ENTRADA_DE_DADOS_INVALIDA(-1),
    SENHA_CONFIRMACAO_INVALIDA(-2),
    EMAIL_JA_EXISTE(-3),
    EMAIL_SENHA_INCORRETO(-4),
    USUARIO_NAO_ENCONTRADO(-5),
    NAO_IDENTIFICADO(-999);

    private Integer codigo;

    EValidacao(Integer codigo){
        this.codigo = codigo;
    }
}
