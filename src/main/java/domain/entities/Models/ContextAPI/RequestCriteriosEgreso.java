package domain.entities.Models.ContextAPI;

public class RequestCriteriosEgreso {
    public int idCriterioPresupuesto;
    public String nombreCriterioPresupuesto;

    public RequestCriteriosEgreso(int idCriterioPresupuesto, String nombreCriterioPresupuesto) {
        this.idCriterioPresupuesto = idCriterioPresupuesto;
        this.nombreCriterioPresupuesto = nombreCriterioPresupuesto;
    }
}
