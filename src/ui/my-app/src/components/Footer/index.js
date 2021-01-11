import React, { PureComponent } from "react";
import "./footer.css";

class Footer extends PureComponent {
  render() {
    return (
      <footer className="footer">
        <div className="footer-wrapper">
          <img src="" alt="Logo" className="footer-logo" />
          <span className="footer-copy">&copy;&nbsp;2021</span>
          <div className="footer-navbar">
            <a href="/" className="footer-link">
              Termos de uso
            </a>
            <a href="/" className="footer-link">
              Política de privacidade
            </a>
          </div>
        </div>
      </footer>
    );
  }
}

export default Footer;