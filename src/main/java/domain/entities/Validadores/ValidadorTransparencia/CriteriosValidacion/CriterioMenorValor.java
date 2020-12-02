package domain.entities.Validadores.ValidadorTransparencia.CriteriosValidacion;

import domain.entities.Models.Entidades.ConfiguracionEntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;
import domain.entities.Models.Transacciones.Presupuesto;

import java.util.List;

public class CriterioMenorValor extends CriteriosValidacion{


    @Override
    public boolean validar(Egreso egreso, ConfiguracionEntidadJuridica config) throws Exception {
       super.checkeoExcepciones(egreso,config);
        List<Presupuesto> presupuestos = egreso.getListaPresupuestos();
        if(egreso.getImporte() <= 0){
            throw new Exception("nada que ver aca...... no fue ninguna coima ni nada de eso");
        }

        for (Presupuesto presupuesto : presupuestos) {
            if (presupuesto.getImporte() <= 0) {
                throw new Exception("..... de nuevo, nada que ver por aca en presupuesto de ID " + presupuesto.getIdPresupuesto());
            }
            if(egreso.getPresupuestoElegido().getImporte() > presupuesto.getImporte()){
                throw new Exception("papu, hermano, amigo del alma, no te das cuenta que tenias que elegir este presupuesto de ID " + presupuesto.getIdPresupuesto() + " con el importe de $" + presupuesto.getImporte()+ "??????????!!!!!!!!");
            }
        }

        return true;
    }
}
