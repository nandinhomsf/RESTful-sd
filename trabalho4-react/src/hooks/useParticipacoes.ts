import {useQuery} from "@tanstack/react-query";
import Participacao from "../interfaces/Participacao.ts";
import axios from "axios";

const useParticipacoes = (slug: string | undefined = undefined) => {
    let url = "http://localhost:8080/participacoes";
    const queryKey = ['participacoes'];
    if (slug) {
        url += `/usuario/${slug}`;
        queryKey.push(slug);
    }
    return useQuery({
        queryKey: queryKey,
        queryFn: () => axios
            .get<Participacao[]>(url)
            .then((res) => res.data),
        staleTime: 10_000, // default zero
    })
}

const useParticipacao = (id: string) => {
    return useQuery({
        queryKey: ['participacoes', id],
        queryFn: () => axios
            .get<Participacao>(`http://localhost:8080/participacoes/${id}`)
            .then((res) => res.data),
        staleTime: 10_000, // default zero
    })
}

export {useParticipacoes, useParticipacao}