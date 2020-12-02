package domain.entities.Models.Categorias;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import domain.entities.Models.Categorias.Categorias.CategoriaPresupuesto;
import domain.entities.Models.Transacciones.Presupuesto;

import javax.persistence.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPresupuestoXCategoria")
@Entity
@Table(name = "presupuestoxcategoria")
public class PresupuestoXCategoria {

    @Id
    @GeneratedValue
    private int idPresupuestoXCategoria;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idCategoriaPresupuesto", referencedColumnName = "idCategoriaPresupuesto")
    private CategoriaPresupuesto categoriapresupuesto;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idPresupuesto", referencedColumnName = "idPresupuesto")
    private Presupuesto presupuesto;

    public PresupuestoXCategoria() {
    }

    public int getId() {
        return idPresupuestoXCategoria;
    }

    public void setId(int id) {
        this.idPresupuestoXCategoria = id;
    }

    public CategoriaPresupuesto getCategoriaPresupuesto() {
        return categoriapresupuesto;
    }

    public void setCategoriaPresupuesto(CategoriaPresupuesto categoriaPresupuesto) {
        this.categoriapresupuesto = categoriaPresupuesto;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }
}
