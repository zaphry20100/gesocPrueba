package domain.entities.Models.ContextAPI;

import domain.entities.Models.Categorias.Categorias.CategoriaPresupuesto;
import domain.entities.Models.Transacciones.Egreso;
import domain.entities.Models.Transacciones.Ingreso;

public class RequestCategoriaIngreso {
    public Ingreso ingreso;
    public CategoriaPresupuesto categoria;
}
