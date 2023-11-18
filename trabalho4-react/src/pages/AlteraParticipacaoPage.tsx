import  { useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { useParticipacao } from '../hooks/useParticipacoes';
import useAlterarParticipacao from '../hooks/useAlterarParticipacao';
import useUsuarios from '../hooks/useUsuarios';
import { zodResolver } from '@hookform/resolvers/zod';
import { FieldValues, useForm } from 'react-hook-form';
import { z } from 'zod';
import Participacao from '../interfaces/Participacao';

const regexImagem = /^[a-z]+\.(jpg|png|gif|bmp)$/;
const regexData = /^[0-9]{2}\/[0-9]{2}\/[0-9]{4}$/;
const schema = z.object({
    nome: z
        .string()
        .min(1, { message: 'O nome deve ser informado.' })
        .min(3, { message: 'O nome deve conter pelo menos 3 caracteres.' }),
    usuario: z.string().nonempty({ message: 'O usuario deve ser informado.' }),
    data_cadastro: z
        .string()
        .min(1, { message: 'A data de cadastro deve ser informada.' })
        .regex(regexData, { message: 'Data inválida.' }),
    xp: z.number({ invalid_type_error: 'O xp deve ser informado. ' }).min(1, { message: 'O xp mínimo é 1.' }),
    credito: z.number({ invalid_type_error: 'O credito deve ser informado. ' }).min(5, { message: 'O credito mínimo é 5.' }),
    imagem: z
        .string()
        .min(1, { message: 'A imagem deve ser informada.' })
        .regex(regexImagem, { message: 'Nome de imagem inválido.' }),
});
type FormData = z.infer<typeof schema>;

const AlteraParticipacaoPage = () => {
    const { id } = useParams();
    const { data: participacao, isLoading, error } = useParticipacao(id!);
    const { data: usuarios, error: errorUsuario } = useUsuarios();
    const alterarParticipacao = useAlterarParticipacao();

    const {
        register,
        handleSubmit,
        reset,
        formState: { errors },
        setValue,
    } = useForm<FormData>({ resolver: zodResolver(schema) });

    useEffect(() => {
        if (participacao) {
            // Popula os campos do formulário com os dados da participação existente
            setValue('nome', participacao.nome);
            setValue('usuario', participacao.usuario.id.toString());
            // Adicione outros campos conforme necessário
        }
    }, [participacao, setValue]);

    const onSubmit = ({
                          nome,
                          usuario,
                          data_cadastro,
                          xp,
                          credito,
                          imagem,
                      }: FieldValues) => {
        const participacaoAtualizada: Participacao = {
            ...participacao!,
            nome: nome,
            usuario: { id: Number(usuario), nome: '' },
            dataCadastro: new Date(
                data_cadastro.substring(6, 10) +
                '-' +
                data_cadastro.substring(3, 5) +
                '-' +
                data_cadastro.substring(0, 2)
            ),
            xp: xp,
            credito: credito,
            imagem: imagem,
        };

        reset(); // Para limpar as caixas de texto do form
        alterarParticipacao.mutate(participacaoAtualizada);
    };

    if (isLoading) return <h6>Carregando...</h6>;
    if (error || !participacao) throw error;
    if (errorUsuario) throw errorUsuario;
    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <div className="row">
                <div className="col-xl-6">
                    <div className="row mb-2">
                        <label htmlFor="nome" className="col-xl-2 fw-bold">
                            Nome
                        </label>
                        <div className="col-xl-10">
                            <input
                                {...register("nome")}
                                type="text"
                                id="nome"
                                className={
                                    errors.nome
                                        ? "form-control form-control-sm is-invalid"
                                        : "form-control form-control-sm"
                                }
                            />
                            <div className="invalid-feedback">{errors.nome?.message}</div>
                            {/*
              {errors.nome && <p className="text-danger">{errors.nome.message}</p>}
              */}
                        </div>
                    </div>
                </div>
            </div>
            <div className="row mb-1">
                <div className="col-xl-6">
                    <div className="row mb-2">
                        <label htmlFor="usuario" className="col-xl-2 fw-bold">
                            Usuario
                        </label>
                        <div className="col-xl-10">
                            <select
                                {...register("usuario")}
                                className={
                                    errors.usuario
                                        ? "form-control form-control-sm is-invalid"
                                        : "form-control form-control-sm"
                                }
                            >
                                <option value="">
                                    Selecione um usuario
                                </option>
                                {usuarios?.map((usuario, index) => (
                                    <option key={index} value={usuario.id}>
                                        {usuario.nome}
                                    </option>
                                ))}
                            </select>
                            <div className="invalid-feedback">
                                {errors.usuario?.message}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="col-xl-6">
                    <div className="row mb-2">
                        <label htmlFor="data_cadastro" className="col-xl-3 fw-bold">
                            Data Cadastro
                        </label>
                        <div className="col-xl-9">
                            <input
                                {...register("data_cadastro")}
                                type="text"
                                id="data_cadastro"
                                className={
                                    errors.data_cadastro
                                        ? "form-control form-control-sm is-invalid"
                                        : "form-control form-control-sm"
                                }
                            />
                            <div className="invalid-feedback">
                                {errors.data_cadastro?.message}
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div className="row mb-1">
                <div className="col-xl-6">
                    <div className="row mb-2">
                        <label htmlFor="xp" className="col-xl-2 fw-bold">
                            Xp
                        </label>
                        <div className="col-xl-10">
                            <input
                                {...register("xp", { valueAsNumber: true })}
                                type="number"
                                step="0.01"
                                min="1"
                                id="xp"
                                className={
                                    errors.xp
                                        ? "form-control form-control-sm is-invalid"
                                        : "form-control form-control-sm"
                                }
                            />
                            <div className="invalid-feedback">{errors.xp ? errors.xp.message : null}</div>
                        </div>
                    </div>
                </div>
            </div>

            <div className="row mb-1">
                <div className="col-xl-6">
                    <div className="row mb-2">
                        <label htmlFor="credito" className="col-xl-2 fw-bold">
                            credito
                        </label>
                        <div className="col-xl-10">
                            <input
                                {...register("credito", { valueAsNumber: true })}
                                type="number"
                                min="5"
                                id="credito"
                                className={
                                    errors.credito
                                        ? "form-control form-control-sm is-invalid"
                                        : "form-control form-control-sm"
                                }
                            />
                            <div className="invalid-feedback">{errors.credito ? errors.credito.message : null}</div>
                        </div>
                    </div>
                </div>
            </div>

            <div className="row mb-1">
                <div className="col-xl-6">
                    <div className="row mb-2">
                        <label htmlFor="imagem" className="col-xl-2 fw-bold">
                            Imagem
                        </label>
                        <div className="col-xl-10">
                            <input
                                {...register("imagem")}
                                type="text"
                                id="imagem"
                                className={
                                    errors.imagem
                                        ? "form-control form-control-sm is-invalid"
                                        : "form-control form-control-sm"
                                }
                            />
                            <div className="invalid-feedback">{errors.imagem?.message}</div>
                        </div>
                    </div>
                </div>
            </div>


            <div className="row mb-5">
                <div className="col-xl-6">
                    <div className="row">
                        <div className="col-xl-10 offset-xl-2">
                            <button id="botao" type="submit" className="btn btn-primary btn-sm">
                                <img src="/skin/database_add.png" /> Alterar
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    );
};

export default AlteraParticipacaoPage;
