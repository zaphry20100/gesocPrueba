package server;

import domain.controllers.*;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Entidades.Entidadbase;
import domain.entities.Models.Transacciones.DocumentoComercial;
import spark.Spark;

import static spark.Spark.options;
import static spark.utils.urldecoding.UrlDecode.path;

public class Router {

    public static void init() {




        Spark.staticFileLocation("/public");
//        Spark.before((rq, rp) -> rp.header("Access-Control-Allow-Origin", "*"));
        Router.configure();
    }

    private static void configure() {

        Spark.get("/hola",(req, res) -> "SGSGSDGSDGSGD Heroku World");

        EntidadJuridicaRestController entidadJuridicaRestController = new EntidadJuridicaRestController();
        Spark.post("/dev/entJuridica", entidadJuridicaRestController::crear);                           // ok
        Spark.delete("/dev/entJuridica/:id", entidadJuridicaRestController::eliminar);                  // ok
        Spark.put("/dev/entJuridica", entidadJuridicaRestController::modificar);                        // ok
        Spark.options("/dev/entJuridica/:id", entidadJuridicaRestController::existe);                   // ok
        Spark.get("/dev/entJuridica/:id", entidadJuridicaRestController::mostrar);                      // ok
        Spark.get("/dev/entJuridica", entidadJuridicaRestController::mostrarTodos);                     // ok
        Spark.post("/dev/entJuridica-realciones", entidadJuridicaRestController::crearRelacionesExt);


        // ABML para UI
        EntidadbaseRestController entidadbaseRestController = new EntidadbaseRestController();
        Spark.post("/gesoc/entBase/:idEntJur", entidadbaseRestController::crear);                       //ok
        Spark.delete("/gesoc/entBase/:idEntJur/:id", entidadbaseRestController::eliminar);              //ok
        Spark.put("/gesoc/entBase/:idEntJur", entidadbaseRestController::modificar);                    //ok
        Spark.options("/gesoc/entBase/:idEntJur/:id", entidadbaseRestController::existe);               //ok
        Spark.get("/gesoc/entBase/:idEntJur/:id", entidadbaseRestController::mostrar);                  //ok
        Spark.get("/dev/entBase", entidadbaseRestController::mostrarTodos);                             //ok
        Spark.get("/gesoc/entBase/:idEntJur", entidadbaseRestController::mostrarTodosDeEntJuridica);    //ok

        EgresosRestController egresosRestController = new EgresosRestController();
        Spark.post("/gesoc/egreso/:idEntJur", egresosRestController::crear);                            //ok
        Spark.delete("/gesoc/egreso/:idEntJur/:id", egresosRestController::eliminar);                   //ok
        Spark.put("/gesoc/egreso/:idEgreso", egresosRestController::modificar);                         //ok
        Spark.options("/gesoc/egreso/:idEntJur/:id", egresosRestController::existe);                    //ok
        Spark.get("/gesoc/egreso/:idEntJur/:id", egresosRestController::mostrar);                       //ok
        Spark.get("/gesoc/egreso/:idEntJur", egresosRestController::mostrarTodos);                      //ok

        Spark.post("/gesoc/revisoresEgreso/:idEntJur/:id", egresosRestController::agregarRevisores);                      //ok

        EgresosPresupuestosController egresosPresupuestosController = new EgresosPresupuestosController();
        Spark.post("/gesoc/egresoPresupuestosNuevos/:idEntJur", egresosPresupuestosController::crearConPresupuestos);
        //Spark.post("/gesoc/egresoPresupuestosAsignados/:idEntJur", egresosPresupuestosController::crearConPresupuestosAsignados);

        IngresosRestController ingresosRestController = new IngresosRestController();
        Spark.post("/gesoc/ingreso/:idEntJur", ingresosRestController::crear);                          //
        Spark.delete("/gesoc/ingreso/:idEntJur/:id", ingresosRestController::eliminar);                 //
        Spark.put("/gesoc/ingreso/:idEntJur", ingresosRestController::modificar);                       //
        Spark.options("/gesoc/ingreso/:idEntJur/:id", ingresosRestController::existe);                  //
        Spark.get("/gesoc/ingreso/:idEntJur/:id", ingresosRestController::mostrar);                     //
        Spark.get("/gesoc/ingreso/:idEntJur", ingresosRestController::mostrarTodos);                    //

        ProveedorRestController proveedorRestController = new ProveedorRestController();
        Spark.post("/gesoc/proveedor/:idEntJur", proveedorRestController::crear);                       //
        Spark.delete("/gesoc/proveedor/:idEntJur/:id", proveedorRestController::eliminar);              //
        Spark.put("/gesoc/proveedor/:idEntJur", proveedorRestController::modificar);                    //
        Spark.options("/gesoc/proveedor/:id", proveedorRestController::existe);                         //
        Spark.get("/gesoc/proveedor/:idEntJur/:id", proveedorRestController::mostrar);                  //
        Spark.get("/gesoc/proveedor/:idEntJur", proveedorRestController::mostrarTodos);                 //

        PresupuestoRestController presupuestoRestController = new PresupuestoRestController();
        Spark.post("/gesoc/presupuesto/:idEntJur", presupuestoRestController::crear);                       //
        Spark.delete("/gesoc/presupuesto/:idEntJur/:id", presupuestoRestController::eliminar);              //
        Spark.put("/gesoc/presupuesto/:idEntJur", presupuestoRestController::modificar);                    //
        Spark.options("/gesoc/presupuesto/:idEntJur/:id", presupuestoRestController::existe);               //
        Spark.get("/gesoc/presupuesto/:idEntJur/:id", presupuestoRestController::mostrar);                  //
        Spark.get("/gesoc/presupuesto/:idEntJur", presupuestoRestController::mostrarTodos);                 //

        ItemRestController itemRestController = new ItemRestController();
        Spark.post("/gesoc/item/:idEntJur", itemRestController::crear);                                               //
        Spark.delete("/gesoc/item/:idEntJur/:id", itemRestController::eliminar);                                      //
        Spark.put("/gesoc/item/:idEntJur/:id", itemRestController::modificar);                                        //
        Spark.options("/gesoc/item/:idEntJur/:id", itemRestController::existe);                                       //
        Spark.get("/gesoc/item/:idEntJur/:id", itemRestController::mostrar);                                          //
        Spark.get("/gesoc/item/:idEntJur", itemRestController::mostrarTodos);                                         //

        DocumentoComercialController documentoComercialController = new DocumentoComercialController();
        Spark.post("/gesoc/docCom", documentoComercialController::crear);                                   //
        Spark.delete("/gesoc/docCom/:id", documentoComercialController::eliminar);                          //
        Spark.put("/gesoc/docCom/:id", documentoComercialController::modificar);                            //
        Spark.options("/gesoc/docCom/:id", documentoComercialController::existe);                           //
        Spark.get("/gesoc/docCom/:id", documentoComercialController::mostrar);                              //
        Spark.get("/gesoc/docCom", documentoComercialController::mostrarTodos);                             //

        MensajesRestController mensajesRestController = new MensajesRestController();
        Spark.post("/gesoc/mensaje/:idBandeja", mensajesRestController::crear);                                //
        Spark.delete("/gesoc/mensaje/:idBandeja/:id", mensajesRestController::eliminar);                       //
        Spark.put("/gesoc/mensaje/:idBandeja/:id", mensajesRestController::modificar);                         //
        Spark.options("/gesoc/mensaje/:idBandeja/:id", mensajesRestController::existe);                        //
        Spark.get("/gesoc/mensaje/:idBandeja/:id", mensajesRestController::mostrar);                           //
        Spark.get("/gesoc/mensaje/:idBandeja", mensajesRestController::mostrarTodos);                          //
        Spark.get("/gesoc/leerMensajes/:idUser", mensajesRestController::mostrarMensajes);                      //
        Spark.get("/gesoc/mandarMensajes/:idUser", mensajesRestController::crearMensajes);                      //

        UsuarioRestController usuarioRestController = new UsuarioRestController();
        Spark.post("/gesoc/usuario/:idEntJur", usuarioRestController::crear);                               //
        Spark.delete("/gesoc/usuario/:idEntJur/:id", usuarioRestController::eliminar);                      //
        Spark.put("/gesoc/usuario/:idEntJur", usuarioRestController::modificar);                            //
        Spark.options("/gesoc/usuario/:idEntJur/:id", usuarioRestController::existe);                       //
        Spark.get("/gesoc/usuario/:idEntJur/:id", usuarioRestController::mostrar);                          //
        Spark.get("/gesoc/usuario/:idEntJur", usuarioRestController::mostrarTodos);                         //

        DireccionRestController direccionRestController = new DireccionRestController();
        Spark.post("/gesoc/direccion/:idEntJur", direccionRestController::crear);                             //
        Spark.delete("/gesoc/direccion/:idEntJur/:id", direccionRestController::eliminar);                    //
        Spark.put("/gesoc/direccion/:idEntJur", direccionRestController::modificar);                          //
        Spark.options("/gesoc/direccion/:idEntJur/:id", direccionRestController::existe);                     //
        Spark.get("/gesoc/direccion/:idEntJur/:id", direccionRestController::mostrar);                        //
        Spark.get("/gesoc/direccion/:idEntJur", direccionRestController::mostrarTodos);                       //

        CriteriosRestController criteriosRestController = new CriteriosRestController();
        Spark.post("/gesoc/criterios/:idEntJur", criteriosRestController::crear);                                    //
        Spark.delete("/gesoc/criterios/:idEntJur/:id", criteriosRestController::eliminar);                           //
        Spark.put("/gesoc/criterios/:idEntJur", criteriosRestController::modificar);                                 //
        Spark.options("/gesoc/criterios/:idEntJur/:id", criteriosRestController::existe);                            //
        Spark.get("/gesoc/criterios/:idEntJur/:id", criteriosRestController::mostrar);                               //
        Spark.get("/gesoc/criterios/:idEntJur", criteriosRestController::mostrarTodos);                              //

        CategoriasRestController categoriasRestController = new CategoriasRestController();
        Spark.post("/gesoc/categorias/:idEntJur", categoriasRestController::crear);                                  //
        Spark.delete("/gesoc/categorias/:idEntJur/:id", categoriasRestController::eliminar);                         //
        Spark.put("/gesoc/categorias/:idEntJur", categoriasRestController::modificar);                               //
        Spark.options("/gesoc/categorias/:idEntJur/:id", categoriasRestController::existe);                          //
        Spark.get("/gesoc/categorias/:idEntJur/:id", categoriasRestController::mostrar);                             //
        Spark.get("/gesoc/categorias/:idEntJur", categoriasRestController::mostrarTodos);                            //

        Spark.post("/gesoc/categoriasEgreso/:idEntJur", categoriasRestController::agregarCategoriaEgreso);
        Spark.post("/gesoc/categoriasIngreso/:idEntJur", categoriasRestController::agregarCategoriaIngreso);
        Spark.post("/gesoc/categoriasPresupuesto/:idEntJur", categoriasRestController::agregarCategoriaPresupuesto);

        // Funcionalidades
        VinculadorController vinculadorController = new VinculadorController();
        Spark.get("/gesoc/criteriosVinculacion/:idEntJur", vinculadorController::mostrar);                          //
        Spark.post("/gesoc/vincularOperaciones/:idEntJur", vinculadorController::vincular);                         //
        Spark.get("/gesoc/consultarEstado/:idEntJur", vinculadorController::consultarEstado);

        ValidadorTrasparenciaRestController validadorTrasparenciaRestController = new ValidadorTrasparenciaRestController();
        Spark.get("/gesoc/ValidadorTransparencia/estado/:idEntJur", validadorTrasparenciaRestController::mostrarEstado);                          //
        Spark.get("/gesoc/ValidadorTransparencia/ejecutar/:idEntJur", validadorTrasparenciaRestController::ejecutarValidacion);                         //
        Spark.get("/gesoc/ValidadorTransparencia/ejecutar/:idEntJur/:id", validadorTrasparenciaRestController::ejecutarValidacion);

        ConfiguracionEntJuridicaController configEntJurController = new ConfiguracionEntJuridicaController();
        Spark.get("/gesoc/configuracionEntidadJuridica/:id", configEntJurController::mostrar);            //
        Spark.put("/gesoc/configuracionEntidadJuridica", configEntJurController::modificar);          //

        CategoriaEntidadRestController categoriaEntidadRestController = new CategoriaEntidadRestController();
        Spark.post("/dev/categoriasEntidad", categoriaEntidadRestController::crearCategorias);
        Spark.post("/gesoc/recategorizar", categoriaEntidadRestController::recategorizar);
        Spark.get("/gesoc/categoriaEntidad/:id", categoriaEntidadRestController::mostrar);

        EntBasicasController entBasicasController = new EntBasicasController();
        Spark.post("/gesoc/medioPago", entBasicasController::crearMedioPago);
        Spark.get("/gesoc/medioPago",entBasicasController::mostrarMedioPago);
        Spark.get("/gesoc/moneda", entBasicasController::crearMonedas);
        Spark.get("/gesoc/mostrarMonedas", entBasicasController::mostrarMoneda);

        LoginController loginController = new LoginController();
        Spark.post("/gesoc/login", loginController::autenticarUsuario);

        RolRestController rolRestController = new RolRestController();
        Spark.post("/gesoc/rol", rolRestController::crear);
        Spark.get("/gesoc/rol/:id", rolRestController::mostrar);
        Spark.get("/gesoc/rol", rolRestController::mostrarTodos);

    }
}
