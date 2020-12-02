using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace VinculadorGesoc.Models
{
    public class IngresoSalida
    {
        public int idIngreso { get; set; }
        public List<int> egresos { get; set; }
    }
}
