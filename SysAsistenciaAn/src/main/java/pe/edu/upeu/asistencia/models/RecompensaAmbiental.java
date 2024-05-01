package pe.edu.upeu.asistencia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @Table(name = "recompensa_ambiental")
    public class RecompensaAmbiental {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "recompensa_id")
        private Long recompensaId;

        @Column(name = "nombre", nullable = false)
        private String nombre;

        @Column(name = "descripcion", nullable = false)
        private String descripcion;

        @Column(name = "condiciones", nullable = false)
        private String condiciones;

        @Column(name = "puntos", nullable = false)
        private String puntos;
    }

