SRC=graph_basic.c graph_print.c graph_algos.c graphaux.c Dijkstra_dans_le_metro.c main1.c main2.c
OBJ=graph_basic.o graph_print.o graph_algos.o graphaux.o Dijkstra_dans_le_metro.o main1.o main2.o exo1 exo2 exo3
# version LINUX:
CC = g++
CCFLAGS = -g -DLINUX

# version HP-UX: 
#CC = cc
#CCFLAGS = -g -DHP -Aa -DDEFTIMEVAL

obj:	$(OBJ)

doc:	graphes.dox $(SRC)
	doxygen graphes.dox; mv doc/html/* Html/doc

clean:
	rm -f $(OBJ); rm -f *.exe

.SUFFIXES:	.exe

.c.exe:	
	$(CC) $(CCFLAGS) $< $(OBJ) -lm -exe $@

exo1 : main1.o
	$(CC) $(CCFLAGS) -o exo1 main1.o graphaux.o graph_basic.o graph_print.o graph_algos.o Dijkstra_dans_le_metro.o
exo2 : main2.o
	$(CC) $(CCFLAGS) -o exo2 main2.o graphaux.o graph_basic.o graph_print.o graph_algos.o Dijkstra_dans_le_metro.o
exo3 : main3.o
	$(CC) $(CCFLAGS) -o exo3 main3.o graphaux.o graph_basic.o graph_print.o graph_algos.o Dijkstra_dans_le_metro.o

graphaux.o:	graphes.h graphaux.h graphaux.c
	$(CC) $(CCFLAGS) -c graphaux.c

graph_basic.o:	graphes.h graphaux.h graph_basic.c
	$(CC) $(CCFLAGS) -c graph_basic.c

graph_print.o:	graphes.h graphaux.h graph_print.c
	$(CC) $(CCFLAGS) -c graph_print.c

graph_algos.o:	graphes.h graphaux.h graph_algos.c
	$(CC) $(CCFLAGS) -c graph_algos.c

Dijkstra_dans_le_metro.o: graphes.h graphaux.h graph_basic.c graph_print.c graph_algos.c Dijkstra_dans_le_metro.h
	$(CC) $(CCFLAGS) -c Dijkstra_dans_le_metro.c

main1.o: graphes.h graphaux.h graph_basic.c graph_print.c graph_algos.c Dijkstra_dans_le_metro.h main1.c
	$(CC) $(CCFLAGS) -c main1.c

main2.o: graphes.h graphaux.h graph_basic.c graph_print.c graph_algos.c Dijkstra_dans_le_metro.h main2.c
	$(CC) $(CCFLAGS) -c main2.c

main3.o: graphes.h graphaux.h graph_basic.c graph_print.c graph_algos.c Dijkstra_dans_le_metro.h main3.c
	$(CC) $(CCFLAGS) -c main3.c

