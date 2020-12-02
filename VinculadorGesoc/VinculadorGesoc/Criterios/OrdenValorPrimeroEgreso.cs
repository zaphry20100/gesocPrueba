using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using VinculadorGesoc.Helpers;
using VinculadorGesoc.Models;

namespace VinculadorGesoc.Criterios
{
    public class OrdenValorPrimeroEgreso : ICriterioVinculacion
    {

        public bool asignar(OperacionesEntrada operacionesEntrada, OperacionesSalida operacionesSalida)
        {

            double importesAsignados = 0;

            IngresoEntrada ingresoAnalizar = ListHelper.RemoveAndGetItem(operacionesEntrada.ingresos, 0); //puede romper
            IngresoSalida ingresoSalida = new IngresoSalida();
            ingresoSalida.egresos = new List<int>();
            ingresoSalida.idIngreso = ingresoAnalizar.idIngreso;

            if (operacionesEntrada.ingresos.Count == 0) //si es el ultimo ingreso
            {
                double importeEgresosSum = operacionesEntrada.egresos.Sum(x => x.importe);
                if (importeEgresosSum >= ingresoAnalizar.importe)
                {
                    EgresoEntrada egresoEntrada = ListHelper.RemoveAndGetItem(operacionesEntrada.egresos, 0);
                    while (egresoEntrada.importe <= (ingresoAnalizar.importe - importesAsignados))
                    {
                        importesAsignados += egresoEntrada.importe;
                        ingresoSalida.egresos.Add(egresoEntrada.idEgreso);
                        egresoEntrada = ListHelper.RemoveAndGetItem(operacionesEntrada.egresos, 0);
                    }
                    operacionesSalida.ingresos.Add(ingresoSalida);
                }
                return false;
            }
            else
            {
                if (operacionesEntrada.egresos.Count >1)
                {
                    EgresoEntrada egresoEntrada = ListHelper.RemoveAndGetItem(operacionesEntrada.egresos, 0);
                    while (egresoEntrada.importe <= (ingresoAnalizar.importe - importesAsignados))
                    {
                        if (operacionesEntrada.egresos.Count == 0) //si es el ultimo ingreso
                        {
                            if (egresoEntrada.importe == ingresoAnalizar.importe)
                            {
                                ingresoSalida.egresos.Add(egresoEntrada.idEgreso);
                            }
                            return false;
                        }
                        else
                        {
                            importesAsignados += egresoEntrada.importe;
                            ingresoSalida.egresos.Add(egresoEntrada.idEgreso);
                        }
                        egresoEntrada = ListHelper.RemoveAndGetItem(operacionesEntrada.egresos, 0);
                    }
                    operacionesSalida.ingresos.Add(ingresoSalida);
                }
                else
                {
                    if (operacionesEntrada.egresos.Count == 0) //si es el ultimo ingreso
                    {
                        return false;
                    }
                    else
                    {
                        EgresoEntrada egresoEntrada = ListHelper.RemoveAndGetItem(operacionesEntrada.egresos, 0);
                        if (egresoEntrada.importe == ingresoAnalizar.importe)
                        {
                            ingresoSalida.egresos.Add(egresoEntrada.idEgreso);
                        }
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
