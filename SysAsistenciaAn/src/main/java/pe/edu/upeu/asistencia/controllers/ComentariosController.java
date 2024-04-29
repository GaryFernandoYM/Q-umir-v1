package pe.edu.upeu.asistencia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.asistencia.models.Comentarios;
import pe.edu.upeu.asistencia.services.ComentariosService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/asis/comentarios")
public class ComentariosController {

    @Autowired
    private ComentariosService comentariosService;

    @GetMapping("/list")
    public ResponseEntity<List<Comentarios>> listComentarios() {
        List<Comentarios> comentarios = comentariosService.findAll();
        return ResponseEntity.ok(comentarios);
    }

    @PostMapping("/crear")
    public ResponseEntity<Comentarios> createComentario(@RequestBody Comentarios comentario) {
        Comentarios data = comentariosService.save(comentario);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Comentarios> getComentarioById(@PathVariable Long id) {
        Comentarios comentario = comentariosService.getComentarioById(id);
        return ResponseEntity.ok(comentario);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteComentario(@PathVariable Long id) {
        Map<String, Boolean> response = comentariosService.delete(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Comentarios> updateComentario(@PathVariable Long id, @RequestBody Comentarios comentarioDetails) {
        Comentarios updatedComentario = comentariosService.update(comentarioDetails, id);
        return ResponseEntity.ok(updatedComentario);
    }
}
