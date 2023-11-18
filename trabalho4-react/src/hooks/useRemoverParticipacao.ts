import { useMutation, useQueryClient } from "@tanstack/react-query";
import ApiParticipacao from "../api/apiParticipacao.ts";

const apiParticipacao = new ApiParticipacao();

const useRemoverParticipacao= () => {
    const queryClient = useQueryClient();
    return useMutation({
      mutationFn: apiParticipacao.remover,
      onSuccess: () => {
        queryClient.invalidateQueries({
          queryKey: ['participacoes']
        })
      }  
    })
}

export default useRemoverParticipacao;