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
import pe.edu.upeu.asistencia.models.RecompensaAmbiental;
import pe.edu.upeu.asistencia.repositories.RecompensaAmbientalRepository;

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
public class RecompensaAmbientalServiceImp implements RecompensaAmbientalService {

    @Autowired
    private RecompensaAmbientalRepository recompensaRepo;

    @Override
    public RecompensaAmbiental save(RecompensaAmbiental recompensa) {
        try {
            return recompensaRepo.save(recompensa);
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<RecompensaAmbiental> findAll() {
        try {
            return recompensaRepo.findAll();
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        RecompensaAmbiental recompensa = recompensaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recompensa ambiental no existe con id: " + id));

        recompensaRepo.delete(recompensa);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;
    }

    @Override
    public RecompensaAmbiental getRecompensaAmbientalById(Long id) {
        RecompensaAmbiental findRecompensa = recompensaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento ambiental no existe con id: " + id));
        return findRecompensa;
    }

    @Override
    public RecompensaAmbiental update(RecompensaAmbiental recompensa, Long id) {
        return null;
    }

    @Override
    public RecompensaAmbiental updateRecompensa(RecompensaAmbiental recompensa, Long id) {
        RecompensaAmbiental recompensaExistente = recompensaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recompensa ambiental no existe con id: " + id));

        recompensaExistente.setNombre(recompensa.getNombre());
        recompensaExistente.setDescripcion(recompensa.getDescripcion());
        recompensaExistente.setCondiciones(recompensa.getCondiciones());
        recompensaExistente.setPuntos(recompensa.getPuntos());

        return recompensaRepo.save(recompensaExistente);
    }
}
