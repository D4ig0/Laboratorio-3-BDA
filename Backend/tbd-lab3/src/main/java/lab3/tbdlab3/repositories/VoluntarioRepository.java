package lab3.tbdlab3.repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import lab3.tbdlab3.models.Habilidad;
import lab3.tbdlab3.models.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;


import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class VoluntarioRepository implements IVoluntarioRepository {

    @Autowired
    MongoDatabase database;

    @Override
    public void createVoluntario(Voluntario voluntario) {
        MongoCollection<Voluntario> collection = database.getCollection("voluntario", Voluntario.class);
        collection.insertOne(voluntario);
    }

    @Override
    public Optional<Voluntario> getVoluntarioByCorreo(String correo) {
        MongoCollection<Voluntario> collection = database.getCollection("voluntario", Voluntario.class);
        Bson filter = Filters.eq("correo", correo);
        Voluntario voluntario = collection.find(filter).first();
        return Optional.ofNullable(voluntario);
    }

    @Override
    public List<Voluntario> getAllVoluntarios() {
        MongoCollection<Voluntario> collection = database.getCollection("voluntario", Voluntario.class);
        List<Voluntario> voluntarios = collection.find().into(new ArrayList<>());
        return voluntarios;
    }

    @Override
    public void updateVoluntario(Voluntario voluntario) {
        MongoCollection<Voluntario> collection = database.getCollection("voluntario", Voluntario.class);
        collection.replaceOne(Filters.eq("_id", voluntario.get_id()), voluntario);
    }

    @Override
    public void deleteVoluntario(String correo) {
        MongoCollection<Voluntario> collection = database.getCollection("voluntario", Voluntario.class);
        collection.deleteOne(Filters.eq("correo", correo));
    }

    public void addHabilidadesVoluntario(String correo, List<Habilidad> habilidades) {
        Voluntario voluntario = getVoluntarioByCorreo(correo)
                .orElseThrow(() -> new IllegalArgumentException("Voluntario no encontrado"));

        voluntario.getHabilidades().addAll(habilidades);
        updateVoluntario(voluntario);
    }

    @Override
    public List<Habilidad> getHabilidadesVoluntario(String correo) {
        Voluntario voluntario = getVoluntarioByCorreo(correo)
                .orElseThrow(() -> new IllegalArgumentException("Voluntario no encontrado"));

        return voluntario.getHabilidades();
    }

    public void deleteHabilidadVoluntario(String correo, String codigo) {
        MongoCollection<Voluntario> collection = database.getCollection("voluntario", Voluntario.class);
        Bson filter = Filters.eq("correo", correo);
        Bson update = Updates.pull("habilidades", Filters.eq("codigo", codigo));
        collection.updateOne(filter, update);
    }

    @Override
    public void deleteHabilidadesVoluntario(String correo) {
        MongoCollection<Voluntario> collection = database.getCollection("voluntario", Voluntario.class);
        collection.updateOne(Filters.eq("correo", correo), Updates.unset("habilidad"));
    }

    @Override
    public Integer countHabilidades() {
        MongoCollection<Voluntario> collection = database.getCollection("voluntario", Voluntario.class);
        List<Voluntario> voluntarios = collection.find().into(new ArrayList<>());
        Integer count = 0;
        ArrayList lol = new ArrayList();
        for (Voluntario voluntario : voluntarios) {
            for(Habilidad habilidad : voluntario.getHabilidades()){
                if(!lol.contains(habilidad.getCodigo())){
                    System.out.println(habilidad.getCodigo());
                    count++;
                    lol.add(habilidad.getCodigo());
                }
            }
        }
        return count;
    }
}

