OBJ=graph_basic.o graph_print.o graphaux.o seq.o TP1

CC = g++
CCFLAGS = -g -DLINUX

obj:	$(OBJ)

clean:
	rm -f $(OBJ); rm -f *.exe
	
.c.exe:	
	$(CC) $(CCFLAGS) $< $(OBJ) -lm -exe $@	
	
TP1 : seq.o
	$(CC) $(CCFLAGS) -o TP1 seq.o graphaux.o graph_basic.o graph_print.o
	
seq.o : seq.h seq.c
	$(CC) $(CCFLAGS) -c seq.c	

graphaux.o:	graphes.h graphaux.h graphaux.c
	$(CC) $(CCFLAGS) -c graphaux.c

graph_basic.o:	graphes.h graphaux.h graph_basic.c
	$(CC) $(CCFLAGS) -c graph_basic.c

graph_print.o:	graphes.h graphaux.h graph_print.c
	$(CC) $(CCFLAGS) -c graph_print.c
