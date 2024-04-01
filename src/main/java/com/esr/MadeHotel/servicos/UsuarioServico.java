package com.esr.MadeHotel.servicos;

import com.esr.MadeHotel.exception.*;
import com.esr.MadeHotel.modelo.builders.UsuarioBuilder;
import com.esr.MadeHotel.modelo.dtos.UsuarioCadastroRequestDTO;
import com.esr.MadeHotel.modelo.dtos.UsuarioLoginRequestDTO;
import com.esr.MadeHotel.modelo.dtos.UsuarioResponseDTO;
import com.esr.MadeHotel.modelo.entidade.login.Usuario;
import com.esr.MadeHotel.modelo.enums.EValidacao;
import com.esr.MadeHotel.repositorios.UsuarioRepositorio;
import com.esr.MadeHotel.util.SenhasUsuariosUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsuarioServico {

    @Autowired
    private UsuarioRepositorio repositorio;

    public UsuarioResponseDTO cadastrarNovoUsuario(UsuarioCadastroRequestDTO request){
        try {
            verificaSeEmailJaCadastrado(request.getEmail());
            verificarSeConfirmacaoSenhaCorresponde(request.getSenha(), request.getConfirmarSenha());
            Usuario usuario = repositorio.save(
                    UsuarioBuilder.build(
                            request, SenhasUsuariosUtil.criptografarSenha(request.getSenha())));
            return UsuarioBuilder.retornoUsuarioCadastrado(usuario);
        }  catch (SenhaInvalidaException | EmailJaCadastradoException ex) {
            throw ex;
        } catch (Exception e){
            log.error(String.format("Ocorreu um erro ao cadastrar um novo usuário."),e);
            throw new MadeHotelRuntimeException(EValidacao.NAO_IDENTIFICADO);
        }
    }

    public UsuarioResponseDTO loginUsuario(UsuarioLoginRequestDTO request){
        try {
            Optional<Usuario> usuario = buscarPorEmail(request.getEmail());
            verificarSeSenhaPertenceAoUsuario(
                    SenhasUsuariosUtil.criptografarSenha(request.getSenha()), usuario);
            return UsuarioBuilder.retornoUsuarioCadastrado(usuario.get());
        } catch (SenhaInvalidaException ex){
            throw ex;
        } catch (Exception e){
            log.error(String.format("Ocorreu um erro ao fazer o login."),e);
            throw new MadeHotelRuntimeException(EValidacao.NAO_IDENTIFICADO);
        }
    }

    public UsuarioResponseDTO atualizaDesatualizaUsuario(Long id) {
        try {
            Usuario usuario = buscarPorId(id);
            if(usuario.getUsuarioAtivo()){
                usuario.setUsuarioAtivo(false);
            } else {
                usuario.setUsuarioAtivo(true);
            }
            return UsuarioBuilder.retornoUsuarioCadastrado(repositorio.save(usuario));
        } catch (UsuarioNaoEncontradoException ex){
            throw ex;
        } catch (Exception e){
            log.error(String.format("Ocorreu um erro ao ativar ou desativar usuário."),e);
            throw new MadeHotelRuntimeException(EValidacao.NAO_IDENTIFICADO);
        }
    }

    public List<UsuarioResponseDTO> listarTodosUsuarios() {
        return UsuarioBuilder.retornarTodosUsuarios(repositorio.findAll());
    }

    public void deletarUsuario(Long id) {
        Usuario usuario = buscarPorId(id);
        repositorio.delete(usuario);
    }

    private void verificaSeEmailJaCadastrado(String email) {
        Optional<Usuario> emailExiste = buscarPorEmail(email);
        if(emailExiste.isPresent()){
            throw new EmailJaCadastradoException(EValidacao.EMAIL_JA_EXISTE);
        }
    }

    private void verificarSeConfirmacaoSenhaCorresponde(String senha, String confirmarSenha) {
        if(!senha.equals(confirmarSenha))
            throw new SenhaInvalidaException(EValidacao.SENHA_CONFIRMACAO_INVALIDA);
    }

    private void verificarSeSenhaPertenceAoUsuario(String senha, Optional<Usuario> usuario){
        if (usuario.isEmpty() || !usuario.get().getSenha().equals(senha))
            throw new SenhaInvalidaException(EValidacao.EMAIL_SENHA_INCORRETO);
    }

    private Optional<Usuario> buscarPorEmail(String email){
        return repositorio.findByEmail(email);
    }

    private Usuario buscarPorId(Long id){
        return repositorio.findById(id)
                .orElseThrow(() ->
                        new UsuarioNaoEncontradoException(
                                EValidacao.USUARIO_NAO_ENCONTRADO, id.toString()));
    }
}
