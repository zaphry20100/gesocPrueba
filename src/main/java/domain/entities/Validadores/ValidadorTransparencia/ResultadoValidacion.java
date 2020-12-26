package domain.entities.Validadores.ValidadorTransparencia;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import domain.entities.Models.Transacciones.Egreso;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idValidacion")
@Entity
@Table(name ="resultadovalidacion")
public class ResultadoValidacion {

    @Id
    @GeneratedValue
    private int idValidacion;

    @Column(name ="detalles")
    private String detalles;

    @Column(name ="resultado")
    private boolean resultado = false;

    @Column(name ="fechaValidacion", columnDefinition = "date")
    private LocalDate fechaValidacion;

    @ManyToOne
    @JoinColumn(name = "idEgreso", referencedColumnName = "idEgreso")
    private Egreso egreso;

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    public void setIdValidacion(int idValidacion) {
        this.idValidacion = idValidacion;
    }

    public int getIdValidacion() {
        return idValidacion;
    }

    public Egreso getEgreso() {
        return egreso;
    }

    public void setEgreso(Egreso egreso) {
        this.egreso = egreso;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public LocalDate getFechaValidacion() {
        return fechaValidacion;
    }

    public void setFechaValidacion(LocalDate fechaValidacion) {
        this.fechaValidacion = fechaValidacion;
    }

    @Override
    public String toString() {
        return "Estimado usuario, El egreso " + this.getEgreso().getIdEgreso() + " tuvo este resultado de validación: " +this.getDetalles();
    }
}
