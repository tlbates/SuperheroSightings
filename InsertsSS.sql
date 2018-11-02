use SuperSightings;

insert into Person (Name, Description, Hero, Villain) values 
('Spiderman', 'Has a red and blue jumpsuit with webs stitched in.',1,0),
('Black Panther', 'King of Wakanda',1,0),
('Hulk', 'Large green man that you do not want to anger.',1,0),
('Thor', 'Asguardian god and heir of Asguard. Has a large hammer.',1,0),
('Iron Man', 'Has a red metal suit that has cannons and also very cocky.',1,0),
('Captain America', 'Always has a shield on hand and a shield insignia on his chest.',1,0),
('Doctor Strange', 'Has a cape and uses reformed yoga to bend time and space to his will.',1,0),
('Star-Lord', 'Also known as Peter Quill. Normally jamming to old tunes and saving the galaxy.',1,0),
('Thanos', 'Big. Purple. Uses a glove to kill half the universe.',0,1),
('Loki', 'The god of mischief loves irritating his older brother and enslaving people.',0,1),
('Nebula', 'Purple and also has a hatred for her sister. Has energy blasters.',0,1),
('Doctor Doom', 'Covered in metal and uses electricity with harm to raise his self esteem.',0,1),
('Killmonger', 'Believes he is the rightful ruler of Wakanda.',0,1),
('Green Goblin', 'Uses a flying skateboard to wreak havoc and throw bombs at people.',0,1),
('Doctor Octopus', 'A doctor that has metal arms to look like a sea creature.',0,1),
('Venom', 'A symbiotic creature that attaches itself to people and makes them powerful.',0,1);

insert into Superpower (Description) values
('Flying'),
('Super Strength'),
('Spidey Sense'),
('Climbing Walls'),
('Shooting Webs'),
('Turning Green and Huge'),
('Lightning Abilities'),
('Invincible Shield'),
('Telekenesis'),
('Bending Time and Space'),
('Shapeshifting'),
('Teleportation'),
('Immortality');


insert into PersonSuperpowers (PersonID, SuperpowerID) values
(1,2),
(1,3),
(1,4),
(1,5),
(2,2),
(3,2),
(3,6),
(4,7),
(4,1),
(7,9),
(7,10),
(7,12),
(8,2),
(9,9),
(9,11),
(14,2),
(15,2),
(16,2);

insert into Location (name, Description, Latitude, Longitude) values
('Central Park','A park in the middle of Manhattan that includes ballfields and a zoo.',40.78098,-73.970226),
('Hard Rock Cafe','A cafe that produces 24/7 hard rock music with food.',40.756928,-73.986585),
('The White House','Where the president resides',38.897175,-77.036566),
('Martha and Kents House','Supermans childhood home.',38.037313, -97.941317),
('Software Guild','A school for people that love to stare at computers.',38.253954, -85.748286),
('Ballard Park','A skatepark in Tupelo MS.',34.247443, -88.765886),
('Rocketown','A punk music concert room/skatepark.',36.154246, -86.773087);

insert into Location (name, Description) values 
('In another dimension', 'Not a hundred percent sure honestly'),
('Another Planet','In another galaxy.');

insert into Location (name, Description, Street, City, State, ZIP, Country) values
('The Helicarrier','A floating aircraft carrier where the avengers have heated meetings.','The Sky','Wherever they want.','All of them.',10101,'USA'),
('Spiderman Sucks Inc.','A room in an alley somewhere.','123 Spiderhate Avenue','Manhattan','New York',10023,'USA'),
('No More Siblings LLC','A very large building with alot of spite built into it.','776 Why Street','L.A.','California',91313,'USA'),
('More Please','All your wildest dreams.','Seaway Lane','Pacific','Ocean',88768,'USA');

insert into Organization (Name, Email, phone, LocationID) values
('The Avengers', 'saveDaWorld@gmail.com', '6634354453', 10),
('The Spider Exterminators', 'destroyspidey@please.com', '5547689922', 11),
('The Jealous Types', 'comeonnnn@sibling.com', '6634354453', 12),
('I Just Want Power', 'pleaseletmeenslaveyou@yes.com', '3342253344', 13);

insert into OrganizationPeople (PersonID, OrganizationID) values
(1,1),
(2,1),
(3,1),
(4,1),
(5,1),
(6,1),
(7,1),
(8,1),
(9,4),
(12,4),
(13,4),
(10,3),
(11,3),
(15,2),
(14,2),
(16,2);

insert into Sighting (Date, LocationID) values
('2018/07/21',6),
('2018/07/28',1),
('2018/07/31',3),
('2018/08/10',4),
('2018/08/15',2),
('2018/09/21',5),
('2018/09/28',8),
('2018/10/24',9),
('2018/10/30',7);

insert into PersonSightings (PersonID, SightingID) values
(16,1),
(15,1),
(14,1),
(1,2),
(3,2),
(4,2),
(6,3),
(5,3),
(8,3),
(11,4),
(12,5),
(9,6),
(10,7),
(13,8),
(7,7);

