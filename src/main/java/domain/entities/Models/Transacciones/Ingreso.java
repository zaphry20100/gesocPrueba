package domain.entities.Models.Transacciones;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.google.gson.Gson;
import domain.entities.Models.Categorias.EgresoXCategoria;
import domain.entities.Models.Categorias.IngresoXCategoria;
import domain.entities.Models.Entidades.EntidadJuridica;
import org.hibernate.annotations.Cascade;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idIngreso")
@Entity
@Table (name = "ingreso")
public class Ingreso {//extends Operacion{

    @Id
    @GeneratedValue
    private int idIngreso;

    @Column(name="importe")
    private double importe;

    @Column(name="descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idMoneda", referencedColumnName = "idMoneda")
    private Moneda moneda;

    @Column (name = "estado")
    private boolean estado;

    @Column (name = "fechaIngreso", columnDefinition = "date")
    private LocalDate fechaIngreso = LocalDate.now();

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idEntidadJuridica", referencedColumnName = "idEntidadJuridica")
    private EntidadJuridica entidadjuridica;

    /*
        @ManyToOne
        @JoinColumn(name = "idEgreso", referencedColumnName = "idEgreso")
        private Egreso egreso;

        @OneToMany(mappedBy = "ingreso", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
        private List<Egreso> listaEgresos;
    */

    @OneToMany(mappedBy = "ingreso", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<EgresoXIngreso> egresosXIngresos;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "ingreso", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<IngresoXCategoria> categorias;

    public Ingreso() {
        egresosXIngresos = new ArrayList<>();
        categorias = new ArrayList<>();

    }

    public void quitarRepetidos(){
        egresosXIngresos = egresosXIngresos.stream().distinct().collect(Collectors.toList());
        categorias = categorias.stream().distinct().collect(Collectors.toList());
    }

    public List<EgresoXIngreso> getEgresoXIngreso() {
        return egresosXIngresos;
    }

    public void setEgresoXIngreso(List<EgresoXIngreso> egresoXIngreso) {
        this.egresosXIngresos = egresoXIngreso;
    }

    public int getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(int idIngreso) {
        this.idIngreso = idIngreso;
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

    public void agregarEgreso(EgresoXIngreso egresoXIngreso){
        egresosXIngresos.add(egresoXIngreso);
    }

    public JSONObject toJSON(){
        JSONArray egresos = new JSONArray();
        this.egresosXIngresos.forEach(x-> {
            egresos.put(x.getEgreso().getIdEgreso());
        });
        List<EgresoXIngreso> ingXEgr = this.egresosXIngresos;
        this.egresosXIngresos = new ArrayList<>();
        JSONObject request = new JSONObject(this);
        request.remove("egresoXIngreso");
        request.put("egresos", egresos);
        this.egresosXIngresos = ingXEgr;
        return request;
    }

    public void darBaja(){
        this.estado = false;
    }

    public boolean estaActivo(){
        return this.estado;
    }

    public EntidadJuridica getEntidadjuridica() {
        return entidadjuridica;
    }

    public void setEntidadjuridica(EntidadJuridica entidadjuridica) {
        this.entidadjuridica = entidadjuridica;
    }

    public List<IngresoXCategoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<IngresoXCategoria> categorias) {
        this.categorias = categorias;
    }
}
