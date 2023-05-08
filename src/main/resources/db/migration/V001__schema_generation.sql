-- SCRIPT CHARACTER API
create table calendar_date (id  bigserial not null, historicDay int4, historicMonth int4, historicYear int4, idCalendar int8, idHistoricMonth int8, nameCalendar varchar, nameHistoricMonth varchar, negativeYear boolean, id_historicalDate int8 not null, primary key (id));
create table character (id  bigserial not null, comments varchar, createAt timestamp not null, name varchar not null, sex varchar not null, tags varchar, updateAt timestamp, id_historical_date_birth int8, id_historical_date_death int8, id_father int8, id_mother int8, primary key (id));
create table historical_date (id  bigserial not null, date timestamp, primary key (id));
alter table if exists calendar_date add constraint fk1_calendar_date foreign key (id_historicalDate) references historical_date;
alter table if exists character add constraint fk1_character foreign key (id_historical_date_birth) references historical_date;
alter table if exists character add constraint fk2_character foreign key (id_historical_date_death) references historical_date;
alter table if exists character add constraint fk3_character foreign key (id_father) references character;
alter table if exists character add constraint fk4_character foreign key (id_mother) references character;