package Cadastro;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        SistemaMonit sistemaMonit = new SistemaMonit();
        Scanner ler = new Scanner(System.in);

        int opcaoMenu1 = -1;
        sistemaMonit.cadUsuario(0, "admin", "admin123");

        while (opcaoMenu1 != 3) {
            sistemaMonit.menuAdmUsu();
            opcaoMenu1 = ler.nextInt();

            if (opcaoMenu1 == 1) {
                // ADMIN LOGIN
                System.out.println("\nInforme o ID do Admin:");
                int idUsuario = ler.nextInt();
                System.out.println("\nInforme a senha:");
                String senhaUsuario = ler.next();

                if (idUsuario == 0 && senhaUsuario.equals("admin123")) {
                    System.out.println("\nLogin do Usuário efetuado com sucesso");
                    System.out.println("Bem vindo ao sistema de controle!!\n");
                    Thread.sleep(600);

                    int opcaoAdmin = -1;
                    while (opcaoAdmin != 4) {
                        sistemaMonit.menuAdministrador();
                        opcaoAdmin = ler.nextInt();

                        if (opcaoAdmin == 1) {
                            sistemaMonit.listarUsuariosCadastrados();
                        } else if (opcaoAdmin == 2) {
                            System.out.println("\nInforme o ID do usuário:");
                            int id = ler.nextInt();
                            System.out.println("\nNovo nome:");
                            String nome = ler.next();
                            System.out.println("\nNova senha:");
                            String senha = ler.next();
                            sistemaMonit.editarUsuario(id, nome, senha);
                        } else if (opcaoAdmin == 3) {
                            System.out.println("\nInforme o ID do usuário:");
                            int id = ler.nextInt();
                            sistemaMonit.excluirUsuario(id);
                        } else if (opcaoAdmin == 4) {
                            System.out.println("\nVoltando ao menu anterior...");
                        } else {
                            System.out.println("Opção inválida!");
                        }
                    }

                } else {
                    System.out.println("\nAdmin não encontrado!");
                    Thread.sleep(1000);
                }

            } else if (opcaoMenu1 == 2) {
                // MENU USUÁRIO
                int opcaoMenu2 = -1;

                while (opcaoMenu2 != 3) {
                    sistemaMonit.exibirMenuInicial();
                    opcaoMenu2 = ler.nextInt();

                    if (opcaoMenu2 == 1) {
                        // CADASTRO USUÁRIO
                        System.out.println("\nInforme o ID do Usuário:");
                        int id = ler.nextInt();
                        while (sistemaMonit.existeUsuario(id)) {
                            System.out.println("Já existe um usuário com esse ID! Por favor, escolha outro ID:");
                            id = ler.nextInt();
                        }
                        System.out.println("\nInforme o nome:");
                        String nome = ler.next();
                        System.out.println("\nInforme a senha:");
                        String senha = ler.next();
                        sistemaMonit.cadUsuario(id, nome, senha);
                        System.out.println("Usuário cadastrado!");
                        Thread.sleep(1000);

                    } else if (opcaoMenu2 == 2) {
                        // LOGIN USUÁRIO
                        System.out.println("\nInforme o ID do Usuário:");
                        int idUsuario = ler.nextInt();
                        System.out.println("\nInforme a senha:");
                        String senhaUsuario = ler.next();

                        if (sistemaMonit.loginUsuario(idUsuario, senhaUsuario)) {
                            boolean condominioSelecionado = false;

                            while (!condominioSelecionado) {
                                sistemaMonit.exibirCadastroCondominio();
                                int opcaoCond = ler.nextInt();

                                if (opcaoCond == 1) {
                                    System.out.println("\nInforme o ID do condomínio:");
                                    int idCond = ler.nextInt();
                                    System.out.println("\nInforme o Nome do condomínio:");
                                    String nomeCond = ler.next();
                                    System.out.println("\nInforme o Endereço do condomínio:");
                                    String endCond = ler.next();

                                    sistemaMonit.cadCondominios(idCond, nomeCond, endCond);
                                    condominioSelecionado = true;

                                } else if (opcaoCond == 2) {
                                    System.out.println("\nInforme o ID do condomínio já cadastrado:");
                                    int idCondExistente = ler.nextInt();

                                    if (sistemaMonit.buscarCondominio(idCondExistente) != null) {
                                        System.out.println("Condomínio encontrado com sucesso!");
                                        condominioSelecionado = true;
                                    } else {
                                        System.out.println("Condomínio não encontrado!");
                                        System.out.println("1 - Tentar novamente");
                                        System.out.println("2 - Cadastrar novo condomínio");
                                        int novaOpcao = ler.nextInt();

                                        if (novaOpcao == 2) {
                                            System.out.println("\nInforme o ID do condomínio:");
                                            int novoId = ler.nextInt();
                                            System.out.println("\nInforme o Nome do condomínio:");
                                            String novoNome = ler.next();
                                            System.out.println("\nInforme o Endereço do condomínio:");
                                            String novoEndereco = ler.next();

                                            sistemaMonit.cadCondominios(novoId, novoNome, novoEndereco);
                                            System.out.println("Novo condomínio cadastrado!");
                                            condominioSelecionado = true;
                                        }
                                    }

                                } else {
                                    System.out.println("\nVoltando ao menu anterior...");
                                    condominioSelecionado = false;
                                    break;
                                }
                            }

                            if (condominioSelecionado) {
                                int opcaoMenu4 = -1;

                                while (opcaoMenu4 != 9) {
                                    sistemaMonit.menuPrincipal();
                                    opcaoMenu4 = ler.nextInt();

                                    if (opcaoMenu4 == 1) {
                                        System.out.println("\nInforme o ID da Placa Solar:");
                                        int idPlaca = ler.nextInt();
                                        System.out.println("\nInforme a potência da Placa Solar:");
                                        double potencia = ler.nextDouble();
                                        sistemaMonit.cadPainelSolar(idPlaca, potencia);
                                        System.out.println("Placa Solar cadastrada!");
                                        Thread.sleep(1000);

                                    } else if (opcaoMenu4 == 2) {
                                        System.out.println("\nInforme o ID da Residência:");
                                        int idResid = ler.nextInt();
                                        System.out.println("\nInforme o endereço da Residência:");
                                        String endereco = ler.next();
                                        System.out.println("\nInforme o consumo da Residência:");
                                        double consumo = ler.nextDouble();
                                        sistemaMonit.cadastrarResidencia(idResid, endereco, consumo);
                                        System.out.println("Residência cadastrada!");
                                        Thread.sleep(1000);

                                    } else if (opcaoMenu4 == 3) {
                                        sistemaMonit.listarCondominiosCadastrados();
                                    } else if (opcaoMenu4 == 4) {
                                        sistemaMonit.listarPainelCadastrados();
                                    } else if (opcaoMenu4 == 5) {
                                        sistemaMonit.listarResidenciasCadastrada();
                                    } else if (opcaoMenu4 == 6) {
                                        sistemaMonit.relatorioCondominio();
                                    } else if (opcaoMenu4 == 7) {
                                        System.out.println("Informe o ID do Usuário responsável:");
                                        int idResp = ler.nextInt();
                                        System.out.println("Informe o ID do Condomínio:");
                                        int idCond = ler.nextInt();
                                        sistemaMonit.atrelarCondominio(idResp, idCond);
                                    } else if (opcaoMenu4 == 8) {
                                        sistemaMonit.listarUsuariosCadastrados();
                                    } else if (opcaoMenu4 == 9) {
                                        System.out.println("\nVoltando ao menu anterior...");
                                    } else {
                                        System.out.println("Opção inválida!");
                                    }
                                }
                            }

                        } else {
                            System.out.println("\nID ou senha incorretos!");
                            Thread.sleep(1000);
                        }

                    } else if (opcaoMenu2 == 3) {
                        System.out.println("\nVoltando ao menu anterior...");
                    } else {
                        System.out.println("Opção inválida!");
                    }
                }

            } else if (opcaoMenu1 == 3) {
                System.out.println("Sistema encerrado.");
            } else {
                System.out.println("Opção inválida!");
            }
        }

        ler.close();
    }
}
