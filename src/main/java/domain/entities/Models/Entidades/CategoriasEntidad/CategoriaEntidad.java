package domain.entities.Models.Entidades.CategoriasEntidad;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import domain.entities.Models.Entidades.EntidadJuridica;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idcategoriaentidad")
@Entity
@Table (name = "categoriaentidad")
public class CategoriaEntidad {

    @Id
    @GeneratedValue
    private int idcategoriaentidad;

    @Column(name = "descripcion")
    private String descripcion;

    @JsonIgnore
    @OneToMany(mappedBy = "categoriaentidad", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<EntidadJuridica> entidadjuridicas;

    public CategoriaEntidad() {
        entidadjuridicas = new ArrayList<>();
    }

    public int getIdcategoriaentidad() {
        return idcategoriaentidad;
    }

    public void setIdcategoriaentidad(int idcategoriaentidad) {
        this.idcategoriaentidad = idcategoriaentidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<EntidadJuridica> getEntidadjuridicas() {
        return entidadjuridicas;
    }

    public void setEntidadjuridicas(List<EntidadJuridica> entidadjuridicas) {
        this.entidadjuridicas = entidadjuridicas;
    }

    public CategoriaEntidad agregarEntJuridica(EntidadJuridica entJur){
        this.entidadjuridicas.add(entJur);
        return null;
    }
}
