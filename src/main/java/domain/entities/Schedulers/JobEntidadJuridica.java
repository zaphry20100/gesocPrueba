package domain.entities.Schedulers;

import org.quartz.*;

import java.util.concurrent.Callable;

public class JobEntidadJuridica<T> implements Job {

    private Callable<T> miFuncion;

    public void aEjecutar(Callable<T> funcion){
        try{
           miFuncion = funcion;
        }catch(Exception exception){
            System.out.println("No.");
        }
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try{
            JobEntidadJuridica jobEntidadJuridica = (JobEntidadJuridica)context.getScheduler().getContext().get("JobEntidadJuridica");
            System.out.println("Se esta validado por scheduler.");
            jobEntidadJuridica.miFuncion.call();
            System.out.println("Se han validado todos los egresos.");
        }catch(Exception exception){
            System.out.println("Sos o te haces? No, no se pudo validar, ok?");
        }

    }
}
