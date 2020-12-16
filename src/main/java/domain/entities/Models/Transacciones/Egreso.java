package domain.entities.Models.Transacciones;

import com.fasterxml.jackson.annotation.*;
import domain.entities.Models.Categorias.EgresoXCategoria;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Usuarios.Revisor;
import domain.entities.Models.Usuarios.Usuario;
import domain.entities.Validadores.ValidadorTransparencia.ResultadoValidacion;
//import domain.entities.Validadores.ValidadorTransparencia.ValidadorTransparencia;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.beans.IntrospectionException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEgreso")
@Entity
@Table (name = "egreso")
public class Egreso {//extends Operacion{

    @Id
    @GeneratedValue
    private int idEgreso;

    @Column(name="importe")
    private double importe;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="detalleMedioPago")
    private String detalleMedioPago;

    @Column (name = "numeroinstrumentopago")
    private long numeroInstrumentoPago;

    @Column (name = "validado")
    private boolean validado = false;

    @Column (name = "estado")
    private boolean estado = true;

    @Column (name = "fechaegreso", columnDefinition = "date")
    private LocalDate fechaEgreso = LocalDate.now();

//    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idMoneda", referencedColumnName = "idMoneda")
    private Moneda moneda;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idEntidadJuridica", referencedColumnName = "idEntidadJuridica")
    private EntidadJuridica entidadjuridica;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor")
    private Proveedor proveedor;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne
    @JoinColumn(name = "presupuestoElegido")
    private Presupuesto presupuestoElegido;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idmediodepago", referencedColumnName = "idmediodepago")
    private Mediodepago mediodepago;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idDocumentoComercial", referencedColumnName = "idDocumentoComercial")
    private DocumentoComercial docCom;

    @JsonIgnore
    @OneToMany(mappedBy = "egreso", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Presupuesto> listaPresupuestos;

    @JsonIgnore
    @OneToMany(mappedBy = "egreso", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Egresoxitem> listaEgresxItem;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "egreso", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Revisor> revisores;

    @JsonIgnore
    @Transient
    int presupuestoSeleccionado;


    @Transient
    List<Integer> presupuestos;

    //Vinculacion
    /*
    @OneToMany(mappedBy = "egreso", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Ingreso> listaIngresos;

    @ManyToOne
    @JoinColumn(name = "idIngreso", referencedColumnName = "idIngreso")
    private Ingreso ingreso;

     */

//  @JoinTable(
//  name = "egresoxingreso",
//  joinColumns = @JoinColumn(name = "id_egreso",nullable = false),

    @JsonIgnore
    @OneToMany(mappedBy = "egreso", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<EgresoXIngreso> egresosXIngresos;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "egreso", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<EgresoXCategoria> categorias;

    @JsonIgnore
    @OneToMany(mappedBy = "egreso", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<ResultadoValidacion> resultadoValidacions;

    public Egreso() {
        listaPresupuestos = new ArrayList<>();
        listaEgresxItem = new ArrayList<>();
        revisores = new ArrayList<>();
        egresosXIngresos = new ArrayList<>();
        categorias = new ArrayList<>();

        resultadoValidacions = new ArrayList<>();

        this.quitarRepetidos();

    }
/*
    public void validar(){
        String resultadoValidacion = ValidadorTransparencia.validar(this);
        ServicioMensajes.mandarMensaje(resultadoValidacion, revisores);
        validado =  resultadoValidacion.equals("Estimado usuario vengo a informarle el resultado del egreso " +  this.idEgreso +  " esto: " +"Todo piola");
    }
*/

    public void quitarRepetidos(){
        listaPresupuestos               = listaPresupuestos.stream().distinct().collect(Collectors.toList());    ;
        listaEgresxItem                 = listaEgresxItem.stream().distinct().collect(Collectors.toList());      ;
        revisores                       = revisores.stream().distinct().collect(Collectors.toList());            ;
        egresosXIngresos                = egresosXIngresos.stream().distinct().collect(Collectors.toList());     ;
        categorias                      = categorias.stream().distinct().collect(Collectors.toList());;
        resultadoValidacions            = resultadoValidacions.stream().distinct().collect(Collectors.toList()); ;
    }

    public List<EgresoXIngreso> getEgresoXIngreso() {
        return egresosXIngresos;
    }

    public void setEgresoXIngreso(List<EgresoXIngreso> egresoXIngreso) {
        this.egresosXIngresos = egresoXIngreso;
    }

    public int getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(int idEgreso) {
        this.idEgreso = idEgreso;
    }

    public long getNumeroInstrumentoPago() {
        return numeroInstrumentoPago;
    }

    public void setNumeroInstrumentoPago(long numeroInstrumentoPago) {
        this.numeroInstrumentoPago = numeroInstrumentoPago;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

    public LocalDate getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(LocalDate fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public EntidadJuridica getEntidadJuridica() {
        return entidadjuridica;
    }

    public void setEntidadJuridica(EntidadJuridica entidadJuridica) {
        this.entidadjuridica = entidadJuridica;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Presupuesto getPresupuestoElegido() {
        return presupuestoElegido;
    }

    public void setPresupuestoElegido(Presupuesto presupuestoElegido) {
        this.presupuestoElegido = presupuestoElegido;
    }

    public Usuario getUsuarioCreador() {
        return usuario;
    }

    public void setUsuarioCreador(Usuario usuarioCreador) {
        this.usuario = usuarioCreador;
    }

    public Mediodepago getMediodepago() {
        return mediodepago;
    }

    public void setMediodepago(Mediodepago mediodepago) {
        this.mediodepago = mediodepago;
    }

    public DocumentoComercial getDocCom() {
        return docCom;
    }

    public void setDocCom(DocumentoComercial docCom) {
        this.docCom = docCom;
    }

    public List<Presupuesto> getListaPresupuestos() {
        return listaPresupuestos;
    }

    public void setListaPresupuestos(List<Presupuesto> listaPresupuestos) {
        this.listaPresupuestos = listaPresupuestos;
    }

    public List<Egresoxitem> getListaEgresxItem() {
        return listaEgresxItem;
    }

    public void setListaEgresxItem(List<Egresoxitem> listaEgresxItem) {
        this.listaEgresxItem = listaEgresxItem;
    }

    public List<Revisor> getRevisores() {
        return revisores;
    }

    public void setRevisores(List<Revisor> revisores) {
        this.revisores = revisores;
    }

    public List<EgresoXCategoria> getCategoriaPresupuestos() {
        return categorias;
    }

    public void setCategoriaPresupuestos(List<EgresoXCategoria> categoriaPresupuestos) {
        this.categorias = categoriaPresupuestos;
    }

    public List<ResultadoValidacion> getResultadoValidacions() {
        return resultadoValidacions;
    }

    public void setResultadoValidacions(List<ResultadoValidacion> resultadoValidacions) {
        this.resultadoValidacions = resultadoValidacions;
    }

    public JSONObject toJSON(){
        JSONArray ingresos = new JSONArray();
        this.egresosXIngresos.forEach(x-> {
            ingresos.put(x.getIngreso().getIdIngreso());
        });
        JSONObject request = new JSONObject(this);
        List<EgresoXIngreso> ingXEgr = this.egresosXIngresos;
        this.egresosXIngresos = new ArrayList<>();
        request.remove("egresoXIngreso");
        request.put("ingresos", ingresos);
        this.egresosXIngresos = ingXEgr;


        int idEntJuridica = this.entidadjuridica.getIdEntidadJuridica();
        EntidadJuridica entidadJuridicaAux = this.entidadjuridica;
        this.entidadjuridica = null;

        request.remove("entidadjuridica");
        request.put("entidadjuridica", idEntJuridica);
        this.entidadjuridica = entidadJuridicaAux;

        return request;
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

    public void agregarIngreso(EgresoXIngreso egresoXIngreso){
        egresosXIngresos.add(egresoXIngreso);
    }

    public void darBaja(){
        this.estado = false;
    }

    public boolean estaActivo(){
        return this.estado;
    }

    public String getDetalleMedioPago() {
        return detalleMedioPago;
    }

    public void setDetalleMedioPago(String detalleMedioPago) {
        this.detalleMedioPago = detalleMedioPago;
    }

    public int getPresupuestoSeleccionado() {
        return presupuestoSeleccionado;
    }

    public void setPresupuestoSeleccionado(int presupuestoSeleccionado) {
        this.presupuestoSeleccionado = presupuestoSeleccionado;
    }

    public List<Integer> getPresupuestos() {
        return presupuestos;
    }

    public void setPresupuestos(List<Integer> presupuestos) {
        this.presupuestos = presupuestos;
    }
}
