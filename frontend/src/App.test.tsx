import React from "react";
import { render, screen } from "@testing-library/react";
import App from "./App";

describe("App", () => {
  test("renders main heading", () => {
    render(<App />);
    const heading = screen.getByText(/Kjøp Bilforsikring/i);
    expect(heading).toBeInTheDocument();
  });
});
