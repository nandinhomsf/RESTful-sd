import { useMutation, useQueryClient } from "@tanstack/react-query";
import ApiParticipacao from "../api/apiParticipacao.ts";

const apiParticipacao = new ApiParticipacao();

const useCadastrarParticipacao= () => {
    const queryClient = useQueryClient();
    return useMutation({
      mutationFn: apiParticipacao.cadastrar,
      onSuccess: () => {
        queryClient.invalidateQueries({
          queryKey: ['participacoes']
        })
      }  
    })
}

export default useCadastrarParticipacao;