using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using VinculadorGesoc.Criterios;
using VinculadorGesoc.Models;
using VinculadorGesoc.Ordenador;

namespace VinculadorGesoc.Vinculador
{


    public class VinculadorAPI
    {

        private IOrdenador _ordenador;
        private ICriterioVinculacion _criterioVinculacion;

        public OperacionesSalida vincular(OperacionesEntrada operacionesEntrada)
        {
            bool ejecutar = true;
            OperacionesSalida operacionesSalida = new OperacionesSalida();
            operacionesSalida.egresos = new List<EgresoSalida>();
            operacionesSalida.ingresos = new List<IngresoSalida>();
            List<CriterioOrdenamiento> criterioOrdenamientos = operacionesEntrada.criterios;
            int asignacionesEficaces = 0;
            int asignacionesParciales = 0;

            if (operacionesEntrada.ingresos.Count >0 && operacionesEntrada.egresos.Count > 0)
            {
                while (ejecutar)
                {
                    foreach (var criterio in criterioOrdenamientos)
                    {
                        if (!ejecutar)
                        {
                            break;
                        }
                        asignacionesEficaces = criterio.cantAsignacionesEficaces;
                        setStrategy(criterio);
                        _ordenador.ordenar(operacionesEntrada);
                        if (operacionesEntrada.ingresos.Count > 0 && operacionesEntrada.egresos.Count > 0)
                        {
                            while (asignacionesParciales < asignacionesEficaces && ejecutar)
                            {
                                ejecutar = _criterioVinculacion.asignar(operacionesEntrada, operacionesSalida);
                        
                                asignacionesParciales++;
                            }
                            asignacionesParciales = 0;

                        }

                    }
                }
            }
            return operacionesSalida;
        }

        private void setStrategy(CriterioOrdenamiento criterio)
        {
            setOrdenador(criterio.criterioVinculacion, criterio.ordenamientoMayor);
            setCriterioVinculador(criterio.criterioVinculacion);
        }

        private void setOrdenador(string nombreCriterio, bool ordenamientoMayor)
        {
            string ordenadorConfig = new ConfigurationBuilder().AddJsonFile("appsettings.json").Build().GetValue<string>($"config:{nombreCriterio}:OrdenadorAPI");

            _ordenador = (IOrdenador)Activator.CreateInstance(obtenerTipo(ordenadorConfig));
            _ordenador.setOrdenamiento(ordenamientoMayor);
        }

        private void setCriterioVinculador(string nombreCriterio)
        {
            string criterioConfig = new ConfigurationBuilder().AddJsonFile("appsettings.json").Build().GetValue<string>($"config:{nombreCriterio}:CriterioVinculacionAPI");
    
            _criterioVinculacion = (ICriterioVinculacion)Activator.CreateInstance(obtenerTipo(criterioConfig));
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

        public List<NombreCriterio> ObtenerCriteriosVinculacion()
        {
            List<NombreCriterio> criterios = new ConfigurationBuilder().AddJsonFile("appsettings.json").Build().GetSection("nombresCriterios").Get<List<NombreCriterio>>();
            return criterios;
        }

    }
}

