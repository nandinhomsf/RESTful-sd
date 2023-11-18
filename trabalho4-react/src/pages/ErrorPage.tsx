import { isRouteErrorResponse, useRouteError } from "react-router-dom";
import NavBar from "../components/NavBar";

const ErrorPage = () => {
  const error = useRouteError();

  return (
    <>
      <NavBar />
      <div className="container mt-3">
        <h6>Página de Erro</h6>
        {isRouteErrorResponse(error)
          ? "Endereço de página inválido"
          : error instanceof Error
          ? error.message
          : "Erro desconhecido"}
      </div>
    </>
  );
};

export default ErrorPage;
