package domain.entities.Models.Usuarios;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import domain.entities.Models.Transacciones.Egreso;

import javax.persistence.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idRevisor")
@Entity
@Table (name = "revisor")
public class Revisor {
    @Id
    @GeneratedValue
    private int idRevisor;

    @ManyToOne
    @JoinColumn(name = "idEgreso", referencedColumnName = "idEgreso")
    private Egreso egreso;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

    public Egreso getEgreso() {
        return egreso;
    }

    public void setEgreso(Egreso egreso) {
        this.egreso = egreso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
