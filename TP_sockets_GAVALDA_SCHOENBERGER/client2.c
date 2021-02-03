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
	sprintf(msg, "GET /%s HTTzeaezaze\
Host: localhost:8000\
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:83.0) Gecko/20100101 Firefox/83.0\
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\
Accept-Language: fr,fr-FR;q=0.8,en-US;q=0.5,en;q=0.3\
Accept-Encoding: gzip, deflate\
Connection: keep-alive\
Upgrade-Insecure-Requests: 1\
Cache-Control: max-age=0", filename);

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

	printf("write begin \n");
//---------------- write -----------------------------------------

	if(write(s, msg, sizeof(msg)) == -1){
		perror("write error");
		return -1;
	}

	printf("read begin\n");
//----------------- read ----------------------------------------
	int read_desc;
	while(read(s, msg2, sizeof(msg2)) > 0)
	{
			printf("%s\n", msg2);
	}	

	printf("%s\n", msg2);
//---------------- close ----------------------------------------

	if(close(s) == -1){
		perror("close error");
		return -1;
	}
	return 0;	
}
