package domain.entities.VinculacionOperaciones.Models;

import java.util.ArrayList;
import java.util.List;

public class OperacionSalida {
    private List<IngresoSalida> ingresos;
    private List<EgresoSalida> egresos;

    public OperacionSalida(){
        ingresos = new ArrayList<>();
        egresos = new ArrayList<>();
    }

    public List<IngresoSalida> getIngresos() {
        return ingresos;
    }

    public List<EgresoSalida> getEgresos() {
        return egresos;
    }
}
