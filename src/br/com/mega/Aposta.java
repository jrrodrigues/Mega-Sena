package br.com.mega;

public class Aposta {
	
	private Integer numSorteio;
	private Integer dez1;
	private Integer dez2;
	private Integer dez3;
	private Integer dez4;
	private Integer dez5;
	private Integer dez6;
	
	public Integer getNumSorteio() {
		return numSorteio;
	}
	public void setNumSorteio(Integer numSorteio) {
		this.numSorteio = numSorteio;
	}
	public Integer getDez1() {
		return dez1;
	}
	public void setDez1(Integer dez1) {
		this.dez1 = dez1;
	}
	public Integer getDez2() {
		return dez2;
	}
	public void setDez2(Integer dez2) {
		this.dez2 = dez2;
	}
	public Integer getDez3() {
		return dez3;
	}
	public void setDez3(Integer dez3) {
		this.dez3 = dez3;
	}
	public Integer getDez4() {
		return dez4;
	}
	public void setDez4(Integer dez4) {
		this.dez4 = dez4;
	}
	public Integer getDez5() {
		return dez5;
	}
	public void setDez5(Integer dez5) {
		this.dez5 = dez5;
	}
	public Integer getDez6() {
		return dez6;
	}
	public void setDez6(Integer dez6) {
		this.dez6 = dez6;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dez1 == null) ? 0 : dez1.hashCode());
		result = prime * result + ((dez2 == null) ? 0 : dez2.hashCode());
		result = prime * result + ((dez3 == null) ? 0 : dez3.hashCode());
		result = prime * result + ((dez4 == null) ? 0 : dez4.hashCode());
		result = prime * result + ((dez5 == null) ? 0 : dez5.hashCode());
		result = prime * result + ((dez6 == null) ? 0 : dez6.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aposta other = (Aposta) obj;
		if (dez1 == null) {
			if (other.dez1 != null)
				return false;
		} else if (!dez1.equals(other.dez1))
			return false;
		if (dez2 == null) {
			if (other.dez2 != null)
				return false;
		} else if (!dez2.equals(other.dez2))
			return false;
		if (dez3 == null) {
			if (other.dez3 != null)
				return false;
		} else if (!dez3.equals(other.dez3))
			return false;
		if (dez4 == null) {
			if (other.dez4 != null)
				return false;
		} else if (!dez4.equals(other.dez4))
			return false;
		if (dez5 == null) {
			if (other.dez5 != null)
				return false;
		} else if (!dez5.equals(other.dez5))
			return false;
		if (dez6 == null) {
			if (other.dez6 != null)
				return false;
		} else if (!dez6.equals(other.dez6))
			return false;
		return true;
	}
}
