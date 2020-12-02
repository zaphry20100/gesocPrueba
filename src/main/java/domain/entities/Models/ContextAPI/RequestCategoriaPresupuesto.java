package domain.entities.Models.ContextAPI;

import domain.entities.Models.Categorias.Categorias.CategoriaPresupuesto;
import domain.entities.Models.Transacciones.Egreso;
import domain.entities.Models.Transacciones.Presupuesto;

import javax.naming.ldap.PagedResultsControl;

public class RequestCategoriaPresupuesto {
    public Presupuesto presupuesto;
    public CategoriaPresupuesto categoria;
}
