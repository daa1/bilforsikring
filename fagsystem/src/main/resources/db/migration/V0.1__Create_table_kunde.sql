create table Kunde(
    id identity not null primary key,
    fodselsnummer varchar(11) not null,
    fornavn varchar(255) not null,
    etternavn varchar(255) not null,
    epost varchar(255) not null
);
