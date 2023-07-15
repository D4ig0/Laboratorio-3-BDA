package lab3.tbdlab3.services;

import lab3.tbdlab3.models.Habilidad;
import lab3.tbdlab3.models.Voluntario;
import lab3.tbdlab3.repositories.IVoluntarioRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class VoluntarioService {
    @Autowired
    IVoluntarioRepository voluntarioRepository;

    public void createVoluntario(@NonNull String nombre, @NonNull String correo, @NonNull String password, List<Habilidad> habilidades) {
        Optional<Voluntario> existingVoluntario = voluntarioRepository.getVoluntarioByCorreo(correo);
        if (existingVoluntario.isPresent()) {
            throw new IllegalArgumentException("Ya existe un voluntario con ese correo.");
        }
        if (!isValidCorreo(correo)) {
            throw new IllegalArgumentException("El correo ingresado no es valido");
        }
        validateHabilidades(habilidades);
        Voluntario voluntario = new Voluntario();
        voluntario.setCorreo(correo);
        voluntario.setNombre(nombre);
        voluntario.setPassword(password);
        voluntario.setHabilidades(habilidades);
        voluntarioRepository.createVoluntario(voluntario);
    }

    public Integer countHabilidades() {
        System.out.println("loool");
        return voluntarioRepository.countHabilidades();
    }

    public Voluntario getVoluntarioByCorreo(String correo) {
        return voluntarioRepository.getVoluntarioByCorreo(correo).orElseThrow(() -> new IllegalArgumentException("No se encontró el voluntario"));
    }

    public List<Voluntario> getAllVoluntarios() {
        return voluntarioRepository.getAllVoluntarios();
    }

    public void updateVoluntario(Voluntario voluntario) {
        validateVoluntario(voluntario.getCorreo());
        validateHabilidades(voluntario.getHabilidades());
        voluntarioRepository.updateVoluntario(voluntario);
    }

    public void deleteVoluntario(String correo) {
        validateVoluntario(correo);
        voluntarioRepository.deleteVoluntario(correo);
    }

    public void agregarHabilidades(String correo, List<Habilidad> habilidades) {
        validateVoluntario(correo);
        Voluntario voluntario = voluntarioRepository.getVoluntarioByCorreo(correo).get();
        voluntario.getHabilidades().addAll(habilidades);
        validateHabilidades(voluntario.getHabilidades());
        voluntarioRepository.addHabilidadesVoluntario(correo, habilidades);
    }

    private void validateVoluntario(String correo) {
        Optional<Voluntario> existingVoluntario = voluntarioRepository.getVoluntarioByCorreo(correo);
        if (!existingVoluntario.isPresent()) {
            throw new IllegalArgumentException("No se encontró el voluntario");
        }
        if (!isValidCorreo(correo)) {
            throw new IllegalArgumentException("El correo ingresado no es valido");
        }
    }

    private boolean isValidCorreo(String correo) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    private void validateHabilidades(List<Habilidad> habilidades) {
        Set<String> codigos = new HashSet<>();
        for (Habilidad habilidad : habilidades) {
            if (codigos.contains(habilidad.getCodigo())) {
                throw new IllegalArgumentException("Las habilidades del voluntario no pueden tener códigos duplicados.");
            }
            codigos.add(habilidad.getCodigo());
        }
    }
}
