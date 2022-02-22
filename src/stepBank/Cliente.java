package stepBank;

public abstract class Cliente {

	private String nome;
	private String sobrenome;
	private String dataNascimento;
	private String senha;
	private TipoDocumento tipoDocumento;
	private String numeroDocumento;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setProfissao(String nextLine) {
		// TODO Auto-generated method stub
		
	}

	public void setSalario(String nextLine) {
		// TODO Auto-generated method stub
		
	}

	public void setNomeEmpresa(String nextLine) {
		// TODO Auto-generated method stub
		
	}

	public void setAtividadesEmpresa(String nextLine) {
		// TODO Auto-generated method stub
		
	}

	public void setCep(String nextLine) {
		// TODO Auto-generated method stub
		
	}

	public boolean getNomeEmpresa() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getAtividadesEmpresa() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getProfissao() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSalario() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean getCep() {
		// TODO Auto-generated method stub
		return false;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	
}
