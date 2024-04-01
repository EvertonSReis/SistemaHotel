package com.esr.MadeHotel.modelo.entidade;

import com.esr.MadeHotel.modelo.enums.EstadoEnum;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Endereco {

    private String logradouro;

    @Column(name = "numero_endereco")
    private String numeroEndereco;

    private String cidade;

    private EstadoEnum estado;

    private Long cep;

    @Column(name = "codigo_pais")
    private Long codigoPais;
}
