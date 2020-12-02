import domain.entities.Services.LocationService;
import domain.entities.Services.MonedaAPI;
import domain.entities.Validadores.AutenticadorContrasenas.AutenticadorContrasenas;
import org.quartz.*;
import server.Router;
import spark.Spark;
import spark.debug.DebugScreen;

import java.io.Console;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.port;

public class Main {
    public static void main(String[] args) {


        Spark.port(getHerokuAssignedPort());
        get("/hello", (req, res) -> "Hello Heroku World");
//        Router.init();
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
