using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using VinculadorGesoc.Models;
using VinculadorGesoc.Ordenador.Filtros;

namespace VinculadorGesoc.Ordenador
{
    public class OrdenadorFecha : IOrdenador
    {
        private bool _ordenamientoMayor;
        private List<IFiltro> filtros = new List<IFiltro>();

        
        public void setOrdenamiento(bool ordenamientoMayor)
        {
            _ordenamientoMayor = ordenamientoMayor;
            IList<string> filtrosString = new ConfigurationBuilder().AddJsonFile("appsettings.json").Build().GetSection("filtros:filtrosLista").Get<List<string>>();
            
            foreach (var item in filtrosString)
            {
                filtros.Add((IFiltro)Activator.CreateInstance(obtenerTipo(item)));
            }

            
        }

        
        private Type obtenerTipo(string tipo)
        {
            Type t = Type.GetType(tipo);
            if (t == null)
            {
                throw new Exception("Type " + tipo + " not found.");
            }
            return t;
        }

        public OperacionesEntrada ordenar(OperacionesEntrada operaciones)
        {
            filtros.ForEach(x => operaciones = x.filtrar(operaciones));
            if (_ordenamientoMayor)
            {
                operaciones.egresos = operaciones.egresos.OrderByDescending(e => e.fechaCreacion).ToList();
                operaciones.ingresos = operaciones.ingresos.OrderByDescending(i => i.fechaCreacion).ToList();
                return operaciones;
            }
            else
            {
                operaciones.egresos = operaciones.egresos.OrderBy(e => e.fechaCreacion).ToList();
                operaciones.ingresos = operaciones.ingresos.OrderBy(i => i.fechaCreacion).ToList();
                return operaciones;
            }
        }
    }
}
