package fernando;

import fernando.exception.EntidadeNaoEncontradaException;
import fernando.exception.ViolacaoDeConstraintException;
import fernando.modelo.Participacao;
import fernando.modelo.Usuario;
import fernando.exception.ErrorHandler;
import corejava.Console;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


public class ClientMain {

    private static final Logger logger = LoggerFactory.getLogger(ClientMain.class);
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        restTemplate.setErrorHandler(new ErrorHandler());

        logger.info("Iniciando a execução da aplicação cliente.");

        long id;
        int xp, creditos;
        String nome,aux;
        Usuario umUsuario;
        Participacao umaParticipacao;

        boolean continua = true;
        while (continua) {
            System.out.println("\nO que você deseja fazer?");
            System.out.println("\n1. Cadastrar um usuario");
            System.out.println("2. Alterar um usuario");
            System.out.println("3. Remover um usuario");
            System.out.println("4. Recuperar um usuario");
            System.out.println("5. Listar todos os usuarios");
            System.out.println("6. Listar todas participacoes por usuarios");
            System.out.println("7. Cadastrar uma participacao");
            System.out.println("8. Alterar uma participacao");
            System.out.println("9. Remover uma participacao");
            System.out.println("10. Recuperar uma participacao");
            System.out.println("11. Listar todas participacoes");
            System.out.println("12. Sair");


            int opcao = Console.readInt("\nDigite um número entre 1 e 12:");

            switch (opcao) {
                case 1 -> {
                    nome = Console.readLine("\nInforme o nome do usuario: ");
                    xp = Console.readInt("\nInforme o xp: ");
                    creditos = Console.readInt("\nInforme o credito: ");
                    List<Participacao> participacoes = new ArrayList<>();

                    String add = Console.readLine("Deseja adicionar uma participacao? (s/n): ");


                    umUsuario = new Usuario(nome, xp, creditos, new ArrayList<>());
                    try {

                        ResponseEntity<Usuario> res = restTemplate.postForEntity(
                                "http://localhost:8080/usuarios/",
                                umUsuario,
                                Usuario.class
                        );
                        umUsuario = res.getBody();
                        if (umUsuario == null) {
                            System.out.println("\nErro ao cadastrar o usuario!");
                            break;
                        }

                    } catch (ViolacaoDeConstraintException e) {
                        System.out.println(e.getMessage());
                        break;
                    }


                    while (add.equals("s")) {

                        aux = Console.readLine("\nInforme o campeao da participacao: ");
                        umaParticipacao = new Participacao(aux,umUsuario);
                        try {
                            ResponseEntity<Participacao> res = restTemplate.postForEntity(
                                    "http://localhost:8080/participacoes/",
                                    umaParticipacao,
                                    Participacao.class
                            );
                            umaParticipacao = res.getBody();
                            if (umaParticipacao == null) {
                                System.out.println("\nErro ao cadastrar o participacao!");
                                break;
                            }

                        } catch (ViolacaoDeConstraintException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        participacoes.add(umaParticipacao);
                        add = Console.readLine("\nDeseja continuar adicionando mais participacoes? (s/n): ");
                    }
                    umUsuario.setParticipacoes(participacoes);
                    System.out.println("\nUsuario com id " + umUsuario.getId() + " cadastrado com sucesso!");
                    System.out.println(umUsuario);
                }

                case 2 -> {
                    try {
                        umUsuario = recuperarObjeto(
                                "Informe o id do usuario que você deseja alterar: ",
                                "http://localhost:8080/usuarios/{idUsuario}",
                                Usuario.class
                        );
                    } catch (EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println(umUsuario);

                    System.out.println("\nO que você deseja alterar?");
                    System.out.println("\n1. Nome");
                    System.out.println("\n2. Xp");
                    System.out.println("\n3. Creditos");

                    int opcaoAlteracao = Console.readInt("\nDigite o número 1 a 3:");

                    switch (opcaoAlteracao) {
                        case 1 -> {
                            nome = Console.readLine("Digite o novo nome: ");
                            umUsuario.setNome(nome);
                        }
                        case 2 -> {
                            xp = Console.readInt("Digite o novo xp: ");
                            umUsuario.setXp(xp);
                        }
                        case 3 -> {
                            creditos = Console.readInt("Digite o novo credito: ");
                            umUsuario.setCreditos(creditos);
                        }
                        default -> System.out.println("\nOpção inválida");
                    }

                    try {
                        restTemplate.put("http://localhost:8080/usuarios/", umUsuario);

                        System.out.println("\nUsuario de ID " + umUsuario.getId() + " alterado com sucesso!");
                        System.out.println(umUsuario);
                    } catch (ViolacaoDeConstraintException | EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 3 -> {
                    try {
                        umUsuario = recuperarObjeto(
                                "Informe o ID do usuario que você deseja remover: ",
                                "http://localhost:8080/usuarios/{idUsuario}",
                                Usuario.class
                        );
                    }
                    catch (EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println(umUsuario);

                    String resp = Console.readLine("\nConfirma a remoção do usuario?");

                    if (resp.equals("s")) {
                        try {
                            restTemplate.delete("http://localhost:8080/usuarios/{id}", umUsuario.getId());

                            System.out.println("\nUsuario de ID " + umUsuario.getId() + " removido com sucesso!");
                        } catch (EntidadeNaoEncontradaException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Usuario não removido.");
                    }
                }
                case 4 -> {
                    try {
                        umUsuario = recuperarObjeto(
                                "Informe o ID do usuario que você deseja recuperar: ",
                                "http://localhost:8080/usuarios/{idUsuario}", Usuario.class);
                    }
                    catch (EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println(umUsuario);
                }
                case 5 -> {
                    ResponseEntity<Usuario[]> res = restTemplate.exchange(
                            "http://localhost:8080/usuarios/",
                            HttpMethod.GET,
                            null,
                            Usuario[].class
                    );
                    Usuario[] usuarios = res.getBody();

                    if (usuarios != null) {
                        for (Usuario uUsuario : usuarios) {
                            System.out.println(uUsuario);
                        }
                    }
                }
                case 6 -> {
                    id = Console.readInt("\nInforme o ID do Usuario: ");

                    ResponseEntity<Participacao[]> res = restTemplate.exchange(
                            "http://localhost:8080/participacoes/usuario/{idUsuario}",
                            HttpMethod.GET,
                            null,
                            Participacao[].class,
                            id
                    );
                    Participacao[] participacaos = res.getBody();

                    if (participacaos == null || participacaos.length == 0) {
                        System.out.println("\nNenhuma participacao foi encontrado com esta usuario.");
                        break;
                    }

                    for (Participacao participacao : participacaos) {
                        System.out.println(participacao);
                    }
                }
                case 7 -> {
                    nome = Console.readLine("Informe o nome do campeao: ");
                    try {
                        umUsuario = recuperarObjeto(
                                "Informe o ID do usuario que você deseja recuperar: ",
                                "http://localhost:8080/usuarios/{id}", Usuario.class);
                    }
                    catch (EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    umaParticipacao = new Participacao(nome, umUsuario);

                    try {
                        ResponseEntity<Participacao> res = restTemplate.postForEntity(
                                "http://localhost:8080/participacoes/",
                                umaParticipacao,
                                Participacao.class
                        );
                        umaParticipacao = res.getBody();
                        if (umaParticipacao == null) {
                            System.out.println("\nErro ao cadastrar o participacao!");
                            break;
                        }

                        System.out.println("\nParticipacao com id " + umaParticipacao.getId() + " cadastrado com sucesso!");
                        System.out.println(umaParticipacao);
                    } catch (ViolacaoDeConstraintException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 8 -> {
                    try {
                        umaParticipacao = recuperarObjeto(
                                "Informe o id da participacao que você deseja alterar: ",
                                "http://localhost:8080/participacoes/{idParticipacao}",
                                Participacao.class
                        );
                    }
                    catch (EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println(umaParticipacao);

                    System.out.println("\nO que você deseja alterar?");
                    System.out.println("\n1. Nome do campeao");
                    System.out.println("\n2. Usuario");

                    int opcaoAlteracao = Console.readInt("\nDigite o número 1 ou 2:");

                    if (opcaoAlteracao == 1) {
                        nome = Console.readLine("Digite o nome do novo campeao: ");
                        umaParticipacao.setCampeao(nome);
                    } else if (opcaoAlteracao == 2) {
                        try {
                            umUsuario = recuperarObjeto(
                                    "Informe o ID do usuario que você deseja recuperar: ",
                                    "http://localhost:8080/usuarios/{idUsuario}", Usuario.class);
                        }
                        catch (EntidadeNaoEncontradaException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        umaParticipacao.setUsuario(umUsuario);
                    }

                     else {
                        System.out.println("\nOpção inválida");
                        break;
                    }

                    try {
                        restTemplate.put("http://localhost:8080/participacoes/", umaParticipacao);

                        System.out.println("\nParticipacao de ID " + umaParticipacao.getId() + " alterado com sucesso!");
                        System.out.println(umaParticipacao);
                    } catch (ViolacaoDeConstraintException | EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 9 -> {
                    try {
                        umaParticipacao = recuperarObjeto(
                                "Informe o ID da participacao que você deseja remover: ",
                                "http://localhost:8080/participacoes/{idParticipacao}",
                                Participacao.class
                        );
                    }
                    catch (EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println(umaParticipacao);

                    String resp = Console.readLine("\nConfirma a remoção da participacao?");

                    if (resp.equals("s")) {
                        try {

                            restTemplate.delete("http://localhost:8080/participacoes/{idParticipacao}", umaParticipacao.getId());

                            System.out.println("\nParticipacao de ID " + umaParticipacao.getId() + " removido com sucesso!");
                        } catch (EntidadeNaoEncontradaException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Participacao não removida.");
                    }
                }
                case 10 -> {
                    try {
                        umaParticipacao = recuperarObjeto(
                                "Informe o ID da participacao que você deseja recuperar: ",
                                "http://localhost:8080/participacoes/{idParticipacao}", Participacao.class);
                    }
                    catch (EntidadeNaoEncontradaException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println(umaParticipacao);
                }
                case 11 -> {
                    ResponseEntity<Participacao[]> res = restTemplate.exchange(
                            "http://localhost:8080/participacoes/",
                            HttpMethod.GET,
                            null,
                            Participacao[].class
                    );
                    Participacao[] participacoes = res.getBody();

                    if (participacoes != null) {
                        for (Participacao umParticipacao : participacoes) {
                            System.out.println(umParticipacao);
                        }
                    }
                }
                case 12 -> continua = false;
                default -> System.out.println("\nOpção inválida!");
            }
        }
    }

    private static <T> T recuperarObjeto(String msg, String url, Class<T> classe) {
        int id = Console.readInt('\n' + msg);
        return restTemplate.getForObject(url, classe, id);
    }
}