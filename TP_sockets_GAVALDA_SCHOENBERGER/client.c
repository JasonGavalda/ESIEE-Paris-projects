#include <netinet/in.h>
#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>

int main(int argc, char* argv[]){

	if(argc < 3){
		perror("not enough arguments");
		return -1;
	}
	int port = atoi(argv[1]);
	int host_port = atoi(argv[2]);
	char msg[1024];
	sprintf(msg,"%s", argv[3]);
	char msg2[1024];


	char pid[32];
	sprintf(pid, " client pid : %d",(int)getpid());
	strcat (msg, pid);

	int chaussette;
	chaussette = socket(AF_INET, SOCK_DGRAM, 0);

	if(chaussette == -1){
		perror("socket");
		return -1;
	}

// -------------- client ad
	struct sockaddr_in ad;
	memset(&ad, 0, sizeof(ad));
	ad.sin_family = AF_INET;
	ad.sin_port = htons(port);
	ad.sin_addr.s_addr = INADDR_ANY;

//-------------- host adress
	struct hostent *host = gethostbyname( "localhost");
	if(host == NULL){
		perror("host not found");
	}

	struct sockaddr_in ad_serv;
	memset(&ad_serv, 0, sizeof(ad_serv));
	ad_serv.sin_family = AF_INET;
	ad_serv.sin_port = htons(host_port);
	memcpy(&ad_serv.sin_addr.s_addr,host->h_addr,host->h_length);


// ----- bound
	int bound;
	bound = bind(chaussette, (struct sockaddr *) &ad, sizeof(ad));
	if (bound == -1){
		perror("bind failure");
		return -1;
	}

	printf("%s\n", msg);
	sendto(chaussette, msg, sizeof(msg), 0, (struct sockaddr *) &ad_serv,
		sizeof(ad_serv));
	
	unsigned int socklen = (unsigned int) sizeof(ad_serv);
	recvfrom(chaussette	, msg2, sizeof(msg2), 0, (struct sockaddr *)&ad_serv, &socklen);

	printf("%s\n", msg2);

	return 0;
}
