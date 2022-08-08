using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Projet_IMA
{
    static class ProjetEleve
    {
        public static void Go()
        {

            Texture T1 = new Texture("brick01.jpg");
           
            int larg = BitmapEcran.GetWidth();
            int haut = BitmapEcran.GetHeight();
            float r_x = 1.5f;   // repetition de la texture en x
            float r_y = 1.0f;   // repetition de la texture en y
            float pas = 0.01f;
            // Test
            Couleur Red = new Couleur(1.0f, 0.0f, 0.0f);
            Couleur Green = new Couleur(0.0f, 1.0f, 0.0f);
            Couleur Blue = new Couleur(0.0f, 0.0f, 1.0f);
            Couleur Purple = new Couleur(0.5f, 0.1f, 1.0f);
            Couleur Orange = new Couleur(1.0f, 0.5f, 0.0f);
            Couleur Yellow = new Couleur(1.0f, 1.0f, 0f);
            Couleur White = new Couleur(1.0f, 1.0f, 1.0f);
            Couleur Cyan = new Couleur(0.0f, 1.0f, 1.0f);
            Couleur Magenta = new Couleur(1.0f, 0.0f, 1.0f);
            Couleur Black = new Couleur(0.0f, 0.0f, 0.0f);

            Lampe LumiereAmbiante = new Lampe(White, 0.15f);
            LampeDirectionnelle LampeD = new LampeDirectionnelle(new Couleur(1.0f, 1.0f, 1.0f), 0.85f, new V3(1, -1, 1));
            LampeDirectionnelle LampeD2 = new LampeDirectionnelle(new Couleur(1.0f, 1.0f, 1.0f), 0.15f, new V3(-1, -1, -1));

            List<LampeDirectionnelle> ListeLampesD = new List<LampeDirectionnelle>();
            ListeLampesD.Add(LampeD);
            ListeLampesD.Add(LampeD2);

            Eclairage Eclairage = new Eclairage(LumiereAmbiante, ListeLampesD);
            BumpMapping BumpMapper = new BumpMapping(0.05f);

            V3 PosCam = new V3(larg / 2, -larg, haut / 2);

            V3 A = new V3(0, larg, 0);
            V3 B = new V3(larg, larg, 0);
            V3 C = new V3(0, larg, haut);
            V3 D = new V3(larg, larg, haut);
            V3 O = new V3(0, 0, 0);
            V3 HG = new V3(0, 0, haut);
            V3 HD = new V3(larg, 0, haut);
            V3 BD = new V3(larg, 0, 0);
            
            Sphere SA = new Sphere(150,0,200, 100, Green, "rock.jpg");
            Sphere SB = new Sphere(B, 60, Red);
            Sphere SC = new Sphere(C, 60, Red);
            Sphere SM = new Sphere(larg / 2 - 80, larg / 2+10, haut / 2 + 50, 80, Red, "uvtest.jpg", 0);
            Sphere SBM = new Sphere(800, 0, haut / 2, 100, White, "bump38.jpg", 38f);
            Parallelogramme MurFond = new Parallelogramme(A, B, C, Blue, "brick01.jpg");
            Parallelogramme MurGauche = new Parallelogramme(O, A, HG, Purple, "brick01.jpg");
            Parallelogramme MurDroite = new Parallelogramme(B, BD, D, Yellow, "brick01.jpg");
            Parallelogramme Plafond = new Parallelogramme(C, D, HG, Green, "fibre.jpg");
            Parallelogramme Sol = new Parallelogramme(O, BD, A, Orange, "wood.jpg");
            

            Sphere S_White = new Sphere(50, 100, 100, 40, White);
            Sphere S_Red = new Sphere(150, 100, 100, 40, Red);
            Sphere S_Yellow = new Sphere(250, 100, 100, 40, Yellow, "gold.jpg", 1f);
            Sphere S_Green = new Sphere(350, 100, 100, 40, Green);
            Sphere S_Cyan = new Sphere(450, 100, 100, 40, Cyan);
            Sphere S_Blue = new Sphere(550, 100, 100, 40, Blue);
            Sphere S_Magenta = new Sphere(650, 100, 100, 40, Magenta);
            Sphere S_Black = new Sphere(750, 100, 100, 40, Black);

            Scene test = new Scene(Eclairage, BumpMapper);
            
            
            test.AjouterObjet(SA);
            test.AjouterObjet(SB);
            test.AjouterObjet(SC);
            test.AjouterObjet(SM);
            test.AjouterObjet(SBM);
            test.AjouterObjet(MurFond);
            test.AjouterObjet(MurGauche);
            test.AjouterObjet(MurDroite);
            test.AjouterObjet(Plafond);
            test.AjouterObjet(Sol);
            
            
            test.AjouterObjet(S_White);
            test.AjouterObjet(S_Red);
            test.AjouterObjet(S_Yellow);
            test.AjouterObjet(S_Green);
            test.AjouterObjet(S_Cyan);
            test.AjouterObjet(S_Blue);
            test.AjouterObjet(S_Magenta);
            test.AjouterObjet(S_Black);
            

            test.Affichage(PosCam);


            // test des opérations sur les vecteurs

            V3 t = new V3(1, 0, 0);
            V3 r = new V3(0, 1, 0);
            V3 k = t + r;
            float p = k * t * 2;
            V3 n = t ^ r;
            V3 m = -t;

      
        }
    }
}
