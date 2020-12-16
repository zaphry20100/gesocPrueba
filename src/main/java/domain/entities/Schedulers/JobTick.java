package domain.entities.Schedulers;

import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;
import domain.entities.Models.Usuarios.Rol;
import domain.entities.Validadores.ValidadorTransparencia.ValidadorTransparencia;
import domain.repositories.factories.FactoryRepositorio;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;
import java.util.concurrent.Callable;

public class JobTick implements Job {

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        //System.out.println("This is the job A");
        List<EntidadJuridica> entidadesJuridicas = FactoryRepositorio.get(EntidadJuridica.class).buscarTodos();
        entidadesJuridicas.forEach(x -> {
//            ValidadorTransparencia.setConfig(x.getConfiguracionEntidadJuridica());
//            for(Egreso egreso: x.getTodosLosEgresos()){
//                if(!egreso.isValidado()) {
//                    ValidadorTransparencia.validar(egreso);
//                    System.out.println("Egreso " + egreso.getIdEgreso() + " fue validado.");
//                }
//            }
            Rol rol = new Rol();
            rol.setDescripcion("ejemplo scheduler " + x.getIdEntidadJuridica() );
            FactoryRepositorio.get(Rol.class).agregar(rol);
        });



        System.out.println("Fin validacion por scheduler.");
    }
}