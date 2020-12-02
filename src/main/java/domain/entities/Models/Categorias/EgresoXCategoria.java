package domain.entities.Models.Categorias;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import domain.entities.Models.Categorias.Categorias.CategoriaPresupuesto;
import domain.entities.Models.Transacciones.Egreso;

import javax.persistence.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEgresoXCategoria")
@Entity
@Table(name = "egresoxcategoria")
public class EgresoXCategoria {

    @Id
    @GeneratedValue
    private int idEgresoXCategoria;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idCategoriaPresupuesto", referencedColumnName = "idCategoriaPresupuesto")
    private CategoriaPresupuesto categoriapresupuesto;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idEgreso", referencedColumnName = "idEgreso")
    private Egreso egreso;

    public EgresoXCategoria() {
    }

    public int getId() {
        return idEgresoXCategoria;
    }

    public void setId(int id) {
        this.idEgresoXCategoria = id;
    }

    public CategoriaPresupuesto getCategoriaPresupuesto() {
        return categoriapresupuesto;
    }

    public void setCategoriaPresupuesto(CategoriaPresupuesto categoriaPresupuesto) {
        this.categoriapresupuesto = categoriaPresupuesto;
    }

    public Egreso getEgreso() {
        return egreso;
    }

    public void setEgreso(Egreso egreso) {
        this.egreso = egreso;
    }
}
