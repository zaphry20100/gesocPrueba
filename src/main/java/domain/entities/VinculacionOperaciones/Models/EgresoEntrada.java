package domain.entities.VinculacionOperaciones.Models;

import domain.entities.Models.Transacciones.Egreso;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class EgresoEntrada {
    public int idEgreso;
    public String fechaCreacion;
    public double importe;

    public EgresoEntrada(Egreso egresoNormal) {
        this.idEgreso = egresoNormal.getIdEgreso();
        this.fechaCreacion ="2020-09-23T00:00:00Z";
        this.importe = egresoNormal.getImporte();
    }
}
