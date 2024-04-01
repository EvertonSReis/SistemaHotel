package com.esr.MadeHotel.modelo.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginRequestDTO {

    @NotEmpty
    private String email;

    @NotEmpty
    private String senha;
}
