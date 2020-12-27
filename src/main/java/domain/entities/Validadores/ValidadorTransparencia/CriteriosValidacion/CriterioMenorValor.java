package domain.entities.Validadores.ValidadorTransparencia.CriteriosValidacion;

import domain.entities.Models.Entidades.ConfiguracionEntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;
import domain.entities.Models.Transacciones.Presupuesto;

import java.util.List;

public class CriterioMenorValor extends CriteriosValidacion{


    @Override
    public boolean validar(Egreso egreso, ConfiguracionEntidadJuridica config) throws Exception {
        System.out.println("CriterioMenorValor");
        super.checkeoExcepciones(egreso,config);
        List<Presupuesto> presupuestos = egreso.getListaPresupuestos();
        if(egreso.getImporte() <= 0){
            throw new Exception("El importe del presupuesto es invalido (negativo o nulo)");
        }

        for (Presupuesto presupuesto : presupuestos) {
            if (presupuesto.getImporte() <= 0) {
                throw new Exception("El presupuesto " + presupuesto.getIdPresupuesto() + " tiene importe del presupuesto es invalido (negativo o nulo)");
            }
            if(egreso.getPresupuestoElegido().getImporte() > presupuesto.getImporte()){
                throw new Exception("El presupuesto elegido no es el de menor importe");
            }
        }
        System.out.println("Estado: true ");
        return true;
    }
}
