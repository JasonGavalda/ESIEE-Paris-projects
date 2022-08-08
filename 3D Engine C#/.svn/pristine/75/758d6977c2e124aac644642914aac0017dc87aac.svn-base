using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;
using System.Drawing.Imaging;

namespace Projet_IMA
{
    public abstract class Objet
    {
        public V3 A { get; set; }
        public Couleur CouleurObjet;
        public Texture texture;
        public int K { get; set; }

        public float BMK {get;set;}

        public abstract V3 Interaction(Raycast pRay);

        public abstract V3 Normale(V3 pPixel);

        public abstract float getU(V3 pPixel);

        public abstract float getUNorm(V3 pPixel);

        public abstract float getV(V3 pPixel);

        public abstract float getVNorm(V3 pPixel);

        public abstract V3 DeriveeU(V3 pPixel);

        public abstract V3 DeriveeV(V3 pPixel);

        public abstract Couleur getCouleurTexture(V3 pPixel);
    }
}