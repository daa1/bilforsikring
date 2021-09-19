import React from "react";
import { fireEvent, render, screen, waitFor } from "@testing-library/react";
import App from "./App";
import userEvent from "@testing-library/user-event";

describe("App", () => {
  test("renders main heading", () => {
    render(<App />);
    const heading = screen.getByText(/Kjøp Bilforsikring/i);
    expect(heading).toBeInTheDocument();
  });

  test("should show default validation error after clicking 'kjøp'", async () => {
    render(<App />);
    const submit = screen.getByText("Kjøp");
    userEvent.click(submit);
    await waitFor(() => expect(screen.getByText(/Registreringsnummer er påkrevd/i)).toBeInTheDocument());
  });

  test("should specific pattern validation error after clicking 'kjøp'", async () => {
    render(<App />);
    fireEvent.input(screen.getAllByLabelText(/Bilens registreringsnummer/i)[0], {
      target: { value: "wat" },
    });
    const submit = screen.getByText("Kjøp");
    userEvent.click(submit);
    await waitFor(() => expect(screen.getByText(/Må være på form XX00000/i)).toBeInTheDocument());
  });
});
