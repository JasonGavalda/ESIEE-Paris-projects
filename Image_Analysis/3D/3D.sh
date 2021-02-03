convexhull sB.pgm 3 sC1.pgm
dilation sC1.pgm carre5x5 sC1d.pgm
dilation sC1d.pgm ligne_droite_bas sC1d2.pgm
dilation sC1d2.pgm ligne_gauche_haut sC1d3.pgm
inverse sC1d3.pgm sC5.pgm
display sC5.pgm

min sA.pgm sC1d3.pgm sC6.pgm
display sC6.pgm
erosion sC6.pgm gd_ligne sC6e.pgm
display sC6e.pgm
dilation sC6e.pgm gd_ligne sC6ed.pgm
display sC6ed.pgm
dilation sC6ed.pgm gd_ligne sC6ed2.pgm
erosion sC6ed2.pgm gd_ligne sC6e2d2.pgm
dilation sC6e2d2.pgm carre3x3 sC7.pgm
display sC7.pgm

min sA.pgm sC5.pgm sC8.pgm
display sC8.pgm
max sC8.pgm sC7.pgm sD.pgm
display sD.pgm
