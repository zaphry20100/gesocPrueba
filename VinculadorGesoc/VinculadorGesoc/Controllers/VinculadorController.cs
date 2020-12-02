using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using VinculadorGesoc.Models;
using VinculadorGesoc.Vinculador;

namespace VinculadorGesoc.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class VinculadorController : ControllerBase
    {
        private VinculadorAPI _vinculador;
        public VinculadorController()
        {
            _vinculador = new VinculadorAPI();
        }


        [HttpPost("VincularOperaciones")]
        public IActionResult ObtenerIngresosEgresos(OperacionesEntrada operacionesEntrada)
        {
            var res = _vinculador.vincular(operacionesEntrada);
            Console.WriteLine(JsonConvert.SerializeObject(res, Formatting.Indented));
            return Ok(res);
        }

        [HttpGet("criteriosVinculacion")]
        public IActionResult ObtenerCriteriosVinculacion()
        {
            var res = _vinculador.ObtenerCriteriosVinculacion();
            Console.WriteLine(res);
            return Ok(res);
        }



    }
}
