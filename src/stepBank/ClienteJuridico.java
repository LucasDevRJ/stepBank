package stepBank;

public class ClienteJuridico extends Cliente {

	private String nomeEmpresa;
	private String atividadesEmpresa;
	private String cep;
	
	public boolean getNomeEmpresa() {
		return nomeEmpresa != null;
	}
	
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	
	public boolean getAtividadesEmpresa() {
		return atividadesEmpresa != null;
	}
	
	public void setAtividadesEmpresa(String atividadesEmpresa) {
		this.atividadesEmpresa = atividadesEmpresa;
	}
	
	public boolean getCep() {
		return cep != null;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
}
