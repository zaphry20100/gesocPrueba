package domain.entities.VinculacionOperaciones.Models;

import java.util.ArrayList;
import java.util.List;

public class IngresoSalida {
    public int idIngreso;
    public List<Integer> egresos;

    public IngresoSalida() {
        this.egresos = new ArrayList<>();
    }
}
