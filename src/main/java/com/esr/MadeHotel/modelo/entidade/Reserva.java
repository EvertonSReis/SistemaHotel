package com.esr.MadeHotel.modelo.entidade;

import com.esr.MadeHotel.modelo.enums.SituacaoReservaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_quarto")
    private Quarto quarto;

    @Column(name = "data_programada_entrada")
    private LocalDateTime dataProgramadaEntrada;

    @Column(name = "data_programada_saida")
    private LocalDateTime dataProgramadaSaida;

    @Column(name = "data_real_entrada")
    private LocalDateTime dataRealEntrada;

    @Column(name = "data_real_saida")
    private LocalDateTime dataRealSaida;

    @Column(name = "situacao_reserva")
    private SituacaoReservaEnum situacaoReserva;

    @Column(name = "valor_Reserva")
    private BigDecimal valorDaReserva;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
