import React from "react";
import { render, screen } from "@testing-library/react";
import TextInput from "./TextInput";

const setupTextInput = (): JSX.Element => {
  return <TextInput labelText={"Test input"} fieldName={"test"} />;
};

describe("TextInput", () => {
  test("Should be rendered in document", () => {
    render(setupTextInput());
    const testInput = screen.getByLabelText("Test input");
    expect(testInput).toBeInTheDocument();
  });
});
