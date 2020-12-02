package domain.entities.Models.Transacciones;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEgresoXItem")
@Entity
@Table(name ="egresoxitem")
public class Egresoxitem {

    @Id
    @GeneratedValue
    private int idEgresoXItem;

    @Column(name = "valorUnitario")
    private double valorUnitario;

    @Column(name = "cantidad")
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "idEgreso", referencedColumnName = "idEgreso")
    private Egreso egreso;

    @ManyToOne
    @JoinColumn(name = "idItem", referencedColumnName = "idItem")
    private Item item;

    public int getIdEgresoXItem() {
        return idEgresoXItem;
    }

    public void setIdEgresoXItem(int idEgresoXItem) {
        this.idEgresoXItem = idEgresoXItem;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Egreso getEgreso() {
        return egreso;
    }

    public void setEgreso(Egreso egreso) {
        this.egreso = egreso;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
