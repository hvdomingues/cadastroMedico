import Yup from 'yup'

export default function getYupValidationSchema(values) {
  return Yup.object().shape({
    nomeCompleto: Yup.string()
      .max(120, 'O número máximo de caracteres permitidos é de 120.')
      .required('O nome deve ser preenchido.'),
    telefone: Yup.string()
      .max(10, 'O telefone deve conter 10 caraceteres, verifique-o.')
      .min(10, 'O telefone deve conter 10 caraceteres, verifique-o.')
      .required('O telefone deve ser preenchido.'),
    celular: Yup.string()
      .max(11, 'O celular deve conter 10 ou 11 caraceteres, verifique-o.')
      .min(10, 'O celular deve conter 10 ou 11 caraceteres, verifique-o.')
      .required('O celular deve ser preenchido.'),
    crm: Yup.string()
      .max(7, 'O crm deve conter 7 caraceteres, verifique-o.')
      .min(7, 'O crm deve conter 7 caraceteres, verifique-o.')
      .required('O CRM deve ser preenchido.'),
    cep: Yup.string()
      .max(7, "O CEP deve conter 8 digítos")
      .min(7, "O CEP deve conter 8 digítos")
      .required('O CEP é necessário para o cadastro.'),
    numero: Yup.string()
      .required('O numero do endereço é necessário para o cadastro.'),
  })
}