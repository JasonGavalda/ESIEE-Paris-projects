min sA.pgm sB.pgm sC1.pgm
display sC1.pgm
heightmaxima sC1.pgm 1 175 sC2.pgm
display sC2.pgm

inverse sC2.pgm sC2i.pgm
min sA.pgm sC2i.pgm sC3.pgm
display sC3.pgm
max sC3.pgm sC2.pgm sC.pgm
display sC.pgm
