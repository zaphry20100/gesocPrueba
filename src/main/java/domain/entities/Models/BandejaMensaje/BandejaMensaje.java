package domain.entities.Models.BandejaMensaje;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import domain.entities.Models.BandejaMensaje.Filtros.Filtro;
import domain.entities.Models.Usuarios.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idbandejamensaje")
@Entity
@Table(name = "bandejamensaje")
public class BandejaMensaje {

    @Id
    @GeneratedValue
    private int idbandejamensaje;

    @OneToMany(mappedBy = "bandejamensaje", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Mensaje> mensajes;

    @JsonIgnore
    @Transient
    private List<Filtro> listaFiltros;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name ="idUsuario")
    private Usuario usuario;

    public BandejaMensaje() {
        mensajes = new ArrayList<>();
        listaFiltros = new ArrayList<>();

    }


    public void quitarRepetidos(){
        this.setMensajes(this.getMensajes().stream().distinct().collect(Collectors.toList()));
    }

    public List<Mensaje> leerMensajes() {
        List<Mensaje> mensajesFiltrados = new ArrayList<>();
        if(!listaFiltros.isEmpty()){
            for (Filtro filtro: listaFiltros) {
                mensajesFiltrados = filtro.filtrar(mensajes);
            }
        }else{
            mensajesFiltrados = mensajes;
        }
        mensajesFiltrados.forEach(mensaje -> {
            mensaje.setLeido(true);
        });
        return mensajesFiltrados;
    }

    public void agregarMensajes(Mensaje mensaje){
        mensajes.add(mensaje);
    }

    public void agregarFiltro(Filtro filtro){
        listaFiltros.add(filtro);
    }

    public void removerFiltro(Filtro filtro){
        listaFiltros.remove(filtro);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public BandejaMensaje setUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public int getIdbandejamensaje() {
        return idbandejamensaje;
    }

    public void setIdbandejamensaje(int idbandejamensaje) {
        this.idbandejamensaje = idbandejamensaje;
    }

    private List<Mensaje> getMensajes() {
        return mensajes;
    }

    private void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
}
