package domain.entities.VinculacionOperaciones.Models;

import domain.entities.Models.Transacciones.Egreso;

import java.util.ArrayList;
import java.util.List;

public class EgresoSalida {
    public int idEgreso;
    public List<Integer> ingresos;

    public EgresoSalida() {
        this.ingresos = new ArrayList<>();
    }

    public Egreso getEgreso(){
        Egreso egreso = new Egreso();
        egreso.setIdEgreso(this.idEgreso);

        return egreso;
    }
}
