package domain.entities.Models.Usuarios;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idRolXPermiso")
@Entity
@Table (name = "rolxpermiso")
public class Rolxpermiso {
    @Id
    @GeneratedValue
    private int idRolXPermiso;

    
    @ManyToOne
    @JoinColumn(name = "idPermiso", referencedColumnName = "idPermiso")
    private Permiso permiso;

    
    @ManyToOne
    @JoinColumn(name = "idRol", referencedColumnName = "idRol")
    private Rol rol;

    public int getIdRolXPermiso() {
        return idRolXPermiso;
    }

    public void setIdRolXPermiso(int idRolXPermiso) {
        this.idRolXPermiso = idRolXPermiso;
    }
    
    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
