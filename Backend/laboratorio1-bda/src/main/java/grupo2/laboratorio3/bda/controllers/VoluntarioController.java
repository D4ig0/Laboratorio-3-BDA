package grupo2.laboratorio3.bda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import grupo2.laboratorio3.bda.models.Habilidad;
import grupo2.laboratorio3.bda.models.Voluntario;
import grupo2.laboratorio3.bda.services.VoluntarioService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/voluntarios")
public class VoluntarioController {
    @Autowired
    VoluntarioService voluntarioService;

    @PostMapping("/")
    public ResponseEntity createVoluntario(@RequestParam String nombre, @RequestParam String correo, @RequestParam String password, @RequestParam List<Habilidad> habilidades){
        try{
            voluntarioService.createVoluntario(nombre, correo, password, habilidades);
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

    @GetMapping("/")
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
    public ResponseEntity deleteVoluntario(@PathVariable("id") String correo){
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
}
