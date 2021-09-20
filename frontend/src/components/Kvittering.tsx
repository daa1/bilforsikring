import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import ApiService from "../service/ApiService";
import { Avtale } from "../model/Avtale";

type KvitteringParams = {
  avtaleId: string;
};

export default function Kvittering() {
  const { avtaleId } = useParams<KvitteringParams>();
  const [loading, setLoading] = useState(true);
  const [avtale, setAvtale] = useState<Avtale>();

  useEffect(() => {
    const api = new ApiService();
    setLoading(true);
    api
      .getBestilling(avtaleId)
      .then((response) => {
        setLoading(false);
        if (!response.ok) {
          throw Error(response.statusText);
        }
        return response.json();
      })
      .then((data: Avtale) => setAvtale(data))
      .catch(() => setLoading(false));
  }, [avtaleId]);

  return (
    <React.Fragment>
      {loading && <p>Laster avtale...</p>}
      {!loading && avtale && (
        <div className="padding-vertical">
          <h2>Avtale mottatt!</h2>
          <p>
            Hei {avtale.kunde.fornavn}, vi har motatt din bestilling. Ved spørsmål kontakt oss på{" "}
            <a href="mailto:post@postkontoret.no">post@postkontoret.no</a> og oppgi avtalenummer {avtale.id}.
          </p>
        </div>
      )}
    </React.Fragment>
  );
}
