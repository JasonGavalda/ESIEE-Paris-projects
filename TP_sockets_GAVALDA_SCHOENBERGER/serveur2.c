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
	if(argc < 1){
		perror("not enough arguments");
	}
	int port = atoi(argv[1]);
	char msg[1024];


	int serveur;
	struct sockaddr_in serveuradd, clientadd;
	int res;
	struct hostent* host;


	
	host = gethostbyname("localhost");
	if(host == NULL)
	{ 
		perror("gethost");
		return -1;
	}
	
	serveur = socket(AF_INET, SOCK_STREAM, 0);
	if (serveur == -1)
	{
		perror("socket");
		return -1;
	}

	memset(serveuradd.sin_zero, 0, sizeof(serveuradd));	
	serveuradd.sin_family = AF_INET;
	serveuradd.sin_port = htons(port);	
	//serveuradd.sin_addr.s_addr = INADDR_ANY;
	memcpy(&serveuradd.sin_addr.s_addr,host->h_addr,host->h_length);



	res = bind (serveur, (struct sockaddr *)&serveuradd, sizeof(serveuradd));
	if(res == -1) {
		perror("bind");
		return -1;
	}

	res = listen (serveur, 1);
	if(res == -1) {
		perror("listen");
		return -1;
	}
	printf("listening\n");


	while(1){
	

		unsigned int ad_len = sizeof(struct sockaddr_in);
		int clientaccept = accept (serveur, (struct sockaddr*) &clientadd, &ad_len);
		if(res == -1) {
			perror("accept");
			return -1;
		}
//-------------------------- lire -----------------------------
		printf("accepting\n");
		int lire = read(clientaccept, msg, sizeof(msg));
		if(lire == -1){
			perror("lire");
			return -1;
		}
		else if(lire == 0){
			perror("nothing to read");
			exit(1);
		}
//----------------------- write -------------------------------
		printf("%s", msg);
		write(clientaccept, msg, sizeof(msg));
	}
	//-------- connection is DONE;

	return 0;	
}
