/* T. Grandpierre - Application distribue'e pour TP IF4-DIST 2004-2005

But : 

fournir un squelette d'application capable de recevoir des messages en 
mode non bloquant provenant de sites connus. L'objectif est de fournir
une base pour implementer les horloges logique/vectorielle/scalaire, ou
bien pour implementer l'algorithme d'exclusion mutuelle distribue'

Syntaxe :
         arg 1 : Numero du 1er port
	 arg 2 et suivant : nom de chaque machine

--------------------------------
Exemple pour 3 site :

Dans 3 shells lances sur 3 machines executer la meme application:

pc5201a>./dist 5000 pc5201a.esiee.fr pc5201b.esiee.fr pc5201c.esiee.fr
pc5201b>./dist 5000 pc5201a.esiee.fr pc5201b.esiee.fr pc5201c.esiee.fr
pc5201c>./dist 5000 pc5201a.esiee.fr pc5201b.esiee.fr pc5201c.esiee.fr

pc5201a commence par attendre que les autres applications (sur autres
sites) soient lance's

Chaque autre site (pc5201b, pc5201c) attend que le 1er site de la
liste (pc5201a) envoi un message indiquant que tous les sites sont lance's


Chaque Site passe ensuite en attente de connexion non bloquante (connect)
sur son port d'ecoute (respectivement 5000, 5001, 5002).
On fournit ensuite un exemple permettant 
1) d'accepter la connexion 
2) lire le message envoye' sur cette socket
3) il est alors possible de renvoyer un message a l'envoyeur ou autre si
necessaire 

*/
#include<time.h>
#include <stdbool.h>
#include <stdio.h>
#include<stdlib.h>
#include<netinet/in.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<unistd.h>
#include<fcntl.h>
#include<netdb.h>
#include<string.h>

int GetSitePos(int Nbsites, char *argv[]) ;
void WaitSync(int socket);
void SC();
void demandeSC();
void recoitSC();
void recoitLib();



struct msg{
	bool flag;
	char Type[20];
	int ELm;
	int Site;
};
void printFile(struct msg * file, int size);

int max(int a, int b){
	if(a > b){
		return a;
	}
	return b;
}

/*Envoi d'un msg de synchro à la machine Site/Port*/
void SendSync(char *Site, int Port, char * stringMsg) {
  struct hostent* hp;
  int s_emis;
  int longtxt;
  struct sockaddr_in sock_add_emis;
  int size_sock;

  /*-------------Création socket------------------------------------------*/
  if ( (s_emis=socket(AF_INET, SOCK_STREAM,0))==-1) {
    perror("SendSync : Creation socket");
    exit(-1);
  }
    
  /*-------------Récupération des caractéristiques de l'hôte--------------*/
  hp = gethostbyname(Site);
  if (hp == NULL) {
    perror("SendSync: Gethostbyname");
    exit(-1);
  }

  size_sock=sizeof(struct sockaddr_in);
  sock_add_emis.sin_family = AF_INET;
  sock_add_emis.sin_port = htons(Port);
  memcpy(&sock_add_emis.sin_addr.s_addr, hp->h_addr, hp->h_length);
  
  /*------------Connexion à l'hôte---------------------------------------*/
  if (connect(s_emis, (struct sockaddr*) &sock_add_emis,size_sock)==-1) {
    perror("SendSync : Connect");
    exit(-1);
  }
     
  longtxt =strlen(stringMsg);
  /*Emission d'un message de synchro*/
  if(write(s_emis,stringMsg,longtxt) == -1)
  {
  	perror("write error");
  	exit(-1);
  }
  close (s_emis); 
}
void buildMessage(struct msg message, char * buffer){
	sprintf(buffer, "%s %d %d.", message.Type, message.ELm, message.Site);
}

struct msg readMessage(char * readRequest, int size){
	int i = 0;
	int j = 0;
	char * buffer = (char*)malloc(size * sizeof(char));
	struct msg messageRequest;

	/*------------Récupération du Type-----------------*/
	while(readRequest[i] != ' '){
		buffer[i] = readRequest[i];
		i++;
	}
	strcpy(messageRequest.Type, buffer);
	/*-------------------------------------------------*/
	i++;
	j=0;
	/*------------Récupération de l'estampille---------*/
	while(readRequest[i] != ' '){
		buffer[j] = readRequest[i];
		i++;
		j++;
	}
	buffer[j] = '\0';
	messageRequest.ELm = atoi(buffer);
	/*-------------------------------------------------*/
	i++;
	j=0;
	/*------------Récupération du Site-----------------*/
	while(readRequest[i] != '.'){
		buffer[j] = readRequest[i];
		i++;
		j++;
	}
	buffer[j] = '\0';
	messageRequest.Site =atoi(buffer);
	/*-------------------------------------------------*/
	free(buffer);
	messageRequest.flag = true; //Passage du flag à true, pour valider le message.
	return messageRequest;
}

void sendMessage(struct msg message, int PortBase, int Site){
	char buffer[40];
	buildMessage(message, buffer); //Construction de la chaîne de caractères à partir de message.
	printf("Message envoye : %s to site %d\n", buffer, Site);
	SendSync("localhost", PortBase, buffer); //Envoi d'un message de synchro au Site.
}

void sendMessageToAll(struct msg newRequest, int PortZero, int NbSites, int id){
		for(int l = 0; l < NbSites; l++){
			if(l != id)
				sendMessage(newRequest, PortZero + l, id);
		}
}

void insert(struct msg * tabRequests, int NbSites, struct msg * newRequest){
	printFile(tabRequests, NbSites);
	printf ("insert req = %s, %d, %d\n", newRequest->Type, newRequest->ELm, newRequest->Site);
	for(int i = NbSites - 1; i>=0; i--)
	{
		if(tabRequests[i].flag){ // Si la requete existe
			if(tabRequests[i].ELm == newRequest->ELm && tabRequests[i].Site < newRequest->Site){
					tabRequests[i+1] = *newRequest; // Placement de la requete dans la file.
					printFile(tabRequests, NbSites);
					return;
			}
			else if(tabRequests[i].ELm < newRequest->ELm){ // Si l'horloge de la requête insérée est plus grande.
				tabRequests[i+1] = *newRequest; // Placement de la requete dans la file.
				printFile(tabRequests, NbSites);
				return;
			}
			else
				tabRequests[i+1] = tabRequests[i]; // Dans tous les autres cas, on décale les autres requetes sur la droite.
		}
	}
	tabRequests[0] = *newRequest;
	printFile(tabRequests, NbSites);
}

void removeFirstMsg(struct msg * tabRequests, int NbSites){
	int i;
	for(i = 0; i < NbSites; i++)
	{
		if(tabRequests[i].flag){
			tabRequests[i] = tabRequests[i + 1]; // On décale tous les messages sur la gauche.
		}
	}
	struct msg falseRequest = (struct msg){.flag = false, .Type = "falseRequest", .ELm = -1, .Site = -1};
	tabRequests[NbSites - 1] = falseRequest;
}

int peekSite(struct msg * tabRequests){
	if(tabRequests[0].flag){
		return tabRequests[0].Site;
	}
	else return -1;		
}

void initFile(struct msg* tabRequests, int size){
	struct msg falseRequest = (struct msg){.flag = false, .Type = "falseRequest", .ELm = -1, .Site = -1};
	for(int i = 0; i < size; i++){
		tabRequests[i] = falseRequest;
	}
}

void printFile(struct msg * file, int size)
{
	printf("---------------\n");
	for(int i = 0; i < size; i++){
		if(file[i].flag)
			printf ("tab[%d] = %s, %d, %d\n", i, file[i].Type, file[i].ELm, file[i].Site);
	}
	printf("---------------\n");
}

int GetSitePos(int NbSites, char *argv[]) {
	return atoi(argv[2]) - atoi(argv[1]);
}


/*Attente bloquante d'un msg de synchro sur la socket donnée*/
void WaitSync(int s_ecoute) {
  char texte[40];
  int l;
  int s_service;
  struct sockaddr_in sock_add_dist;
  unsigned int size_sock;
  size_sock=sizeof(struct sockaddr_in);
  printf("WaitSync : ");fflush(0);
  s_service=accept(s_ecoute,(struct sockaddr*) &sock_add_dist, &size_sock);
  l=read(s_service,texte,39);
  texte[l] ='\0';
  printf("%s\n",texte); fflush(0);
  close (s_service);
} 


/***********************************************************************/
/***********************************************************************/
/***********************************************************************/
/***********************************************************************/

int main (int argc, char* argv[]) {
  struct sockaddr_in sock_add, sock_add_dist;
  unsigned int size_sock;
  int s_ecoute, s_service;
  char texte[40];
  int i,l;
  float t;
  srand( time( NULL ) );

  int PortBase=-1; /*Numero du port de la socket a` creer*/
  int NSites=-1; /*Nb total de sites*/


  if (argc!=4) {
    printf("Il faut donner le numero de port du site et le nombre total de sites");
    exit(-1);
  }

  /*----Nombre de sites (adresses de machines)---- */
  //NSites=argc-2;
  NSites=atoi(argv[3]); //-2;
  struct msg file[NSites];
  initFile(file, NSites);


  /*CREATION&BINDING DE LA SOCKET DE CE SITE*/
  int PortZero=atoi(argv[1]);
  PortBase=atoi(argv[2]) ;//+GetSitePos(NSites, argv);
  printf("Numero de port de ce site %d\n",PortBase);

  sock_add.sin_family = AF_INET;
  sock_add.sin_addr.s_addr= htons(INADDR_ANY);  
  sock_add.sin_port = htons(PortBase);

  if ( (s_ecoute=socket(AF_INET, SOCK_STREAM,0))==-1) {
    perror("Creation socket");
    exit(-1);
  }

  if ( bind(s_ecoute,(struct sockaddr*) &sock_add, \
	    sizeof(struct sockaddr_in))==-1) {
    perror("Bind socket");
    exit(-1);
  }
  
  listen(s_ecoute,30);
  /*----La socket est maintenant créée, bindée et listen----*/

  if (PortZero == PortBase){ 
    /*Le site 0 attend une connexion de chaque site : */
    for(i=0;i<NSites-1;i++) {
	      WaitSync(s_ecoute);
	}

    printf("Site 0 : toutes les synchros des autres sites recues \n");fflush(0);
    /*et envoie un msg a chaque autre site pour les synchroniser */
    for(i=0;i<NSites-1;i++) 
      SendSync("localhost", atoi(argv[1])+i+1, "** SYNCHRO **");
    } else {
      /* Chaque autre site envoie un message au site0 
	 (1er  dans la liste) pour dire qu'il est lance'*/
      SendSync("localhost", atoi(argv[1]), "** SYNCHRO **");
      /*et attend un message du Site 0 envoye' quand tous seront lance's*/
      printf("Wait Synchro du Site 0\n");fflush(0);
      WaitSync(s_ecoute);
      printf("Synchro recue de  Site 0\n");fflush(0);
  }

  
  /* Passage en mode non bloquant du accept pour tous*/
  /*---------------------------------------*/
  fcntl(s_ecoute,F_SETFL,O_NONBLOCK);
  size_sock=sizeof(struct sockaddr_in);
  
  int id = GetSitePos(NSites, argv);
  int nbSitesAnswered = 0;
  bool waitAnswer;
  bool requestInFile;
  int horloge = 1;
  /* Boucle infini*/
  while(1) {
    /* On commence par tester l'arrivee d'un message */
    s_service=accept(s_ecoute,(struct sockaddr*) &sock_add_dist,&size_sock);
    if(s_service>0) {

    /*Extraction et affichage du message */
		l=read(s_service,texte,39);
	
		printf("Message recu : %s\n",texte); fflush(0);
		struct msg newRequest = readMessage(texte, 39); // Création du message.
		horloge = max(horloge, newRequest.ELm) + 1; // Mise à jour de l'horloge du site.
		if(strcmp(newRequest.Type, "Requete") == 0){
			insert(file, NSites, &newRequest); //Si le message est une requête, il est placé dans la file d'attente.

			struct msg answerRequest = (struct msg){.flag = true, .Type = "Reponse", .ELm = horloge, .Site = id }; // Création du message de réponse.
			
			sendMessage(answerRequest, PortZero + newRequest.Site, id); // Envoi de la réponse.
		}
		else if(strcmp(newRequest.Type, "Liberation") == 0){
			if(!(newRequest.Site == file[0].Site)){
				//removeFirstMsg(file, NSites);
				printf("Error liberation and first message sites are different %d != %d\n", file[0].Site, newRequest.Site);
			}
			else{
				removeFirstMsg(file, NSites); // On retire le premier message de la file.
			}


		}
		else if(strcmp(newRequest.Type, "Reponse") == 0){
			nbSitesAnswered ++;
		}
		else
			printf("Requete pas comprise : %s, %d, %d\n", newRequest.Type, newRequest.ELm, newRequest.Site);

      close (s_service);
    }
  

    /* Petite boucle d'attente : c'est ici que l'on peut faire des choses*/
    for(l=0;l<1000000;l++) { 
      t=t*3;
      t=t/3;
    }

	float frand = (float)rand()/(float)RAND_MAX; // Création d'un flottant aléatoire.
	if(frand > 0.6 && frand < 0.7){
		horloge++; // Si la condition est celle-ci, on incrémente l'horloge du site.

	}
	else if(frand > 0.7 && frand < 0.8 && !requestInFile){
		struct msg newRequest = (struct msg){.flag = true, .Type = "Requete", .ELm = horloge, .Site = id }; // Si la condition est respectée, on créé une requête.
		insert(file, NSites, &newRequest); // On insère cette requête dans la file d'attente.

		sendMessageToAll(newRequest, PortZero, NSites, id); // On l'envoie à tous les sites.
		waitAnswer = true;
		requestInFile = true; // On signifie que la requête est placée et qu'on attend une 	réponse.
	}

	
	if(nbSitesAnswered == NSites - 1) // on verifie si on a toutes les reponses.
		waitAnswer = false;

	
	if(requestInFile && !waitAnswer && peekSite(file) == id){
		//Section Critique
		printf("Section critique : %d\n", horloge);
		removeFirstMsg(file, NSites); // On retire le message en tête de file.
		struct msg newRequest = (struct msg){.flag = true, .Type = "Liberation", .ELm = horloge, .Site = id }; // On créé un message de Libération de la Section Critique.
		sendMessageToAll(newRequest, PortZero, NSites, id); // On l'envoie à tous les sites.
		requestInFile = false;
		nbSitesAnswered = 0;
	}
	else if(requestInFile && !waitAnswer){
		//printf("wrong id, cant critic");
	}
	printf(".");
	fflush(0); /* pour montrer que le serveur est actif*/
  }


  close (s_ecoute);  
  return 0;
}


