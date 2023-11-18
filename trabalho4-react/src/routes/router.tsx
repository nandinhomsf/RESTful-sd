import { createBrowserRouter } from "react-router-dom";
import CadastrarParticipacaoPage from "../pages/CadastrarParticipacaoPage";
import CardsDeParticipacoesPage from "../pages/CardsDeParticipacoesPage";
import ErrorPage from "../pages/ErrorPage";
import HomePage from "../pages/HomePage";
import ParticipacaoPage from "../pages/ParticipacaoPage";
import Layout from "./Layout";
import ListaDeParticipacoesPage from "../pages/ListaDeParticipacoesPage.tsx";
import AlteraParticipacaoPage from "../pages/AlteraParticipacaoPage.tsx";


const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    // define uma página de erro que será exibida automaticamente
    // no caso de erro. 
    // É preciso incluir a NavBar dentro dela pois ela não é filho
    // de layout onde se encontra a navbar.
    errorElement: <ErrorPage />,
    children: [
      { path: "", 
        element: <HomePage />,
        children: [
          { path: "", element: <CardsDeParticipacoesPage /> },
          { path: ":slug", element: <CardsDeParticipacoesPage /> },
        ]
      },
      { path: "cadastrar-participacao", element: <CadastrarParticipacaoPage /> },
      { path: "listar-participacoes", element: <ListaDeParticipacoesPage /> },
      { path: "participacao/:id", element: <ParticipacaoPage /> },
      { path: "alterar-participacao/:id", element: <AlteraParticipacaoPage />},
    ],
  },
]);
export default router;
