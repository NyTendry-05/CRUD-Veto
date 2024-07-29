create database animatronicv3;
\c animatronicv3;
CREATE EXTENSION IF NOT EXISTS pgcrypto;
create table proprietaire (
    id serial primary key,
    nom varchar(50),
    coordonnees varchar(21)
);
create table animal (
    id serial primary key,
    idProprietaire int references proprietaire(id) on delete cascade,
    nom varchar(25)
);
create table garde (
    id serial primary key,
    nom varchar(50)
);
create table sitting (
    id serial primary key,
    idAnimal int references animal(id) on delete cascade,
    idGarde int references garde(id) on delete cascade,
    debut TIMESTAMP default now(),
    fin TIMESTAMP default now()
);
create table admin (
    id serial primary key,
    username varchar(25),
    pwd varchar(256)
);

INSERT INTO admin (username, pwd) VALUES ('Freddy', encode(digest('1234', 'sha256'), 'hex'));

create view detailsitting as select sitting.idAnimal, garde.id as idgarde , garde.nom, sitting.debut, sitting.fin 
from garde left join sitting on garde.id = sitting.idGarde;

-- '2024-07-18 12:00:00' to '2024-07-18 16:00:00' 

select * from garde where id not in (select idGarde from detailsitting where 
(('2024-07-18 12:00:00' >= debut and '2024-07-18 12:00:00' <= fin) or ('2024-07-18 16:00:00' >= debut and '2024-07-18 16:00:00' <= fin)) 
or (( debut >= '2024-07-18 12:00:00' and debut <= '2024-07-18 16:00:00') or ( fin >= '2024-07-18 12:00:00' and fin <= '2024-07-18 16:00:00')));

select * from animal where id not in (select idAnimal from detailsitting where 
(('2024-07-17 12:00:00' >= debut and '2024-07-17 12:00:00' <= fin) or ('2024-07-17 16:00:00' >= debut and '2024-07-17 16:00:00' <= fin)) 
or (( debut >= '2024-07-17 12:00:00' and debut <= '2024-07-17 16:00:00') or ( fin >= '2024-07-17 12:00:00' and fin <= '2024-07-17 16:00:00')));