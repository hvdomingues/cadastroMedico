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
    { nomeEspecialidade: "", idEspecialidade: ""
    }
  ],
  endereco: {
    cep: "",
    estado: "",
    cidade: "",
    bairro: "",
    rua: "",
    numero: "",
  },

  email: "",
  password: "",
  passwordConfirmation: "",
  consent: false,
};

export default function SignUpFormContainer() {
  return (
    <Formik
      initialValues={initialValues}
      validate={validate(getValidationSchema)}
      onSubmit={onSubmit}
      render={SignUpForm}
    />
  );
}

async function onSubmit(values, { setSubmitting, setErrors }) {
  
  axios.post(`http://localhost:8080/api/medico`, { values })
      .then(res => {
        setSubmitting = false;
        console.log(res);
        console.log(res.data);
      }).catch(function (error){

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
          console.log('Error', error.message);
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
            {initialValues.especialidades.map((especialidade) => {
              return (
                <li>
                  <input
                    key={especialidade.especialidadeId}
                    onChange={handleChange}
                    type="checkbox"
                    checked={especialidade.isChecked}
                    value={especialidade.nomeEspecialidade}
                  />{" "}
                  {especialidade.nomeEspecialidade}
                </li>
              );
            })}
          </ul>
        </label>
        <div className="form-field-error">{errors.especialidades}</div>
      </div>
      <div className="divDireita">



      </div>


      <button className="btn btn-primary bt-validar" onClick={handleSubmit}>
        {isSubmitting ? "Loading" : "Sign Up"}
      </button>
    </div>
  );
}

