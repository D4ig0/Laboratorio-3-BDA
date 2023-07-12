package grupo2.laboratorio3.bda.repositories;

import java.util.List;
import java.util.Optional;

import grupo2.laboratorio3.bda.models.Habilidad;
import grupo2.laboratorio3.bda.models.Voluntario;

public interface IVoluntarioRepository {
    void createVoluntario(Voluntario voluntario);
    Optional<Voluntario> getVoluntarioByCorreo(String correo);
    List<Voluntario> getAllVoluntarios();
    void updateVoluntario(Voluntario voluntario);
    void deleteVoluntario(String correo);
    void addHabilidadesVoluntario(String correo, List<Habilidad> habilidad);
    List<Habilidad> getHabilidadesVoluntario(String correo);
    void deleteHabilidadVoluntario(String correo, String Habilidad);
    void deleteHabilidadesVoluntario(String correo);
    void countHabilidades();
}
