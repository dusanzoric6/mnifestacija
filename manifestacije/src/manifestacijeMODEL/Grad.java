package manifestacijeMODEL;

public class Grad {
	protected int ptt;
	protected String naziv;
	
	public int getPtt() {
		return ptt;
	}
	public void setPtt(int ptt) {
		this.ptt = ptt;
	}
	public String getGrad() {
		return naziv;
	}
	public void setGrad(String grad) {
		this.naziv = grad;
	}
	public Grad(int ptt, String grad) {
		this.ptt = ptt;
		this.naziv = grad;
	}

	@Override
	public String toString() {
		return "Grrad [ptt= " + ptt + ", grad= " + naziv + "]";
	}
	
}
