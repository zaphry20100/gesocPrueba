package domain.entities.Models.Transacciones;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.intellij.lang.annotations.JdkConstants;

import javax.persistence.*;
import java.util.ArrayList;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEgresoXIngreso")
@Entity
@Table(name="egresoxingreso")
public class EgresoXIngreso {

    @Id
    @GeneratedValue
    private int idEgresoXIngreso;

    @ManyToOne
    @JoinColumn(name = "idEgreso", referencedColumnName = "idEgreso")
    
    private Egreso egreso;

    @ManyToOne
    @JoinColumn(name = "idIngreso", referencedColumnName = "idIngreso")
    
    private Ingreso ingreso;

    public Egreso getEgreso() {
        return egreso;
    }

    public void setEgreso(Egreso egreso) {
        this.egreso = egreso;
    }

    public Ingreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(Ingreso ingreso) {
        this.ingreso = ingreso;
    }
}
