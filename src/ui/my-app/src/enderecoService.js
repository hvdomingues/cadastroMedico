import React, { Fragment, useState, useEffect } from 'react';
import axios from 'axios';

function enderecoService(props) {

  const [endereco,setEndereco] = useState(false);
  
  let getEndereco = async(cep) => {
    await axios.get("https://brasilapi.com.br/api/cep/v1/" + cep).then(res => {
      const resultado = res.data;
      setEndereco(resultado);

    })
  }

  useEffect(()=> {
      getEndereco(props.cep);
    })

  return (
    <Fragment>
      <div className="App">
        <header className="App-header">
          <h1>Endereço</h1>

          <ul>
          
            <li>Estado: {endereco.state}</li>
            <li>Cidade: {endereco.city}</li>
            <li>Bairro: {endereco.neighborhood}</li>
            <li>Rua: {endereco.street}</li>


          </ul>
          

        </header>
      </div>
    </Fragment>
  );
}



export default EnderecoService;
