package miTest;

import domain.entities.Models.Direccion.*;
import domain.entities.Models.Entidades.*;
import domain.entities.Models.Transacciones.*;
import domain.entities.Models.Usuarios.Permiso;
import domain.entities.Models.Usuarios.Rol;
import domain.entities.Models.Usuarios.Rolxpermiso;
import domain.entities.Models.Usuarios.Usuario;
import domain.entities.Validadores.ValidadorTransparencia.ResultadoValidacion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelsTest {

//
//    CategoriaEntidad categoriaEntidad = new CategoriaEntidad("Mediana - Tipo 1");
//    Item item1 = new Item(1, "item1", "es un item1", 69, true);
//    Item item2 = new Item(2, "item2", "es un item2", 420, true);
//    Item item3 = new Item(3,"item2", "es un item3", 666, true);
//
//    @Before
//    public void setUp() throws Exception {
//
//    CategoriaEntidad categoriaEntidad = new CategoriaEntidad();
//    categoriaEntidad.setDescripcion("");
//
//    Item item1 = new Item();
//    item1.setDescripcion("item1");
//    item1.setProductoServicio("es un item1");
//    item1.setValor(1);
//    item1.setEstado(true);
//
//    Item item2 = new Item();
//    item2.setDescripcion("item2");
//    item2.setProductoServicio("es un item2");
//    item2.setValor(1);
//    item2.setEstado(true);
//
//    Item item3 = new Item();
//    item3.setDescripcion("item3");
//        item3.setProductoServicio("es un item3");
//        item3.setValor(1);
//        item3.setEstado(true);
//
//
//    }
//
//    /*
//    Ciudad ciudadDeCaba = new Ciudad();
//    Direccion direccionDeCaba = new Direccion(1,"Ruiz Huidobro", 5487, 8, "C",1430, ciudadDeCaba);
//    Direccion direccionDeCaba2 = new Direccion(2,"kcyo", 475,4,"A", 1430, ciudadDeCaba);
//*/
//
//    /*
//    Proveedor proveedor = new Proveedor(12345678765L,direccionDeCaba,"Diego Maradona", true);
//*/
//
//
//  /*
//    ConfiguracionEntidadJuridica configuracionEntidadJuridica = new ConfiguracionEntidadJuridica(new HashMap<String,String>(), new HashMap<String, Integer>());
//*/
//
//  /*
//    EntidadJuridica entidadJuridca = new EntidadJuridica(direccionDeCaba2, categoriaEntidad,"LeoHerbalife", proveedor,new ArrayList<>(), 123, true, configuracionEntidadJuridica, 123,"Construcción",21);
//    Entidadbase entidadbase = new Entidadbase(1,"NO SE", "de nuevo, NO SE", entidadJuridca,true);
//*/
//
//
//  /*
//    Permiso permiso1 = new Permiso(1,"esto es un permiso");
//    Rol rol1 = new Rol(1,"esto es un rol muy importante");
//    Rolxpermiso rolxpermiso1 = new Rolxpermiso(1,permiso1,rol1);
//    Usuario usuario1 = new Usuario(1,rol1,entidadJuridca,"bob esponja","bobesponja@bikinibottom.com", "hamburguesas", true);
//*/
//
//
//  /*
//    Ingreso ingreso = new Ingreso(333, new Date(),3);
//    List<Ingreso> ingresos = new ArrayList<>();
//*/
//
//
//  /*
//    DocumentoComercial documentoComercial1 = new DocumentoComercial(1,"/hola","Factura A");
//    Mediodepago mediodepago1 = new Mediodepago(1,"TC", "TC?", true);
//    Egreso egreso1 = new Egreso(1,entidadJuridca,new ArrayList<>(),new ArrayList<>(), null,usuario1,mediodepago1, item1.getValor() + item2.getValor() + item3.getValor(),Calendar.getInstance().getTime(), 1234, documentoComercial1,ingresos, proveedor);
//    Egresoxitem egresoxitem1 = new Egresoxitem(1, egreso1,item1, item1.getValor(), Calendar.getInstance().getTime());
//    Egresoxitem egresoxitem2 = new Egresoxitem(2, egreso1,item2, item2.getValor(), Calendar.getInstance().getTime());
//    Egresoxitem egresoxitem3 = new Egresoxitem(3, egreso1,item3, item3.getValor(), Calendar.getInstance().getTime());
//*/
//
//
//  /*
//    List<Egresoxitem> listaEXI = new ArrayList<>();
//*/
//
//
//
//  /*
//    Presupuesto presupuesto1 = new Presupuesto(1, egreso1,documentoComercial1, item1.getValor() + item2.getValor(), "detalles");
//    Presupuesto presupuesto2 = new Presupuesto(2,egreso1,documentoComercial1,item3.getValor(),"mas detalles");
//    PresupuestoXItem presupuestoXItem1 = new PresupuestoXItem(1, presupuesto1, item1);
//    PresupuestoXItem presupuestoXItem2 = new PresupuestoXItem(2, presupuesto1, item2);
//    PresupuestoXItem presupuestoXItem3 = new PresupuestoXItem(3, presupuesto2, item3);
//
//    List<Presupuesto> presupuestos = new ArrayList<>();
//*/
//
//  /*
//    ResultadoValidacion resultadoValidacion = new ResultadoValidacion(1, egreso1, "detalles", Calendar.getInstance().getTime(), true);
//*/
//
//
//
//
////    @Test
////    public void test_categoria(){
////
////        Assert.assertNotNull(categoriaEntidad.getDescripcion());
////
////    }
//
//    @Test
//    public void test_direccion(){
//
//        Assert.assertEquals(5487,direccionDeCaba.getAltura());
//
//        Assert.assertEquals("Ruiz Huidobro",direccionDeCaba.getCalle());
//
//        Assert.assertEquals(1430,direccionDeCaba.getCodPostal());
//
//        Assert.assertEquals("C",direccionDeCaba.getDepto());
//
//        Assert.assertEquals(1,direccionDeCaba.getIdDireccion());
//
//        Assert.assertNotNull(direccionDeCaba.getCiudad());
//
//        Assert.assertEquals(8,direccionDeCaba.getPiso());
//    }
//
//    @Test
//    public void test_documento_comercial(){
//
//        Assert.assertEquals(1,documentoComercial1.getNumeroDocCom());
//
//        Assert.assertEquals("/hola",documentoComercial1.getPath());
//
//        Assert.assertEquals("Factura A",documentoComercial1.getTipo());
//    }
//
//    @Test
//    public void test_egreso(){
//
//        Assert.assertNotNull(egreso1.getEntidadJuridica());
//
//        Assert.assertNotNull(egreso1.getFechaEgreso());
//
//        Assert.assertEquals(1,egreso1.getIdEgreso());
//
//        /*Assert.assertEquals(1155,egreso1.getImporte());*/
//
//        Assert.assertNotNull(egreso1.getMedioPago());
//
//        Assert.assertNotNull(egreso1.getDocCom());
//
//        egreso1.setPresupuestoElegido(presupuesto1);
//
//        Assert.assertEquals(1234,egreso1.getNumeroInstrumentoPago());
//
//        Assert.assertNotNull(egreso1.getUsuario());
//    }
//
//    @Test
//    public void test_egreso_x_item(){
//
//        Assert.assertNotNull(egresoxitem1.getEgreso());
//
//        Assert.assertNotNull(egresoxitem1.getFechaEgreso());
//
//        Assert.assertEquals(1,egresoxitem1.getIdEgresoXItem());
//
//        /*Assert.assertEquals(2131,egresoxitem1.getImporte());*/
//
//        Assert.assertNotNull(egresoxitem1.getItem());
//    }
//
//    @Test
//    public void test_entidad_base(){
//
//        Assert.assertNotNull(entidadbase.getDescripcion());
//
//        Assert.assertNotNull(entidadbase.getEntidadJuridica());
//
//        Assert.assertTrue(entidadbase.getEstado());
//
//        Assert.assertEquals(1,entidadbase.getIdEntidadBase());
//
//        Assert.assertEquals("NO SE",entidadbase.getNombreFicticio());
//    }
//
//    @Test
//    public void test_entidad_juridica(){
//        entidadJuridca.setConfiguracionEntidadJuridica(configuracionEntidadJuridica);
//
//        Assert.assertNotNull(entidadJuridca.getConfiguracionEntidadJuridica());
//
//        Assert.assertNotNull(entidadJuridca.getCategoriaEntidad());
//
//        Assert.assertEquals(123,entidadJuridca.getCodigoInscripcion());
//
//        Assert.assertNotNull(entidadJuridca.getDireccion());
//
//        Assert.assertTrue(entidadJuridca.getEstado());
//
//        Assert.assertEquals("LeoHerbalife" ,entidadJuridca.getNombreFicticio());
//
//        Assert.assertNotNull(entidadJuridca.getProveedor());
//    }
//
//    @Test
//    public void test_item(){
//
//        Assert.assertEquals("es un item1",item1.getDescripcion());
//
//        Assert.assertTrue(item1.getEstado());
//
//        Assert.assertEquals(1, item1.getIdItem());
//
//        Assert.assertNotNull(item1.getProductoServicio());
//
//        /*Assert.assertEquals(69, item1.getValor());*/
//    }
//
//   /* @Test
//    public void test_localidad(){
//
//        Assert.assertEquals(1, ciudadDeCaba.getIdLocalidad());
//
//        Assert.assertEquals("Saavedra", ciudadDeCaba.getLocalidadDetalle());
//
//        Assert.assertNotNull(ciudadDeCaba.getPartido());
//    }*/
//
//    @Test
//    public void test_medio_de_pago(){
//
//        Assert.assertEquals("TC",mediodepago1.getDescripcion());
//
//        Assert.assertTrue(mediodepago1.getEstado());
//
//        Assert.assertEquals(1, mediodepago1.getIdMedioPago());
//
//        Assert.assertEquals("TC?",mediodepago1.getTipo());
//    }
//
//    /*@Test
//    public void test_partido(){
//
//        Assert.assertEquals(1, partidoDeCaba.getIdPartido());
//
//        Assert.assertNotNull(partidoDeCaba.getPartidoDetalle());
//
//        Assert.assertNotNull(partidoDeCaba.getProvincia());
//    }*/
//
//    @Test
//    public void test_permiso(){
//
//        Assert.assertEquals("esto es un permiso" ,permiso1.getDescripcion());
//
//        Assert.assertEquals(1, permiso1.getIdPermiso());
//    }
//
//    @Test
//    public void test_proveedor(){
//        Assert.assertEquals(12345678765L, proveedor.getCuit());
//
//        Assert.assertNotNull(proveedor.getDireccion());
//
//        Assert.assertTrue(proveedor.getEstado());
//
//        Assert.assertEquals("Diego Maradona",proveedor.getNombre());
//    }
//
//    /*@Test
//    public void test_provincia(){
//
//        Assert.assertEquals(1,caba.getIdProvincia());
//
//        Assert.assertEquals("CABA",caba.getProvinciaDetalle());
//    }*/
//
//    @Test
//    public void test_rol(){
//
//        Assert.assertEquals("esto es un rol muy importante",rol1.getDescripcion());
//
//        Assert.assertEquals(1, rol1.getIdRol());
//    }
//
//    @Test
//    public void test_rol_x_permiso(){
//
//        Assert.assertNotNull(rolxpermiso1.getRol());
//
//        Assert.assertNotNull(rolxpermiso1.getPermiso());
//
//        Assert.assertEquals(1, rolxpermiso1.getIdRolXPermiso());
//    }
//
//    @Test
//    public void test_usuario(){
//
//        Assert.assertEquals("hamburguesas",usuario1.getClave());
//
//        Assert.assertNotNull(usuario1.getEntidadJuridica());
//
//        Assert.assertTrue(usuario1.getEstado());
//
//        Assert.assertEquals(1, usuario1.getIdUsuario());
//
//        Assert.assertEquals("bobesponja@bikinibottom.com",usuario1.getMail());
//
//        Assert.assertEquals("bob esponja",usuario1.getNombreUsuario());
//
//        Assert.assertNotNull(usuario1.getRol());
//    }
//
//    @Test
//    public void test_presupuesto(){
//
//        Assert.assertNotNull(presupuesto1.getEgreso());
//
//        Assert.assertNotNull(presupuesto1.getDocCom());
//
//        Assert.assertEquals(1,presupuesto1.getIdPresupuesto());
//
//        Assert.assertEquals("detalles", presupuesto1.getDetalles());
//
//        Assert.assertEquals(1234, presupuesto1.getImporte());
//
//    }
//
//    @Test
//    public  void test_presupuestoXItem(){
//
//        Assert.assertNotNull(presupuestoXItem1.getPresupuesto());
//
//        Assert.assertEquals(1,presupuestoXItem1.getIdPresupuestoXItem());
//
//        Assert.assertNotNull(presupuestoXItem1.getItem());
//    }
//
//    @Test
//    public void test_validacionTransparencias(){
//
//        Assert.assertNotNull(resultadoValidacion.getEgreso());
//
//        Assert.assertEquals("detalles", resultadoValidacion.getDetalles());
//
//        Assert.assertNotNull(resultadoValidacion.getFechaValidacion());
//
//        Assert.assertEquals(1, resultadoValidacion.getIdValidacion());
//
//
//    }
//
//    @Test
//    public void test_ConfiguracionEntidadJuridia(){
//        Map<String,String> map = new HashMap<>();
//        map.put("cantidadPresupuestos", "3");
//        map.put("seleccionProveedor", "1");
//
//        configuracionEntidadJuridica.setConfigEntidadJuridica(map);
//        Assert.assertEquals("1", map.get("seleccionProveedor"));
//    }

}
