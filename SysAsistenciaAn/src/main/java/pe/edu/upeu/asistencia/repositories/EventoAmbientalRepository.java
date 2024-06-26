/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.asistencia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.asistencia.models.EventoAmbiental;

import java.util.Optional;

/**
 *
 * @author EP-Ing_Sist.-CALIDAD
 */
@Repository
public interface EventoAmbientalRepository extends JpaRepository<EventoAmbiental, Long> {
    Optional<EventoAmbiental> findByTitulo(String titulo);
}
