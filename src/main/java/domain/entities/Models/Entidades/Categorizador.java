package domain.entities.Models.Entidades;

import domain.entities.Models.Entidades.CategoriasEntidad.CategoriaEntidad;
import domain.repositories.factories.FactoryRepositorio;

import java.util.List;

public class Categorizador {

    private static CategoriaEntidad obtenerCategoria(List<CategoriaEntidad> categoriasEntidad, String nombreCategoria){
        CategoriaEntidad categoriaEntidad = categoriasEntidad.stream().filter(x -> nombreCategoria.equals(x.getDescripcion())).findFirst().orElse(null);
        return categoriaEntidad;
    }

    public static void categorizar(EntidadJuridica entidadJuridica, int empleados, String actividad, double promedioActividad) throws Exception {

        List<CategoriaEntidad> categoriasEntidad = FactoryRepositorio.get(CategoriaEntidad.class).buscarTodos();
        entidadJuridica.setActividad(actividad);
        entidadJuridica.setCantidadEmpleados(empleados);
        entidadJuridica.setPromedioVentas(promedioActividad);

        ejecutarCategorizacion(empleados, actividad, promedioActividad, entidadJuridica, categoriasEntidad);
        return;
    }

    public static void categorizar(EntidadJuridica entidadJuridica) throws Exception {
        List<CategoriaEntidad> categoriasEntidad = FactoryRepositorio.get(CategoriaEntidad.class).buscarTodos();

        ejecutarCategorizacion(entidadJuridica.getCantidadEmpleados(), entidadJuridica.getActividad(), entidadJuridica.getPromedioVentas(),entidadJuridica, categoriasEntidad);
        return;
    }


    private static String analisisPromedioAnual(double valorMicro, double valorPeque, double valorMediana1, double valorMediana2, double promedioActividad){
        if(promedioActividad > valorMicro){
            if(promedioActividad > valorPeque){
                if(promedioActividad > valorMediana1){
                    if(promedioActividad > valorMediana2){
                        return "No tiene";
                    }else{
                        return "Mediana - Tipo 2";
                    }
                }else{
                    return "Mediana - Tipo 1";
                }
            }else{
                return "Pequeña";
            }
        }else{
            return "Micro";
        }
    }



    private static String ejecutarCategorizacion(int empleados, String actividad, double promedioActividad, EntidadJuridica entidadJuridica, List<CategoriaEntidad> categoriasEntidad) throws Exception {
        String resultado = "";
        if(actividad.equals("Construcción")){
            if(empleados <= 12){
                resultado = analisisPromedioAnual(19450000, 115370000, 643710000, 965460000, promedioActividad);

            }else if(empleados <= 45){
                resultado = analisisPromedioAnual(0,115370000,643710000,965460000, promedioActividad);
            }else if(empleados <= 200){
                resultado = analisisPromedioAnual(0,0,643710000,965460000,promedioActividad);
            }else if(empleados <= 590){
                resultado = analisisPromedioAnual(0,0,0,965460000,promedioActividad);
            }else{
                resultado = "No tiene";
            }
        }
        if(actividad.equals("Servicios")){
            if(empleados <= 7){
                resultado = analisisPromedioAnual(9900000, 59710000, 494200000, 705790000, promedioActividad);
            }else if(empleados <= 30){
                resultado = analisisPromedioAnual(0,59710000,494200000,705790000, promedioActividad);
            }else if(empleados <= 165){
                resultado = analisisPromedioAnual(0,0,494200000,705790000,promedioActividad);
            }else if(empleados <= 535){
                resultado = analisisPromedioAnual(0,0,0,705790000,promedioActividad);
            }else{
                resultado = "No tiene";
            }
        }
        if(actividad.equals("Comercio")){
            if(empleados <= 7){
                resultado = analisisPromedioAnual(36320000, 247200000, 1821760000, 2602540000L, promedioActividad);
            }else if(empleados <= 35){
                resultado = analisisPromedioAnual(0,247200000,1821760000,2602540000L, promedioActividad);
            }else if(empleados <= 125){
                resultado = analisisPromedioAnual(0,0,1821760000,2602540000L,promedioActividad);
            }else if(empleados <= 345){
                resultado = analisisPromedioAnual(0,0,0,2602540000L,promedioActividad);
            }else{
                resultado = "No tiene";
            }
        }
        if(actividad.equals("Industria y Minería")){
            if(empleados <= 15){
                resultado = analisisPromedioAnual(33920000, 243290000, 1651750000, 2540380000L, promedioActividad);
            }else if(empleados <= 60){
                resultado = analisisPromedioAnual(0,243290000,1651750000,2540380000L, promedioActividad);
            }else if(empleados <= 235){
                resultado = analisisPromedioAnual(0,0,1651750000,2540380000L,promedioActividad);
            }else if(empleados <= 655){
                resultado = analisisPromedioAnual(0,0,0,2540380000L,promedioActividad);
            }else{
                resultado = "No tiene";
            }
        }
        if(actividad.equals("Agropecuario")){
            if(empleados <= 5){
                resultado = analisisPromedioAnual(17260000, 71960000, 426720000, 678810000, promedioActividad);
            }else if(empleados <= 10){
                resultado = analisisPromedioAnual(0,71960000,426720000,678810000, promedioActividad);
            }else if(empleados <= 50){
                resultado = analisisPromedioAnual(0,0,426720000,678810000,promedioActividad);
            }else if(empleados <= 215){
                resultado = analisisPromedioAnual(0,0,0,678810000,promedioActividad);
            }else{
                resultado = "No tiene";
            }
        }

        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, resultado));
        return resultado;

    }


//    private static String ejecutarCategorizacion(int empleados, String actividad, double promedioActividad, EntidadJuridica entidadJuridica, List<CategoriaEntidad> categoriasEntidad) throws Exception {
//        switch (actividad) {
//            case "Construcción":
//                if (empleados >= 12 && empleados < 45) {
//                    if (promedioActividad < 15230000L) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "No tiene"));
//                        return "No tiene";
//                    } else if (promedioActividad >= 90310000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
//                        return "Pequeña";
//                    }
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Micro"));
//                    return "Micro";
//                } else if (empleados < 200) {
//                    if (promedioActividad >= 503880000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
//                        return "Mediana - Tipo 1";
//                    }
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
//                    return "Pequeña";
//                } else if (empleados < 590) {
//                    if (promedioActividad >= 755740000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
//
//                        return "Mediana - Tipo 2";
//                    }
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
//                    return "Mediana - Tipo 1";
//                } else {
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
//                    return "Mediana - Tipo 2";
//                }
//
//            case "Servicios":
//                if (empleados >= 7 && empleados < 30) {
//                    if (promedioActividad < 8500000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "No tiene"));
//                        return "No tiene";
//                    } else if (promedioActividad >= 50950000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
//                        return "Pequeña";
//
//                    }
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Micro"));
//                    return "Micro";
//                } else if (empleados < 165) {
//                    if (promedioActividad >= 425170000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
//                        return "Mediana - Tipo 1";
//                    }
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
//                    return "Pequeña";
//                } else if (empleados < 535) {
//                    if (promedioActividad >= 755740000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
//                        return "Mediana - Tipo 2";
//                    }
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
//                    return "Mediana - Tipo 1";
//                } else {
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
//                    return "Mediana - Tipo 2";
//                }
//
//            case "Comercio":
//                if (empleados >= 7 && empleados < 35) {
//                    if (promedioActividad < 29740000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "No tiene"));
//                        return "No tiene";
//                    } else if (promedioActividad >= 178860000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
//                        return "Pequeña";
//                    }
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Micro"));
//                    return "Micro";
//                } else if (empleados < 125) {
//                    if (promedioActividad >= 1502750000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
//                        return "Mediana - Tipo 1";
//                    }
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
//                    return "Pequeña";
//                } else if (empleados < 345) {
//                    if (promedioActividad >= 2146810000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
//                        return "Mediana - Tipo 2";
//                    }
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
//                    return "Mediana - Tipo 1";
//                } else {
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
//                    return "Mediana - Tipo 2";
//                }
//            case "Industria y Minería":
//                if (empleados >= 15 && empleados < 60) {
//                    if (promedioActividad < 26540000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "No tiene"));
//                        return "No tiene";
//                    } else if (promedioActividad >= 190410000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
//                        return "Pequeña";
//                    }
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Micro"));
//                    return "Micro";
//                } else if (empleados < 235) {
//                    if (promedioActividad >= 1190330000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
//                        return "Mediana - Tipo 1";
//                    }
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
//                    return "Pequeña";
//                } else if (empleados < 655) {
//                    if (promedioActividad >= 1739590000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
//                        return "Mediana - Tipo 2";
//                    }
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
//                    return "Mediana - Tipo 1";
//                } else {
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
//                    return "Mediana - Tipo 2";
//                }
//
//            case "Agropecuario":
//                if (empleados >= 5 && empleados < 10) {
//                    if (promedioActividad < 12890000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "No tiene"));
//                        return "No tiene";
//                    } else if (promedioActividad >= 48480000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
//                        return "Pequeña";
//                    }
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Micro"));
//                    return "Micro";
//                } else if (empleados < 50) {
//                    if (promedioActividad >= 345430000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
//                        return "Mediana - Tipo 1";
//                    }
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
//                    return "Pequeña";
//                } else if (empleados < 215) {
//                    if (promedioActividad >= 547890000) {
//                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
//                        return "Mediana - Tipo 2";
//                    }
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
//                    return "Mediana - Tipo 1";
//                } else {
//                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
//                    return "Mediana - Tipo 2";
//                }
//            default:
//                entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "No tiene"));
//                return "No tiene";
//
//
//        }
//    }


}
