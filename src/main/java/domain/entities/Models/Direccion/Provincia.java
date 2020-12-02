package domain.entities.Models.Direccion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idProvincia")
@Entity
@Table(name = "provincia")
public class Provincia {

    @Id
    @GeneratedValue
    public int idProvincia;

    @Column (name = "id")
    public String id;

    @Column (name = "nombre")
    public String nombre;

    
    @ManyToOne
    @JoinColumn(name = "idPais", referencedColumnName = "idPais")
    public Pais pais;

    
    @OneToMany(mappedBy = "provincia", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    public List<Ciudad> ciudades;

    public Provincia() {
        ciudades = new ArrayList<>();
    }
}
