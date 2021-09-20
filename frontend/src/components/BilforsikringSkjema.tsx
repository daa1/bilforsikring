import TextInput from "./TextInput";
import SelectInput from "./SelectInput";
import React from "react";
import { useForm } from "react-hook-form";
import { BilforsikringData } from "../model/BilforsikringData";

type BilforsikringSkjemaProps = {
  bestillCallback: (data: BilforsikringData) => void;
};

export default function BilforsikringSkjema({ bestillCallback }: BilforsikringSkjemaProps) {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<BilforsikringData>();

  const onSubmit = handleSubmit((data) => bestillCallback(data));

  return (
    <form onSubmit={onSubmit} className="forsikring-form">
      <div className="form-row">
        <div className="col">
          <TextInput
            labelText={"Bilens registreringsnummer"}
            labelNoWrap={true}
            fieldName={"registreringsnummer"}
            placeholderText={"E.g AB12345"}
            id={"bilforsikring-regnr"}
            required={true}
            pattern={/^[A-Z,a-z]{2}[1-9]{1}\d{4}$/i}
            hasError={errors.registreringsnummer !== undefined}
            register={register}
          />
          {errors.registreringsnummer && (
            <p className="validation-error" role="alert">
              {errors.registreringsnummer?.type === "required" && "Registreringsnummer er påkrevd"}
              {errors.registreringsnummer?.type === "pattern" && "Må være på form XX00000"}
            </p>
          )}
        </div>
      </div>

      <div className="form-row">
        <div className="col">
          <SelectInput
            label={"Din bonus"}
            required={true}
            fieldName={"bonus"}
            options={[
              { label: "Velg bonus", value: "" },
              { label: "10%", value: "10" },
              { label: "20%", value: "20" },
              { label: "30%", value: "30" },
            ]}
            hasError={errors.etternavn !== undefined}
            register={register}
          />
          {errors.bonus && (
            <p className="validation-error" role="alert">
              {errors.bonus?.type === "required" && "Velg bonus"}
            </p>
          )}
        </div>
      </div>

      <div className="form-row">
        <div className="col">
          <TextInput
            labelText={"Fødselsnummer"}
            fieldName={"fodselsnummer"}
            placeholderText={"11 siffer"}
            id={"bilforsikring-fnr"}
            required={true}
            hasError={errors.fodselsnummer !== undefined}
            register={register}
          />
          {errors.fodselsnummer?.type === "required" && <p className="validation-error">Fødselsnummer er påkrevd</p>}
        </div>
      </div>

      <div className="form-row">
        <div className="col">
          <TextInput
            labelText={"Fornavn"}
            fieldName={"fornavn"}
            id={"bilforsikring-fornavn"}
            required={true}
            hasError={errors.fornavn !== undefined}
            register={register}
          />
          {errors.fornavn?.type === "required" && <p className="validation-error">Fornavn er påkrevd</p>}
        </div>

        <div className="col">
          <TextInput
            labelText={"Etternavn"}
            fieldName={"etternavn"}
            id={"bilforsikring-etternavn"}
            required={true}
            hasError={errors.etternavn !== undefined}
            register={register}
          />
          {errors.etternavn?.type === "required" && <p className="validation-error">Etternavn er påkrevd</p>}
        </div>
      </div>

      <div className="form-row">
        <div className="col">
          <TextInput
            labelText={"E-post"}
            fieldName={"epost"}
            placeholderText={"mail@mail.no"}
            id={"bilforsikring-epost"}
            required={true}
            pattern={/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/i}
            hasError={errors.epost !== undefined}
            register={register}
          />
          {errors.epost && (
            <p className="validation-error" role="alert">
              {errors.epost?.type === "required" && "E-post er påkrevd"}
              {errors.epost?.type === "pattern" && "Skriv en gyldig e-postadresse"}
            </p>
          )}
        </div>
      </div>

      <div className="margin-top">
        <button type="submit" className="btn-primary margin-right">
          Kjøp
        </button>
        <button type="button" className="btn-secondary" onClick={() => reset()}>
          Avbryt
        </button>
      </div>
    </form>
  );
}
