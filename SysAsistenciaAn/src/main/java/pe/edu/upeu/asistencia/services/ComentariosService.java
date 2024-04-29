package pe.edu.upeu.asistencia.services;

import pe.edu.upeu.asistencia.models.Comentarios;

import java.util.List;
import java.util.Map;

public interface ComentariosService {
    Comentarios save(Comentarios comentario);
    List<Comentarios> findAll();
    Map<String, Boolean> delete(Long id);
    Comentarios getComentarioById(Long id);
    Comentarios update(Comentarios comentario, Long id);
}
