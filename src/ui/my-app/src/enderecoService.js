import React from 'react';
import axios from 'axios';







export default async function EnderecoService(cep){

  let resultado;

  await axios.get("https://brasilapi.com.br/api/cep/v1/" + cep).then(res => {
        
      resultado = res.data;
      return resultado;
    
  })
  




}
