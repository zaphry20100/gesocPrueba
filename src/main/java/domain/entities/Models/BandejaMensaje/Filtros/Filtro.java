package domain.entities.Models.BandejaMensaje.Filtros;

import domain.entities.Models.BandejaMensaje.Mensaje;

import java.util.List;

public interface Filtro {

    List<Mensaje> filtrar(List<Mensaje> mensajes);


}

