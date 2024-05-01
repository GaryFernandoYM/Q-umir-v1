/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.asistencia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.asistencia.models.RecompensaAmbiental;
import pe.edu.upeu.asistencia.services.RecompensaAmbientalService;

import java.util.List;
import java.util.Map;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/asis/recompensaambiental")
public class RecompensaAmbientalController {

    @Autowired
    private RecompensaAmbientalService recompensaService;

    @GetMapping("/list")
    public ResponseEntity<List<RecompensaAmbiental>> listRecompensas() {
        List<RecompensaAmbiental> eventos = recompensaService.findAll();
        return ResponseEntity.ok(eventos);
    }

    @PostMapping("/crear")
    public ResponseEntity<RecompensaAmbiental> createRecompensa(@RequestBody RecompensaAmbiental recompensa) {
        RecompensaAmbiental data = recompensaService.save(recompensa);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<RecompensaAmbiental> getRecompensaById(@PathVariable Long id) {
        RecompensaAmbiental recompensa = recompensaService.getRecompensaAmbientalById(id);
        return ResponseEntity.ok(recompensa);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRecompensa(@PathVariable Long id) {
        Map<String, Boolean> response = recompensaService.delete(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<RecompensaAmbiental> updateRecompensa(@PathVariable Long id, @RequestBody RecompensaAmbiental recompensaDetails) {
        RecompensaAmbiental updatedRecompensa = recompensaService.update(recompensaDetails, id);
        return ResponseEntity.ok(updatedRecompensa);
    }
}