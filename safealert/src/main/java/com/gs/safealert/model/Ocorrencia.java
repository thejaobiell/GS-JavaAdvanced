package com.gs.safealert.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ocorrencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "postagem_id")
    private Postagem postagem;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private StatusOcorrencia status;

    @Column(name = "data_ocorrencia", nullable = false)
    private LocalDateTime dataOcorrencia = LocalDateTime.now();

    public enum StatusOcorrencia {
        Antiga,
        Recente,
        Atual
    }
}
