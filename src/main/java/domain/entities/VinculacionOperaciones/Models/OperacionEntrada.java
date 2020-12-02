package domain.entities.VinculacionOperaciones.Models;

import domain.entities.Models.Transacciones.Egreso;
import domain.entities.Models.Transacciones.Ingreso;

import java.util.List;

public class OperacionEntrada {

    public List<CriterioVinculacion> criterios;
    public List<IngresoEntrada> ingresos;
    public List<EgresoEntrada> egresos;

    public OperacionEntrada(List<CriterioVinculacion> criterios, List<IngresoEntrada> ingresos, List<EgresoEntrada> egresos) {
        this.criterios = criterios;
        this.ingresos = ingresos;
        this.egresos = egresos;
    }
}
