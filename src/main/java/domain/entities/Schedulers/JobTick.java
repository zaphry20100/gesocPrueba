package domain.entities.Schedulers;

import domain.entities.Models.Usuarios.Rol;
import domain.repositories.factories.FactoryRepositorio;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobTick implements Job {

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        //System.out.println("This is the job A");
        Rol rol = new Rol();
        rol.setDescripcion("ejemplo scheduler");
        FactoryRepositorio.get(Rol.class).agregar(rol);
    }
}
