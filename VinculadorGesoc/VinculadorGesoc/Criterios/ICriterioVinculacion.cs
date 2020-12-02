using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using VinculadorGesoc.Models;

namespace VinculadorGesoc.Criterios
{
    public interface ICriterioVinculacion
    {
        bool asignar(OperacionesEntrada operacionesEntrada, OperacionesSalida operacionesSalida);
    }
}
