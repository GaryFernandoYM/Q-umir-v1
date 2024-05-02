package pe.edu.upeu.asistencia.services;

import pe.edu.upeu.asistencia.models.Comentario;

import java.util.List;
import java.util.Map;

public interface ComentarioService {
    Comentario save(Comentario comentario);
    List<Comentario> findAll();
    Map<String, Boolean> delete(Integer id);
    Comentario getComentarioById(Integer id);
    Comentario update(Comentario comentario, Integer id);

    List<Comentario> findComentariosByEvento(Long idEvento);

    Map<String, Boolean> delete(Long id);

    Comentario getComentarioById(Long id);

    Comentario update(Comentario comentario, Long id);
}
