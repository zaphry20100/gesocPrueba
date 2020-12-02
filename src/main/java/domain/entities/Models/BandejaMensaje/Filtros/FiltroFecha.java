package domain.entities.Models.BandejaMensaje.Filtros;

import domain.entities.Models.BandejaMensaje.Mensaje;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FiltroFecha implements Filtro {

    private LocalDate fechaInicial;
    private LocalDate fechaFinal;

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    @Override
    public List<Mensaje> filtrar(List<Mensaje> mensajes) {
        List<Mensaje> mensajesFiltrados = new ArrayList<>();

        for (Mensaje mensaje: mensajes) {
            if(mensaje.getFechaCreacion().isAfter(fechaInicial) &&
               mensaje.getFechaCreacion().isBefore(fechaFinal)){
                mensajesFiltrados.add(mensaje);
            }
        }

        return mensajesFiltrados;
    }
}
