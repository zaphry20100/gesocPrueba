package domain.entities.Models.Categorias.Criterios;

import domain.entities.Models.Categorias.Categorias.CategoriaPresupuesto;

import java.util.List;

public class CriterioPresupuestoFactory {

    public static CriterioPresupuesto crearCriterio(String descripcion, CriterioPresupuesto criterioPadre, List<CategoriaPresupuesto> categoriaPresupuestos){
        CriterioPresupuesto critPres = new  CriterioPresupuesto();

        return critPres;
    }

    public static CriterioPresupuesto crearCriterio(String descripcion, List<CategoriaPresupuesto> categoriaPresupuestos){
        CriterioPresupuesto critPres = new  CriterioPresupuesto();

        return critPres;
    }
}
