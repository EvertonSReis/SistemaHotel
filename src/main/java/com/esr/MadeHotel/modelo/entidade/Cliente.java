package com.esr.MadeHotel.modelo.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cliente extends Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Long idPessoa;

    @Column(name = "nome_pessoa")
    private String nomePessoa;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @Column(name = "data_nascimento")
    private Date dataNascimento;

    private String telefone;

    private String celular;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reserva> reserva;
}
