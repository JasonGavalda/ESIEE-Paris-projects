OBJ=TD2.o TD2_test.o TD2 TD2_test

CC = g++
CCFLAGS = -g -DLINUX

obj:	$(OBJ)

clean:
	rm -f $(OBJ); rm -f *.exe
	
.c.exe:	
	$(CC) $(CCFLAGS) $< $(OBJ) -lm -exe $@	
	
TD2 : TD2.o
	$(CC) $(CCFLAGS) -o TD2 TD2.o
	
TD2.o : TD2.c
	$(CC) $(CCFLAGS) -c TD2.c	

TD2_test : TD2_test.o
	$(CC) $(CCFLAGS) -o TD2_test TD2_test.o
	
TD2_test.o : TD2_test.c
	$(CC) $(CCFLAGS) -c TD2_test.c	
