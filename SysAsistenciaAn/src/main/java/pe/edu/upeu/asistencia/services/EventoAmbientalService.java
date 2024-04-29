/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.asistencia.models.Actividad;
import pe.edu.upeu.asistencia.models.EventoAmbiental;
import pe.edu.upeu.asistencia.repositories.EventoAmbientalRepository;

import java.util.List;
import java.util.Map;


/**
 *
 * @author DELL
 */


public interface EventoAmbientalService {
    EventoAmbiental save(EventoAmbiental evento);
    List<EventoAmbiental> findAll();
    Map<String, Boolean> delete(Long id);
    EventoAmbiental getEventoAmbientalById(Long id);
    EventoAmbiental update(EventoAmbiental evento, Long id);
}
