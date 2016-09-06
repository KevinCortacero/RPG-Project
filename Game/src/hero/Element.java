package hero;

public enum Element {
	
   FONDAMENTAL("neutre"),
   BLIZZ("blizz"),
   IGNIS("ignis"),
   ZEPHYR("z�phyr"),
   SISMA("sisma");
	
	private String nom;

	private Element(String nom) {
		this.nom = nom;
	}
	
	public String toString(){
		return this.nom;
	}
}
