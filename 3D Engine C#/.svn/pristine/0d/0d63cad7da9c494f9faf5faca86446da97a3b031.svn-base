using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Projet_IMA
{
    public class Eclairage
    {
        public Lampe LampeAmbiante { get; }
        public List<LampeDirectionnelle> ListeLampes { get; }

        public Eclairage(Lampe pLampeAmbiante, List<LampeDirectionnelle> pListeLampes)
        {
            this.LampeAmbiante = pLampeAmbiante;
            this.ListeLampes = pListeLampes;
        }

        public void EclairagePixel(int u, int v, Raycast pRay, Objet pObjet, V3 Normale)
        {
            // S'il n'y a pas d'objet à éclairer, il n'y a rien à éclairer;
            if (pObjet == null)
                return;

            Normale.Normalize();

            int K = pObjet.K;

            Couleur lumiere_ambiante = this.LampeAmbiante.CouleurLampe * this.LampeAmbiante.Intensite * pRay.CouleurRay;

            Couleur lumiere_diffuse = new Couleur(0, 0, 0);
            Couleur lumiere_speculaire = new Couleur(0, 0, 0);

            foreach(LampeDirectionnelle LD in this.ListeLampes)
            {
                // Calcul de la lumiere diffuse.
                V3 DirectionLampeNormalisee = LD.Direction;
                DirectionLampeNormalisee.Normalize();

                float CosTheta = Math.Max(0, Normale * DirectionLampeNormalisee); // On ne calcule pas l'angle, mais le terme CosTheta à partir des propriétés du produit scalaire.
                if (CosTheta > 0)
                    lumiere_diffuse += LD.CouleurLampe * LD.Intensite * pRay.CouleurRay * CosTheta;
                else
                    lumiere_diffuse += new Couleur(0, 0, 0);
                // Calcul de la lumiere spéculaire.
                V3 R = DirectionLampeNormalisee - 2 * (Normale * (DirectionLampeNormalisee)) * Normale;
                R.Normalize();

                V3 D = pRay.Rd;
                D.Normalize();

                // Test nécessaire pour éviter d'avoir deux lumières speculaires pour la même lampe.
                if (R * D > 0)
                    lumiere_speculaire += (LD.CouleurLampe * LD.Intensite * (float)Math.Pow((D * R), K));
                else
                    lumiere_speculaire += new Couleur(0, 0, 0);
            }

            // Coloration du pixel par addition des trois types de lumière.
            BitmapEcran.DrawPixel(u, v, lumiere_ambiante + lumiere_diffuse + lumiere_speculaire);
        }
    }
}