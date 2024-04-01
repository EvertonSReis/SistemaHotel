package com.esr.MadeHotel.controladores;

import com.esr.MadeHotel.modelo.dtos.UsuarioCadastroRequestDTO;
import com.esr.MadeHotel.modelo.dtos.UsuarioLoginRequestDTO;
import com.esr.MadeHotel.servicos.UsuarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    UsuarioServico servico;

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody UsuarioCadastroRequestDTO requestDTO){
        return new ResponseEntity<>(servico.cadastrarNovoUsuario(requestDTO), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<?> loginUsuario(@RequestBody UsuarioLoginRequestDTO requestDTO){
        return new ResponseEntity<>(servico.loginUsuario(requestDTO), HttpStatus.OK);
    }

    @PutMapping("ativarOuDesativar/{id}")
    public ResponseEntity<?> ativarDesativarUsuario(
            @PathVariable("id") Long id){
        return new ResponseEntity<>(servico.atualizaDesatualizaUsuario(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listarTodos(){
        return new ResponseEntity<>(servico.listarTodosUsuarios(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable("id") Long id){
        servico.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
