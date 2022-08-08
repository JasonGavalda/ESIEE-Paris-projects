using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;
using System.Drawing.Imaging;

namespace Projet_IMA
{

	class Parallelogramme : Objet
	{
		public V3 B;
		public V3 C;
        public V3 AB;
        public V3 AC;

        public Parallelogramme(V3 A, V3 B, V3 C, Couleur pCouleur, String pString, float BMK)
        {
            this.A = A;
            this.B = B;
            this.C = C;
            this.AB = B - A;
            this.AC = C - A;
            this.CouleurObjet = pCouleur;
            this.K = 50;
            this.BMK = BMK;
            this.texture = new Texture(pString);
        }

        public Parallelogramme(V3 A, V3 B, V3 C, Couleur pCouleur, String pString)
        {
            this.A = A;
            this.B = B;
            this.C = C;
            this.AB = B - A;
            this.AC = C - A;
            this.CouleurObjet = pCouleur;
            this.K = 50;
            this.BMK = 0.005f;
            this.texture = new Texture(pString);
        }

        public Parallelogramme(V3 A, V3 B, V3 C, Couleur pCouleur)
        {
            this.A = A;
            this.B = B;
            this.C = C;
            this.AB = B - A;
            this.AC = C - A;
            this.CouleurObjet = pCouleur;
            this.K = 50;
        }

        public override V3 Interaction(Raycast pRay)
        {

            float u, v;

            V3 n = this.AB ^ this.AC / (this.AB ^ this.AC).Norm();

            float t = ((this.A - pRay.R0) * n) / (pRay.Rd * n);

            V3 I = pRay.R0 + t * pRay.Rd;
            V3 AI = I - this.A;

            u = (this.AC ^ n) * AI / (this.AB ^ this.AC).Norm();
            v = (this.AB ^ AI) * n / (this.AC ^ this.AB).Norm();

            if ((u >= 0) && (u <= 1) && (v >= 0) && (v <= 1))
                return (I);
            else
                return new V3(0, 0, 0);
        }

        public override V3 Normale(V3 pPixel)
        {
            return AB ^ AC;
        }

        public override float getU(V3 pPixel)
        {
            return pPixel * this.AB / this.AB.Norm();
        }

        public override float getUNorm(V3 pPixel)
        {
            return getU(pPixel) / this.AB.Norm();
        }

        public override float getV(V3 pPixel)
        {
            return pPixel * this.AC / this.AC.Norm();
        }

        public override float getVNorm(V3 pPixel)
        {
            return getV(pPixel) / this.AC.Norm();
        }

        public override V3 DeriveeU(V3 pPixel)
        {
            return this.AB;
        }

        public override V3 DeriveeV(V3 pPixel)
        {
            return this.AC;
        }

        public override Couleur getCouleurTexture(V3 pPixel)
        {
            if (texture == null)
                return this.CouleurObjet;

            float u = getUNorm(pPixel);
            float v = getVNorm(pPixel);

            return texture.LireCouleur(u,v); 
        }
    }
}