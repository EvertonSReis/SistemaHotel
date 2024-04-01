package com.esr.MadeHotel.modelo.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCadastroRequestDTO {

    @NotEmpty
    private String email;

    @NotEmpty
    private String senha;

    @NotEmpty
    private String confirmarSenha;

    @NotEmpty
    private String nomeUsuario;
}
