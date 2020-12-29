package domain.entities.Models.Categorias.Criterios;

import com.fasterxml.jackson.annotation.*;
import domain.controllers.CategoriaXCriterio;
import domain.entities.Models.Categorias.Categorias.CategoriaPresupuesto;
import domain.entities.Models.Entidades.EntidadJuridica;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idcriteriopresupuesto")
@Entity
@Table(name = "criteriopresupuesto")
public class CriterioPresupuesto {

    @Id
    @GeneratedValue
    private int idcriteriopresupuesto;

    @Column (name = "descripcion")
    private String descripcion;

    @JsonIgnore
    @Column (name = "estado")
    private boolean estado = true;

    @JsonIgnore
    @Transient
    private Long parentId;

    //@JsonIdentityReference(alwaysAsId = true)
    //@ManyToOne ( fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "criterioPadre")
    private int criterioPadre;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idEntidadJuridica", referencedColumnName = "idEntidadJuridica")
    private EntidadJuridica entidadjuridica;

    @JsonIgnore
    @OneToMany(mappedBy = "criteriopresupuesto", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<CategoriaPresupuesto> categoriapresupuestos;

    @JsonIgnore
    @OneToMany(mappedBy = "categoriapresupuesto", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER  )
    private List<CategoriaXCriterio> categoriaXCriterios;

    @JsonIgnore
    @Transient
    private List<CategoriaPresupuesto> categorias;

    //@JsonIdentityReference(alwaysAsId = true)
    //@OneToMany(mappedBy = "criterioPadre", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval=true)
    //private List<Integer> criteriosHijos;

    public CriterioPresupuesto() {
        categoriapresupuestos = new ArrayList<>();
        //criteriosHijos = new ArrayList<>();
        categoriaXCriterios = new ArrayList<>();
        categorias = new ArrayList<>();
    }

    public void quitarRepetidos(){
        categoriapresupuestos = categoriapresupuestos.stream().distinct().collect(Collectors.toList());
        for(CategoriaPresupuesto categoriaPresupuesto:categoriapresupuestos){
            categoriaPresupuesto.quitarRepetidos();
        }
        categoriaXCriterios = categoriaXCriterios.stream().distinct().collect(Collectors.toList());
    }

    public int getIdCriterioPresupuesto() {
        return idcriteriopresupuesto;
    }

    public void setIdCriterioPresupuesto(int idCriterioPresupuesto) {
        this.idcriteriopresupuesto = idCriterioPresupuesto;
    }

    public void darBaja(){
        this.estado = false;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCriterioPadre() {
        return criterioPadre;
    }

    public void setCriterioPadre(int criterioPadre) {
        this.criterioPadre = criterioPadre;
    }

    public EntidadJuridica getEntidadJuridica() {
        return entidadjuridica;
    }

    public void setEntidadJuridica(EntidadJuridica entidadJuridica) {
        this.entidadjuridica = entidadJuridica;
    }


    public List<CategoriaPresupuesto> getCategoriaPresupuestos() {
        return categoriapresupuestos;
    }

    public void setCategoriaPresupuestos(List<CategoriaPresupuesto> categoriaPresupuestos) {
        this.categoriapresupuestos = categoriaPresupuestos;
    }
/*
    public List<CriterioPresupuesto> getCriteriosHijos() {
        return criteriosHijos;
    }

    public void setCriteriosHijos(List<CriterioPresupuesto> criteriosHijos) {
        this.criteriosHijos = criteriosHijos;
    }

     */
}
