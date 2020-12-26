package domain.entities.Validadores.ValidadorTransparencia.CriteriosValidacion;


import domain.entities.Models.Entidades.ConfiguracionEntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;

public abstract class CriteriosValidacion {
    public boolean validar(Egreso egreso, ConfiguracionEntidadJuridica config) throws Exception{ return true;}

    void checkeoExcepciones(Egreso egreso, ConfiguracionEntidadJuridica config) throws Exception {
        if(egreso.getListaPresupuestos().isEmpty()){
            throw new Exception("El egreso " + egreso.getIdEgreso() + " no tiene presupuestos asociados.");
        }
        if (config.getConfigEntidadJuridica().isEmpty()){
            throw new Exception("No pudo ejecutarse la validación porque no hay configuración de validación de transparencia.");
        }
    }
}
