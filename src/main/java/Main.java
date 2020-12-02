import domain.entities.Services.LocationService;
import domain.entities.Services.MonedaAPI;
import domain.entities.Validadores.AutenticadorContrasenas.AutenticadorContrasenas;
import org.quartz.*;

import java.io.Console;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static Scheduler scheduler;
    public static void main(String[] args) {

        AutenticadorContrasenas autenticadorContrasenas = new AutenticadorContrasenas();
        byte[] pass = null;
        try{
            pass = autenticadorContrasenas.hashClave("sofi20100","hola");


            System.out.println("contra: " + autenticadorContrasenas.toHex(pass));
            if ("37b6c0b7099d5cce8171a0612f7d76df".equals(autenticadorContrasenas.toHex(pass).toString())){
                System.out.println("si");
            }

        }
        catch (Exception ex){

        }

        /*
        try{
            System.out.println("Iniciando Scheduler...");
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        }catch (Exception ex){
            System.out.println("No se pudo iniciar el Scheduler.");
        }

        LocationService locationService = LocationService.instancia();

//INICIO MODELOS
        CategoriaEntidad categoriaEntidad = new CategoriaEntidad("Mediana - Tipo 1");
        Item item1 = new Item(1, "item1", "es un item1", 69, true);
        Item item2 = new Item(2, "item2", "es un item2", 420, true);
        Item item3 = new Item(3,"item2", "es un item3", 666, true);

        List<PaisAPI> paises;
        List<ProvinciaAPI> provincias;
        List<CiudadAPI> ciudades;
        List<MonedaAPI> monedaAPIS;

        try{
            paises = locationService.listadoDePaises();
            PaisAPI pais = paises.get(0);
            provincias = locationService.listadoDeProvincias(pais);
            ProvinciaAPI provincia = provincias.get(0);
            ciudades = locationService.listadoDeCiudades(provincia);
            monedaAPIS = locationService.listadoDeMonedas();
        }catch(IOException ex){
            System.out.println("No se pueden obtener las listas");
        }

        Ciudad ciudadDeCaba = new Ciudad();
        Direccion direccionDeCaba = new Direccion(1,"Ruiz Huidobro", 5487, 8, "C",1430, ciudadDeCaba);
        Direccion direccionDeCaba2 = new Direccion(2,"kcyo", 475,4,"A", 1430, ciudadDeCaba);

        Proveedor proveedor = new Proveedor(12345678765L,direccionDeCaba,"Diego Maradona", true);

        Map<String,String> map = new HashMap<>();
        map.put("CriterioCompraDeUnPresupuesto", "si");
        map.put("CriterioCantidadPresupuestos", "2");
        //map.put("CriterioMenorValor", "CRITERIO_MENOR_PRECIO");

        Map<String,Integer> mapScheduler = new HashMap<>();
        mapScheduler.put("IntervaloValidacionEgresos", 1);

        ConfiguracionEntidadJuridica configuracionEntidadJuridica = new ConfiguracionEntidadJuridica(map, mapScheduler);

        Entidadjuridica entidadJuridca = new Entidadjuridica(direccionDeCaba2, categoriaEntidad,"LeoHerbalife", proveedor,new ArrayList<>(), 123, true, configuracionEntidadJuridica,10,"Servicios",50950000);
        Entidadbase entidadbase = new Entidadbase(1,"NO SE", "de nuevo, NO SE", entidadJuridca,true);

        Permiso permiso1 = new Permiso(1,"esto es un permiso");
        Rol rol1 = new Rol(1,"esto es un rol muy importante");
        Rolxpermiso rolxpermiso1 = new Rolxpermiso(1,permiso1,rol1);
        Usuario usuario1 = new Usuario(1,rol1,entidadJuridca,"bob esponja","bobesponja@bikinibottom.com", "hamburguesas", true);

        Ingreso ingreso = new Ingreso(333, new Date(),3);
        List<Ingreso> ingresos = new ArrayList<>();
        ingresos.add(ingreso);

        DocumentoComercial documentoComercial1 = new DocumentoComercial(1,"/hola","Factura A");
        Mediodepago mediodepago1 = new Mediodepago(1,"TC", "TC?", true);
        Egreso egreso1 = new Egreso(1,entidadJuridca,new ArrayList<>(),new ArrayList<>(), null,usuario1,mediodepago1, item1.getValor() + item2.getValor(), Calendar.getInstance().getTime(), 1234, documentoComercial1,ingresos, proveedor);
        Egresoxitem egresoxitem1 = new Egresoxitem(1, egreso1,item1, item1.getValor(), Calendar.getInstance().getTime());
        Egresoxitem egresoxitem2 = new Egresoxitem(2, egreso1,item2, item2.getValor(), Calendar.getInstance().getTime());
        Egresoxitem egresoxitem3 = new Egresoxitem(3, egreso1,item3, item3.getValor(), Calendar.getInstance().getTime());

        List<Egresoxitem> listaEXI = new ArrayList<>();
        listaEXI.add(egresoxitem1);
        listaEXI.add(egresoxitem2);
        listaEXI.add(egresoxitem3);

        egreso1.setListaEgresxItem(listaEXI);

        Presupuesto presupuesto1 = new Presupuesto(1, egreso1,documentoComercial1, item1.getValor() + item2.getValor(), "detalles");
        Presupuesto presupuesto2 = new Presupuesto(2,egreso1,documentoComercial1,item3.getValor(),"mas detalles");

        egreso1.setPresupuestoElegido(presupuesto1);

        PresupuestoXItem presupuestoXItem1 = new PresupuestoXItem(1, presupuesto1, item1);
        PresupuestoXItem presupuestoXItem2 = new PresupuestoXItem(2, presupuesto1, item2);
        PresupuestoXItem presupuestoXItem3 = new PresupuestoXItem(3, presupuesto2, item3);

        List<Presupuesto> presupuestos = new ArrayList<>();
        presupuestos.add(presupuesto1);
        presupuestos.add(presupuesto2);

        egreso1.setListaPresupuestos(presupuestos);


        Egreso egreso2 = new Egreso(2,entidadJuridca,new ArrayList<>(),new ArrayList<>(), null,usuario1,mediodepago1, item1.getValor() + item2.getValor(), Calendar.getInstance().getTime(), 1234, documentoComercial1,ingresos, proveedor);
        Egresoxitem egreso2xitem1 = new Egresoxitem(1, egreso1,item1, item1.getValor(), Calendar.getInstance().getTime());
        Egresoxitem egreso2xitem2 = new Egresoxitem(2, egreso1,item2, item2.getValor(), Calendar.getInstance().getTime());
        Egresoxitem egreso2xitem3 = new Egresoxitem(3, egreso1,item3, item3.getValor(), Calendar.getInstance().getTime());

        List<Egresoxitem> listaE2XI = new ArrayList<>();
        listaE2XI.add(egreso2xitem1);
        listaE2XI.add(egreso2xitem2);
        listaE2XI.add(egreso2xitem3);

        egreso2.setListaEgresxItem(listaE2XI);

        Presupuesto presupuesto12 = new Presupuesto(1, egreso2, documentoComercial1, item1.getValor() + item2.getValor(), "detalles");
        Presupuesto presupuesto22 = new Presupuesto(2, egreso2, documentoComercial1, item3.getValor(),"mas detalles");

        egreso2.setPresupuestoElegido(presupuesto22);

        PresupuestoXItem presupuesto2XItem1 = new PresupuestoXItem(1, presupuesto1, item1);
        PresupuestoXItem presupuesto2XItem2 = new PresupuestoXItem(2, presupuesto1, item2);
        PresupuestoXItem presupuesto2XItem3 = new PresupuestoXItem(3, presupuesto2, item3);

        List<Presupuesto> presupuestos2 = new ArrayList<>();
        presupuestos2.add(presupuesto12);
        //presupuestos2.add(presupuesto22);

        egreso2.setListaPresupuestos(presupuestos2);



        List<Egreso> egresos = new ArrayList<>();
        egresos.add(egreso1);
        egresos.add(egreso2);

        entidadJuridca.setListaEgresos(egresos);

//FIN MODELOS

        ValidadorTransparencia.setConfig(entidadJuridca.getConfiguracionEntidadJuridica());

        List<Entidadjuridica> entidadjuridicaList = new ArrayList<>();
        entidadjuridicaList.add(entidadJuridca);

        JobEntidadJuridica jobEntidadJuridica = new JobEntidadJuridica();

        jobEntidadJuridica.aEjecutar(new Callable() {
            @Override
            public Object call() throws Exception {
                ValidadorTransparencia.setConfig(entidadJuridca.getConfiguracionEntidadJuridica());
                for(Egreso egreso: entidadJuridca.getListaEgresos()){
                    if(!egreso.isValidado()) {
                        egreso.validar();
                        System.out.println("Egreso " + egreso.getIdEgreso() + " fue validado.");
                    }
                }
                return new Object();
            }
        });


        List<JobEntidadJuridica> jobEntidadJuridicas = new ArrayList<>();
        jobEntidadJuridicas.add(jobEntidadJuridica);
        int minutes = entidadJuridca.getConfiguracionEntidadJuridica().getMapScheduler().get("IntervaloValidacionEgresos");


        try{
            scheduler.getContext().put("JobEntidadJuridica", jobEntidadJuridica);

            JobDetail jobDetail = JobBuilder.newJob(jobEntidadJuridica.getClass())
                    .withIdentity("Entidad Juridica Job","grupo1")
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("Entidad Juridica Trigger", "grupo1")
                    .startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule()
                            .withIntervalInMinutes(minutes).repeatForever()).build();

            scheduler.scheduleJob(jobDetail,trigger);
        }catch (Exception ex){
            System.out.println("No se pudo iniciar el Scheduler.");
        }

        try{
            scheduler.start();
        }catch (Exception exception){
            System.out.println("Soy una vistimaaaa");
        }


        List<Usuario> usuariosRevisores = new ArrayList<>();
        usuariosRevisores.add(usuario1);
        egreso1.setRevisores(usuariosRevisores);
        egreso2.setRevisores(usuariosRevisores);

        FiltroLeido filtroLeido = new FiltroLeido();
        usuario1.getBandejaMensaje().agregarFiltro(filtroLeido);

        for(Mensaje mensaje1: usuario1.getBandejaMensaje().leerMensajes()){
            System.out.println("Tu mensaje es: " + mensaje1.getContenido());
        }

        System.out.println("Se terminaron los mensajes.");

        usuario1.getBandejaMensaje().removerFiltro(filtroLeido);
        for(Mensaje mensaje1: usuario1.getBandejaMensaje().leerMensajes()){
            System.out.println("Tu mensaje es: " + mensaje1.getContenido());
        }

        System.out.println("Se terminaron los mensajes.");

        System.out.println("Validador de trasparencias con " + ValidadorTransparencia.resultadosLength() + " resultados");

        entidadJuridca.categorizar();

        System.out.println("Tu categoria es: " + entidadJuridca.getCategoriaEntidad().getDescripcion());



        Menu menu = new Menu();
        menu.printOptions();
        menu.options();



        try{
            scheduler.shutdown();
        }catch (Exception exception){
            System.out.println("No se pudo finalizar el scheduler.");
        }
*/
    }
}