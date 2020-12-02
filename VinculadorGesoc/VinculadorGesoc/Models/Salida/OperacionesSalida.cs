using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace VinculadorGesoc.Models
{
    public class OperacionesSalida
    {
        public List<IngresoSalida> ingresos { get; set; }
        public List<EgresoSalida> egresos { get; set; }
    }
}
