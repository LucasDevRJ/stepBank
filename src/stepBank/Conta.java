package stepBank;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Conta {

	private int numero;
	private int agencia;
	private float saldo;
	private boolean estaAtiva;
	
	Scanner entrada = new Scanner(System.in);
	
	public void gerarConta() throws Erro, IOException {
		if (this.isEstaAtiva() == true) {
			System.out.println("Escolha os números de sua conta: ");
			this.setNumero(entrada.nextInt());

			System.out.println("Escolha os números da sua agência: ");
			this.setAgencia(entrada.nextInt());
			
			FileOutputStream gravarConta = new FileOutputStream("pasta/contas.txt");
			PrintWriter gv = new PrintWriter(gravarConta);
			
			gv.println("Conta Salva");
			System.out.println();
			gv.close();
			gravarConta.close();
			
			FileInputStream lerConta = new FileInputStream("pasta/contas.txt");
			InputStreamReader ler = new InputStreamReader(lerConta);
			BufferedReader lerLinha = new BufferedReader(ler);
			
			String linha; 
			
			do {
				linha = lerLinha.readLine(); 
				if (linha != null) {
					System.out.println(linha); 
				}
				
			} while (linha != null);
			
		}
	}
	
	public abstract void cadastrarConta(Cliente titular) throws Erro, IOException;
	
	public abstract void escolherOpcao(Cliente titular) throws Erro, IOException;
	
	public abstract void sacar(Cliente titular) throws Erro, IOException;
	
	public abstract void acessarInformacoesConta(Cliente titular) throws Erro, IOException;
	
	public void depositar(Cliente titular) throws Erro, IOException {

		if (this.isEstaAtiva() == true) {

			System.out.println("Digite o valor que deseja depositar: ");
			this.setSaldo(entrada.nextFloat());

			while (this.getSaldo() < 0 || this.getSaldo() > 999999999) {
				System.out.println("Digite um valor válido");
				System.out.println("Digite o valor que deseja depositar: ");
				this.setSaldo(entrada.nextFloat());
			}

			System.out.println("Deposito de R$ " + this.getSaldo() + " realizado com sucesso!!!");
			
			FileOutputStream gravarDeposito = new FileOutputStream("pasta/deposito.txt");
			PrintWriter dp = new PrintWriter(gravarDeposito);
			
			dp.println("Deposito Salvo");
			System.out.println();
			dp.close();
			gravarDeposito.close();
			
			FileInputStream lerDeposito = new FileInputStream("pasta/deposito.txt");
			InputStreamReader ler = new InputStreamReader(lerDeposito);
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
			throw new Erro("Ative a conta para poder usa-la!");
		}
	}
	
	public void sair() throws Erro{
		System.out.println("Tchau, tchau!");
		throw new Erro("Reinicie o programa para continuar!");
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public int getAgencia() {
		return agencia;
	}
	
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public boolean isEstaAtiva() {
		return estaAtiva;
	}

	public void setEstaAtiva(boolean estaAtiva) {
		this.estaAtiva = estaAtiva;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
}
