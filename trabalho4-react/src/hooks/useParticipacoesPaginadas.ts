import { useQuery } from "@tanstack/react-query";
import ApiParticipacao from "../api/apiParticipacao.ts";
import QueryStringParticipacao from "../interfaces/queryStringParticipacao.ts";

const apiParticipacao = new ApiParticipacao();

const useParticipacoesPaginadas = (query: QueryStringParticipacao) =>
  useQuery({
    queryKey: ["participacoes", "paginacao", query],
    queryFn: () => apiParticipacao.recuperarPagina({
        params: {
          pagina: query.pagina,
          tamanho: query.tamanho,
          nome: query.nome,
        },
      }),
      staleTime: 10_000, // default zero
      keepPreviousData: true
  });
export default useParticipacoesPaginadas;

// http://localhost:8080/produtos/paginacao?pagina=0&tamanho=5
// const useProdutosPaginados = (pagina: number, tamanho: number) => useQuery({
//   queryKey: ['produtos', 'paginacao', pagina, tamanho],
//   queryFn: () => axios
//     .get<ProdutosPaginados>("http://localhost:8080/produtos/paginacao?pagina=" + pagina + "&tamanho=" + tamanho)
//     .then((res) => res.data),
//     staleTime: 10_000, // default zero
// })
// export default useProdutosPaginados;
