package Cadastro;

import java.util.ArrayList;


public class SistemaMonit {
	
	private Usuario usuarioLogado;
	private ArrayList<Condominio> listaCondominioCadastrados;
	private ArrayList<PainelSolar> listaPainelCadastrados;
	private ArrayList<Residencias> listarResidenciasCadastradas;
	private ArrayList<Usuario> listarUsuariosCadastrados;
	
	public SistemaMonit() {
		this.listaPainelCadastrados = new ArrayList<PainelSolar>();
		this.listaCondominioCadastrados = new ArrayList<Condominio>();
		this.listarResidenciasCadastradas = new ArrayList<Residencias>();
		this.listarUsuariosCadastrados = new ArrayList<Usuario>();
	}
	
	public void cadPainelSolar(int idPlaca, double potencia) {
		PainelSolar painel = new PainelSolar(idPlaca, potencia);
		this.listaPainelCadastrados.add(painel);
	}
	
	public void listarPainelCadastrados() throws InterruptedException {
		System.out.println("\nPaineis Solares cadastrados");
		for(PainelSolar painel : this.listaPainelCadastrados) {
			Thread.sleep(600);
			System.out.println("\nID: " + painel.getidPlaca() + " - Potência: " + painel.getPotencia());
		}
			System.out.println("------------------------------------------");
	}
	
	public void cadastrarResidencia(int id, String endereco, double consumoEnergia) {
		Residencias residencia = new Residencias(id, endereco, consumoEnergia);
		this.listarResidenciasCadastradas.add(residencia);
	}
	
	public void listarResidenciasCadastrada() throws InterruptedException {
		System.out.println("\nResidências cadastradas");
		for(Residencias residencia : this.listarResidenciasCadastradas) {
			Thread.sleep(600);
			System.out.println("\nID: " + residencia.getId() + " - Endereço: " + residencia.getEndereco());
		}
		System.out.println("---------------------------------------------");
	}	

	public void cadCondominios(int idCondominio, String nomeCond, String endCond) {
	    Condominio condominio = new Condominio(idCondominio,nomeCond, endCond);
	    this.listaCondominioCadastrados.add(condominio);

	}
	
	public boolean existeUsuario(int idUsuario) {
	    for (Usuario usuario : this.listarUsuariosCadastrados) {
	        if (usuario.getIdUsuario() == idUsuario) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public void cadUsuario(int idUsuario, String nomeUsuario, String senhaUsuario) {
		Usuario usuario = new Usuario(idUsuario, nomeUsuario, senhaUsuario);
		this.listarUsuariosCadastrados.add(usuario);
	}
	
	public void listarCondominiosCadastrados() throws InterruptedException {
		System.out.println("\nCondominios cadastrados:");
		for(Condominio condominio : this.listaCondominioCadastrados) {
			Thread.sleep(600);
			System.out.println("\nID: " + condominio.getId() + " - Nome Condominio: " + condominio.getNome());
		}
		System.out.println("------------------------------------------");
	}
	
	public void listarUsuariosCadastrados() throws InterruptedException {
		System.out.println("Usuarios cadastrados:");
		for(Usuario usu : this.listarUsuariosCadastrados) {
			Thread.sleep(600);
			System.out.println("\nID: " + usu.getIdUsuario() + " - Nome Usuario: " + usu.getNomeUsuario());
			
			ArrayList<Condominio> condominios = usu.getCondominios();
	        if (condominios.isEmpty()) {
	            System.out.println("Nenhum condomínio atrelado.");
	        } else {
	            System.out.println("Condomínios atrelados:");
	            for (Condominio cond : condominios) {
	                System.out.println("    - ID: " + cond.getId() + ", Nome: " + cond.getNome());
	            }
	        }
	    }
	    System.out.println("------------------------------------------");
	}
	
	public Usuario buscarUsuario(int id) {
		for(Usuario usu : this.listarUsuariosCadastrados) {
			if(usu.getIdUsuario() == id) {
				return usu;
			}
		}
		return null;
	}	
	
	public Condominio buscarCondominio(int idCondominio) {
		
		for(Condominio cond : this.listaCondominioCadastrados) {
			if(cond.getId() == idCondominio) {
				return cond;
			}
		}
		return null;
	}
	
	public void relatorioCondominio() throws InterruptedException {
		 Thread.sleep(600);
	    System.out.println("\nRelatório de Consumo por Residências do Condomínio");
	    System.out.println("---------------------------------");

	    double totalConsumo = 0;
	    double totalGerado = 0;
	    int totalResidencias = this.listarResidenciasCadastradas.size();

	    for (PainelSolar painel : this.listaPainelCadastrados) {
	        totalGerado += painel.getPotencia();
	    }

	    double energiaPorResidencia = totalResidencias > 0 ? totalGerado / totalResidencias : 0;

	    for (Residencias residencia : this.listarResidenciasCadastradas) {
	        totalConsumo += residencia.getConsumoEnergia();
	        residencia.verificarEnergiaRecebida(energiaPorResidencia);
	    }

	    System.out.println("---------------------------------");
	    System.out.println("TOTAL DE CONSUMO DO CONDOMINIO: " + totalConsumo + " kWh");
	    System.out.println("TOTAL GERADO: " + totalGerado + " kWh\n");

	    if (totalGerado >= totalConsumo) {
	        System.out.println("✅ Energia gerada foi suficiente para todas as residências.");
	        System.out.println("Os créditos serão distribuidos parcialmente.\n");
	    } else {
	        System.out.println("⚠️ Energia gerada foi insuficiente para todas as residências.");
	        System.out.println("Os créditos não serão o suficiente para o condominio. Será cobrado o valor a parte pela companhia de energia.");
	    }

	    System.out.println("---------------------------------");
	}

	
		public boolean loginUsuario(int idUsuario, String senhaUsuario) throws InterruptedException {
		    Usuario usuario = this.buscarUsuario(idUsuario);
		    if (usuario != null && usuario.getSenhaUsuario().equals(senhaUsuario)) {
		        this.usuarioLogado = usuario;
		        System.out.println("\nLogin do Usuario efetuado com sucesso");
		        System.out.println("Bem vindo ao nosso sistema " + usuario.getNomeUsuario() + "!\n");
		        Thread.sleep(600);
		        return true;
		    }
		    return false;
		}
		
		public boolean editarUsuario(int idUsuario, String novoNome, String novaSenha) {
		    for (Usuario usuario : this.listarUsuariosCadastrados) {
		        if (usuario.getIdUsuario() == idUsuario) {
		            usuario.setNomeUsuario(novoNome);
		            usuario.setSenhaUsuario(novaSenha);
		            System.out.println("Dados do usuário atualizados!");
		            return true;
		        }
		    }
		    System.out.println("Usuário com ID " + idUsuario + " não encontrado.");
		    return false;
		}
		
		public boolean excluirUsuario(int idUsuario) {
		    for (Usuario usuario : this.listarUsuariosCadastrados) {
		        if (usuario.getIdUsuario() == idUsuario) {
		            this.listarUsuariosCadastrados.remove(usuario);
		            System.out.println("Usuário removido com sucesso!");
		            return true;
		        }
		    }
		    System.out.println("Usuário com ID " + idUsuario + " não encontrado.");
		    return false;
		}
		
	public void atrelarCondominio(int id, int idCondominio) {
		Usuario usuario = this.buscarUsuario(id);
		Condominio condominio = this.buscarCondominio(idCondominio);
		
		if( usuario == null && condominio == null) {
			System.out.println("Usuario não cadastrado e Condominio não cadastrado!");
		}else if(usuario == null) {
			System.out.println("Usuario não cadastrado!");
		}else if(condominio == null) {
			System.out.println("Condominio não cadastrado!");
		}else {
			condominio.setSindico(usuario);
			usuario.adicionarCondominio(condominio);
			System.out.println("Condominio atrelado ao sindicico com sucesso!");
		}
		
	}
	            
	public void menuAdmUsu() {
		System.out.println("--------------------------------------------------------------\r\n"
				+ "|                                                            |\r\n"
				+ "|  Sistema de Monitoramento de Painéis Solares do Condomínio |\r\n"
				+ "|                                                            |\r\n"
				+ "--------------------------------------------------------------\r\n"
				+ "");
		System.out.println("1 - Acessar com Administrador");
		System.out.println("2 - Acessar com Usuario");
		System.out.println("3 - Sair");
		System.out.print("Escolha uma opção: ");
	}

	public void menuAdministrador() throws InterruptedException {
		 Thread.sleep(600);
		System.out.println("\n--------------------------------------------------------------\r\n"
				+ "|                                                            |\r\n"
				+ "| 		  Área do Administrador 						    |\r\n"
				+ "|                                                            |\r\n"
				+ "--------------------------------------------------------------\r\n"
				+ "");
			System.out.println("1 - Listar usuários");
	        System.out.println("2 - Editar usuário");
	        System.out.println("3 - Excluir usuário");
	        System.out.println("4 - Voltar");
	        System.out.print("Escolha uma opção: ");
	}

	public void exibirMenuInicial() throws InterruptedException {
		Thread.sleep(600);
		System.out.println("\n--------------------------------------------------------------\r\n"
				+ "|                                                            |\r\n"
				+ "|  Sistema de Monitoramento de Painéis Solares do Condomínio |\r\n"
				+ "|                                                            |\r\n"
				+ "--------------------------------------------------------------\r\n"
				+ "");
		System.out.println("1 - Cadastrar novo Usuario");
		System.out.println("2 - Realizar Login");
		System.out.println("3 - Sair");
		System.out.print("Escolha uma opção: ");
	}
	
	public void exibirCadastroCondominio() throws InterruptedException {
		Thread.sleep(600);
		System.out.println("\n--------------------------------------------------------------\r\n"
				+ "|                                                            |\r\n"
				+ "|  Sistema de Monitoramento de Painéis Solares do Condomínio |\r\n"
				+ "|                                                            |\r\n"
				+ "--------------------------------------------------------------\r\n"
				+ "");
		
		System.out.println("1 - Cadastrar Condominio");
		System.out.println("2 - Ja possuo o Condominio Cadastrado");
		System.out.println("3 - Sair");
		System.out.print("Escolha uma opção: ");
	}
	
	public void menuPrincipal() throws InterruptedException {
		Thread.sleep(600);
		System.out.println("\n--------------------------------------------------------------\r\n"
				+ "|                                                            |\r\n"
				+ "|  Sistema de Monitoramento de Painéis Solares do Condomínio |\r\n"
				+ "|                                                            |\r\n"
				+ "--------------------------------------------------------------\r\n"
				+ "");
		System.out.println("1 - Cadastrar Painel Solar");
		System.out.println("2 - Cadastar Residencia");
		System.out.println("3 - Conferir condominios cadastrados");
		System.out.println("4 - Confererir paineis cadastrados");
		System.out.println("5 - Confererir Residencias cadastradas");
		System.out.println("6 - Relatório de consumo geral do condominio");
		System.out.println("7 - Atrelar condominio ao sindico responsavel");
		System.out.println("8 - Conferir usuarios cadastrados");
		System.out.println("9 - Sair");	
		System.out.print("Escolha uma opção: ");
	}
	


}




