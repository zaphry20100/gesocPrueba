package domain.entities.Models.Categorias;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import domain.entities.Models.Categorias.Categorias.CategoriaPresupuesto;
import domain.entities.Models.Transacciones.Ingreso;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idIngresoXCategoria")
@Entity
@Table(name = "ingresoxcategoria")
public class IngresoXCategoria {

    @Id
    @GeneratedValue
    private int idIngresoXCategoria;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idCategoriaPresupuesto", referencedColumnName = "idCategoriaPresupuesto" )
    private CategoriaPresupuesto categoriapresupuesto;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idIngreso", referencedColumnName = "idIngreso" )
    private Ingreso ingreso;

    public IngresoXCategoria() {
    }

    public int getId() {
        return idIngresoXCategoria;
    }

    public void setId(int id) {
        this.idIngresoXCategoria = id;
    }

    public CategoriaPresupuesto getCategoriaPresupuesto() {
        return categoriapresupuesto;
    }

    public void setCategoriaPresupuesto(CategoriaPresupuesto categoriaPresupuesto) {
        this.categoriapresupuesto = categoriaPresupuesto;
    }

    public Ingreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(Ingreso ingreso) {
        this.ingreso = ingreso;
    }
}
