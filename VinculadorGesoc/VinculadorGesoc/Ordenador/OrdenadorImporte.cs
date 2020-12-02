using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using VinculadorGesoc.Models;

namespace VinculadorGesoc.Ordenador
{
    public class OrdenadorImporte : IOrdenador
    {
        private bool _ordenamientoMayor;


        public void setOrdenamiento(bool ordenamientoMayor)
        {
            _ordenamientoMayor = ordenamientoMayor;
        }

        public OperacionesEntrada ordenar(OperacionesEntrada operaciones)
        {
            if (_ordenamientoMayor)
            {
                operaciones.egresos = operaciones.egresos.OrderByDescending(e => e.importe).ToList();
                operaciones.ingresos = operaciones.ingresos.OrderByDescending(i => i.importe).ToList();
                return operaciones;
            }
            else
            {
                operaciones.egresos = operaciones.egresos.OrderBy(e => e.importe).ToList();
                operaciones.ingresos = operaciones.ingresos.OrderBy(i => i.importe).ToList();
                return operaciones;
            }
        }
    }
}
