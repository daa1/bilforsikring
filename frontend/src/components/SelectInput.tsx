import React from "react";
import { UseFormRegister } from "react-hook-form";

type SelectInputProps = {
  id?: string;
  label: string;
  fieldName: string;
  required: boolean;
  options: Nedtrekksvalg[];
  hasError?: boolean;
  register: UseFormRegister<any>;
};

export type Nedtrekksvalg = {
  label: string;
  value: string;
};

export default function SelectInput({
  label,
  id = "select-input-" + Math.floor(Math.random() * 1000000 + 1),
  fieldName,
  required = false,
  options,
  hasError = false,
  register,
}: SelectInputProps) {
  return (
    <React.Fragment>
      <label htmlFor={id} className="label">
        {label}
      </label>

      <div className={"select-wrapper" + (hasError ? " has-error" : "")}>
        <select id={id} {...register(fieldName, { required: required })}>
          {options.map((nedtrekksvalg) => (
            <option key={nedtrekksvalg.value} value={nedtrekksvalg.value}>
              {nedtrekksvalg.label}
            </option>
          ))}
        </select>
      </div>
    </React.Fragment>
  );
}
