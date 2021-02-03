#include <stdio.h>
#include <stdlib.h>
#include <netdb.h>
#include <netinet/in.h> /* pour protocole ds cas AF_INET */
#include <sys/types.h> 
#include <sys/socket.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>

int main(int argc, char * argv[])
{
	if(argc < 3){
		perror("not enough arguments");
	}
	int port = atoi(argv[1]);
	char* hostname = argv[2];
	char* filename = argv[3];
	char msg[1024];
	sprintf(msg, "%s", filename);

	char msg2[1024];


	struct sockaddr_in serveuradd;
	struct hostent* host;

//------------------ socket -----------------------------------------
	printf("socket \n");
	int s = socket(AF_INET,SOCK_STREAM,0);
	if (s == -1)
	{
		perror("socket");
		return -1;
	}	

//------------- connect ---------------------------------------------
	
	printf("host acquisition \n");
	host = gethostbyname(hostname);
	if(host == NULL)
	{ 
		perror("gethost");
		return -1;
	}

	printf("host acquired \n");

	serveuradd.sin_family = AF_INET;
	serveuradd.sin_port = htons(port);	
	memcpy(&serveuradd.sin_addr.s_addr,host->h_addr,host->h_length);



	printf("connexion \n");

	int connection = connect(s, (struct sockaddr*) &serveuradd, sizeof(serveuradd));
	if (connection == -1)
	{
		perror("connection failed");
		return -1;
	}

//---------------- write -----------------------------------------


	if(port == 8000){
		printf("writing:  %s\n", msg);
		if(write(s, msg, sizeof(msg)) == -1){
			perror("write error");
			return -1;
		}
	}

//----------------- read ----------------------------------------
	if(port == 8001){
		printf("read begin\n");
		while(read(s, msg2, sizeof(msg2)) > 0)
		{	
			printf("%s \n", msg2);
		}	
	}
//---------------- close ----------------------------------------

	if(close(s) == -1){
		perror("close error");
		return -1;
	}
	return 0;	
}

