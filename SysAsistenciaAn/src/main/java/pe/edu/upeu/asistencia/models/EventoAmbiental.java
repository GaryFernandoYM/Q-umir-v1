/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author EP-Ing_Sist.-CALIDAD
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "eventos_ambientales")
public class EventoAmbiental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    private String estado;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate fecha;

    @JsonFormat(pattern = "HH:mm:ss")
    @Basic(optional = false)
    @Column(name = "hora", nullable = false)
    @Temporal(TemporalType.TIME)
    private LocalTime hora;

    private String ubicacion;
}
