import CadastroDeParticipacoesForm from "../components/CadastroDeParticipacoesForm.tsx";
import {useState} from "react";
import useRemoverParticipacao from "../hooks/useRemoverParticipacao.ts";
import useParticipacoesPaginadas from "../hooks/useParticipacoesPaginadas.ts";
import Pesquisa from "../components/Pesquisa.tsx";
import TabelaDeParticipacoes from "../components/TabelaDeParticipacoes.tsx";
import Paginacao from "../components/Paginacao.tsx";
import useAlterarParticipacao from "../hooks/useAlterarParticipacao.ts";


function CadastrarParticipacao() {

    const tamanho = 5;
    const [pagina, setPagina] = useState(0);
    const [nome, setNome] = useState("");

    const removerParticipacao = useRemoverParticipacao();
    const alterarParticipacao = useAlterarParticipacao();

    const handleAlteraParticipacao = (id: number) => {
        const participacao = participacoes.find(p => p.id === id);
        if (participacao) {
            alterarParticipacao.mutate(participacao);
            setPagina(0);
        }
    }
    const handleRemovedParticipacao = (id: number) => {
        removerParticipacao.mutate(id);
        setPagina(0);
    }
    const handleRetrievedNome = (nome: string) => {
        setNome(nome);
        setPagina(0);
    };
    const handleSelectedPage = (pagina: number) => setPagina(pagina);

    const {
        data: resultadoPaginado,
        isLoading,
        error,
    } = useParticipacoesPaginadas({pagina, tamanho, nome});

    if (isLoading) return <h6>Carregando...</h6>;
    // if (removerProduto.isLoading) return <h6>Removendo...</h6>;

    if (error || !resultadoPaginado) throw error;
    if (removerParticipacao.error) throw removerParticipacao.error;

    const participacoes = resultadoPaginado.itens;
    const totalDePaginas = resultadoPaginado.totalDePaginas;
    // const totalDeProdutos = resultadoPaginado.totalDeItens;
    // const paginaCorrente = resultadoPaginado.paginaCorrente;

    return (
        <>
            <div className="mb-4">
                <h5>Cadastro de Participacoes</h5>
                <hr className="mt-0"/>
            </div>

            <CadastroDeParticipacoesForm/>

            <div className="mb-4">
                <h5>Lista de Participacoes</h5>
                <hr className="mt-0"/>
            </div>
            <Pesquisa nome={nome} onRetrieveNome={handleRetrievedNome}/>
            <TabelaDeParticipacoes onRemoveParticipacao={handleRemovedParticipacao} participacoes={participacoes}
                                   onAlteraParticipacao={handleAlteraParticipacao}/>
            <Paginacao
                pagina={pagina}
                totalDePaginas={totalDePaginas}
                onSelectPage={handleSelectedPage}
            />

        </>
    );
}

export default CadastrarParticipacao;
