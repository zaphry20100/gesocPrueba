using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace VinculadorGesoc.Models
{
    public class CriterioOrdenamiento
    {
        public string criterioVinculacion { get; set; }
        public int cantAsignacionesEficaces { get; set; }
        public bool ordenamientoMayor { get; set; }
    }
}
