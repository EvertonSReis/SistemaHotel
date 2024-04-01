package com.esr.MadeHotel.servicos;

import com.esr.MadeHotel.exception.EmailJaCadastradoException;
import com.esr.MadeHotel.exception.SenhaInvalidaException;
import com.esr.MadeHotel.modelo.dtos.UsuarioCadastroRequestDTO;
import com.esr.MadeHotel.modelo.dtos.UsuarioLoginRequestDTO;
import com.esr.MadeHotel.modelo.dtos.UsuarioResponseDTO;
import com.esr.MadeHotel.modelo.entidade.login.Usuario;
import com.esr.MadeHotel.modelo.enums.EValidacao;
import com.esr.MadeHotel.repositorios.UsuarioRepositorio;
import com.esr.MadeHotel.util.MensagensUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UsuarioServicoTest {

    @InjectMocks UsuarioServico servico;

    @Mock UsuarioRepositorio usuarioRepositorio;

    @Test
    @DisplayName("Deveria cadastrar novo usuário.")
    void deveriaCadastrarNovoUsuario(){
        UsuarioCadastroRequestDTO dto = new UsuarioCadastroRequestDTO();
        dto.setNomeUsuario("Usuario Teste");
        dto.setEmail("usuario@teste.com");
        dto.setSenha("123456");
        dto.setConfirmarSenha("123456");

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setEmail(dto.getEmail());

        doReturn(usuario).when(usuarioRepositorio).save(any());
        UsuarioResponseDTO retorno = servico.cadastrarNovoUsuario(dto);

        assertEquals(retorno.getNomeUsuario(), dto.getNomeUsuario());
        assertEquals(retorno.getEmail(), dto.getEmail());
        verify(usuarioRepositorio, times(1)).save(any());
    }

    @Test
    @DisplayName("Deveria gerar erro ao informar senha correspondente inválida.")
    void deveriaGerar400AoConfirmarSenhaInvalida(){
        try (MockedStatic<MensagensUtils> mensagens = Mockito.mockStatic(MensagensUtils.class)){
            mensagens.when(() -> MensagensUtils.getEnumLabel(any())).thenReturn("teste");

            Integer codigo = -2;
            UsuarioCadastroRequestDTO dto = new UsuarioCadastroRequestDTO();
            dto.setNomeUsuario("Usuario Teste");
            dto.setEmail("usuario@teste.com");
            dto.setSenha("123456");
            dto.setConfirmarSenha("1234567");

            SenhaInvalidaException exception =
                assertThrows(SenhaInvalidaException.class, () -> servico.cadastrarNovoUsuario(dto));
            assertEquals(exception.getCodigo(), codigo);
        }
    }

    @Test
    @DisplayName("Deveria gerar erro ao informar um email que já tenha cadastro.")
    void deveriaGerar400AoCadastrarEmailJaCadastrado(){
        try (MockedStatic<MensagensUtils> mensagens = Mockito.mockStatic(MensagensUtils.class)){
            mensagens.when(() -> MensagensUtils.getEnumLabel(any())).thenReturn("teste");

            Integer codigo = -3;
            UsuarioCadastroRequestDTO dto = new UsuarioCadastroRequestDTO();
            dto.setNomeUsuario("Usuario Teste");
            dto.setEmail("usuario@teste.com");
            dto.setSenha("123456");
            dto.setConfirmarSenha("123456");

            doThrow(new EmailJaCadastradoException(EValidacao.EMAIL_JA_EXISTE))
                    .when(usuarioRepositorio).findByEmail(anyString());

            EmailJaCadastradoException exception =
                    assertThrows(EmailJaCadastradoException.class, () -> servico.cadastrarNovoUsuario(dto));
            assertEquals(exception.getCodigo(), codigo);
        }
    }

    @Test
    @DisplayName("Deveria realizar login com sucesso.")
    void deveriaRealizarLogin(){

        UsuarioLoginRequestDTO dto = new UsuarioLoginRequestDTO();
        dto.setEmail("usuario@teste.com");
        dto.setSenha("123456");

        Usuario usuario = buildUsuario();

        doReturn(Optional.of(usuario)).when(usuarioRepositorio).findByEmail(anyString());
        UsuarioResponseDTO retorno = servico.loginUsuario(dto);
        assertEquals(retorno.getId(), usuario.getId());
        assertEquals(retorno.getNomeUsuario(), usuario.getNomeUsuario());
        assertEquals(retorno.getEmail(), usuario.getEmail());
        assertEquals(retorno.getUsuarioAtivo(), usuario.getUsuarioAtivo());

        verify(usuarioRepositorio, times(1)).findByEmail(anyString());
    }

    @Test
    @DisplayName("Deveria gerar erro ao informar login ou senha inválido.")
    void deveriaGerar400AoInformarLoginOuSenhaInvalida(){
        try (MockedStatic<MensagensUtils> mensagens = Mockito.mockStatic(MensagensUtils.class)){
            mensagens.when(() -> MensagensUtils.getEnumLabel(any())).thenReturn("teste");

            Integer codigo = -4;
            UsuarioLoginRequestDTO dto = new UsuarioLoginRequestDTO();
            dto.setEmail("usuario@teste.com");
            dto.setSenha("123456");

            SenhaInvalidaException exception =
                    assertThrows(SenhaInvalidaException.class, () -> servico.loginUsuario(dto));
            assertEquals(exception.getCodigo(), codigo);
        }
    }

    @Test
    @DisplayName("Deveria atualizar se usuário ativo ou inativo.")
    void deveriaAtivarOuDesativarUsuario(){

        Long id = 1L;
        Usuario usuario = buildUsuario();
        Usuario statusUsuarioAlterado = buildUsuario();
        statusUsuarioAlterado.setUsuarioAtivo(false);
        doReturn(Optional.of(usuario)).when(usuarioRepositorio).findById(anyLong());
        doReturn(statusUsuarioAlterado).when(usuarioRepositorio).save(any());
        UsuarioResponseDTO retorno = servico.atualizaDesatualizaUsuario(id);

        assertEquals(retorno.getNomeUsuario(), statusUsuarioAlterado.getNomeUsuario());
        assertEquals(retorno.getEmail(), statusUsuarioAlterado.getEmail());
        assertEquals(retorno.getUsuarioAtivo(), statusUsuarioAlterado.getUsuarioAtivo());

        verify(usuarioRepositorio, times(1)).findById(anyLong());
        verify(usuarioRepositorio, times(1)).save(any());
    }

    private Usuario buildUsuario(){
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("usuario@teste.com");
        usuario.setSenha("958D51602BBFBD18B2A084BA848A827C29952BFEF170C936419B0922994C0589");
        usuario.setNomeUsuario("Usuario teste");
        usuario.setUsuarioAtivo(true);

        return usuario;
    }
}
