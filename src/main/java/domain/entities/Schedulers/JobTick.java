package domain.entities.Schedulers;

import domain.controllers.ValidadorTrasparenciaRestController;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;
import domain.entities.Models.Usuarios.Rol;
import domain.entities.Validadores.ValidadorTransparencia.ValidadorTransparencia;
import domain.repositories.factories.FactoryRepositorio;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.concurrent.Callable;

public class JobTick implements Job {

    private ValidadorTransparencia validadorTransparencia;

    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        //System.out.println("This is the job A");


        if(validadorTransparencia==null){
            validadorTransparencia = new ValidadorTransparencia();
        }
        
        System.out.println("Se esta validado por scheduler.");

        List<EntidadJuridica> entidadesJuridicas = FactoryRepositorio.get(EntidadJuridica.class).buscarTodos();
        entidadesJuridicas.forEach(x -> {
            x.quitarRepetidos();
            x.getTodosLosEgresos().forEach(y->y.quitarRepetidos());
            System.out.println("Ent Jur: "+x.getIdEntidadJuridica());
            //validadorTransparencia.setConfig(x.getConfiguracionEntidadJuridica());
            for(Egreso egreso: x.getTodosLosEgresos()){
                if(!egreso.isValidado()) {
                    egreso.validar();
                    System.out.println("Egreso " + egreso.getIdEgreso() + " fue validado.");
                }
            }
        });

        System.out.println("Fin validacion por scheduler.");

//        try{
//
//            String str = "A";
//            BufferedWriter writer = new BufferedWriter(new FileWriter("./testFile.txt", true));
//            writer.append(' ');
//            writer.append(str);
//            writer.close();
//
//            BufferedReader reader = new BufferedReader(new FileReader("./testFile.txt"));
//            String currentLine = reader.readLine();
//            reader.close();
//
//            System.out.println("READED: "+ currentLine);
//
//            Rol rol = new Rol();
//            rol.setDescripcion(currentLine);
//            FactoryRepositorio.get(Rol.class).agregar(rol);
//
//        }catch(Exception ex){
//
//        }


    }
}