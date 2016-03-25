package manifestacijeMODEL;

public class Manifestacija {
	protected int id;
	protected String naziv;
	protected int brojPosetilaca;
	protected Grad grad;
	@Override
	public String toString() {
		return "manifestacija [id= " + id + ", naziv= " + naziv
				+ ", brojPosetilaca= " + brojPosetilaca + ", grad= " + grad.getGrad() + "("+grad.getPtt()+")]";
	}
	
	public Manifestacija(int id, String naziv, int brojPosetilaca, Grad grad) {
		this.id = id;
		this.naziv = naziv;
		this.brojPosetilaca = brojPosetilaca;
		this.grad = grad;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getBrojPosetilaca() {
		return brojPosetilaca;
	}
	public void setBrojPosetilaca(int brojPosetilaca) {
		this.brojPosetilaca = brojPosetilaca;
	}
	public Grad getGrad() {
		return grad;
	}
	public void setGrad(Grad grad) {
		this.grad = grad;
	}
	
	
}
