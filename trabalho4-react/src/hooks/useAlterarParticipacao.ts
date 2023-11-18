import { useMutation, useQueryClient } from "@tanstack/react-query";
import ApiParticipacao from "../api/apiParticipacao.ts";

const apiParticipacao = new ApiParticipacao();

const useAlterarParticipacao = () => {
  const queryClient = useQueryClient();
  return useMutation(apiParticipacao.alterar, {
    onSuccess: () => {
      queryClient.invalidateQueries({
        queryKey: ['participacoes']
      })
    }
  });
};

export default useAlterarParticipacao;