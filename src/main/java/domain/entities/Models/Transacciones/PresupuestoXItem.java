package domain.entities.Models.Transacciones;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPresupuestoXItem")
@Entity
@Table (name = "presupuestoxitem")
public class PresupuestoXItem {

    @Id
    @GeneratedValue
    private int idPresupuestoXItem;

    @Column (name = "cantidad")
    private int cantidad;

    @Column (name = "valorUnitario")
    private double valorUnitario;

    @ManyToOne
    @JoinColumn(name = "idPresupuesto", referencedColumnName = "idPresupuesto")
    private Presupuesto presupuesto;

    
    @ManyToOne
    @JoinColumn(name = "idItem", referencedColumnName = "idItem")
    private Item item;


    public int getIdPresupuestoXItem() {
        return idPresupuestoXItem;
    }

    public void setIdPresupuestoXItem(int idPresupuestoXItem) {
        this.idPresupuestoXItem = idPresupuestoXItem;
    }

    public domain.entities.Models.Transacciones.Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(domain.entities.Models.Transacciones.Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public domain.entities.Models.Transacciones.Item getItem() {
        return item;
    }

    public void setItem(domain.entities.Models.Transacciones.Item item) {
        this.item = item;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double importe) {
        this.valorUnitario = valorUnitario;
    }
}
