import {useParticipacoes} from "../hooks/useParticipacoes.ts";
import {Link, useParams} from "react-router-dom";
import useUsuarios from "../hooks/useUsuarios.ts";
import ParticipacaoCard from "../components/ParticipacaoCard.tsx";

const HomePage = () => {
    const {slug} = useParams();
    const {data: participacoes, isLoading, error} = useParticipacoes(slug);
    const {data: usuarios, isLoading: isLoading2, error: error2} = useUsuarios();


    if (isLoading || isLoading2) return <h6>Carregando...</h6>;


    // !produtos para que não seja preciso usar optional chaining abaixo.
    if (error || !participacoes) throw error; // para que a página de erro do router exiba o erro.
    if (error2 || !usuarios) throw error2; // para que a página de erro do router exiba o erro.

    return (
        <>
            <div className="container-fluid">
                <div className="row">
                    <div className="col-3">
                        <div className="mb-4">
                            <h5>Usuarios</h5>
                            <hr className="mt-0"/>
                        </div>
                        <ul className="list-group">
                            <li className="list-group-item" key={0}>
                                <h4>
                                    <Link to={"/"}>Todos</Link>
                                </h4>
                            </li>
                            {usuarios.map((usuario) => (
                                <li className="list-group-item" key={usuario.id}>
                                    <h4>
                                        <Link to={"/" + usuario.slug}>{usuario.nome}</Link>
                                    </h4>
                                </li>
                            ))}
                        </ul>
                    </div>
                    <div className="col-9">
                        <div className="mb-4">
                            <h5>Cards de Participacoes</h5>
                            <hr className="mt-0"/>
                        </div>
                        {participacoes.map((participacao) => (
                            <ParticipacaoCard participacao= {participacao} />
                        ))}
                    </div>
                </div>
            </div>
        </>
    );
}

export default HomePage;
