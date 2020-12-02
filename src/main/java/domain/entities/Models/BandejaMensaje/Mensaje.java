package domain.entities.Models.BandejaMensaje;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idMensaje")
@Entity
@Table(name = "mensaje")
public class Mensaje {

    @Id
    @GeneratedValue
    private int idMensaje;

    @Column(name = "asunto")
    private String asunto;

    @Column(name = "contenido")
    private String contenido;

    @Column(name = "fechaCreacion", columnDefinition = "date")
    private LocalDate fechaCreacion = LocalDate.now();

    @Column(name = "leido")
    private boolean leido = false;

    @Column(name = "estado")
    private boolean estado = true;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "idbandejamensaje", referencedColumnName = "idbandejamensaje")
    private BandejaMensaje bandejamensaje;

    public static Mensaje createMensaje(String asunto, String contenido, LocalDate fechaCreacion) {
        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto(asunto);
        mensaje.setContenido(contenido);
        mensaje.setFechaCreacion(fechaCreacion);
        return mensaje;
    }

    public void darBaja(){
        this.estado = false;
    }

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    public BandejaMensaje getBandejamensaje() {
        return bandejamensaje;
    }

    public void setBandejamensaje(BandejaMensaje bandejamensaje) {
        this.bandejamensaje = bandejamensaje;
    }
}