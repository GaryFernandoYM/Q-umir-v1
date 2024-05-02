package pe.edu.upeu.asistencia.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upeu.asistencia.exceptions.AppException;
import pe.edu.upeu.asistencia.exceptions.ResourceNotFoundException;

import pe.edu.upeu.asistencia.models.Comentario;
import pe.edu.upeu.asistencia.repositories.ComentarioRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
public class ComentarioServiceImp implements ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepo;

    @Override
    public Comentario save(Comentario comentario) {
        try {
            comentario.setFecha(LocalDate.now()); // Establecer la fecha actual al guardar el comentario
            return comentarioRepo.save(comentario);
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Comentario> findAll() {
        try {
            return comentarioRepo.findAll();
        } catch (Exception e) {
            throw new AppException("Error-" + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Map<String, Boolean> delete(Integer id) {
        return Map.of();
    }

    @Override
    public Comentario getComentarioById(Integer id) {
        return null;
    }

    @Override
    public Comentario update(Comentario comentario, Integer id) {
        return null;
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        Comentario comentario = comentarioRepo.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException("Comentario no existe con id: " + id));

        comentarioRepo.delete(comentario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);

        return response;
    }

    @Override
    public Comentario getComentarioById(Long id) {
        return comentarioRepo.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException("Comentario no existe con id: " + id));
    }

    @Override
    public Comentario update(Comentario comentario, Long id) {
        Comentario comentarioExistente = comentarioRepo.findById(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException("Comentario no existe con id: " + id));
        // Actualizar los campos del comentarioExistente con los valores del comentario recibido
        comentarioExistente.setUsuarioId(comentario.getUsuarioId());
        comentarioExistente.setTipoEntidad(comentario.getTipoEntidad());
        comentarioExistente.setContenido(comentario.getContenido());
        return comentarioRepo.save(comentarioExistente);
    }

    @Override
    public List<Comentario> findComentariosByEvento(Long idEvento) {
        // Implementación para buscar comentarios por evento
        return List.of();
    }
}
