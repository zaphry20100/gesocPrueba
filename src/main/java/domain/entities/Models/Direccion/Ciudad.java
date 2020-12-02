package domain.entities.Models.Direccion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCiudad")
@Entity
@Table(name = "ciudad")
public class Ciudad {

    @Id
    @GeneratedValue
    public int idCiudad;

    @Column (name = "id")
    public String id;

    @Column (name = "nombre")
    public String nombre;

    
    @ManyToOne
    @JoinColumn(name = "idPais", referencedColumnName = "idPais")
    public Pais pais;

    
    @ManyToOne
    @JoinColumn(name = "idProvincia", referencedColumnName = "idProvincia")
    public Provincia provincia;

    
    @OneToMany (mappedBy = "ciudad", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    public List<Direccion> direcciones;

    public Ciudad() {
        direcciones = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
}
