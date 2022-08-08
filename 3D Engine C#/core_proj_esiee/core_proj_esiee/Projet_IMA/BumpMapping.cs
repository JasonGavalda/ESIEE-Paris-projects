using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Projet_IMA
{
    public class BumpMapping
    {
        public float K;

        public BumpMapping (float K)
        {
            this.K = K;
        }
        public V3 NormaleBumpee(V3 pPixel, Objet pObjet)
        {
            V3 Normale = pObjet.Normale(pPixel);
            if (pObjet.texture == null)
                return Normale;

            float dhdu, dhdv;
            pObjet.texture.Bump(pObjet.getUNorm(pPixel), pObjet.getVNorm(pPixel), out dhdu, out dhdv);

            V3 T2 = dhdv * (pObjet.DeriveeU(pPixel) ^ Normale);
            V3 T3 = dhdu * (Normale ^ pObjet.DeriveeV(pPixel));

            if(pObjet.BMK != this.K)
                return Normale + pObjet.BMK * (T2 + T3);
            else
                return Normale + K*(T2+T3);
        }
    }
}