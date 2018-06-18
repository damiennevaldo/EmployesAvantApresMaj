/*On supprime les tables si elles existent */
DROP TABLE EMPLOYES;

/*Création de la table EMPLOYES*/
CREATE TABLE "EMPLOYES" (
	"INSEE" VARCHAR(10) NOT NULL,
	"NOM" VARCHAR(25) NOT NULL,
	"PRENOM" VARCHAR(25) NOT NULL,
	"ADRESSE" VARCHAR(150) NOT NULL,
	"GRADE" VARCHAR(10) NOT NULL,
	"RESPONSABLE" VARCHAR(20) NOT NULL,
	"JNAISS" VARCHAR(10),
	"MNAISS" VARCHAR(50) ,
	"ANAISS" VARCHAR(10),
	"JEMBAUCHE" VARCHAR(10) ,
	"MEMBAUCHE" VARCHAR(50) ,
	"AEMBAUCHE" VARCHAR(25) ,
	CONSTRAINT primary_key_membre PRIMARY KEY (INSEE)
);

/*Insertion de 4 membres*/
INSERT INTO EMPLOYES(INSEE,NOM,PRENOM,ADRESSE,GRADE,RESPONSABLE,JNAISS,MNAISS,ANAISS,JEMBAUCHE,MEMBAUCHE,AEMBAUCHE) VALUES
('1000','Simpson','Homer','2 avenue Duff','GR1','Philipe','28','dec','1991','14','dec','2003'),
('4000','Simpson','Bart','3 avenue Duff','GR2','Loup','1','jan','1998','14','fev','2006'),
('2000','Lagaffe','Gaston','10 rue des Rebelles','GR4','Trip','14','mar','2001','04','avr','1993');
