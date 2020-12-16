package domain.entities.Validadores.ValidadorTransparencia.CriteriosValidacion;

import domain.entities.Models.Entidades.ConfiguracionEntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;
import domain.entities.Models.Transacciones.Presupuesto;

import java.util.List;

public class CriterioCompraDeUnPresupuesto extends CriteriosValidacion {
    @Override
    public boolean validar(Egreso egreso, ConfiguracionEntidadJuridica config) throws Exception {
        super.checkeoExcepciones(egreso, config);
        List<Presupuesto> presupuestos = egreso.getListaPresupuestos();
        if(egreso.getPresupuestoElegido() == null){
            throw new Exception("no te puedo reiterar lo idiota que sos si ya me venis poniendo mil cosas mal no lo puedo creer. No te das cuenta que no hay un presupuesto elegido en egreso? dios u disgust me");
        }
        boolean loContiene = presupuestos.stream().anyMatch(x -> (x.getIdPresupuesto() == egreso.getPresupuestoElegido().getIdPresupuesto()));
        return loContiene;

    }
}
