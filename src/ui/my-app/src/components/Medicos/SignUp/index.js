import React from "react";
import { Formik } from "formik";
import validate from "./validate-spected";
import getValidationSchema from "./getValidationSchema-spected";
import "./SignUpForm.css";
import axios from "axios";

const initialValues = {
  nomeCompleto: "",
  telefone: "",
  celular: "",
  crm: "",
  especialidades: [
    {
      nomeEspecialidade: "Alergologia",
      idEspecialidade: "1",
      isChecked: false,
    },
    { nomeEspecialidade: "Angiologia", idEspecialidade: "2", isChecked: false },
    {
      nomeEspecialidade: "Buco Maxilo",
      idEspecialidade: "3",
      isChecked: false,
    },
    {
      nomeEspecialidade: "Cardiologia Clínica",
      idEspecialidade: "4",
      isChecked: false,
    },
    {
      nomeEspecialidade: "Cardiologia Infantil",
      idEspecialidade: "5",
      isChecked: false,
    },
    {
      nomeEspecialidade: "Cirurgia Cabeça e Pescoço",
      idEspecialidade: "6",
      isChecked: false,
    },
    {
      nomeEspecialidade: "Cirurgia Cardíaca",
      idEspecialidade: "7",
      isChecked: false,
    },
    {
      nomeEspecialidade: "Cirurgia de Tórax",
      idEspecialidade: "8",
      isChecked: false,
    },
  ],
  endereco: {
    cep: "",
    estado: "",
    cidade: "",
    bairro: "",
    rua: "",
    numero: "",
  },
};

let valueIsSetted = false;

const checkBoxValues = initialValues.especialidades;

export default function SignUpFormContainer() {
  return (
    <Formik
      initialValues={initialValues}
      checkBoxValues={checkBoxValues}
      validate={validate(getValidationSchema)}
      onSubmit={onSubmit}
      render={SignUpForm}
    />
  );
}

function setIsChecked(e) {

  if (e.target.checked) {
    checkBoxValues[e.target.value - 1].isChecked = true;
  } else {
    checkBoxValues[e.target.value - 1] = false;
  }

}

async function onSubmit(values, { setSubmitting, setErrors }) {
  let especialidades = [];

  checkBoxValues.forEach((element, index) => {
    if (element.isChecked === true) {
      let especialidade = {
        idEspecialidade: index + 1,
      };

      especialidades.push(especialidade);
    }
  });


  values.especialidades = especialidades;

  axios
    .post(`http://localhost:8080/api/medico`, { values })
    .then((res) => {
      setSubmitting = false;
      console.log(res);
      console.log(res.data);
    })
    .catch(function (error) {
      if (error.response) {
        // Request made and server responded
        console.log(error.response.data);
        console.log(error.response.status);
        console.log(error.response.headers);
      } else if (error.request) {
        // The request was made but no response was received
        console.log(error.request);
      } else {
        // Something happened in setting up the request that triggered an Error
        console.log("Error", error.message);
      }
    });
}

function SignUpForm(props) {
  const { isSubmitting, errors, handleChange, handleSubmit } = props;

  return (
    <div className="form">
      <div className="divEsquerda">
        <label className="form-field" htmlFor="nomeCompleto">
          <span>Nome completo:</span>
          <input name="nomeCompleto" type="text" onChange={handleChange} />
        </label>
        <div className="form-field-error">{errors.nomeCompleto}</div>

        <label className="form-field" htmlFor="telefone">
          <span>Telefone:</span>
          <input name="telefone" type="tel" onChange={handleChange} />
        </label>
        <div className="form-field-error">{errors.telefone}</div>

        <label className="form-field" htmlFor="celular">
          <span>Celular:</span>
          <input name="celular" type="tel" onChange={handleChange} />
        </label>
        <div className="form-field-error">{errors.celular}</div>

        <label className="form-field" htmlFor="crm">
          <span>CRM:</span>
          <input name="crm" type="text" onChange={handleChange} />
        </label>
        <div className="form-field-error">{errors.crm}</div>

        <label className="form-field" htmlFor="especialidades">
          <span>Especialidades</span>
          <ul>
            {initialValues.especialidades.map((especialidade, index) => {
              return (
                <li>
                  <input
                    name={especialidade.nomeEspecialidade}
                    
                    type="checkbox"
                    onChange={setIsChecked}
                    value={especialidade.idEspecialidade}
                  />{" "}
                  {especialidade.nomeEspecialidade}
                </li>
              );
            })}
          </ul>
        </label>
      </div>
      <div className="divDireita"></div>

      <button className="btn btn-primary bt-validar" onClick={handleSubmit}>
        {isSubmitting ? "Loading" : "Sign Up"}
      </button>
    </div>
  );
}
