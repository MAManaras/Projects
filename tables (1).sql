drop table ArtInExhib;
drop table ArtInHall;
drop table ArtInShow;
drop table creator; 
drop table artifact; 
drop table showcase;
drop table hall;
drop table exhibition;



CREATE TABLE Exhibition
(
    title VARCHAR(50),
    description VARCHAR(500),
    place VARCHAR(50),
    cost NUMBER(10),
    expenses NUMBER(10),
    donations NUMBER(10),
    tickets NUMBER(10),
    fromDate DATE,
    toDate DATE,
    CONSTRAINT pk_exhibition PRIMARY KEY(title)
);    


CREATE TABLE Hall
(
    hallID NUMBER(10),
    hallName VARCHAR(50),
    floor VARCHAR(50),
    multimedia VARCHAR(50),
    width NUMBER(3,2),
    height NUMBER(3,2),
    length NUMBER(3,2),
    CONSTRAINT pk_hall PRIMARY KEY(hallID)
);



CREATE TABLE Showcase
(
    showcaseID NUMBER(10),
    artifactType VARCHAR(50),
    width NUMBER(3,2),
    height NUMBER(3,2),
    length NUMBER(3,2),
    hall NUMBER(10),
    CONSTRAINT pk_showcase PRIMARY KEY(showcaseID),
    CONSTRAINT fk_showcase_hall FOREIGN KEY(hall) REFERENCES hall(hallID)
);



CREATE TABLE Artifact
(
    artifactID NUMBER(10),
    artifactName VARCHAR(50),
    type VARCHAR(50),
    description VARCHAR(500),
    creationYear DATE,
    width NUMBER(3,2),
    height NUMBER(3,2),
    length NUMBER(3,2),
    photo BLOB,
    exhibTitle VARCHAR(50),
    showcase NUMBER(10),
    hall NUMBER(10),
    CONSTRAINT pk_artifact PRIMARY KEY(artifactID),
    CONSTRAINT fk_artifact_exhibition FOREIGN KEY(exhibTitle) REFERENCES exhibition(title),
    CONSTRAINT fk_artifact_showcase FOREIGN KEY(showcase) REFERENCES showcase(showcaseID),
    CONSTRAINT fk_artifact_hall FOREIGN KEY(hall) REFERENCES hall(hallID)    
);   


CREATE TABLE Creator
(
    name VARCHAR(50),
    artID NUMBER(10),
    CONSTRAINT pk_creator PRIMARY KEY(name,artID),
    CONSTRAINT fk_creator_artifact FOREIGN KEY(artID) REFERENCES artifact(artifactID)
);



CREATE TABLE ArtInExhib
(
    artID NUMBER(10),
    exhibTitle VARCHAR(50),
    placementDate DATE,
    CONSTRAINT pk_ArtInExhib PRIMARY KEY(artID,exhibTitle),
    CONSTRAINT fk_ArtInExhib_artifact FOREIGN KEY(artID) REFERENCES artifact(artifactID),
    CONSTRAINT fk_ArtInExhib_exhibition FOREIGN KEY(exhibTitle) REFERENCES exhibition(title)
);


CREATE TABLE ArtInHall
(
    artID NUMBER(10),
    hall NUMBER(10),
    placementDate DATE,
    CONSTRAINT pk_ArtInHall PRIMARY KEY(artID,hall),
    CONSTRAINT fk_ArtInHall_artifact FOREIGN KEY(artID) REFERENCES artifact(artifactID),
    CONSTRAINT fk_ArtInHall_hall FOREIGN KEY(hall) REFERENCES hall(hallID)
);


CREATE TABLE ArtInShow
(
    artID NUMBER(10),
    showcase NUMBER(10),
    placementDate DATE,
    CONSTRAINT pk_ArtInShow PRIMARY KEY(artID,showcase),
    CONSTRAINT fk_ArtInShow_artifact FOREIGN KEY(artID) REFERENCES artifact(artifactID),
    CONSTRAINT fk_ArtInShow_showcase FOREIGN KEY(showcase) REFERENCES showcase(showcaseID)
);

