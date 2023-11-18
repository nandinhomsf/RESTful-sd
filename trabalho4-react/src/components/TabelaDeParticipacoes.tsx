import moment from "moment";
import Participacao from "../interfaces/Participacao";
import { Link } from "react-router-dom";

interface Props {
    participacoes: Participacao[];
    onRemoveParticipacao: (id: number) => void;
    onAlteraParticipacao: (id: number) => void;
}

const TabelaDeParticipacoes = ({participacoes, onRemoveParticipacao, onAlteraParticipacao}: Props) => {
    return (
        <table className="table table-responsive table-bordered table-hover table-sm">
            <thead>
            <tr>
                <th className="align-middle text-center">Id</th>
                <th className="align-middle text-center">Imagem</th>
                <th className="align-middle text-center">Usuario</th>
                <th className="align-middle text-center">Nome</th>
                <th className="align-middle text-center">Data de cadastro</th>
                <th className="align-middle text-center">Xp</th>
                <th className="align-middle text-center">Creditos</th>
                <th className="align-middle text-center">Ação</th>
            </tr>
            </thead>
            <tbody>
            {participacoes.map((participacao) => (
                <tr key={participacao.id}>
                    <td width="5%" className="align-middle text-center">
                        {participacao.id}
                    </td>
                    <td width="10%" className="align-middle text-center">
                        <img src={participacao.imagem} style={{width: "25px"}}/>
                    </td>
                    <td width="10%" className="align-middle text-center">
                        {participacao.usuario.nome}
                    </td>
                    <td width="20%" className="align-middle text-center">
                        {participacao.nome}
                    </td>
                    <td width="15%" className="align-middle text-center">
                        {moment(participacao.dataCadastro).format("DD/MM/YYYY")}
                    </td>
                    <td width="10%" className="align-middle text-center">
                        {participacao.xp}
                    </td>
                    <td width="10%" className="align-middle text-center">
                        {participacao.credito}
                    </td>
                    <td width="20%" className="align-middle text-center">
                        <Link to={"/alterar-participacao/" + participacao.id}>
                        <button
                            onClick={() => onAlteraParticipacao(participacao.id!)

                        }
                            className="btn btn-warning btn-sm" style={{ marginRight: '10px' }}>

                                Alterar
                        </button>
                        </Link>
                        <button
                            onClick={() => onRemoveParticipacao(participacao.id!)}
                            className="btn btn-danger btn-sm"
                        >
                            Remover
                        </button>

                    </td>
                </tr>
            ))}
            </tbody>
        </table>
    );
};

export default TabelaDeParticipacoes;
