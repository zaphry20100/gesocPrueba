package miTest;

import domain.entities.Models.Direccion.Ciudad;
import domain.entities.Models.Direccion.Direccion;
import domain.entities.Models.Entidades.CategoriasEntidad.CategoriaEntidad;
import domain.entities.Models.Entidades.ConfiguracionEntidadJuridica;
import domain.entities.Models.Entidades.EntidadJuridica;
import domain.entities.Models.Entidades.Entidadbase;
import domain.entities.Models.Transacciones.*;
import domain.entities.Models.Usuarios.Permiso;
import domain.entities.Models.Usuarios.Rol;
import domain.entities.Models.Usuarios.Rolxpermiso;
import domain.entities.Models.Usuarios.Usuario;
import domain.entities.Validadores.ValidadorTransparencia.ResultadoValidacion;
import org.junit.Before;

import java.util.List;

public class TestsFuncionales {

    Usuario usuario1;
    Permiso permiso1;
    Rol rol1;
    Rolxpermiso rolxpermiso1;

    CategoriaEntidad categoriaEntidad;

    Item item1;// = new Item(1, "item1", "es un item1", 69, true);
    Item item2;// = new Item(2, "item2", "es un item2", 420, true);
    Item item3;// = new Item(3,"item2", "es un item3", 666, true);
    Ciudad ciudadDeCaba;// = new Ciudad();
    Direccion direccionDeCaba;// = new Direccion(1,"Ruiz Huidobro", 5487, 8, "C",1430, ciudadDeCaba);
    Direccion direccionDeCaba2;// = new Direccion(2,"kcyo", 475,4,"A", 1430, ciudadDeCaba);
    Proveedor proveedor;// = new Proveedor(12345678765L,direccionDeCaba,"Diego Maradona", true);

    ConfiguracionEntidadJuridica configuracionEntidadJuridica;// = new ConfiguracionEntidadJuridica(new HashMap<String,String>(),new HashMap<String,Integer>());

    EntidadJuridica entidadJuridca;// = new Entidadjuridica(direccionDeCaba2, categoriaEntidad,"LeoHerbalife", proveedor,new ArrayList<>(), 123, true, configuracionEntidadJuridica, 123,"Construccion",503880001);
    Entidadbase entidadbase;// = new Entidadbase(1,"NO SE", "de nuevo, NO SE", entidadJuridca,true);

    /*
    Permiso permiso1;// = new Permiso(1,"esto es un permiso");
    Rol rol1;// = new Rol(1,"esto es un rol muy importante");
    Rolxpermiso rolxpermiso1;// = new Rolxpermiso(1,permiso1,rol1);
    Usuario usuario1;// = new Usuario(1,rol1,entidadJuridca,"bob esponja","bobesponja@bikinibottom.com", "hamburguesas", true);
*/

    Ingreso ingreso;// = new Ingreso(333, new Date(),3);
    List<Ingreso> ingresos;// = new ArrayList<>();

    DocumentoComercial documentoComercial1;// = new DocumentoComercial(1,"/hola","Factura A");
    Mediodepago mediodepago1;// = new Mediodepago(1,"TC", "TC?", true);
    Egreso egreso1;// = new Egreso(1,entidadJuridca,new ArrayList<>(),new ArrayList<>(), null,usuario1,mediodepago1, item1.getValor() + item2.getValor() + item3.getValor(), Calendar.getInstance().getTime(), 1234, documentoComercial1, ingresos, proveedor);
    Egresoxitem egresoxitem1;// = new Egresoxitem(1, egreso1,item1, item1.getValor(), Calendar.getInstance().getTime());
    Egresoxitem egresoxitem2;// = new Egresoxitem(2, egreso1,item2, item2.getValor(), Calendar.getInstance().getTime());
    Egresoxitem egresoxitem3;// = new Egresoxitem(3, egreso1,item3, item3.getValor(), Calendar.getInstance().getTime());

    List<Egresoxitem> listaEXI;// = new ArrayList<>();

    Presupuesto presupuesto1;// = new Presupuesto(1, egreso1,documentoComercial1, item1.getValor() + item2.getValor(), "detalles");
    Presupuesto presupuesto2;// = new Presupuesto(2,egreso1,documentoComercial1,item3.getValor(),"mas detalles");

    PresupuestoXItem presupuestoXItem1;// = new PresupuestoXItem(1, presupuesto1, item1);
    PresupuestoXItem presupuestoXItem2;// = new PresupuestoXItem(2, presupuesto1, item2);
    PresupuestoXItem presupuestoXItem3;// = new PresupuestoXItem(3, presupuesto2, item3);

    List<Presupuesto> presupuestos;// = new ArrayList<>();

    ResultadoValidacion resultadoValidacion;// = new ResultadoVa


    @Before
    public void setUp() throws Exception {

        permiso1 = new Permiso();
        permiso1.setDescripcion("esto es un permiso");

        rol1 = new Rol();
        rol1.setDescripcion("esto es un rol muy importante");

        rolxpermiso1 = new Rolxpermiso();
        rolxpermiso1.setPermiso(permiso1);
        rolxpermiso1.setRol(rol1);

        usuario1 = new Usuario();
        usuario1.setRol(rol1);
        usuario1.setEntidadJuridica(entidadJuridca);
        usuario1.setNombreUsuario("bob esponja");
        usuario1.setMail("bobesponja@bikinibottom.com");
        usuario1.setClave("hamburguesas");
        usuario1.setEstado(true);

        CategoriaEntidad categoriaEntidad = new CategoriaEntidad();
        categoriaEntidad.setDescripcion("Mediana - Tipo 1");

        Item item1 = new Item();
    }
/*
    @Test
    public void test_egreso_validar_usuario_tiene_mensaje(){

        List<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(usuario1);

        egreso1.setRevisores(usuarioList);

        List<Presupuesto> presupuestos = new ArrayList<>();
        presupuestos.add(presupuesto1);
        presupuestos.add(presupuesto2);

        egreso1.setListaPresupuestos(presupuestos);
        egreso1.setPresupuestoElegido(presupuesto1);

        ValidadorTransparencia.setConfig(entidadJuridca.getConfiguracionEntidadJuridica());

        egreso1.validar();

        ValidadorTransparencia.assertNotNull(usuario1.getBandejaMensaje().leerMensajes().size() == 1);
        Assert.assertNotNull(ValidadorTransparencia.resultadosLength() == 1);

    }

    @Test
    public void test_config_validacion(){
        Map<String, String> map = new HashMap<>();
        map.put("CriterioCompraDeUnPresupuesto", "si");

        ConfiguracionEntidadJuridica configuracionEntidadJuridica1 = new ConfiguracionEntidadJuridica(map, new HashMap<String,Integer>());

        ValidadorTransparencia.setConfig(configuracionEntidadJuridica1);

        List<Presupuesto> presupuestos = new ArrayList<>();
        presupuestos.add(presupuesto1);

        egreso1.setListaPresupuestos(presupuestos);
        egreso1.setPresupuestoElegido(presupuesto1);

        egreso1.validar();
        Assert.assertTrue(egreso1.isValidado());

        map.put("CriterioCantidadPresupuestos", "2");
        configuracionEntidadJuridica1.setConfigEntidadJuridica(map);
        ValidadorTransparencia.setConfig(configuracionEntidadJuridica1);

        egreso1.validar();
        Assert.assertFalse(egreso1.isValidado());

        presupuestos.add(presupuesto2);
        egreso1.setListaPresupuestos(presupuestos);

        egreso1.validar();
        Assert.assertTrue(egreso1.isValidado());
    }

    @Test
    public void test_categorizar_entidad_juridica(){
        entidadJuridca.categorizar();

        Assert.assertEquals(entidadJuridca.getCategoriaEntidad().getDescripcion(),"Mediana - Tipo 1");
    }
*/
}
