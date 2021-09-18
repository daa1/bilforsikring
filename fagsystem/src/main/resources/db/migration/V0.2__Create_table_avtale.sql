create table Avtale(
    id identity not null primary key,
    registreringsnummer varchar(7) not null,
    bonus integer not null,
    status varchar(50) not null,
    kunde_id integer references Kunde(id)
);
