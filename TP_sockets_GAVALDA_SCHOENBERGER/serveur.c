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
	struct sockaddr_in clientadd, serveuradd;
	int res;
	struct hostent* host;

	
	host = gethostbyname("localhost");
	if(host == NULL)
	{ 
		perror("gethost");
		return -1;
	}
	
	serveur = socket(AF_INET, SOCK_DGRAM, 0);
	if (serveur == -1)
	{
		perror("socket");
		return -1;
	}

	memset(serveuradd.sin_zero, 0, sizeof(serveuradd));	
	serveuradd.sin_family = AF_INET;
	serveuradd.sin_port = htons(port);
	serveuradd.sin_addr.s_addr= htonl(INADDR_ANY);


	res = bind (serveur, (struct sockaddr *)&serveuradd, sizeof(serveuradd));
	if(res == -1) {
		perror("bind");
		return -1;
	}
	
	unsigned int socklen = (unsigned int) sizeof(serveuradd);
	recvfrom(serveur, msg, sizeof(msg), 0, (struct sockaddr *)&clientadd, &socklen); // RECEIVE FROM
	
	printf("%s\n", msg);
	char pid[32];
	sprintf(pid, " PID Serveur : %d",(int)getpid());
	strcat(msg, pid);
	printf("%s\n", msg);
	
	sendto(serveur, msg, sizeof(msg), 0, (struct sockaddr *)&clientadd, sizeof(clientadd)); //send to
	
	return 0;
}
