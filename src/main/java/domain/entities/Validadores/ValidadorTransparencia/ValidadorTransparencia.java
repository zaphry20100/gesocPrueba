package domain.entities.Validadores.ValidadorTransparencia;

import domain.entities.Models.Entidades.ConfiguracionEntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;
import domain.entities.Validadores.ValidadorTransparencia.CriteriosValidacion.CriteriosValidacion;
import domain.repositories.factories.FactoryRepositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ValidadorTransparencia{
    private static ConfiguracionEntidadJuridica config;
    private static List<CriteriosValidacion> listaCriterios;
    private static List<ResultadoValidacion> resultadosValidaciones = new ArrayList<>();


    public ValidadorTransparencia(ConfiguracionEntidadJuridica config) {
        ValidadorTransparencia.config = config;

        setListaCriterios();
    }

    public ValidadorTransparencia(){}

    public List<CriteriosValidacion> getListaCriterios() {
        return ValidadorTransparencia.listaCriterios;
    }

    public static void setListaCriterios() {
        ValidadorTransparencia.listaCriterios = new ArrayList<>();

        for (String criterio: config.getConfigEntidadJuridica().keySet()) { //Se setean dos veces los criterios!
            Class<?> nombreClaseCriterio;

            try{
                nombreClaseCriterio = Class.forName("domain.entities.Validadores.ValidadorTransparencia.CriteriosValidacion." + criterio);
                listaCriterios.add((CriteriosValidacion) nombreClaseCriterio.newInstance());
            }catch (ClassNotFoundException exception){
                System.out.println("No existe la clase");
            }catch (InstantiationException exception){
                System.out.println("Nooo");
            }catch (IllegalAccessException exception){
                System.out.println("No podes querido");
            }
        }
    }


    public static String validar(Egreso egreso) {
        final String[] detalleResultado = {"Todo piola"};

        boolean resultado = false;

        resultado = ValidadorTransparencia.listaCriterios.stream().allMatch(x -> {

            try {
                boolean validacion = x.validar(egreso, config);
                if(!validacion){
                    detalleResultado[0] = "No amigo, asi no...";
                }
                return validacion;
            } catch (Exception e) {
                System.out.println("Nope :( Exception");
                detalleResultado[0] = e.getMessage(); //ya veremos
            }
            return false;
        });

        //ResultadoValidacion resultadoValidacion = new ResultadoValidacion(1, egreso, detalleResultado[0], LocalDate.now(), resultado);
        ResultadoValidacion resultadoValidacion = new ResultadoValidacion();
        resultadoValidacion.setEgreso(egreso);
        resultadoValidacion.setDetalles(detalleResultado[0]);
        resultadoValidacion.setFechaValidacion(LocalDate.now());
        resultadoValidacion.setResultado(resultado);
        ValidadorTransparencia.resultadosValidaciones.add(resultadoValidacion);
        FactoryRepositorio.get(ResultadoValidacion.class).agregar(resultadoValidacion);

        egreso.setValidado(resultado);
        FactoryRepositorio.get(Egreso.class).modificar(egreso);

        return resultadoValidacion.toString();
    }

    public static int resultadosLength(){
        return resultadosValidaciones.size();
    }

    public static ConfiguracionEntidadJuridica getConfig() {
        return config;
    }

    public static void setConfig(ConfiguracionEntidadJuridica config) {
        ValidadorTransparencia.config = config;
        setListaCriterios();
    }


}
