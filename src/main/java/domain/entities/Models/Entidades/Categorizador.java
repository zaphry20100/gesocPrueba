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

    private static String ejecutarCategorizacion(int empleados, String actividad, double promedioActividad, EntidadJuridica entidadJuridica, List<CategoriaEntidad> categoriasEntidad) throws Exception {
        switch (actividad) {
            case "Construcción":
                if (empleados >= 12 && empleados < 45) {
                    if (promedioActividad < 15230000L) {
                        return "No tiene";
                    } else if (promedioActividad >= 90310000) {
                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
                        return "Pequeña";
                    }
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Micro"));
                    return "Micro";
                } else if (empleados < 200) {
                    if (promedioActividad >= 503880000) {
                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
                        return "Mediana - Tipo 1";
                    }
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
                    return "Pequeña";
                } else if (empleados < 590) {
                    if (promedioActividad >= 755740000) {
                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));

                        return "Mediana - Tipo 2";
                    }
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
                    return "Mediana - Tipo 1";
                } else {
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
                    return "Mediana - Tipo 2";
                }

            case "Servicios":
                if (empleados >= 7 && empleados < 30) {
                    if (promedioActividad < 8500000) {
                        return "No tiene";
                    } else if (promedioActividad >= 50950000) {
                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
                        return "Pequeña";

                    }
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Micro"));
                    return "Micro";
                } else if (empleados < 165) {
                    if (promedioActividad >= 425170000) {
                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
                        return "Mediana - Tipo 1";
                    }
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
                    return "Pequeña";
                } else if (empleados < 535) {
                    if (promedioActividad >= 755740000) {
                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
                        return "Mediana - Tipo 2";
                    }
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
                    return "Mediana - Tipo 1";
                } else {
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
                    return "Mediana - Tipo 2";
                }

            case "Comercio":
                if (empleados >= 7 && empleados < 35) {
                    if (promedioActividad < 29740000) {
                        return "No tiene";
                    } else if (promedioActividad >= 178860000) {
                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
                        return "Pequeña";
                    }
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Micro"));
                    return "Micro";
                } else if (empleados < 125) {
                    if (promedioActividad >= 1502750000) {
                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
                        return "Mediana - Tipo 1";
                    }
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
                    return "Pequeña";
                } else if (empleados < 345) {
                    if (promedioActividad >= 2146810000) {
                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
                        return "Mediana - Tipo 2";
                    }
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
                    return "Mediana - Tipo 1";
                } else {
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
                    return "Mediana - Tipo 2";
                }
            case "Industria y Minería":
                if (empleados >= 15 && empleados < 60) {
                    if (promedioActividad < 26540000) {
                        return "No tiene";
                    } else if (promedioActividad >= 190410000) {
                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
                        return "Pequeña";
                    }
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Micro"));
                    return "Micro";
                } else if (empleados < 235) {
                    if (promedioActividad >= 1190330000) {
                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
                        return "Mediana - Tipo 1";
                    }
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
                    return "Pequeña";
                } else if (empleados < 655) {
                    if (promedioActividad >= 1739590000) {
                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
                        return "Mediana - Tipo 2";
                    }
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
                    return "Mediana - Tipo 1";
                } else {
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
                    return "Mediana - Tipo 2";
                }

            case "Agropecuario":
                if (empleados >= 5 && empleados < 10) {
                    if (promedioActividad < 12890000) {
                        return "No tiene";
                    } else if (promedioActividad >= 48480000) {
                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
                        return "Pequeña";
                    }
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Micro"));
                    return "Micro";
                } else if (empleados < 50) {
                    if (promedioActividad >= 345430000) {
                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
                        return "Mediana - Tipo 1";
                    }
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Pequeña"));
                    return "Pequeña";
                } else if (empleados < 215) {
                    if (promedioActividad >= 547890000) {
                        entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
                        return "Mediana - Tipo 2";
                    }
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 1"));
                    return "Mediana - Tipo 1";
                } else {
                    entidadJuridica.setCategoriaentidad(obtenerCategoria(categoriasEntidad, "Mediana - Tipo 2"));
                    return "Mediana - Tipo 2";
                }
            default:
                return "No tiene";


        }
    }


}
