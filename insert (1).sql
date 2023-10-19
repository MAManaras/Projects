alter session
set nls_date_format = 'DD-MM-YYYY';



--creation of exhibition
INSERT INTO exhibition VALUES
(
    'The Greatest Impressionists',  --exhibition title
    'a collection of the 19th-century most famous impressionists',  --description
    'inside the museum',  --place
    300000,  --cost in euro
    50000,  --expenses in euro
    80000,  --donations in euro
    500000,  --ticket gains in euro
    '25-02-1999',  --begin date
    '29-06-2003'  --end date
);



--insertion of 5 halls
INSERT INTO hall VALUES
(
    1,  --hall ID
    'Impressionism',  --hall name
    1,  --floor
    'multimedia hall',  --multimedia or not
    500,  --height in cm
    20000,  --length in cm
    30000  --width in cm
);


INSERT INTO hall VALUES
(
    2,  --hall ID
    'Post Impressionism',  --hall name
    1,  --floor
    'multimedia hall',  --multimedia or not
    500,  --height in cm
    20000,  --length in cm
    30000  --width in cm
);


INSERT INTO hall VALUES
(
    3,  --hall ID
    'Figure painting',  --hall name
    2,  --floor
    'multimedia hall',  --multimedia or not
    500,  --height in cm
    10000,  --length in cm
    20000  --width in cm
);


INSERT INTO hall VALUES
(
    4,  --hall ID
    'Statues Hall',  --hall name
    3,  --floor
    'multimedia hall',  --multimedia or not
    500,  --height in cm
    20000,  --length in cm
    30000  --width in cm
);


INSERT INTO hall VALUES
(
    5,  --hall ID
    'Art Nouveau',  --hall name
    4,  --floor
    'multimedia hall',  --multimedia or not
    500,  --height in cm
    20000,  --length in cm
    40000  --width in cm
);





--insertion of 10 showcases
INSERT INTO showcase VALUES
(
    1,  --showcase ID
    'statue',  --type of artifact
    200,  --height in cm
    80,  --length in cm
    80,  --width in cm
    4  --hall ID
);

INSERT INTO showcase VALUES
(
    2,  --showcase ID
    'statue',  --type of artifact
    120,  --height in cm
    90,  --length in cm
    70,  --width in cm
    4  --hall ID
);

INSERT INTO showcase VALUES
(
    3,  --showcase ID
    'decorative item',  --type of artifact
    20,  --height in cm
    20,  --length in cm
    20,  --width in cm
    5  --hall ID
);

INSERT INTO showcase VALUES
(
    4,  --showcase ID
    'decorative item',  --type of artifact
    60,  --height in cm
    50,  --length in cm
    40,  --width in cm
    5  --hall ID
);

INSERT INTO showcase VALUES
(
    5,  --showcase ID
    'decorative item',  --type of artifact
    70,  --height in cm
    40,  --length in cm
    40,  --width in cm
    5  --hall ID
);

INSERT INTO showcase VALUES
(
    6,  --showcase ID
    'decorative item',  --type of artifact
    160,  --height in cm
    110,  --length in cm
    40,  --width in cm
    5  --hall ID
);

INSERT INTO showcase VALUES
(
    7,  --showcase ID
    'decorative item',  --type of artifact
    50,  --height in cm
    50,  --length in cm
    50,  --width in cm
    5  --hall ID
);

INSERT INTO showcase VALUES
(
    8,  --showcase ID
    'decorative item',  --type of artifact
    160,  --height in cm
    60,  --length in cm
    50,  --width in cm
    5  --hall ID
);

INSERT INTO showcase VALUES
(
    9,  --showcase ID
    'decorative item',  --type of artifact
    30,  --height in cm
    30,  --length in cm
    30,  --width in cm
    5  --hall ID
);

INSERT INTO showcase VALUES
(
    10,  --showcase ID
    'painting',  --type of artifact
    60,  --height in cm
    50,  --length in cm
    40,  --width in cm
    1  --hall ID
);



--insertion of 30 artifacts
--we assume that all artifacts belong to a museum hall and are no part of a travelling exhibition
INSERT INTO artifact VALUES
(
    1,  --artifact ID
    'London, Houses of Parliament: Sun Breaking Through the Fog',  --artifact name
    'painting',  --type
    'oil on canvas',  --description
    1904,  --creation year
    101.5,  --height in cm
    112.5,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    1,  --hall ID
    '25-02-1999',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    2,  --artifact ID
    'The Water Lily Pond: Green Harmony',  --artifact name
    'painting',  --type
    'oil on canvas',  --description
    1899,  --creation year
    100,  --height in cm
    103.5,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    1,  --hall ID
    '25-02-1999',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    3,  --artifact ID
    'Danse in the Country',  --artifact name
    'painting',  --type
    'oil on canvas',  --description
    1883,  --creation year
    205.5,  --height in cm
    115.2,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    1,  --hall ID
    '25-02-1999',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    4,  --artifact ID
    'The Reader',  --artifact name
    'painting',  --type
    'oil on canvas',  --description
    1883,  --creation year
    46.5,  --height in cm
    38.5,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    10,  --showcase ID
    1,  --hall ID
    '25-02-1999',  --date of placement in a hall
    '25-02-1999',  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    5,  --artifact ID
    'The Dancing Class',  --artifact name
    'painting',  --type
    'oil on canvas',  --description
    1876,  --creation year
    109.5,  --height in cm
    99.3,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    1,  --hall ID
    '25-02-1999',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    6,  --artifact ID
    'Little Dancer of Fourteen Years',  --artifact name
    'sculpture',  --type
    'patinated bronze statue, tulle tutu, satin ribbon, wooden base',  --description
    1931,  --creation year
    103.8,  --height in cm
    48.8,  --length in cm
    50,  --width in cm
    null,  --photograph
    null,  --exhibition title
    1,  --showcase ID
    4,  --hall ID
    '12-05-1998',  --date of placement in a hall
    '12-05-1998',  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    7,  --artifact ID
    'Ballet Rehearsal on Stage',  --artifact name
    'painting',  --type
    'oil on canvas',  --description
    1824,  --creation year
    89.5,  --height in cm
    105.5,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    1,  --hall ID
    '25-02-1999',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
( 
    8,  --artifact ID
    'The Reading',  --artifact name
    'painting',  --type
    'oil on canvas',  --description
    1883,  --creation year
    77,  --height in cm
    91.5,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    1,  --hall ID
    '25-02-1999',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    9,  --artifact ID
    'Arearea',  --artifact name
    'painting',  --type
    'oil on canvas',  --description
    1892,  --creation year
    95.5,  --height in cm
    116,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    2,  --hall ID
    '27-12-2001',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    10,  --artifact ID
    'The White Horse',  --artifact name
    'painting',  --type
    'oil on canvas',  --description
    1898,  --creation year
    165.5,  --height in cm
    115.8,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    2,  --hall ID
    '27-12-2001',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    11,  --artifact ID
    'The Kiss',  --artifact name
    'sculpture',  --type
    'marble group',  --description
    1898,  --creation year
    181.5,  --height in cm
    112.3,  --length in cm
    117.0,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    4,  --hall ID
    '12-05-1998',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    12,  --artifact ID
    'Eternal Springtime',  --artifact name
    'sculpture',  --type
    'bronze group',  --description
    1884,  --creation year
    40,  --height in cm
    53,  --length in cm
    30,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    4,  --hall ID
    '12-05-1998',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    13,  --artifact ID
    'Crouching Woman',  --artifact name
    'sculpture',  --type
    'bronze statue',  --description
    1909,  --creation year
    90,  --height in cm
    60,  --length in cm
    50,  --width in cm
    null,  --photograph
    null,  --exhibition title
    2,  --showcase ID
    4,  --hall ID
    '12-05-1998',  --date of placement in a hall
    '12-05-1998',  --date of placement in a showcase
     null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    14,  --artifact ID
    'Bedroom in Arles',  --artifact name
    'painting',  --type
    'oil on canvas',  --description
    1889,  --creation year
    74.5,  --height in cm
    90.7,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    2,  --hall ID
    '27-12-2001',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    15,  --artifact ID
    'The Starry Night',  --artifact name
    'painting',  --type
    'oil on canvas',  --description
    1888,  --creation year
    94.5,  --height in cm
    114,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    2,  --hall ID
    '27-12-2001',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    16,  --artifact ID
    'Portrait of the Artist',  --artifact name
    'painting',  --type
    'oil on canvas',  --description
    1889,  --creation year
    91.5,  --height in cm
    80.2,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    2,  --hall ID
    '27-12-2001',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    17,  --artifact ID
    'Equality After Death',  --artifact name
    'painting',  --type
    'oil in canvas',  --description
    1848,  --creation year
    173,  --height in cm
    299.5,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    3,  --hall ID
    '18-09-1997',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    18,  --artifact ID
    'The Assault',  --artifact name
    'painting',  --type
    'oil in canvas',  --description
    1898,  --creation year
    207,  --height in cm
    157.5,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    3,  --hall ID
    '18-09-1997',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    19,  --artifact ID
    'Dante and Virgile',  --artifact name
    'painting',  --type
    'oil in canvas',  --description
    1850,  --creation year
    307.5,  --height in cm
    253.5,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    3,  --hall ID
    '18-09-1997',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    20,  --artifact ID
    'The Village Road, Auvers',  --artifact name,
    'painting',  --type
    'oil on canvas',  --description
    1873,  --creation year
    69.5,  --height in cm
    78,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    2,  --hall ID
    '27-12-2001',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    21,  --artifact ID
    'The Hanged Man House, Auvers-sur-Oise',  --artifact name
    'painting',  --type
    'oil on canvas',  --description
    1873,  --creation year
    75.3,  --height in cm
    87,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    2,  --hall ID
    '27-12-2001',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    22,  --artifact ID
    'The Opera Ball',  --artifact name
    'painting',  --type
    'oil on canvas',  --description
    1886,  --creation year
    85,  --height in cm
    63,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    3,  --hall ID
    '18-09-1997',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    23,  --artifact ID
    'Madame Valtesse de la Bigne',  --artifact name
    'painting',  --type
    'oil on canvas',  --description
    1879,  --creation year
    222.5,  --height in cm
    143,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    null,  --showcase ID
    3,  --hall ID
    '18-09-1997',  --date of placement in a hall
    null,  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    24,  --artifact ID
    'Snowball Cup',  --artifact name
    'decorative item',  --type
    'cup: soft porcelain, transparent enamels, gold highlights',  --description
    1903,  --creation year
    8.5,  --height in cm
    10.8,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    3,  --showcase ID
    5,  --hall ID
    '17-12-2000',  --date of placement in a hall
    '17-12-2000',  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    25,  --artifact ID
    'Vase',  --artifact name
    'decorative item',  --type
    'vase: three-layer crystal, engraved decoration, gilded and silvered bronze setting',  --description
    1890,  --creation year
    42.3,  --height in cm
    35,  --length in cm
    25.5,  --width in cm
    null,  --photograph
    null,  --exhibition title
    4,  --showcase ID
    5,  --hall ID
    '17-12-2000',  --date of placement in a hall
    '17-12-2000',  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    26,  --artifact ID
    'Wall Mirror',  --artifact name
    'decorative item',  --type
    'mirror with wood core',  --description
    1910,  --creation year
    52,  --height in cm
    31,  --length in cm
    3,  --width in cm
    null,  --photograph
    null,  --exhibition title
    5,  --showcase ID
    5,  --hall ID
    '17-12-2000',  --date of placement in a hall
    '17-12-2000',  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    27,  --artifact ID
    'Women in White',  --artifact name
    'decorative item',  --type
    'tapestry: wool on canvas, petit point needlepoint',  --description
    1895,  --creation year
    150,  --height in cm
    98,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    6,  --showcase ID
    5,  --hall ID
    '17-12-2000',  --date of placement in a hall
    '17-12-2000',  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    28,  --artifact ID
    'Tufts of Barley',  --artifact name
    'decorative item',  --type
    'fan: carved horn and openwork, mother-of-pearl inlays',  --description
    1911,  --creation year
    21.4,  --height in cm
    38.7,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    7,  --showcase ID
    5,  --hall ID
    '17-12-2000',  --date of placement in a hall
    '17-12-2000',  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    29,  --artifact ID
    'Nannies Promenade, Frieze of Carriages',  --artifact name
    'decorative item',  --type
    'screen consisting of a series of four sheets lithographed in five colors',  --description
    1897,  --creation year
    143,  --height in cm
    46,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    8,  --showcase ID
    5,  --hall ID
    '17-12-2000',  --date of placement in a hall
    '17-12-2000',  --date of placement in a showcase
    null  --date of placement in an exhibition
);

INSERT INTO artifact VALUES
(
    30,  --artifact ID
    'Cup',  --artifact name
    'decorative item',  --type
    'cup: molten glass',  --description
    1910,  --creation year
    5.2,  --height in cm
    12.4,  --length in cm
    null,  --width in cm
    null,  --photograph
    null,  --exhibition title
    9,  --showcase ID
    5,  --hall ID
    '17-12-2000',  --date of placement in a hall
    '17-12-2000',  --date of placement in a showcase
    null  --date of placement in an exhibition
);




--creators of artifacts
INSERT INTO creator VALUES
(
    'Monet, Claude',  --creator's name
    1  --artifact ID
);

INSERT INTO creator VALUES
(
    'Monet, Claude',  --creator's name
    2  --artifact ID
);

INSERT INTO creator VALUES
(
    'Renoir, Pierre Auguste',  --creator's name
    3  --artifact ID
);

INSERT INTO creator VALUES
(
    'Renoir, Pierre Auguste',  --creator's name
    4  --artifact ID
);

INSERT INTO creator VALUES
(
    'Degas, Edgar',  --creator's name
    5  --artifact ID
);

INSERT INTO creator VALUES
(
    'Degas, Edgar',  --creator's name
    6  --artifact ID
);

INSERT INTO creator VALUES
(
    'Degas, Edgar',  --creator's name
    7  --artifact ID
);

INSERT INTO creator VALUES
(
    'Manet, Edouard',  --creator's name
    8  --artifact ID
);

INSERT INTO creator VALUES
(
    'Gauguin, Paul',  --creator's name
    9  --artifact ID
);

INSERT INTO creator VALUES
(
    'Gauguin, Paul',  --creator's name
    10  --artifact ID
);

INSERT INTO creator VALUES
(
    'Rodin, Auguste',  --creator's name
    11  --artifact ID
);

INSERT INTO creator VALUES
(
    'Turcan, Jean',  --creator's name
    11  --artifact ID
);

INSERT INTO creator VALUES
(
    'Rodin, Auguste',  --creator's name
    12  --artifact ID
);

INSERT INTO creator VALUES
(
    'Rodin, Auguste',  --creator's name
    13  --artifact ID
);

INSERT INTO creator VALUES
(
    'Rudier, Alexis',  --creator's name
    13  --artifact ID
);

INSERT INTO creator VALUES
(
    'Rudier, Eugene',  --creator's name
    13  --artifact ID
);

INSERT INTO creator VALUES
(
    'Van Gogh, Vincent',  --creator's name
    14  --artifact ID
);

INSERT INTO creator VALUES
(
    'Van Gogh, Vincent',  --creator's name
    15  --artifact ID
);

INSERT INTO creator VALUES
(
    'Van Gogh, Vincent',  --creator's name
    16  --artifact ID
);

INSERT INTO creator VALUES
(
    'Bouguereau, William',  --creator's name
    17  --artifact ID
);

INSERT INTO creator VALUES
(
    'Bouguereau, William',  --creator's name
    18  --artifact ID
);

INSERT INTO creator VALUES
(
    'Bouguereau, William',  --creator's name
    19  --artifact ID
);

INSERT INTO creator VALUES
(
    'Cezanne, Paul',  --creator's name
    20  --artifact ID
);

INSERT INTO creator VALUES
(
    'Cezanne, Paul',  --creator's name
    21  --artifact ID
);

INSERT INTO creator VALUES
(
    'Gervex, Henri',  --creator's name
    22  --artifact ID
);

INSERT INTO creator VALUES
(
    'Gervex, Henri',  --creator's name
    23  --artifact ID
);

INSERT INTO creator VALUES
(
    'Naudot, Camille',  --creator's name
    24  --artifact ID
);

INSERT INTO creator VALUES
(
    'Georges Pannier',  --creator's name
    25  --artifact ID
);

INSERT INTO creator VALUES
(
    'Henry Pannier',  --creator's name
    25  --artifact ID
);

INSERT INTO creator VALUES
(
    'Antoni Gaudi',  --creator's name
    26  --artifact ID
);

INSERT INTO creator VALUES
(
    'Paul Ranson',  --creator's name
    27  --artifact ID
);

INSERT INTO creator VALUES
(
    'Bastard, Georges',  --creator's name
    28  --artifact ID
);

INSERT INTO creator VALUES
(
    'Bonnard, Pierre',  --creator's name
    29  --artifact ID
);

INSERT INTO creator VALUES
(
    'Dammouse, Albert',  --creator's name
    30  --artifact ID
);


--placement of artifacts in the exhibition
UPDATE artifact SET exhibTitle='The Greatest Impressionists',ArtInExhib='25-02-1999' WHERE artifactID=1;
UPDATE artifact SET exhibTitle='The Greatest Impressionists',ArtInExhib='25-02-1999' WHERE artifactID=2;
UPDATE artifact SET exhibTitle='The Greatest Impressionists',ArtInExhib='25-02-1999' WHERE artifactID=3;
UPDATE artifact SET exhibTitle='The Greatest Impressionists',ArtInExhib='25-02-1999' WHERE artifactID=4;
UPDATE artifact SET exhibTitle='The Greatest Impressionists',ArtInExhib='25-02-1999' WHERE artifactID=5;
UPDATE artifact SET exhibTitle='The Greatest Impressionists',ArtInExhib='25-02-1999' WHERE artifactID=7;
UPDATE artifact SET exhibTitle='The Greatest Impressionists',ArtInExhib='25-02-1999' WHERE artifactID=8;




COMMIT;