package domain.entities.Models.Usuarios;

import com.fasterxml.jackson.annotation.*;
import domain.entities.Models.BandejaMensaje.BandejaMensaje;
import domain.entities.Models.BandejaMensaje.Mensaje;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUsuario")
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue
    private int idUsuario;

    @Column(name="nombre")
    private String nombre;

    @Column(name="apellido")
    private String apellido;

    @Column(name="mail")
    private String mail;

    @Column(name="nombreUsuario")
    private String nombreUsuario;

    @Column(name="clave")
    private String clave;

    @Column(name="estado")
    private boolean estado = true;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idRol", referencedColumnName = "idRol")
    private Rol rol;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idEntidadJuridica", referencedColumnName = "idEntidadJuridica")
    private EntidadJuridica entidadjuridica;

    @OneToOne
    @JoinColumn(name = "idbandejamensaje")
    private BandejaMensaje bandejamensaje;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Egreso> egresos;

    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "usuario", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Revisor> revisiones;

    public Usuario() {
        egresos = new ArrayList<>();
        revisiones = new ArrayList<>();
    }

    private void quitarRepetidos(){
        this.revisiones = revisiones.stream().distinct().collect(Collectors.toList());

    }

    public void darBaja(){
        this.estado = false;
    }

    public List<Egreso> getEgreso() {
        return egresos;
    }

    public void setEgreso(List<Egreso> egreso) {
        this.egresos = egreso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public EntidadJuridica getEntidadJuridica() {
        return entidadjuridica;
    }

    public void setEntidadJuridica(EntidadJuridica entidadJuridica) {
        this.entidadjuridica = entidadJuridica;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public BandejaMensaje getBandejaMensaje() {
        return bandejamensaje;
    }

    public void setBandejaMensaje(BandejaMensaje bandejaMensaje) {
        this.bandejamensaje = bandejaMensaje;
    }

    public void recibirMensaje(Mensaje mensaje){
        bandejamensaje.agregarMensajes(mensaje);
    }

    public List<Egreso> getEgresos() {
        return egresos;
    }

    public void setEgresos(List<Egreso> egresos) {
        this.egresos = egresos;
    }

    public List<Revisor> getRevisiones() {
        return revisiones;
    }

    public void setRevisiones(List<Revisor> revisiones) {
        this.revisiones = revisiones;
    }
}
