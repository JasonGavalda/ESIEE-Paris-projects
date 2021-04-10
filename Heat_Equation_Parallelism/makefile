OBJ=function.o main2D.o main1D.o TP1D TP2D

CC = gcc
CCFLAGS = -g -DLINUX

obj:	$(OBJ)

clean:
	rm -f $(OBJ); rm -f *.exe
	
.c.exe:	
	$(CC) $(CCFLAGS) $< $(OBJ) -lm -exe $@	

TP1D : main1D.o
	$(CC) $(CCFLAGS) -fopenmp main1D.c function.c -o TP1D
	
TP2D : main2D.o
	$(CC) $(CCFLAGS) -fopenmp main2D.c function.c -o TP2D

