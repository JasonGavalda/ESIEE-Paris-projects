#include <stdio.h>
#include <stdlib.h>
#include <netdb.h>
#include <netinet/in.h> /* pour protocole ds cas AF_INET */
#include <sys/types.h> 
#include <sys/socket.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>
#include <time.h>
#include <arpa/inet.h>

int main(int argc, char * argv[])
{
	if(argc < 2){
		perror("not enough arguments");
	}

	int portRead = atoi(argv[1]);
	int portWrite  = atoi(argv[2]);
	char buffer[1024];

	unsigned int addrlen;
	int reader, writer, s1w, s2r;
	struct sockaddr_in writeadd, readadd, clientadd;
	int res;

	fd_set box;
	FILE * fPtr;

// ---------------------------- open file -------------------

	fPtr = fopen("log_file.txt","w");

	if(fPtr == NULL)
	{
		perror("can't create file");
		exit(EXIT_FAILURE);
	}
	fclose(fPtr);

// ----------------------------- serveuradd ---------------
	memset(writeadd.sin_zero, 0, sizeof(writeadd));	
	writeadd.sin_family = AF_INET;
	writeadd.sin_port = htons(portWrite);	
	writeadd.sin_addr.s_addr = htonl(INADDR_ANY);

	memset(readadd.sin_zero, 0, sizeof(readadd));	
	readadd.sin_family = AF_INET;
	readadd.sin_port = htons(portRead);	
	readadd.sin_addr.s_addr = htonl(INADDR_ANY);

//----------------------- socket read	--------------------------
	s2r = socket(AF_INET, SOCK_STREAM, 0);
	if (s2r == -1)
	{
		perror("socket");
		return -1;
	}

	res = bind (s2r, (struct sockaddr *)&readadd, sizeof(readadd));
	if(res == -1) {
		perror("bind");
		return -1;
	}

	
//------------------------------- socketWrite ---------------	
	s1w = socket(AF_INET, SOCK_STREAM, 0);
	if (s1w == -1)
	{
		perror("socket");
		return -1;
	}

	res = bind (s1w, (struct sockaddr *)&writeadd, sizeof(writeadd));
	if(res == -1) {
		perror("bind");
		return -1;
	}

//-------------------------- socket listen --------------------------

	printf("ecoute de la socket %d ", s1w);
	res = listen (s1w, 1);
	if(res == -1) {
		perror("listen");
		return -1;
	}
	printf("listening on write\n");

	printf("ecoute de la socket %d ", s2r);
	res = listen (s2r, 1);
	if(res == -1) {
		perror("listen");
		return -1;
	}
	printf("listening on read\n");



        
    while(1){    

	//----------------------- select ----------------------------------
		FD_ZERO(&box);
		FD_SET(s1w, &box);
		FD_SET(s2r, &box);

		printf("select \n");
	    res = select(FD_SETSIZE, &box,0, 0, 0);
     	printf("select out \n");   
		if(res<0)	{
			perror("select() a echoué");
		}
	
        if(res>0)
        {
		// ---------------- treat write request ---------------------------			
        	if(FD_ISSET(s1w, &box))
        	{
				printf("start sending log_file \n");

            	addrlen = sizeof(struct sockaddr_in);
            	reader = accept(s1w, (struct sockaddr *) &clientadd, &addrlen);
            	if (reader == -1)	
            		perror("accept echoue");
				
				fPtr = fopen("log_file.txt","r");
				while(fgets(buffer, sizeof(buffer), fPtr) != NULL){
					write(reader, buffer, sizeof(buffer));

				}
				fclose(fPtr);

				printf("end sending log_file \n");
			}
		// -------------------- treat read request ----------------------
			if(FD_ISSET(s2r, &box))
        	{
				printf("start writing in log_file \n");
				//------ preparing to read
            	addrlen = sizeof(struct sockaddr_in);
            	writer = accept(s2r, (struct sockaddr *) &clientadd, &addrlen);
            	if (writer == -1){
           			perror("accept() failed");
				}

				//-------------- write in file date and adress ------------
				time_t t;
				time(&t);
				struct tm *currentTime = localtime(&t);

				fPtr = fopen("log_file.txt","a");
				sprintf(buffer, "connection made at: %s, by ", asctime(currentTime));
				fputs(buffer, fPtr);
				inet_ntop(AF_INET, &clientadd.sin_addr, buffer, 1024);
				fputs(buffer, fPtr);

				fputs(". File requested: ", fPtr);

				//----------- read socket
				int readDesc = read(writer, buffer, sizeof(buffer));
				while(readDesc > 0)
				{
					printf("%d, %s \n", readDesc, buffer);

					fputs(buffer, fPtr);
					readDesc = read(writer, buffer, sizeof(buffer));
				}
				fputs("\n", fPtr);
				fclose(fPtr);
				//------------- end read socket

				printf("end writing in log_file\n\n");
			}
		}
	}	
	return 0;	
}
