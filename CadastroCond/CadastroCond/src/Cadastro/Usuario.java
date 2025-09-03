package Cadastro;

import java.util.ArrayList;

public class Usuario {
	private final int idUsuario;
	private String nomeUsuario;
	private String senhaUsuario;
	private ArrayList<Condominio> condominios;
	
	public Usuario(int idUsuario, String nomeUsuario, String senhaUsuario) {
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.senhaUsuario = senhaUsuario;
		this.condominios = new ArrayList<>();
	}
	
	public void adicionarCondominio(Condominio condominio) {
        this.condominios.add(condominio);
    }

    public ArrayList<Condominio> getCondominios() {
        return this.condominios;
    }

	public int getIdUsuario() {
		return idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}


	
}
