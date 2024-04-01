package com.esr.MadeHotel.modelo.entidade;

import com.esr.MadeHotel.modelo.enums.SituacaoQuartoEnum;
import com.esr.MadeHotel.modelo.enums.TipoQuartoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_quarto")
    private Long id;

    private TipoQuartoEnum tipoQuarto;

    @Column(name = "numero_quarto")
    private Long numeroQuarto;

    @Column(name = "situacao_quarto")
    private SituacaoQuartoEnum situacaoQuartoEnum;

    @OneToMany(mappedBy = "quarto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reserva> reservas;

}
