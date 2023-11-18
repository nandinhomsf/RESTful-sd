import { Link } from "react-router-dom";
import lol from "/lol.jpg";

function NavBar() {
  return (
    <>
      <div className="container mt-3 mb-2">
        <div className="row">
          <div className="col-3 d-flex align-items-center">
            <Link to="/" style={{ textDecoration: "none", fontSize: "16px" }}>
              <img className="d-none d-md-block" src={lol} style={{ width: "70px" }} />
            </Link>
          </div>
          <div className="col-6">
            <ul style={{ listStyleType: "none" }}>
              <li className="d-flex justify-content-center">
                <Link to="/cadastrar-participacao" style={{ textDecoration: "none" }}>
                  Cadastrar participacao
                </Link>
              </li>
              <li className="d-flex justify-content-center">
                <Link to="/listar-participacoes" style={{ textDecoration: "none" }}>
                  Listar participacoes
                </Link>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div className="bg-danger" style={{ padding: "3px" }}></div>
    </>
  );
}
export default NavBar;
