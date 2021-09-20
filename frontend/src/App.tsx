import React from "react";
import { Route, Switch, useHistory } from "react-router-dom";
import BilforsikringSkjema from "./components/BilforsikringSkjema";
import ApiService from "./service/ApiService";
import { BilforsikringData } from "./model/BilforsikringData";
import { Avtale } from "./model/Avtale";
import Kvittering from "./components/Kvittering";

function App() {
  let history = useHistory();
  const api = new ApiService();
  const sendBestilling = (data: BilforsikringData) => {
    api
      .sendBestilling(data)
      .then((response) => {
        if (!response.ok) {
          throw Error(response.statusText);
        }
        return response.json();
      })
      .then((data: Avtale) => history.push(`/kvittering/${data.id}`))
      .catch(() => {
        history.push("/feil");
      });
  };

  return (
    <main className="container">
      <h1>Kjøp Bilforsikring</h1>
      <Switch>
        <Route exact path="/feil">
          <p>Beklager, noe feilet med innsendingen</p>
        </Route>
        <Route exact path="/kvittering/:avtaleId">
          <Kvittering />
        </Route>
        <Route path="/">
          <p>
            Det er fire forskjellige forsikringer å velge mellom. Ansvarsforsikring er lovpålagt om kjøretøyet er registrert og skal brukes
            på veien. I tillegg kan du utvide forsikringen avhengig av hvor gammel bilen din er og hvordan du bruker den.
          </p>

          <BilforsikringSkjema bestillCallback={sendBestilling} />
        </Route>
      </Switch>
    </main>
  );
}

export default App;
