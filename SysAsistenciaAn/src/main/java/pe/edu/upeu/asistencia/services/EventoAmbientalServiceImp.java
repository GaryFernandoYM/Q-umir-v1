/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upeu.asistencia.exceptions.AppException;
import pe.edu.upeu.asistencia.exceptions.ResourceNotFoundException;

import pe.edu.upeu.asistencia.models.EventoAmbiental;
import pe.edu.upeu.asistencia.repositories.EventoAmbientalRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
@RequiredArgsConstructor
@Service
@Transactional
public class EventoAmbientalServiceImp implements EventoAmbientalService {

    @Autowired
    private EventoAmbientalRepository eventoRepo;

    @Override
    public EventoAmbiental save(EventoAmbiental evento) {
        try {
            return eventoRepo.save(evento);
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<EventoAmbiental> findAll() {
        try {
            return eventoRepo.findAll();
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        EventoAmbiental evento = eventoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento ambiental no existe con id: " + id));

        eventoRepo.delete(evento);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;
    }

    @Override
    public EventoAmbiental getEventoAmbientalById(Long id) {
        EventoAmbiental findEvento = eventoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento ambiental no existe con id: " + id));
        return findEvento;
    }

    @Override
    public EventoAmbiental update(EventoAmbiental evento, Long id) {
        EventoAmbiental eventoExistente = eventoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento ambiental no existe con id: " + id));
        eventoExistente.setTitulo(evento.getTitulo());
        eventoExistente.setDescripcion(evento.getDescripcion());
        eventoExistente.setFecha(evento.getFecha());
        eventoExistente.setHora(evento.getHora());
        eventoExistente.setUbicacion(evento.getUbicacion());
        return eventoRepo.save(eventoExistente);
    }
}
