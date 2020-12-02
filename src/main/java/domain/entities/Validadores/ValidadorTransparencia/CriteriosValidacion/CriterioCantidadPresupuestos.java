package domain.entities.Validadores.ValidadorTransparencia.CriteriosValidacion;

import domain.entities.Models.Entidades.ConfiguracionEntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;

public class CriterioCantidadPresupuestos extends CriteriosValidacion{

    @Override
    public boolean validar(Egreso egreso, ConfiguracionEntidadJuridica config) throws Exception {
        super.checkeoExcepciones(egreso, config);
        egreso.quitarRepetidos();
        return egreso.getListaPresupuestos().size() == Integer.parseInt(config.getConfigEntidadJuridica().get("CriterioCantidadPresupuestos"));
    }
}
