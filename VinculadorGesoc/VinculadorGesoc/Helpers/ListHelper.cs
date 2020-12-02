using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace VinculadorGesoc.Helpers
{
    public static class ListHelper
    {
        public static T RemoveAndGetItem<T>(this IList<T> list, int iIndexToRemove)
        {
            var item = list[iIndexToRemove];
            list.RemoveAt(iIndexToRemove);
            return item;
        }
    }
}
