export type Avtale = {
  id: number;
  registreringsnummer: string;
  bonus: string;
  kunde: Kunde;
};

export type Kunde = {
  id: number;
  fodselsnummer: string;
  fornavn: string;
  etternavn: string;
  epost: string;
};
