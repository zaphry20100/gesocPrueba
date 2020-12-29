package domain.entities.Models.Transacciones;

import com.fasterxml.jackson.annotation.*;
import domain.entities.Models.Direccion.Direccion;
import domain.entities.Models.Entidades.EntidadJuridica;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idProveedor")
@Entity
@Table (name = "proveedor")
public class Proveedor {

    @Id
    @GeneratedValue
    private int idProveedor;

    @Column(name = "cuit")
    private long cuit;

    @Column(name = "nombre")
    private String nombre;

    @JsonIgnore
    @Column(name = "estado")
    private boolean estado = true;

    @OneToOne
    @JoinColumn(name = "idDireccion")
    private Direccion direccion;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idEntidadJuridica", referencedColumnName = "idEntidadJuridica")
    private EntidadJuridica entidadJuridica;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "proveedor", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Egreso> egresos;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "proveedor", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Presupuesto> presupuestos;

    public Proveedor() {

        egresos = new ArrayList<>();
        presupuestos = new ArrayList<>();
    }

    public void quitarRepetidos(){
        egresos = egresos.stream().distinct().collect(Collectors.toList());
        presupuestos = presupuestos.stream().collect(Collectors.toList());
    }

    public void darBaja(){
        this.estado = false;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }


    public EntidadJuridica getEntidadJuridica() {
        return entidadJuridica;
    }

    public void setEntidadJuridica(EntidadJuridica entidadJuridica) {
        this.entidadJuridica = entidadJuridica;
    }
}
