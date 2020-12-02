package domain.entities.Validadores.ValidadorTransparencia.CriteriosValidacion;


import domain.entities.Models.Entidades.ConfiguracionEntidadJuridica;
import domain.entities.Models.Transacciones.Egreso;

public abstract class CriteriosValidacion {
    public boolean validar(Egreso egreso, ConfiguracionEntidadJuridica config) throws Exception{ return true;}

    void checkeoExcepciones(Egreso egreso, ConfiguracionEntidadJuridica config) throws Exception {
        if(egreso.getListaPresupuestos().isEmpty()){
            throw new Exception("ami que onda sos idiota no hay un presupuesto chabon quien pensas que soy eh");
        }
        if (config.getConfigEntidadJuridica().isEmpty()){
            throw new Exception("o sea no aprendiste nada chabon no viste que estas cosas no se hacen, el config esta vacio bolas. Quien pensas que soy eh pero ahora en el config");
        }
    }
}
