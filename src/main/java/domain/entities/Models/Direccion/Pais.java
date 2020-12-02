package domain.entities.Models.Direccion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPais")
@Entity
@Table (name = "pais")
public class Pais {

    @Id
    @GeneratedValue
    public int idPais;

    @Column (name = "id")
    public String id;

    @Column (name = "nombre")
    public String nombre;

    
    @OneToMany(mappedBy = "pais", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    public List<Provincia> provincias;

    
    @OneToMany(mappedBy = "pais", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    public List<Ciudad> ciudades;

    public Pais() {
        provincias = new ArrayList<>();
        ciudades = new ArrayList<>();
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

    public List<Provincia> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<Provincia> provincias) {
        this.provincias = provincias;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }
}
