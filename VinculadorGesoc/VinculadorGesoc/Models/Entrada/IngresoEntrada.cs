using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace VinculadorGesoc.Models
{
    public class IngresoEntrada
    {
        public int idIngreso {get;set;}
        public DateTime fechaCreacion { get; set; }
        public double importe { get; set; }
        public int periodoAceptabilidad { get; set; }

    }
}
