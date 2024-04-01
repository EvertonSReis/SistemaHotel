package com.esr.MadeHotel.modelo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SituacaoReservaEnum {

    CANCELADA(0),
    AGUARDANDO(1),
    EM_USO(2);

    private int situacao;
}
