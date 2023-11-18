import axios, { AxiosRequestConfig } from "axios";
import ResultadoPaginado from "../interfaces/resultadoPaginado";

class ApiGenerica<T> {
    static axiosInstance = axios.create({
        baseURL: "http://localhost:8080",
    })
    endpoint: string;
    constructor(endpoint: string) {
        this.endpoint = endpoint;
    }

    remover = (id: number) => 
      ApiGenerica.axiosInstance
        .delete(this.endpoint + "/" + id)
        .then((res) => res.data)

    cadastrar = (obj: T) => 
      ApiGenerica.axiosInstance
        .post<T>(this.endpoint, obj)
        .then((res) => res.data)
    
    recuperarPagina = (config: AxiosRequestConfig) =>   
      ApiGenerica.axiosInstance
        .get<ResultadoPaginado<T>>(this.endpoint + "/paginacao", config)
        .then((res) => res.data);    
     
    recuperarTodos = () =>   
      ApiGenerica.axiosInstance
        .get<T[]>(this.endpoint)
        .then((res) => res.data);

    alterar = (obj: T) =>
        ApiGenerica.axiosInstance
            .put<T>(this.endpoint, obj)
            .then((res) => res.data);
}

export default ApiGenerica;