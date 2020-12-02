package domain.entities.VinculacionOperaciones.Models;

import domain.entities.Models.Transacciones.Ingreso;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class IngresoEntrada {
    public int idIngreso;
    public String fechaCreacion;
    public double importe;
    public int periodoAceptabilidad;

    public IngresoEntrada(Ingreso ingresoNormal) {
        this.idIngreso = ingresoNormal.getIdIngreso();
        this.fechaCreacion = "2020-09-23T00:00:00Z";
        this.importe = ingresoNormal.getImporte();
        this.periodoAceptabilidad = 50;
    }
}
