package com.esr.MadeHotel.modelo.builders;

import com.esr.MadeHotel.modelo.dtos.UsuarioCadastroRequestDTO;
import com.esr.MadeHotel.modelo.dtos.UsuarioResponseDTO;
import com.esr.MadeHotel.modelo.entidade.login.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioBuilder {

    private static final Boolean USUARIO_ATIVO = true;

    public static Usuario build(UsuarioCadastroRequestDTO usuarioCadastroRequestDTO, String senhaCriptografada){
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(usuarioCadastroRequestDTO.getNomeUsuario());
        usuario.setUsuarioAtivo(USUARIO_ATIVO);
        usuario.setEmail(usuarioCadastroRequestDTO.getEmail());
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setSenha(senhaCriptografada);

        return usuario;
    }

    public static UsuarioResponseDTO retornoUsuarioCadastrado(Usuario usuario){
        UsuarioResponseDTO retorno = new UsuarioResponseDTO();
        retorno.setId(usuario.getId());
        retorno.setNomeUsuario(usuario.getNomeUsuario());
        retorno.setEmail(usuario.getEmail());
        retorno.setUsuarioAtivo(usuario.getUsuarioAtivo());

        return retorno;
    }

    public static List<UsuarioResponseDTO> retornarTodosUsuarios(List<Usuario> usuarios) {
        List<UsuarioResponseDTO> usuarioList = new ArrayList<>();
        usuarios.forEach(usuario -> usuarioList.add(UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nomeUsuario(usuario.getNomeUsuario())
                .email(usuario.getEmail())
                .usuarioAtivo(usuario.getUsuarioAtivo())
                .build()));
        return usuarioList;
    }
}
