using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace VinculadorGesoc.Models
{
    public class EgresoSalida
    {
        public int idEgreso { get; set; }
        public List<int> ingresos { get; set; }
    }
}
