package stepBank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ContaPoupanca extends Conta {

	private float percentualRendimento = 0.20f;

	Scanner entrada = new Scanner(System.in);

	@Override
	public void escolherOpcao(Cliente titular) throws Erro, IOException {
		System.out.println("Escolha uma opção:\nCriar Conta Poupança - 1\nDepositar - 2\nSacar - 3\nInformações Conta - 4\nSair - 5");
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
				acessarInformacoesConta(titular);
			default:
				System.err.println("Digito inválido!!!");
				escolherOpcao(titular);
				break;
			}

		} while (escolha != "1" || escolha != "2" || escolha != "3");
	}

	@Override
	public void sacar(Cliente titular) throws Erro, IOException {
		if (this.isEstaAtiva() == true) {

			System.out.println("Digite o valor que deseja sacar da sua conta: ");
			float valorSaque = entrada.nextFloat();

			while (valorSaque > this.getSaldo() || valorSaque < 0) {
				System.err
						.println("Sua conta possui R$ " + this.getSaldo() + ", portanto pegue um valor possível dela!");
				System.out.println("Digite o valor que deseja sacar da sua conta: ");
				valorSaque = entrada.nextFloat();
			}

			float valorSaldoAtual = this.getSaldo();
			valorSaldoAtual = valorSaldoAtual + valorSaldoAtual * this.getPercentualRendimento();
			valorSaldoAtual = valorSaldoAtual - valorSaque;
			this.setSaldo(valorSaldoAtual);

			System.out.println("Informações da Conta:");
			System.out.println("Valor Sacado: R$ " + valorSaque);
			System.out.println("Percentual do Rendimento: " + this.getPercentualRendimento() * 100 + "%");
			System.out.println("Valor Disponível Atual R$ " + this.getSaldo());
			
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
			System.out.println("Informações da Conta Poupança:");
			System.out.println("Nome: " + titular.getNome());
			System.out.println("Sobrenome: " + titular.getSobrenome());
			System.out.println("CPF ou CNPJ: " + titular.getNumeroDocumento());
			System.out.println("Nome da Empresa: " + titular.getNomeEmpresa());
			System.out.println("Atividades da Empresa: " + titular.getAtividadesEmpresa());
			System.out.println("CEP: " + titular.getCep());
			System.out.println("Data de Nascimento: " + titular.getDataNascimento());
			System.out.println("Senha: " + titular.getSenha());
			System.out.println("Número da Conta: " + this.getNumero());
			System.out.println("Agência da Conta: " + this.getAgencia());
			System.out.println("Saldo: R$ " + this.getSaldo());
			System.out.println();
		}
		escolherOpcao(titular);
	}

	public void cadastrarConta(Cliente titular) throws Erro, IOException {
		boolean cadastrado = false;

		if (this.isEstaAtiva() == false) {
			System.out.println("Preencha todas as informações para cadastrar sua conta");

			do {

				System.out.println("Digite seu nome: ");
				titular.setNome(entrada.nextLine());

				System.out.println("Digite seu sobrenome: ");
				titular.setSobrenome(entrada.nextLine());

				System.out.println("Digite sua data de nascimento: ");
				titular.setDataNascimento(entrada.nextLine());

				System.out.println("Digite a sua senha: ");
				titular.setSenha(entrada.nextLine());

				System.out.println("Digite o nome da empresa: ");
				titular.setNomeEmpresa(entrada.nextLine());

				System.out.println("Digite as atividades da empresa: ");
				titular.setAtividadesEmpresa(entrada.nextLine());
				
				System.out.println("Digite o CEP da empresa: ");
				titular.setCep(entrada.nextLine());

				if (titular.getNome() != "" && titular.getSobrenome() != "" && titular.getDataNascimento() != ""
						&& titular.getSenha() != "" && titular.getNomeEmpresa() && titular.getAtividadesEmpresa() && titular.getCep()) {
					cadastrado = true;
					this.setEstaAtiva(true);

				} else {
					System.err.println("Preencha todas as informações!!!");
				}

			} while (cadastrado == false);

			try {
				gerarConta();
			} catch (InputMismatchException er) {
				er.printStackTrace();
				System.err.println("Digite somente números no Número e Agência da Conta!");
			}
			
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
			

			System.out.println("Número de conta e Agência gerados com sucesso!\nConta Cadastrada!");
			escolherOpcao(titular);
		} else {
			throw new Erro("Conta já cadastrada!");
		}
	}
	
	public float getPercentualRendimento() {
		return percentualRendimento;
	}

	public void setPercentualRendimento(float percentualRendimento) {
		this.percentualRendimento = percentualRendimento;
	}
}
