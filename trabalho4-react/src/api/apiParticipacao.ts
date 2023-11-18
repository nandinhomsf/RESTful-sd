import Participacao from "../interfaces/Participacao";
import ApiGenerica from "./apiGenerica";

class ApiParticipacao extends ApiGenerica<Participacao> {
  constructor() {
    super("/participacoes");
  }
}

export default ApiParticipacao;