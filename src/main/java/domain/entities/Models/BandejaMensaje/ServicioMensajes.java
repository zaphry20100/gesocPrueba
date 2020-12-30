package domain.entities.Models.BandejaMensaje;

import domain.entities.Models.Usuarios.Revisor;
import domain.entities.Models.Usuarios.Usuario;
import domain.repositories.factories.FactoryRepositorio;

import java.time.LocalDate;
import java.util.List;

public class ServicioMensajes {

    public static void mandarMensaje(String mensaje, List<Revisor> revisores){
        if(revisores != null){
            for (Revisor revisor: revisores) {

                Mensaje mensaje1 = Mensaje.createMensaje("Nuevo resultado de validación", mensaje, LocalDate.now());

                //revisor.getUsuario().recibirMensaje(mensaje1);
                //FactoryRepositorio.get(BandejaMensaje.class).modificar(revisor.getUsuario().getBandejaMensaje());

                Usuario user = FactoryRepositorio.get(Usuario.class).buscar(revisor.getUsuario().getIdUsuario());
                mensaje1.setBandejamensaje(user.getBandejaMensaje());

                FactoryRepositorio.get(Mensaje.class).agregar(mensaje1);

                user.getBandejaMensaje().agregarMensajes(mensaje1);
                FactoryRepositorio.get(BandejaMensaje.class).modificar(user.getBandejaMensaje());
            }
        }
    }
}
