import React, { Component } from "react";

import Header from "./components/Header/index";
import Footer from "./components/Footer/index";

import 'bootstrap/dist/css/bootstrap.min.css';

import SignUpFormContainer from "./components/Medicos/SignUp/index";

import "./App.css";

class App extends Component {
  render() {
    return (
        <div className="App">
          <Header />

          <main className="content">
            <SignUpFormContainer />
          </main>

          <Footer />
        </div>
    );
  }
}

export default App;