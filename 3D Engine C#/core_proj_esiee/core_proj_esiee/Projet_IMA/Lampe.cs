using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Projet_IMA
{
    public class Lampe
    {
        public Couleur CouleurLampe;
        public float Intensite;

        public Lampe(Couleur pCouleur, float pIntensite)
        {
            this.CouleurLampe = pCouleur;
            this.Intensite = pIntensite;
        }
    }
}