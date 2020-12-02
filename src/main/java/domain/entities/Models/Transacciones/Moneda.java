package domain.entities.Models.Transacciones;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idMoneda")
@Entity
@Table (name = "moneda")
public class Moneda {
    @Id
    @GeneratedValue
    public int idMoneda;

    @Column(name = "id")
    public String id;

    @Column(name = "descripcion")
    public String descripcion;

    @Column(name = "simbolo")
    public String simbolo;

    //@OneToMany(mappedBy = "moneda", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    //public List<Operacion> operaciones;

    @JsonIgnore
    @OneToMany(mappedBy = "moneda", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    public List<Egreso> egresos;

    @JsonIgnore
    @OneToMany(mappedBy = "moneda", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    public List<Presupuesto> presupuestos;

    @JsonIgnore
    @OneToMany(mappedBy = "moneda", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    public List<Ingreso> ingresos;

    public Moneda() {
        egresos = new ArrayList<>();
        ingresos = new ArrayList<>();
    }
}
