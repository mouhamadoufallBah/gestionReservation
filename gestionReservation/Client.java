public class Client {
	//attribut
	private int nci;
	private String nomComplet;
	private String tel;
	private String addresse;
	private String email;

	//debConstructeur
	public Client(){

	}
	public Client(int nci, String nomComplet, String tel, String addresse, String email){
		this.setNci(nci);
		this.setNomComplet(nomComplet);
		this.setTel(tel);
		this.setAddresse(addresse);
		this.setEmail(email);
	}

//finconstructeur

	//debgetter__setter
	//nci
	public int getNci() {
		return nci;
	}
	public void setNci(int nci) {
		this.nci = nci;
	}
	//nomComplet
	public String getNomComplet() {
		return nomComplet;
	}
	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}
	//tel
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	//addresse
	public String getAddresse() {
		return addresse;
	}
	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}
	//email
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//finGetterSetter







}
