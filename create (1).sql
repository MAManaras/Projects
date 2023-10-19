CREATE TABLE Exhibition
(
    title VARCHAR(70),
    description VARCHAR(500),
    place VARCHAR(50),
    costEURO NUMBER(10),
    expensesEURO NUMBER(10),
    donationsEURO NUMBER(10),
    ticketsEURO NUMBER(10),
    fromDate DATE,
    toDate DATE,
    CONSTRAINT pk_exhibition PRIMARY KEY(title)
);    


CREATE TABLE Hall
(
    hallID NUMBER(10),
    hallName VARCHAR(50),
    floor NUMBER(1),
    multimedia VARCHAR(20),
    heightCM NUMBER(6),
    lengthCM NUMBER(6),
    widthCM NUMBER(6),
    CONSTRAINT pk_hall PRIMARY KEY(hallID)
);



CREATE TABLE Showcase
(
    showcaseID NUMBER(10),
    artifactType VARCHAR(50),
    heightCM NUMBER(6),
    lengthCM NUMBER(6),
    widthCM NUMBER(6),
    hall NUMBER(10),
    CONSTRAINT pk_showcase PRIMARY KEY(showcaseID),
    CONSTRAINT fk_showcase_hall FOREIGN KEY(hall) REFERENCES hall(hallID)
);



CREATE TABLE Artifact
(
    artifactID NUMBER(10),
    artifactName VARCHAR(70),
    type VARCHAR(50),
    description VARCHAR(500),
    creationYear NUMBER(4),
    heightCM NUMBER(6),
    lengthCM NUMBER(6),
    widthCM NUMBER(6),
    photo BLOB,
    exhibTitle VARCHAR(70),
    showcase NUMBER(10),
    hall NUMBER(10),
    ArtInHall DATE,
    ArtInShow DATE,
    ArtInExhib DATE,
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



COMMIT;