/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.asistencia.models.EventoAmbiental;
import pe.edu.upeu.asistencia.services.EventoAmbientalService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/asis/eventoambiental")
public class EventoAmbientalController {

    @Autowired
    private EventoAmbientalService eventoService;

    @GetMapping("/list")
    public ResponseEntity<List<EventoAmbiental>> listEventos() {
        List<EventoAmbiental> eventos = eventoService.findAll();
        return ResponseEntity.ok(eventos);
    }

    @PostMapping("/crear")
    public ResponseEntity<EventoAmbiental> createEvento(@RequestBody EventoAmbiental evento) {
        EventoAmbiental data = eventoService.save(evento);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<EventoAmbiental> getEventoById(@PathVariable Long id) {
        EventoAmbiental evento = eventoService.getEventoAmbientalById(id);
        return ResponseEntity.ok(evento);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEvento(@PathVariable Long id) {
        Map<String, Boolean> response = eventoService.delete(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<EventoAmbiental> updateEvento(@PathVariable Long id, @RequestBody EventoAmbiental eventoDetails) {
        EventoAmbiental updatedEvento = eventoService.update(eventoDetails, id);
        return ResponseEntity.ok(updatedEvento);
    }
}