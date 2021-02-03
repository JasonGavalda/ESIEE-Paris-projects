seuil sA.pgm 105 s105.pgm
seuil sA.pgm 176 s176.pgm
seuil sA.pgm 228 s228.pgm
geodilat s228.pgm s176.pgm 4 -1 sB1.pgm
display sB1.pgm
erosion sB1.pgm carre9x9 sB1e.pgm
dilation sB1e.pgm carre9x9 sB1ed.pgm
display sB1ed.pgm
geodilat sB1ed.pgm s176.pgm 4 -1 sB2.pgm
display sB2.pgm
inverse sB2.pgm sB2i.pgm
geodilat sB2i.pgm sB1.pgm 4 -1 sB3.pgm
display sB3.pgm
areaselnb sB3.pgm 4 12 sB3a.pgm
display sB3a.pgm
erosion sB3a.pgm lignede7 sB3ae.pgm
dilation sB3ae.pgm lignede7 sB3aed.pgm
display sB3aed.pgm
dilation sB3aed.pgm carre3x3 sB4d.pgm
erosion sB4d.pgm carre3x3 sB4de.pgm
display sB4de.pgm
areaselnb sB4de.pgm 8 5 sB5.pgm
display sB5.pgm
erosion sB5.pgm diag sB5e.pgm
dilation sB5e.pgm diag sB5ed.pgm
display sB5ed.pgm
areaselnb sB5ed.pgm 8 3 sB6.pgm
display sB6.pgm
geodilat sB6.pgm sB5.pgm 4 -1 sB7.pgm
display sB7.pgm
inverse sB7.pgm sB7i.pgm
erosion sB7i.pgm carre9x9 sB7ie.pgm
dilation sB7ie.pgm carre9x9 sB7ied.pgm
display sB7ied.pgm
geodilat sB7ied.pgm sB3a.pgm 4 -1 sB8.pgm
display sB8.pgm
areaselnb sB8.pgm 4 1 sB9.pgm
display sB9.pgm
inverse sB9.pgm sB9i.pgm
geodilat sB9i.pgm sB8.pgm 4 -1 sB10.pgm
display sB10.pgm
erosion sB10.pgm diag sB10e.pgm
dilation sB10e.pgm diag sB10ed.pgm
display sB10ed.pgm
dilation sB10ed.pgm carre3x3 sB10ed2.pgm
areaselnb sB10ed2.pgm 4 3 sB11.pgm
display sB11.pgm
inverse sB11.pgm sB11i.pgm
geodilat sB11i.pgm sB10.pgm 4 -1 sB12.pgm
display sB12.pgm
erosion sB12.pgm lignede7 sB12e.pgm
dilation sB12e.pgm lignede7 sB12ed.pgm
areaselnb sB12ed.pgm 4 1 sB13.pgm
display sB13.pgm
dilation sB13.pgm carre5x5 sB13d.pgm
inverse sB13d.pgm sB13i.pgm
geodilat sB13i.pgm sB12.pgm 4 -1 sB14.pgm
display sB14.pgm
dilation sB14.pgm triangle sB14d.pgm
erosion sB14d.pgm triangle sB14de.pgm
display sB14de.pgm
erosion sB14de.pgm colonnede7par3 sB14de2.pgm
dilation sB14de2.pgm colonnede7par3 sB14d2e2.pgm
display sB14d2e2.pgm
erosion sB14d2e2.pgm diag2 sB14d3e2.pgm
dilation sB14d3e2.pgm diag2 sB14d3e3.pgm
display sB14d3e3.pgm
dilation sB14d3e3.pgm carre15x15 sB14d4e3.pgm
display sB14d4e3.pgm
inverse sB14d4e3.pgm sB14i.pgm
geodilat sB14i.pgm sB14.pgm 4 -1 sB.pgm
display sB.pgm
