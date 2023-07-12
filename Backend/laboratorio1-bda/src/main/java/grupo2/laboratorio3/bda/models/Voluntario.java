package grupo2.laboratorio3.bda.models;

import java.util.List;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Voluntario {
    @BsonId
    ObjectId _id;
    private String nombre;
    private String correo;
    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private List<Habilidad> habilidades;
    // @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    // private Integer numeroHabilidades;
}
