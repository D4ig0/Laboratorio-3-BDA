package lab3.tbdlab3.repositories;

import lab3.tbdlab3.models.Habilidad;
import lab3.tbdlab3.models.Voluntario;

import java.util.List;
import java.util.Optional;


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
    Integer countHabilidades();
}
