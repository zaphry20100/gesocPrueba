using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using VinculadorGesoc.Models;

namespace VinculadorGesoc.Ordenador.Filtros
{
    public interface IFiltro
    {
        OperacionesEntrada filtrar(OperacionesEntrada operaciones);
    }
}
