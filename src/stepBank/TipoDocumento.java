package stepBank;


public enum TipoDocumento {
	CPF {
		@Override
		public String geraNumeroDocumento() {
			// TODO Auto-generated method stub
			return GeraCpfCnpj.cpf();
		}
	}, CNPJ {
		@Override
		public String geraNumeroDocumento() {
			// TODO Auto-generated method stub
			return GeraCpfCnpj.cnpj();
		}
	};
	
	public abstract String geraNumeroDocumento();
}
