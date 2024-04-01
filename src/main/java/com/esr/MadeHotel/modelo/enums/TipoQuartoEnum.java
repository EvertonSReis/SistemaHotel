package com.esr.MadeHotel.modelo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoQuartoEnum {

    STAND(0),
    LUXO(1),
    SUPER_LUXO(2);

    private int tipo;
}
