package domain.entities.Models.Entidades;

import com.fasterxml.jackson.annotation.*;
import domain.entities.Models.Transacciones.EgresoXIngreso;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEntidadBase")
@Entity
@Table (name = "entidadbase")
public class Entidadbase {

    @Id
    @GeneratedValue
    private int idEntidadBase;

    @Column (name = "nombreFicticio")
    private String nombreFicticio;

    @Column (name = "descripcion")
    private String descripcion;

    @Column (name = "estado")
    private boolean estado = true;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idEntidadJuridica", referencedColumnName = "idEntidadJuridica")
    private EntidadJuridica entidadjuridica;

    public int getIdEntidadBase() {
        return idEntidadBase;
    }

    public void setIdEntidadBase(int idEntidadBase) {
        this.idEntidadBase = idEntidadBase;
    }

    public String getNombreFicticio() {
        return nombreFicticio;
    }

    public void setNombreFicticio(String nombreFicticio) {
        this.nombreFicticio = nombreFicticio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public EntidadJuridica getEntidadJuridica() {
        return entidadjuridica;
    }

    public void setEntidadJuridica(EntidadJuridica entidadJuridica) {
        this.entidadjuridica = entidadJuridica;
    }

    public void darBaja(){
        this.estado = false;
    }

    public boolean estaActivo(){
        return this.estado;
    }
}
