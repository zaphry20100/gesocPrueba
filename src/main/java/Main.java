import domain.entities.Models.Usuarios.Rol;
import domain.entities.Schedulers.JobTick;
import domain.entities.Services.LocationService;
import domain.entities.Services.MonedaAPI;
import domain.entities.Validadores.AutenticadorContrasenas.AutenticadorContrasenas;
import domain.repositories.factories.FactoryRepositorio;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import server.Router;
import spark.Spark;
import spark.debug.DebugScreen;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.port;

public class Main {

    public static Scheduler sched;
    public static void main(String[] args) {

        SchedulerFactory schedFact = new StdSchedulerFactory();

//        try {
//
//            sched = schedFact.getScheduler();
//            JobDetail job = JobBuilder.newJob(JobTick.class).withIdentity("myJob", "group1").usingJobData("jobSays", "Hello World!").usingJobData("myFloatValue", 3.141f).build();
//            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(30).repeatForever()).build();
//            sched.scheduleJob(job, trigger);
//            sched.start();
//
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }

        Spark.port(getHerokuAssignedPort());
        //get("/hello", (req, res) -> "Hello Heroku World");
        Router.init();
        DebugScreen.enableDebugScreen();

    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 9000; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
