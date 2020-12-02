package domain.entities.Models.Transacciones;

import com.fasterxml.jackson.annotation.*;
import domain.entities.Models.Entidades.EntidadJuridica;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idItem")
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue
    private int idItem;

    @Column(name = "productoServicio")
    private String productoServicio;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "valorUnitario")
    private double valorUnitario;

    @JsonIgnore
    @Column(name = "estado")
    private boolean estado = true;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idEntidadJuridica", referencedColumnName = "idEntidadJuridica")
    private EntidadJuridica entidadjuridica;

    @JsonIgnore
    @OneToMany(mappedBy = "item", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Egresoxitem> listaEgresoxItem;

    @JsonIgnore
    @OneToMany(mappedBy = "item", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<PresupuestoXItem> listaPresupuestoXItem;

    public Item() {
        listaEgresoxItem = new ArrayList<>();
        listaPresupuestoXItem = new ArrayList<>();
    }

    public int getIdItem() {
        return idItem;
    }

    public void darBaja(){
        this.estado = false;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getProductoServicio() {
        return productoServicio;
    }

    public void setProductoServicio(String productoServicio) {
        this.productoServicio = productoServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValor() {
        return valorUnitario;
    }

    public void setValor(double valor) {
        this.valorUnitario = valor;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public EntidadJuridica getEntidadjuridica() {
        return entidadjuridica;
    }

    public void setEntidadjuridica(EntidadJuridica entidadjuridica) {
        this.entidadjuridica = entidadjuridica;
    }
}
