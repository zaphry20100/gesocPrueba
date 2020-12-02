package domain.entities.Models.BandejaMensaje.Filtros;

import domain.entities.Models.BandejaMensaje.Mensaje;

import java.util.ArrayList;
import java.util.List;

public class FiltroLeido implements Filtro {

    @Override
    public List<Mensaje> filtrar(List<Mensaje> mensajes) {
        List<Mensaje> mensajesFiltrados = new ArrayList<>();

        for (Mensaje mensaje: mensajes) {
            if(mensaje.isLeido()){
                mensajesFiltrados.add(mensaje);
            }
        }

        return mensajesFiltrados;
    }
}
