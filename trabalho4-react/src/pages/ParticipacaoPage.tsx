import { useParams } from "react-router-dom";
import {useParticipacao} from "../hooks/useParticipacoes";
import ParticipacaoCard from "../components/ParticipacaoCard";


function ParticipacaoPage() {
  const { id } = useParams();
  const { data: participacao, isLoading, error } = useParticipacao(id!);

  if (isLoading) return <h6>Carregando...</h6>;

  // !participacao para que não seja preciso usar optional chaining abaixo.
  if (error || !participacao) throw error; // para que a página de erro do router exiba o erro.

  return (
    <>
      <div className="container-fluid">


      <div className="mb-4">
        <h5>Card da Participacao</h5>
        <hr className="mt-0" />
      </div>
        <ParticipacaoCard participacao= {participacao} />
          <br/>
          <div className="d-flex justify-content-end">
                    <button className="btn btn-warning" style={{ marginRight: '10px' }}>Alterar</button>
                    <button className="btn btn-danger"
                    >Excluir</button>

          </div>

      </div>

    </>
  );
}
export default ParticipacaoPage;
