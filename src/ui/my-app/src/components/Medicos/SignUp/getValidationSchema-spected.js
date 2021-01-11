export default function getSpectedValidationSchema(values) {
  return {
    nomeCompleto : [
      [(value) => !isEmpty(value), "O nome deve ser preenchido."],
      [(value) => value.length < 120, "O número máximo de caracteres permitidos é de 120."],
    ],
    telefone: [
      [(value) => !isEmpty(value), "O telefone deve ser preenchido."],
      [
        (value) => value.length === 10,
        `O telefone deve conter 10 caraceteres, verifique-o.`,
      ],
    ],
    celular: [
      [(value) => !isEmpty(value), "O celular deve ser preenchido."],
      [
        (value) => value.length === 10 || value.length === 11,
        `O celular deve conter 10 ou 11 caraceteres, verifique-o.`,
      ],
    ],
    crm: [
      [(value) => !isEmpty(value), "O CRM deve ser preenchido."],
      [(value) => value.length == 7, "O crm deve conter 7 caraceteres, verifique-o.",
      ],
    ],
    cep: [
      [(value) => !isEmpty(value), "O CEP é necessário para o cadastro."],
      [(value) => value.length === 8, "O CEP deve conter 8 digítos"],
    ],
    numero: [
      [
        (value) => !isEmpty(value), "O numero do endereço é necessário para o cadastro."
      ],
    ]
  };
}

function isEmpty(value) {
  if(value == null || value === ''){
    return false;
  }
}

