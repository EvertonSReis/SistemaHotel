package com.esr.MadeHotel.modelo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SituacaoQuartoEnum {

    LIBERADO(0),
    OCUPADO(1);

    private int situacao;
}
