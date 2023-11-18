import Moment from "moment/moment";
import Participacao from "../interfaces/Participacao.ts";
import {Link} from "react-router-dom";

interface Props {
    participacao: Participacao;
}

const ParticipacaoCard = ({participacao}: Props) => {
    return (

        <div className="card" key={participacao.id}>
            <div className="row g-0">
                <div className="col-md-4">
                    <img src={"/" + participacao.imagem} className="img-fluid rounded-start" alt="..."/>
                </div>
                <div className="col-md-8">
                    <div className="card-body">
                        <h5 className="card-title">
                            <Link to={"/participacao/" + participacao.id}>{participacao.nome}</Link>
                        </h5>
                        <p className="card-text">
                            XP: {participacao.xp}
                            <br/>
                            Creditos: {participacao.credito}
                            <br/>
                            <small className="text-muted">
                                Usuario: {participacao.usuario.nome}
                                <br/>
                                Data de Cadastro: {Moment(participacao.dataCadastro).format("DD/MM/YYYY")}

                            </small>
                        </p>

                    </div>

                </div>
            </div>

        </div>

    )
}

export default ParticipacaoCard;