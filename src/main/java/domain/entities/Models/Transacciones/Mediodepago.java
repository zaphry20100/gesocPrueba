package domain.entities.Models.Transacciones;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idmediodepago")
@Entity
@Table (name = "mediodepago")
public class Mediodepago {
    @Id
    @GeneratedValue
    private int idmediodepago;

    @Column (name = "descripcion")
    private String descripcion;

    @Column (name = "tipo")
    private String tipo;

    @Column (name = "estado")
    private boolean estado = false;

    @JsonIgnore
    @OneToMany (mappedBy = "mediodepago", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Egreso> egreso;

    public Mediodepago() {
        egreso = new ArrayList<>();
    }


    public int getIdMedioPago() {
        return idmediodepago;
    }

    public void setIdMedioPago(int idMedioPago) {
        this.idmediodepago = idMedioPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


}
