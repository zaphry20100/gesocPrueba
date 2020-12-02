package domain.entities.Models.Entidades;

import com.fasterxml.jackson.annotation.*;
import domain.entities.Models.Categorias.Criterios.CriterioPresupuesto;
import domain.entities.Models.ContextAPI.RequestRecategorizar;
import domain.entities.Models.Direccion.Direccion;
import domain.entities.Models.Entidades.CategoriasEntidad.CategoriaEntidad;
import domain.entities.Models.Entidades.CategoriasEntidad.CategoriaPequenaEmpresa;
import domain.entities.Models.Transacciones.Egreso;
import domain.entities.Models.Transacciones.Ingreso;
import domain.entities.Models.Transacciones.Item;
import domain.entities.Models.Usuarios.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEntidadJuridica")
@Entity
@Table (name = "entidadjuridica")
public class EntidadJuridica {

    @Id
    @GeneratedValue
    private int idEntidadJuridica;

    @Column (name = "estado")
    private boolean estado = true;

    @Column (name = "cuit")
    private long cuit;

    @Column (name = "codigoinscripcion")
    private int codigoInscripcion;

    @Column (name = "cantidadempleados")
    private int cantidadEmpleados;

    @Column (name = "promedioventas")
    private double promedioVentas;

    @Column (name = "nombreficticio")
    private String nombreFicticio;

    @Column (name = "razonsocial")
    private String razonSocial;

    @Column (name = "actividad")
    private String actividad = "No definido";
    
    @ManyToOne
    @JoinColumn(name = "idcategoriaentidad", referencedColumnName = "idcategoriaentidad")
    private CategoriaEntidad categoriaentidad;

    @OneToOne
    @JoinColumn(name = "idConfiguracionEntidadJuridica")
    private ConfiguracionEntidadJuridica configuracionEntidadJuridica;

    @OneToMany(mappedBy = "entidadjuridica", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Direccion> direcciones;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "entidadjuridica", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Item> items;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "entidadjuridica", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Egreso> egresos;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "entidadjuridica", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Ingreso> listaIngresos;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "entidadjuridica", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Entidadbase> entidadesbase;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "entidadjuridica", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Usuario> usuarios;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "entidadjuridica", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<CriterioPresupuesto> criterioPresupuesto;

    public EntidadJuridica() {
        direcciones = new ArrayList<>();
        egresos = new ArrayList<>();
        listaIngresos = new ArrayList<>();
        entidadesbase = new ArrayList<>();
        usuarios = new ArrayList<>();
        criterioPresupuesto = new ArrayList<>();
        items = new ArrayList<>();
        this.quitarRepetidos();
    }

    public void quitarRepetidos(){
        this.direcciones            =   this.direcciones.stream().distinct().collect(Collectors.toList());
        this.egresos                =   this.egresos.stream().distinct().collect(Collectors.toList());
        this.listaIngresos          =   this.listaIngresos.stream().distinct().collect(Collectors.toList());
        this.entidadesbase          =   this.entidadesbase.stream().distinct().collect(Collectors.toList());
        this.usuarios               =   this.usuarios.stream().distinct().collect(Collectors.toList());
        this.criterioPresupuesto    =   this.criterioPresupuesto.stream().distinct().collect(Collectors.toList());
    }

    public void categorizar(EntidadJuridica entidadJuridica, RequestRecategorizar requestRecategorizar){
        try{
            Categorizador.categorizar(entidadJuridica, requestRecategorizar.empleados, requestRecategorizar.actividad, requestRecategorizar.promedioActividad);
        }catch (Exception exception){
            System.out.println("No se pudo categorizar.");
        }
    }

    public void categorizar(EntidadJuridica entidadJuridica){
        try{
            Categorizador.categorizar(entidadJuridica);
        }catch (Exception exception){
            System.out.println("No se pudo categorizar.");
        }
    }

    public void validar(){
        //TODO
    }

    public boolean estaActivo(){
        return this.estado;
    }

    public void darBaja(){
        this.estado = false;
    }

    public int getIdEntidadJuridica() {
        return idEntidadJuridica;
    }

    public void setIdEntidadJuridica(int idEntidadJuridica) {
        this.idEntidadJuridica = idEntidadJuridica;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public long getCuit() {
        return cuit;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public int getCodigoInscripcion() {
        return codigoInscripcion;
    }

    public void setCodigoInscripcion(int codigoInscripcion) {
        this.codigoInscripcion = codigoInscripcion;
    }

    public int getCantidadEmpleados() {
        return cantidadEmpleados;
    }

    public void setCantidadEmpleados(int cantidadEmpleados) {
        this.cantidadEmpleados = cantidadEmpleados;
    }

    public double getPromedioVentas() {
        return promedioVentas;
    }

    public void setPromedioVentas(double promedioVentas) {
        this.promedioVentas = promedioVentas;
    }

    public String getNombreFicticio() {
        return nombreFicticio;
    }

    public void setNombreFicticio(String nombreFicticio) {
        this.nombreFicticio = nombreFicticio;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public CategoriaEntidad getCategoriaentidad() {
        return categoriaentidad;
    }

    public void setCategoriaentidad(CategoriaEntidad categoriaentidad) {
        this.categoriaentidad = categoriaentidad;
    }

    public ConfiguracionEntidadJuridica getConfiguracionEntidadJuridica() {
        return configuracionEntidadJuridica;
    }

    public void setConfiguracionEntidadJuridica(ConfiguracionEntidadJuridica configuracionEntidadJuridica) {
        this.configuracionEntidadJuridica = configuracionEntidadJuridica;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }


    public List<Egreso> getTodosLosEgresos() {
        return egresos;
    }

    public void setEgresos(List<Egreso> egresos) {
        this.egresos = egresos;
    }

    public List<Ingreso> getListaIngresos() {
        return listaIngresos;
    }

    public void setListaIngresos(List<Ingreso> listaIngresos) {
        this.listaIngresos = listaIngresos;
    }

    public List<Entidadbase> getEntidadesbase() {
        return entidadesbase;
    }

    public void setEntidadesbase(List<Entidadbase> entidadesbase) {
        this.entidadesbase = entidadesbase;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<CriterioPresupuesto> getCriterioPresupuesto() {
        return criterioPresupuesto;
    }

    public void setCriterioPresupuesto(List<CriterioPresupuesto> criterioPresupuesto) {
        this.criterioPresupuesto = criterioPresupuesto;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
