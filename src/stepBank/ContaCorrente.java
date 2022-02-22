package stepBank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ContaCorrente extends Conta {

	private float taxa = 0.30f;
	private float saldo;
	private ClienteFisico cliente;

	Scanner entrada = new Scanner(System.in);

	public void escolherOpcao(Cliente titular) throws Erro, IOException {
		System.out.println("Escolha uma op��o:\nCadastrar Conta - 1\nDepositar - 2\nSacar - 3\nTransferir - 4\nInforma��es da Conta - 5\nSair - 6");
		String escolha = entrada.nextLine();

		do {
			switch (escolha) {
			case "1":
				cadastrarConta(titular);
				break;
			case "2":
				depositar(titular);
				break;
			case "3":
				sacar(titular);
				break;
			case "4":
				transferir(titular);
				break;
			case "5":
				acessarInformacoesConta(titular);
				break;
			case "6":
				sair();
				break;
			default:
				System.err.println("Digito inv�lido!!!");
				escolherOpcao(titular);
				break;
			}

		} while (escolha != "1" || escolha != "2" || escolha != "3" || escolha != "4" || escolha != "5" || escolha != "6");
	}

	@Override
	public void sacar(Cliente titular) throws Erro, IOException {
		if (this.isEstaAtiva() == true) {

			System.out.println("Digite o valor que deseja sacar da sua conta: ");
			float valorSaque = entrada.nextFloat();

			while (valorSaque > this.getSaldo() || valorSaque < 0) {
				System.err
						.println("Sua conta possui R$ " + this.getSaldo() + ", portanto pegue um valor poss�vel dela!");
				System.out.println("Digite o valor que deseja sacar da sua conta: ");
				valorSaque = entrada.nextFloat();
			}

			float valorSaldoAtual = this.getSaldo();
			valorSaldoAtual = valorSaldoAtual - valorSaque;
			this.setSaldo(valorSaldoAtual);

			System.out.println("Informa��es da Conta:");
			System.out.println("Valor Sacado: R$ " + valorSaque);
			System.out.println("Valor Dispon�vel Atual R$ " + this.getSaldo());
			
			FileOutputStream gravarSaques = new FileOutputStream("pasta/saques.txt");
			PrintWriter gs = new PrintWriter(gravarSaques);
			
			gs.println("Saque Salvo");
			System.out.println();
			gs.close();
			gravarSaques.close();
			
			FileInputStream lerSaque = new FileInputStream("pasta/saques.txt");
			InputStreamReader ler = new InputStreamReader(lerSaque);
			BufferedReader lerLinha = new BufferedReader(ler);
			
			String linha; 
			
			do {
				linha = lerLinha.readLine(); 
				if (linha != null) {
					System.out.println(linha); 
				}
				
			} while (linha != null);
			

		} else {
			throw new Erro("Ative a conta para poder usa-la!");
		}
		escolherOpcao(titular);
	}

	@Override
	public void acessarInformacoesConta(Cliente titular) throws Erro, IOException {
		if (this.isEstaAtiva() == true) {
			System.out.println("Informa��es da Conta Corrente:");
			System.out.println("Nome: " + titular.getNome());
			System.out.println("Sobrenome: " + titular.getSobrenome());
			System.out.println("CPF ou CNPJ: " + titular.getNumeroDocumento());
			System.out.println("Profiss�o: " + titular.getProfissao());
			System.out.println("Sal�rio: R$ " + titular.getSalario());
			System.out.println("Data de Nascimento: " + titular.getDataNascimento());
			System.out.println("Senha: " + titular.getSenha());
			System.out.println("N�mero da Conta: " + this.getNumero());
			System.out.println("Ag�ncia da Conta: " + this.getAgencia());
			System.out.println("Saldo: R$ " + this.getSaldo());
			System.out.println();
		}
		escolherOpcao(titular);
	}

	public void transferir(Cliente titular) throws Erro, IOException {

		int numero, agencia;
		float valor;

		System.out.println("Digite o n�mero da conta que deseja realzar transfer�ncia: ");
		numero = entrada.nextInt();

		System.out.println("Digite a ag�ncia que deseja realizar a transfer�ncia: ");
		agencia = entrada.nextInt();

		System.out.println("Digite o valor que deseja transferir: ");
		valor = entrada.nextFloat();

		while (valor > this.getSaldo()) {
			System.err.println("Valor inv�lido!!!");
			System.out.println("Digite o valor que deseja transferir: ");
			valor = entrada.nextFloat() + this.getTaxa();
		}
		
		float valorAtual = this.getSaldo();
		valorAtual = valorAtual - valor;
		this.setSaldo(valorAtual);
		
		System.out.println("Transfer�ncia Realizada com Sucesso!");
		System.out.println("N�mero da Conta: " + numero);
		System.out.println("N�mero da Ag�ncia: " + agencia);
		System.out.println("Valor: R$ " + valor);
		System.out.println("Taxa: R$ " + this.getTaxa());
		System.out.println("Valor Atual da Sua Conta: R$ " + this.getSaldo());
		
		FileOutputStream gravarTransferencias = new FileOutputStream("pasta/transferencias.txt");
		PrintWriter gt = new PrintWriter(gravarTransferencias);
		
		gt.println("Transfer�ncia Salva");
		System.out.println();
		gt.close();
		gravarTransferencias.close();
		
		FileInputStream lerTransferencia = new FileInputStream("pasta/transferencias.txt");
		InputStreamReader ler = new InputStreamReader(lerTransferencia);
		BufferedReader lerLinha = new BufferedReader(ler);
		
		String linha; 
		
		do {
			linha = lerLinha.readLine(); 
			if (linha != null) {
				System.out.println(linha); 
			}
			
		} while (linha != null);
		
		
		escolherOpcao(titular);
	}
	
	@Override
	public void cadastrarConta(Cliente titular) throws Erro, IOException {
		boolean cadastrado = false;

		if (this.isEstaAtiva() == false) {
			System.out.println("Preencha todas as informa��es para cadastrar sua conta");

			do {

				System.out.println("Digite seu nome: ");
				titular.setNome(entrada.nextLine());

				System.out.println("Digite seu sobrenome: ");
				titular.setSobrenome(entrada.nextLine());

				System.out.println("Digite sua data de nascimento: ");
				titular.setDataNascimento(entrada.nextLine());

				System.out.println("Digite a sua senha: ");
				titular.setSenha(entrada.nextLine());
				
				System.out.println("Digite sua profiss�o: ");
				titular.setProfissao(entrada.nextLine());
				
				System.out.println("Digite seu sal�rio: ");
				titular.setSalario(entrada.nextLine());
				
				if (titular.getNome() != "" && titular.getSobrenome() != "" && titular.getDataNascimento() != ""
						&& titular.getSenha() != "" && titular.getProfissao() != "" && titular.getSalario() != "") {
					cadastrado = true;
					this.setEstaAtiva(true);

				} else {
					System.err.println("Preencha todas as informa��es corretamente!!!");
				}

			} while (cadastrado == false);

			try {
				gerarConta();
			} catch (InputMismatchException er) {
				er.printStackTrace();
				System.err.println("Digite somente n�meros no N�mero e Ag�ncia da Conta!");
			}

			System.out.println("N�mero de conta e Ag�ncia gerados com sucesso!\nConta Cadastrada!");
			
			FileOutputStream gravarContas = new FileOutputStream("pasta/contas.txt");
			PrintWriter gc = new PrintWriter(gravarContas);
			
			gc.println("Conta Cadastrada Salva");
			System.out.println();
			gc.close();
			gravarContas.close();
			
			FileInputStream lerContas = new FileInputStream("pasta/contas.txt");
			InputStreamReader ler = new InputStreamReader(lerContas);
			BufferedReader lerLinha = new BufferedReader(ler);
			
			String linha; 
			
			do {
				linha = lerLinha.readLine(); 
				if (linha != null) {
					System.out.println(linha); 
				}
				
			} while (linha != null);
			
			escolherOpcao(titular);
		} else {
			throw new Erro("Conta j� cadastrada!");
		}
	}

	public float getTaxa() {
		return taxa;
	}

	public void setTaxa(float taxa) {
		this.taxa = taxa;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public ClienteFisico getCliente() {
		return cliente;
	}

	public void setCliente(ClienteFisico cliente) {
		this.cliente = cliente;
	}

}
