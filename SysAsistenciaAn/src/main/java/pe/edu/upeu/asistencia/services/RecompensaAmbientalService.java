/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.asistencia.services;

import pe.edu.upeu.asistencia.models.RecompensaAmbiental;
import pe.edu.upeu.asistencia.models.RecompensaAmbiental;

import java.util.List;
import java.util.Map;


/**
 *
 * @author DELL
 */


public interface RecompensaAmbientalService {
    RecompensaAmbiental save(RecompensaAmbiental recompensa);
    List<RecompensaAmbiental> findAll();
    Map<String, Boolean> delete(Long id);
    RecompensaAmbiental getRecompensaAmbientalById(Long id);
    RecompensaAmbiental update(RecompensaAmbiental recompensa, Long id);

    RecompensaAmbiental updateRecompensa(RecompensaAmbiental recompensa, Long id);
}
