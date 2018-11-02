drop database if exists SuperSightings;
create database if not exists SuperSightings;
use SuperSightings;

create table Superpower(
	ID int not null auto_increment,
    Description varchar (200) not null,
    primary key (ID)
);

create table PersonSuperpowers(
	PersonID int not null,
    SuperpowerID int not null
);

create table Person(
	ID int not null auto_increment,
    Name varchar(45) not null,
    Description varchar(200) not null,
     Hero tinyint(1),
    Villain tinyint(1),
    primary key (ID)
);

create table OrganizationPeople(
	PersonID int not null,
    OrganizationID int not null
);

create table Organization(
	ID int not null auto_increment,
    Name varchar(45) not null,
    Email varchar(45) not null,
    Phone varchar(10) not null,
    LocationID int not null,
    Primary key (ID)
);

create table Location(
	ID int not null auto_increment,
    Name varchar(30)not null,
    Description varchar(200) not null,
    Latitude decimal(9,6),
    Longitude decimal(9,6),
    Street varchar(45),
    City varchar(45),
    State varchar(45),
    ZIP int,
    Country varchar(45),
    primary key (ID)
);

create table Sighting(
	ID int not null auto_increment,
    Date date not null,
    LocationID int not null,
    primary key (ID)
);	

create table PersonSightings(
	PersonID int not null,
    SightingID int not null
);

alter table PersonSuperpowers add constraint fkPerson foreign key (PersonID) references Person (ID);
alter table PersonSuperpowers add constraint fkSuperpower foreign key (SuperpowerID) references Superpower (ID);

alter table OrganizationPeople add constraint fkPersonID foreign key (PersonID) references Person (ID);
alter table OrganizationPeople add constraint fkOrganization foreign key (OrganizationID) references Organization (ID);

alter table Organization add constraint fkLocationID foreign key (LocationID) references Location (ID);

alter table Sighting add constraint fkLocation foreign key (LocationID) references Location (ID);