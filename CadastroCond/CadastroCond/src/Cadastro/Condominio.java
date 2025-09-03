package Cadastro;

import java.util.ArrayList;

public class Condominio {
	private final int idCondominio;
	private String nomeCond;
	private String endCond;
	private Usuario sindico;
	private ArrayList<Usuario> listaUsuarios;
	
	public Condominio(int idCondominio, String nomeCond, String endCond) {
		this.idCondominio = idCondominio;
		this.nomeCond = nomeCond;
		this.endCond = endCond;
		this.listaUsuarios = new ArrayList <Usuario>();
	}

	public int getId() {
		return idCondominio;
	}	

	public String getNome() {
		return nomeCond;
	}

	public void setNome(String nome) {
		this.nomeCond = nome;
	}

	public String getEndCond() {
		return endCond;
	}

	public void setEndCond(String endCond) {
		this.endCond = endCond;
	}
	
	public ArrayList<Usuario> getListaUsuarios() {
		return this.listaUsuarios;
	}	
	
	public void adicionarUsuario(Usuario usuario) {
		this.listaUsuarios.add(usuario);

	}
	
	public Usuario getSindico() {
	    return sindico;
	}

	public void setSindico(Usuario sindico) {
	    this.sindico = sindico;
	}
	
}

