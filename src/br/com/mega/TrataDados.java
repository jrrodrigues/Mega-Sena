package br.com.mega;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TrataDados {
	
	public TrataDados(Integer qtd) {
		this.qtd = qtd;
		listaDeApostasCaixa = getApostasAnterioresCaixa();
		numerosMaisJogados = getNumerosMaisJogados(qtd);
		numerosMenosJogados = getNumerosMenosJogados(qtd);
	}
	
	private Integer qtd;
	
	private ArrayList<Aposta> listaDeApostasCaixa;
	private ArrayList<Integer> numerosMaisJogados;
	private ArrayList<Integer> numerosMenosJogados;
	
	private ArrayList<DezenaModa> maisJogadosD1;
	private ArrayList<DezenaModa> maisJogadosD2;
	private ArrayList<DezenaModa> maisJogadosD3;
	private ArrayList<DezenaModa> maisJogadosD4;
	private ArrayList<DezenaModa> maisJogadosD5;
	private ArrayList<DezenaModa> maisJogadosD6;
	
	private ArrayList<Aposta> lerApostasArquivoTexto()  {
		ArrayList<Aposta> lista = new ArrayList<Aposta>();
		try {
			InputStream inputStream = getClass().getResourceAsStream("/dados/resultados.txt");
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(reader);
			String linha = br.readLine();
			
			while (linha != null) {
				if(linha.isEmpty()){
					linha = br.readLine();
				} else {
					String [] line = linha.split(";");
					Aposta a = new Aposta();
					a.setNumSorteio(Integer.parseInt(line[0]));
					a.setDez1(Integer.parseInt(line[1]));
					a.setDez2(Integer.parseInt(line[2]));
					a.setDez3(Integer.parseInt(line[3]));
					a.setDez4(Integer.parseInt(line[4]));
					a.setDez5(Integer.parseInt(line[5]));
					a.setDez6(Integer.parseInt(line[6]));
					lista.add(a);
				}
				linha = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao ler o arquivo com as apostas da caixa");
		}
		
		return lista;
	}

	private ArrayList<Aposta> getApostasAnterioresCaixa() {
		ArrayList<Aposta> retorno = lerApostasArquivoTexto();
		recuperaOsMaisJogadosPorDezenaModaOrdenados(retorno);
		return retorno;
	}
	
	private ArrayList<DezenaModa> percorreDezena(Integer dezena, ArrayList<Aposta> lista){
		ArrayList<DezenaModa> listaDezenaModa = new ArrayList<DezenaModa>();
		for (int i = (dezena - 9); i <= dezena; i++) {
			
			DezenaModa d = new DezenaModa();
			d.setDezena(dezena);
			d.setNumero(i);
			d.setQtd(0);
			
			for (Aposta a : lista) {		
				switch (dezena) {
				case 10:
					if(a.getDez1().equals(i)){
						d.setQtd(d.getQtd()+1);
					}
					break;
				case 20:
					if(a.getDez2().equals(i)){
						d.setQtd(d.getQtd()+1);
					}
					break;
				case 30:
					if(a.getDez3().equals(i)){
						d.setQtd(d.getQtd()+1);
					}
					break;
				case 40:
					if(a.getDez4().equals(i)){
						d.setQtd(d.getQtd()+1);
					}
					break;
				case 50:
					if(a.getDez5().equals(i)){
						d.setQtd(d.getQtd()+1);
					}
					break;
				case 60:
					if(a.getDez6().equals(i)){
						d.setQtd(d.getQtd()+1);
					}
					break;
				}
			}
			listaDezenaModa.add(d);
		}
		return listaDezenaModa;
	}
	
	public void verificaSeJogoJaFoiApostado(Aposta aposta) {
		boolean encontrado = false;
		for (Aposta ap : getApostasAnterioresCaixa()) {
			if (aposta.equals(ap)) {
				encontrado = true;
				break;
			}
		}
		if(encontrado){
			System.out.println("Essa aposta já foi efetuada anteriormente.");
		} else {
			System.out.println("Essa aposta nunca foi feita");
		}
	}
	
	private void recuperaOsMaisJogadosPorDezenaModaOrdenados(ArrayList<Aposta> lista){
		DezenaModaComparator dmc = new DezenaModaComparator();
		
		maisJogadosD1 = percorreDezena(10, lista);
		maisJogadosD2 = percorreDezena(20, lista);
		maisJogadosD3 = percorreDezena(30, lista);
		maisJogadosD4 = percorreDezena(40, lista);
		maisJogadosD5 = percorreDezena(50, lista);
		maisJogadosD6 = percorreDezena(60, lista);
		
		Collections.sort(maisJogadosD1, dmc);
		Collections.sort(maisJogadosD2, dmc);
		Collections.sort(maisJogadosD3, dmc);
		Collections.sort(maisJogadosD4, dmc);
		Collections.sort(maisJogadosD5, dmc);
		Collections.sort(maisJogadosD6, dmc);
	}
	
	private void mostraOsMaisJogadosPorDezena(ArrayList<DezenaModa> dezenaModaLista){
		System.out.println("Os mais jogados");
		System.out.println("Dezena | Número | Quantidade de vezes");
		for (DezenaModa dm : dezenaModaLista) {
			System.out.print(dm.getDezena() + " | ");
			System.out.print(dm.getNumero() + " | ");
			System.out.println(dm.getQtd());
		}
	}
	
	public void mostraOsMaisJogados(){
		mostraOsMaisJogadosPorDezena(maisJogadosD1);
		mostraOsMaisJogadosPorDezena(maisJogadosD2);
		mostraOsMaisJogadosPorDezena(maisJogadosD3);
		mostraOsMaisJogadosPorDezena(maisJogadosD4);
		mostraOsMaisJogadosPorDezena(maisJogadosD5);
		mostraOsMaisJogadosPorDezena(maisJogadosD6);
	}
	
	/**
	 * Adiciona
	 * @param qtd
	 * @param dezenaModa
	 * @return
	 */
	private ArrayList<Integer> getNumerosMaisJogadosDaDezena(int qtd, ArrayList<DezenaModa> dezenaModa){
		ArrayList<Integer> retorno = new ArrayList<Integer>();
		for (DezenaModa dm : dezenaModa) {
			if(retorno.size() < qtd){
				retorno.add(dm.getNumero());
			}
		}
		return retorno;
	}
	
	private ArrayList<Integer> getNumerosMenosJogadosDaDezena(int qtd, ArrayList<DezenaModa> dezenaModa){
		ArrayList<Integer> retorno = new ArrayList<Integer>();
		for (int i = dezenaModa.size()-1; i >= 0; i--) {
			if(retorno.size() < qtd){
				retorno.add(dezenaModa.get(i).getNumero());
			}
		}
		return retorno;
	}
	
	private ArrayList<Integer> getNumerosMaisJogados(int qtd){
		ArrayList<Integer> retorno = new ArrayList<Integer>();
		retorno.addAll(getNumerosMaisJogadosDaDezena(qtd, maisJogadosD1));
		retorno.addAll(getNumerosMaisJogadosDaDezena(qtd, maisJogadosD2));
		retorno.addAll(getNumerosMaisJogadosDaDezena(qtd, maisJogadosD3));
		retorno.addAll(getNumerosMaisJogadosDaDezena(qtd, maisJogadosD4));
		retorno.addAll(getNumerosMaisJogadosDaDezena(qtd, maisJogadosD5));
		retorno.addAll(getNumerosMaisJogadosDaDezena(qtd, maisJogadosD6));
		Collections.sort(retorno);
		return retorno;
	}
	
	private ArrayList<Integer> getNumerosMenosJogados(int qtd){
		ArrayList<Integer> retorno = new ArrayList<Integer>();
		retorno.addAll(getNumerosMenosJogadosDaDezena(qtd, maisJogadosD1));
		retorno.addAll(getNumerosMenosJogadosDaDezena(qtd, maisJogadosD2));
		retorno.addAll(getNumerosMenosJogadosDaDezena(qtd, maisJogadosD3));
		retorno.addAll(getNumerosMenosJogadosDaDezena(qtd, maisJogadosD4));
		retorno.addAll(getNumerosMenosJogadosDaDezena(qtd, maisJogadosD5));
		retorno.addAll(getNumerosMenosJogadosDaDezena(qtd, maisJogadosD6));
		Collections.sort(retorno);
		return retorno;
	}

	public ArrayList<Integer> getNumerosMaisJogados() {
		return numerosMaisJogados;
	}

	public void setNumerosMaisJogados(ArrayList<Integer> numerosMaisJogados) {
		this.numerosMaisJogados = numerosMaisJogados;
	}

	public ArrayList<Aposta> getListaDeApostasCaixa() {
		return listaDeApostasCaixa;
	}

	public void setListaDeApostasCaixa(ArrayList<Aposta> listaDeApostasCaixa) {
		this.listaDeApostasCaixa = listaDeApostasCaixa;
	}

	public ArrayList<Integer> getNumerosMenosJogados() {
		return this.numerosMenosJogados;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
}
class DezenaModa {
	private Integer dezena;
	private Integer numero;
	private Integer qtd;
	public Integer getDezena() {
		return dezena;
	}
	public void setDezena(Integer dezena) {
		this.dezena = dezena;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Integer getQtd() {
		return qtd;
	}
	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}
}

class DezenaModaComparator implements Comparator<DezenaModa> {
    public int compare(DezenaModa dm, DezenaModa outraDm) {
        return outraDm.getQtd().compareTo(dm.getQtd());
    }
}