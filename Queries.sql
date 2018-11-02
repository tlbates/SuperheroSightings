use SuperSightings;

select s.*
from Superpower s
inner join PersonSuperpowers ps on s.id = ps.superpowerid
inner join Person p on ps.personid = p.id
where p.id = 1;

select l.*
from Sighting s
inner join Location l on s.Locationid = l.id
where s.id = 1;

select l.*
from Organization o
inner join Location l on o.Locationid = l.id
where o.id = 2;


select o.* 
from Organization o 
inner join OrganizationPeople op on o.id = op.organizationid 
inner join person p on op.personid = p.id 
where p.name = 'Spiderman';