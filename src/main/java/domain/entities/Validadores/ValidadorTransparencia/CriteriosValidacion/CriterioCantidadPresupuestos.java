package domain.entities.Validadores.ValidadorTransparencia.CriteriosValidacion;

import domain.entities.Models.Entidades.ConfiguracionEntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;

public class CriterioCantidadPresupuestos extends CriteriosValidacion{

    @Override
    public boolean validar(Egreso egreso, ConfiguracionEntidadJuridica config) throws Exception {
        System.out.println("CriterioCantidadPresupuestos");
        super.checkeoExcepciones(egreso, config);
        egreso.quitarRepetidos();
        int cantPresupuestoRequeridos = Integer.parseInt(config.getConfigEntidadJuridica().get("CriterioCantidadPresupuestos"));
        int cantPresupuestosEgreso = egreso.getListaPresupuestos().size();
        boolean estaOk = (cantPresupuestoRequeridos == cantPresupuestosEgreso);

        System.out.println("Estado: " + estaOk);
        return estaOk ;
    }
}
