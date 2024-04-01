package com.esr.MadeHotel.modelo.entidade.login;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    private String email;

    private String senha;

    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @Column(name = "usuario_ativo")
    private Boolean usuarioAtivo;

    @Column(name = "data_usuario_inativo")
    private LocalDateTime dataUsuarioInativado;
}
