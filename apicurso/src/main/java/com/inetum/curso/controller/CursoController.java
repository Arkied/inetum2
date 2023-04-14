package com.inetum.curso.controller;

import com.inetum.curso.entity.Curso;
import com.inetum.curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(curso));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        Optional<Curso> oCurso = cursoService.findById(id);
        if (!oCurso.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Curso cursoDetails, @PathVariable Long id) {
        Optional<Curso> curso = cursoService.findById(id);
        if (!curso.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        curso.get().setDescripcion(cursoDetails.getDescripcion());

        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(curso.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!cursoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cursoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<Curso> readAll() {
        List<Curso> cursos = StreamSupport
                .stream(cursoService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return cursos;
    }
}
