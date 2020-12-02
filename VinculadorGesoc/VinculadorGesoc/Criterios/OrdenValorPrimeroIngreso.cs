using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using VinculadorGesoc.Helpers;
using VinculadorGesoc.Models;

namespace VinculadorGesoc.Criterios
{
    public class OrdenValorPrimeroIngreso : ICriterioVinculacion
    {
        public bool asignar(OperacionesEntrada operacionesEntrada, OperacionesSalida operacionesSalida)
        {
            
            double importesAsignados = 0;

            EgresoEntrada egresoAnalizar = ListHelper.RemoveAndGetItem(operacionesEntrada.egresos, 0);
            EgresoSalida egresoSalida = new EgresoSalida();
            egresoSalida.ingresos = new List<int>();
            egresoSalida.idEgreso = egresoAnalizar.idEgreso;

            if (operacionesEntrada.egresos.Count == 0) //si es el ultimo egreso
            {
                double importeIngresosSum = operacionesEntrada.ingresos.Sum(x => x.importe);
                if (importeIngresosSum >= egresoAnalizar.importe)
                {
                    IngresoEntrada ingresoEntrada = ListHelper.RemoveAndGetItem(operacionesEntrada.ingresos, 0);
                    while (ingresoEntrada.importe <= (egresoAnalizar.importe - importesAsignados) )
                    {
                        importesAsignados += ingresoEntrada.importe;
                        egresoSalida.ingresos.Add(ingresoEntrada.idIngreso); 
                        ingresoEntrada = ListHelper.RemoveAndGetItem(operacionesEntrada.ingresos, 0);
                    }
                    operacionesSalida.egresos.Add(egresoSalida);
                }
                return false;
            }
            else
            {
                if (operacionesEntrada.ingresos.Count > 1)
                {
                    IngresoEntrada ingresoEntrada = ListHelper.RemoveAndGetItem(operacionesEntrada.ingresos, 0);
                    while (ingresoEntrada.importe <= (egresoAnalizar.importe - importesAsignados))
                    {
                        if (operacionesEntrada.ingresos.Count == 0) //si es el ultimo ingreso
                        {
                            if (ingresoEntrada.importe == egresoAnalizar.importe)
                            {
                                egresoSalida.ingresos.Add(ingresoEntrada.idIngreso);
                            }
                            return false;
                        }
                        else
                        {
                            importesAsignados += ingresoEntrada.importe;
                            egresoSalida.ingresos.Add(ingresoEntrada.idIngreso);
                        }
                        ingresoEntrada = ListHelper.RemoveAndGetItem(operacionesEntrada.ingresos, 0);
                    }
                    operacionesSalida.egresos.Add(egresoSalida);
                }
                else
                {
                    if (operacionesEntrada.ingresos.Count == 0) //si es el ultimo ingreso
                    {
                        return false;
                    }
                    else
                    {
                        IngresoEntrada ingresoEntrada = ListHelper.RemoveAndGetItem(operacionesEntrada.ingresos, 0);
                        if (ingresoEntrada.importe == egresoAnalizar.importe)
                        {
                            egresoSalida.ingresos.Add(ingresoEntrada.idIngreso);
                        }
                        return false;
                    }
                }
            }
            return true;    
        }
    }
}
