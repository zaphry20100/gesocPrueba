package domain.entities.Models.Categorias.Categorias;

import com.fasterxml.jackson.annotation.*;
import domain.entities.Models.Categorias.Criterios.CriterioPresupuesto;
import domain.entities.Models.Categorias.IngresoXCategoria;
import domain.entities.Models.Categorias.EgresoXCategoria;
import domain.entities.Models.Categorias.PresupuestoXCategoria;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;
import domain.entities.Models.Transacciones.Ingreso;
import domain.entities.Models.Transacciones.Presupuesto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCategoriaPresupuesto")
@Entity
@Table(name = "categoriapresupuesto")
public class CategoriaPresupuesto {

    @Id
    @GeneratedValue
    private int idCategoriaPresupuesto;

    @Column (name = "descripcion")
    private String descripcion;

    @Column (name = "estado")
    private boolean estado = true;

    /*@JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idEgreso", referencedColumnName = "idEgreso")
    private Egreso egreso;*/

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idcriteriopresupuesto", referencedColumnName = "idcriteriopresupuesto")
    private CriterioPresupuesto criteriopresupuesto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idEntidadjuridica", referencedColumnName = "idEntidadjuridica")
    private EntidadJuridica entidadjuridica;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "categoriapresupuesto", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER  )
    private List<PresupuestoXCategoria> presupuestos;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "categoriapresupuesto", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER  )
    private List<EgresoXCategoria> egresos;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "categoriapresupuesto", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER  )
    private List<IngresoXCategoria> ingreso;

    public CategoriaPresupuesto() {
        this.presupuestos = new ArrayList<>();
        this.egresos = new ArrayList<>();
        this.ingreso = new ArrayList<>();
    }

    public void darBaja(){
        this.estado = false;
    }

    public void quitarRepetidos(){
        this.presupuestos=this.presupuestos.stream().distinct().collect(Collectors.toList());
        this.egresos = this.egresos.stream().distinct().collect(Collectors.toList());
        this.ingreso = this.ingreso.stream().distinct().collect(Collectors.toList());
    }

    public int getIdCategoriaPresupuesto() {
        return idCategoriaPresupuesto;
    }

    public void setIdCategoriaPresupuesto(int idCategoriaPresupuesto) {
        this.idCategoriaPresupuesto = idCategoriaPresupuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CriterioPresupuesto getCriteriopresupuesto() {
        return criteriopresupuesto;
    }

    public void setCriteriopresupuesto(CriterioPresupuesto criteriopresupuesto) {
        this.criteriopresupuesto = criteriopresupuesto;
    }

    public EntidadJuridica getEntidadjuridica() {
        return entidadjuridica;
    }

    public void setEntidadjuridica(EntidadJuridica entidadjuridica) {
        this.entidadjuridica = entidadjuridica;
    }

    public List<PresupuestoXCategoria> getPresupuestos() {
        return presupuestos;
    }

    public void setPresupuestos(List<PresupuestoXCategoria> presupuestos) {
        this.presupuestos = presupuestos;
    }

    public List<EgresoXCategoria> getEgresos() {
        return egresos;
    }

    public void setEgresos(List<EgresoXCategoria> egresos) {
        this.egresos = egresos;
    }

    public List<IngresoXCategoria> getIngreso() {
        return ingreso;
    }

    public void setIngreso(List<IngresoXCategoria> ingreso) {
        this.ingreso = ingreso;
    }
}
