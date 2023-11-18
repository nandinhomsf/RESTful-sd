import Usuario from "./Usuario";

interface Participacao {
    id?: number;
    imagem: string;
    usuario: Usuario;
    nome: string;
    xp: number;
    credito: number;
    dataCadastro: Date;
};
export default Participacao;