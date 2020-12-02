using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace VinculadorGesoc.Models
{
    public class OperacionesEntrada
    {
        public List<CriterioOrdenamiento> criterios { get; set; }
        public List<IngresoEntrada> ingresos { get; set; }
        public List<EgresoEntrada> egresos { get; set; }
    }
}
