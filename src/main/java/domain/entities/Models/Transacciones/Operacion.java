package domain.entities.Models.Transacciones;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Operacion {

    //@Id
    //@GeneratedValue
    private int idOperacion;

    //@Column(name="importe")
    private double importe;

    //@Column(name="descripcion")
    private String descripcion;

    //@ManyToOne
    //@JoinColumn(name = "idMoneda", referencedColumnName = "idMoneda")
    private Moneda moneda;

    public int getIdOperacion() {
        return idOperacion;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
