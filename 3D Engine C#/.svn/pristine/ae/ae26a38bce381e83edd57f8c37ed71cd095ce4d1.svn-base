using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Projet_IMA
{
    public class Scene
    {
        List<Objet> ListObjets;
        int Largeur = BitmapEcran.GetWidth();
        int Hauteur = BitmapEcran.GetHeight();
        Eclairage EclairageScene;
        BumpMapping BumpMapper;

        public Scene (Eclairage pEclairage , BumpMapping BumpMapper)
        {
            ListObjets = new List<Objet>();
            this.EclairageScene = pEclairage;
            this.BumpMapper = BumpMapper;
        }

        public void AjouterObjet (Objet pO)
        {
            this.ListObjets.Add(pO);
        }

        public void Affichage (V3 PosCam)
        {
            for(int u = 0 ; u<this.Largeur; u++)
            {
                for(int v = 0; v<this.Hauteur; v++)
                {
                    V3 PosPixel = new V3(u, 0, v); // Position du Pixel sur l'ecran.
                    V3 DirRayon = PosPixel - PosCam; // Vecteur de direction du rayon.
                    Raycast Ray = new Raycast(PosCam, DirRayon, new Couleur(0,0,0));

                    V3 Interaction;
                    V3 PrevInteraction = new V3(0, 0, 0);
                    V3 Normale = new V3(0, 0, 0);

                    Objet ObjetEclaire = null;

                    foreach(Objet O in ListObjets)
                    {
                        Interaction = O.Interaction(Ray);
                        if (Interaction.Norm() != 0)
                        {
                            if ((Interaction - PosCam).Norm() < (PrevInteraction - PosCam).Norm() || PrevInteraction.Norm() == 0)
                            {
                                ObjetEclaire = O;
                                V3 PosPixelScene = Interaction;
                                Ray.CouleurRay = O.getCouleurTexture(PosPixelScene);
                                
                                PrevInteraction = new V3(Interaction.x, Interaction.y, Interaction.z);
                                Normale = ObjetEclaire.Normale(PosPixelScene);
                                Normale = this.BumpMapper.NormaleBumpee(PosPixelScene, ObjetEclaire);
                            }
                        }   
                    }
                    this.EclairageScene.EclairagePixel(u, v, Ray, ObjetEclaire, Normale);
                }
            }
        }
    }
}