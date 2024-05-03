package pe.edu.upeu.asistencia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.asistencia.models.Comentario;
import pe.edu.upeu.asistencia.services.ComentarioService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/asis/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/list")
    public ResponseEntity<List<Comentario>> listComentarios() {
        List<Comentario> comentarios = comentarioService.findAll();
        return ResponseEntity.ok(comentarios);
    }

    @PostMapping("/crear")
    public ResponseEntity<Comentario> createComentario(@RequestBody Comentario comentario) {
        Comentario data = comentarioService.save(comentario);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Comentario> getComentarioById(@PathVariable Long id) {
        Comentario comentario = comentarioService.getComentarioById(id);
        return ResponseEntity.ok(comentario);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteComentario(@PathVariable Long id) {
        Map<String, Boolean> response = comentarioService.delete(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Comentario> updateComentario(@PathVariable Long id, @RequestBody Comentario comentarioDetails) {
        Comentario updatedComentario = comentarioService.update(comentarioDetails, id);
        return ResponseEntity.ok(updatedComentario);
    }
}
