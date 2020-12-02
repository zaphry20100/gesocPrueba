using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using VinculadorGesoc.Models;

namespace VinculadorGesoc.Ordenador.Filtros
{
    public class FiltroPeriodoAceptabilidad : IFiltro
    {
        public OperacionesEntrada filtrar(OperacionesEntrada operaciones)
        {
            operaciones.ingresos = operaciones.ingresos.FindAll(x => {
          
                DateTime date = x.fechaCreacion.AddDays(x.periodoAceptabilidad);

                return DateTime.Now < date && DateTime.Now > x.fechaCreacion;
            
            });
            return operaciones;
        }
    }
}
