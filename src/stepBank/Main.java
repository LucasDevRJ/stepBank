package stepBank;


public class Main {

	public static void main(String[] args) throws Exception {
		
		ClienteFisico cf = new ClienteFisico();
		cf.setTipoDocumento(Enum.valueOf(TipoDocumento.class, "CPF")); //Executa o M�todo
		cf.setNumeroDocumento(cf.getTipoDocumento().geraNumeroDocumento());
		
		ClienteJuridico cj = new ClienteJuridico();
		cj.setTipoDocumento(Enum.valueOf(TipoDocumento.class, "CNPJ")); //Executa o M�todo
		cj.setNumeroDocumento(cj.getTipoDocumento().geraNumeroDocumento());
		
		ContaCorrente cc = new ContaCorrente();
		ContaPoupanca cp = new ContaPoupanca();
		cc.escolherOpcao(cf);
		//cp.escolherOpcao(cj);
		
	}
}
