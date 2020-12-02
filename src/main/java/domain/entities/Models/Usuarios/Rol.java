package domain.entities.Models.Usuarios;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idRol")
@Entity
@Table (name = "rol")
public class Rol {

    @Id
    @GeneratedValue
    private int idRol;

    @Column (name = "descripcion")
    private String descripcion;

    @JsonIgnore
    @OneToMany(mappedBy = "rol", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Rolxpermiso> rolxpermisoList;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "rol", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Usuario> usuarios;

    public Rol() {
        rolxpermisoList = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
