package domain.entities.Models.Usuarios;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPermiso")
@Entity
@Table (name = "permiso")
public class Permiso {

    @Id
    @GeneratedValue
    private int idPermiso;

    @Column(name = "descripcion")
    private String descripcion;


    @OneToMany(mappedBy = "permiso", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Rolxpermiso> rolxpermisoList;

    public Permiso() {
        rolxpermisoList = new ArrayList<>();
    }


    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
