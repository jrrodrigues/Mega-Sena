package br.com.mega;

public class Jogo {

	public static void main(String[] args) throws Exception {
		
		TrataDados dados = new TrataDados(5);
		Sorteio sorteio = new Sorteio(dados);
		sorteio.mostraNumerosMaisJogadosPorDezenaCompleto();
		sorteio.mostraApostaNormal();
		sorteio.mostraApostaInteligente();
		sorteio.mostraApostaAzarona();
		sorteio.mostraNumerosMaisJogadosFiltrados();
		sorteio.mostraNumerosMenosJogadosFiltrados();
	}
}
