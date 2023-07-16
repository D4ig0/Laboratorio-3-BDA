package lab3.tbdlab3.controllers;

import lab3.tbdlab3.models.Habilidad;
import lab3.tbdlab3.models.Voluntario;
import lab3.tbdlab3.services.VoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/voluntarios")
public class VoluntarioController {
    @Autowired
    VoluntarioService voluntarioService;

    @PostMapping
    public ResponseEntity createVoluntario(@RequestBody Voluntario voluntario){
        try{
            voluntarioService.createVoluntario(voluntario.getNombre(), voluntario.getCorreo(), voluntario.getPassword(), voluntario.getHabilidades());
            return ResponseEntity.ok(null);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/{correo}")
    public ResponseEntity<Voluntario> getVoluntario(@PathVariable("correo") String correo){
        try {
            Voluntario voluntario = voluntarioService.getVoluntarioByCorreo(correo);
            return ResponseEntity.ok(voluntario);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().header("message", e.getMessage()).build();
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Voluntario>> getAllVoluntarios(){
        return ResponseEntity.ok(voluntarioService.getAllVoluntarios());
    }

    @PutMapping("/{correo}")
    public ResponseEntity updateVoluntario(@PathVariable("correo") String correo, @RequestBody Voluntario voluntario){
        try {
            voluntario.setCorreo(correo);
            voluntarioService.updateVoluntario(voluntario);
            return ResponseEntity.ok(null);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{correo}")
    public ResponseEntity deleteVoluntario(@PathVariable("correo") String correo){
        try {
            voluntarioService.deleteVoluntario(correo);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/count")
    public ResponseEntity<List<Voluntario>> countHabilidades(){
        try {
            return ResponseEntity.ok(voluntarioService.countHabilidades());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
}
