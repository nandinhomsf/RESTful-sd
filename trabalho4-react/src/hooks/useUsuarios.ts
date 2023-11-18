import { useQuery } from "@tanstack/react-query";
import ApiUsuario from "../api/apiUsuario.ts";

const apiUsuario = new ApiUsuario();

const useUsuarios = () =>
  useQuery({
    queryKey: ["usuarios"],
    queryFn: () => apiUsuario.recuperarTodos(),
      staleTime: 24 * 60 * 60 * 1000, // 24h
      keepPreviousData: true
  });
export default useUsuarios;
