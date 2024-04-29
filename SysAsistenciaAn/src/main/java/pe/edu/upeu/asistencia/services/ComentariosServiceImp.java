package pe.edu.upeu.asistencia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.asistencia.models.Comentarios;
import pe.edu.upeu.asistencia.repositories.ComentariosRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ComentariosServiceImp implements ComentariosService {

    @Autowired
    private ComentariosRepository comentariosRepository;

    @Override
    public Comentarios save(Comentarios comentario) {
        return comentariosRepository.save(comentario);
    }

    @Override
    public List<Comentarios> findAll() {
        return comentariosRepository.findAll();
    }

    @Override
    public Map<String, Boolean> delete(Long id) {
        comentariosRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @Override
    public Comentarios getComentarioById(Long id) {
        Optional<Comentarios> comentario = comentariosRepository.findById(id);
        return comentario.orElse(null);
    }

    @Override
    public Comentarios update(Comentarios comentario, Long id) {
        Optional<Comentarios> existingComentario = comentariosRepository.findById(id);
        if (existingComentario.isPresent()) {
            existingComentario.get().setContenido(comentario.getContenido());
            existingComentario.get().setFecha(comentario.getFecha());
            existingComentario.get().setUsuarioId(comentario.getUsuarioId());
            existingComentario.get().setTipoEntidad(comentario.getTipoEntidad());
            existingComentario.get().setEntidadId(comentario.getEntidadId());
            return comentariosRepository.save(existingComentario.get());
        }
        return null;
    }
}
