import React from "react";
import { UseFormRegister } from "react-hook-form";

type TextInputProps = {
  labelText: string;
  labelNoWrap?: boolean;
  fieldName: string;
  placeholderText?: string;
  id?: string;
  required?: boolean;
  pattern?: RegExp;
  hasError?: boolean;
  register: UseFormRegister<any>;
};

export default function TextInput({
  labelText,
  labelNoWrap = false,
  fieldName,
  placeholderText,
  id = "text-input-" + Math.floor(Math.random() * 1000000 + 1) + "-" + fieldName,
  required = false,
  pattern,
  hasError,
  register,
}: TextInputProps) {
  return (
    <div className={"text-input" + (hasError ? " has-error" : "")}>
      <label className={"label" + (required ? " required" : "") + (labelNoWrap ? " no-wrap" : "")} htmlFor={id}>
        {labelText}
      </label>
      <input type="text" id={id} placeholder={placeholderText} {...register(fieldName, { required: required, pattern: pattern })} />
    </div>
  );
}
