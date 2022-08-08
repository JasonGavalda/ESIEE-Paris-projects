using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;
using System.Drawing.Imaging;

namespace Projet_IMA
{

	class Sphere : Objet
	{
		public float Rayon;
        public Sphere(float x, float y, float z, float pRayon, Couleur pCouleur, String pString)
        {
            this.A = new V3(x, y, z);
            this.Rayon = pRayon;
            this.CouleurObjet = pCouleur;
            this.K = 50;
            this.texture = new Texture(pString);
            this.BMK = 5;
        }

        public Sphere(float x, float y, float z, float pRayon, Couleur pCouleur, String pString, float BMK)
        {
            this.A = new V3(x, y, z);
            this.Rayon = pRayon;
            this.CouleurObjet = pCouleur;
            this.K = 50;
            this.texture = new Texture(pString);
            this.BMK = BMK;
        }

        public Sphere(float x, float y, float z, float pRayon, Couleur pCouleur)
		{
			this.A = new V3(x, y, z);
			this.Rayon = pRayon;
			this.CouleurObjet = pCouleur;
            this.K = 50;
		}

        public Sphere(V3 Position, float pRayon, Couleur pCouleur, String pString)
        {
            this.A = Position;
            this.Rayon = pRayon;
            this.CouleurObjet = pCouleur;
            this.K = 50;
            this.texture = new Texture(pString);
            this.BMK = 5;
        }

        public Sphere(V3 Position, float pRayon, Couleur pCouleur, String pString, float BMK)
        {
            this.A = Position;
            this.Rayon = pRayon;
            this.CouleurObjet = pCouleur;
            this.K = 50;
            this.texture = new Texture(pString);
            this.BMK = BMK;
        }

        public Sphere(V3 Position, float pRayon, Couleur pCouleur)
        {
            this.A = Position;
            this.Rayon = pRayon;
            this.CouleurObjet = pCouleur;
            this.K = 50;
        }

        public override V3 Interaction(Raycast pRay)
        {
            float r = this.Rayon;

            float a = pRay.Rd * pRay.Rd;
            float b = 2 * (pRay.R0 - this.A) * pRay.Rd;
            float c = (pRay.R0 - this.A) * (pRay.R0 - this.A) - r * r;

            float Delta = b * b - 4 * a * c;

            if (Delta > 0)
            {
                float t1 = (-b - (float)Math.Sqrt(Delta)) / (2 * a);
                float t2 = (-b + (float)Math.Sqrt(Delta)) / (2 * a);

                if (t1 > 0 && t2 > 0)
                    return (pRay.R0 + t1 * pRay.Rd);
                else if (t1 < 0 && t2 > 0)
                    return (pRay.R0 + t2 * pRay.Rd);
                else
                    return new V3(0, 0, 0);
            }
            else
                return new V3(0, 0, 0);
        }

        public override V3 Normale(V3 pPixel)
        {
            float u, v;
            IMA.Invert_Coord_Spherique(pPixel-this.A, this.Rayon, out u, out v);
            return new V3(IMA.Cosf(v) * IMA.Cosf(u), IMA.Cosf(v) * IMA.Sinf(u), IMA.Sinf(v));
        }

        public override float getU(V3 pPixel)
        {
            float u, v;
            IMA.Invert_Coord_Spherique(pPixel-this.A, this.Rayon, out u, out v);
            return u;
        }

        public override float getUNorm(V3 pPixel)
        {
            return getU(pPixel)/ (2 * IMA.PI);
        }

        public override float getV(V3 pPixel)
        {
            float u, v;
            IMA.Invert_Coord_Spherique(pPixel-this.A, this.Rayon, out u, out v);
            return v;
        }

        public override float getVNorm(V3 pPixel)
        {
            return (getV(pPixel) + IMA.PI) / IMA.PI;
        }

        public override V3 DeriveeU(V3 pPixel)
        {
            float x = -IMA.Cosf(this.getV(pPixel)) * IMA.Sinf(this.getU(pPixel));
            float y = IMA.Cosf(this.getV(pPixel)) * IMA.Cosf(this.getU(pPixel));
            float z = 0.0f;
            return new V3(x, y, z);
        }

        public override V3 DeriveeV(V3 pPixel)
        {
            float x = -IMA.Cosf(this.getU(pPixel)) * IMA.Sinf(this.getV(pPixel));
            float y = -IMA.Sinf(this.getV(pPixel)) * IMA.Sinf(this.getU(pPixel));
            float z = IMA.Cosf(this.getV(pPixel));
            return new V3(x, y, z);
        }

        public override Couleur getCouleurTexture(V3 pPixel)
        {
            if (texture == null)
                return this.CouleurObjet;

            float u, v;
            u = getUNorm(pPixel);
            v = getVNorm(pPixel);
            return texture.LireCouleur(u, v);
        }
    }
}