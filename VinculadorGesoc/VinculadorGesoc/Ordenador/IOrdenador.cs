using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using VinculadorGesoc.Models;

namespace VinculadorGesoc.Ordenador
{
    public interface IOrdenador
    {
        public OperacionesEntrada ordenar(OperacionesEntrada operaciones);
        public void setOrdenamiento(bool ordenamientoMayor);

    }
}
