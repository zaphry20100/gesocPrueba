package domain.entities.Models.Transacciones;

import com.fasterxml.jackson.annotation.*;
import domain.entities.Models.Categorias.PresupuestoXCategoria;
import domain.entities.Models.ContextAPI.RequestItemXPresupuesto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPresupuesto")
@Entity
@Table (name = "presupuesto")
public class Presupuesto {

    @Id
    @GeneratedValue
    private int idPresupuesto;

    @Column(name = "importe")
    private double importe;

    @Column(name = "detalles")
    private String detalles;

    @JsonIgnore
    @Column(name = "estado")
    private boolean estado = true;

    @Column (name = "fechaPresupuesto", columnDefinition = "date")
    private LocalDate fechaPresupuesto = LocalDate.now();

    @JsonIgnore
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idEgreso", referencedColumnName = "idEgreso")
    private Egreso egreso;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne
    @JoinColumn(name = "egresoAsignado")
    private Egreso egresoAsignado;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne
    @JoinColumn(name = "idDocumentoComercial")
    private DocumentoComercial docCom;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idMoneda", referencedColumnName = "idMoneda")
    private Moneda moneda;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    private Proveedor proveedor;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "presupuesto", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<PresupuestoXCategoria> categorias;

    @JsonIgnore
    @OneToMany(mappedBy = "presupuesto", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<PresupuestoXItem> listaPresupuestoXItem;

    @Transient
    private List<RequestItemXPresupuesto> items;

    public Presupuesto() {
        listaPresupuestoXItem = new ArrayList<>();
        categorias = new ArrayList<>();
        items = new ArrayList<>();
    }

    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public Egreso getEgreso() {
        return egreso;
    }

    public void setEgreso(Egreso egreso) {
        this.egreso = egreso;
    }

    public DocumentoComercial getDocCom() {
        return docCom;
    }

    public void setDocCom(DocumentoComercial docCom) {
        this.docCom = docCom;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public List<PresupuestoXCategoria> getCategorias() {
        return this.categorias;
    }

    public void setCategorias(List<PresupuestoXCategoria> categoria) {
        this.categorias = categoria;
    }

    public void darBaja(){
        this.estado = false;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Egreso getEgresoAsignado() {
        return egresoAsignado;
    }

    public void setEgresoAsignado(Egreso egresoAsingado) {
        this.egresoAsignado = egresoAsingado;
    }


    public List<PresupuestoXItem> getListaPresupuestoXItem() {
        return listaPresupuestoXItem;
    }

    public void setListaPresupuestoXItem(List<PresupuestoXItem> listaPresupuestoXItem) {
        this.listaPresupuestoXItem = listaPresupuestoXItem;
    }

    public LocalDate getFechaPresupuesto() {
        return fechaPresupuesto;
    }

    public void setFechaPresupuesto(LocalDate fechaPresupuesto) {
        this.fechaPresupuesto = fechaPresupuesto;
    }


    public List<RequestItemXPresupuesto> getItems() {
        return items;
    }

    public void setItems(List<RequestItemXPresupuesto> items) {
        this.items = items;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
