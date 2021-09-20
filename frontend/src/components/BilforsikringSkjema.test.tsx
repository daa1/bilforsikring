import React from "react";
import { fireEvent, render, screen, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import BilforsikringSkjema from "./BilforsikringSkjema";
import { BilforsikringData } from "../model/BilforsikringData";

describe("BilforsikringSkjema", () => {
  const bestillCallbackMock = (data: BilforsikringData) => {};

  test("should show default validation error after clicking 'kjøp'", async () => {
    render(<BilforsikringSkjema bestillCallback={bestillCallbackMock} />);
    const submit = screen.getByText("Kjøp");
    userEvent.click(submit);
    await waitFor(() => expect(screen.getByText(/Registreringsnummer er påkrevd/i)).toBeInTheDocument());
  });

  test("should show specific pattern validation error after clicking 'kjøp'", async () => {
    render(<BilforsikringSkjema bestillCallback={bestillCallbackMock} />);
    fireEvent.input(screen.getAllByLabelText(/Bilens registreringsnummer/i)[0], {
      target: { value: "wat" },
    });
    const submit = screen.getByText("Kjøp");
    userEvent.click(submit);
    await waitFor(() => expect(screen.getByText(/Må være på form XX00000/i)).toBeInTheDocument());
  });
});
