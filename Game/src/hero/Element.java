package hero;

public class Element {
	
	public final static Element FONDAMENTAL = new Element("Neutre", 0);
	public final static Element BLIZZ = new Element("Blizz", 1);
	public final static Element IGNIS = new Element("Ignis", 1);
	public final static Element ZEPHYR = new Element("Zephyr", 2);
	public final static Element SISMA = new Element("Sisma", 0);
	
	public int nbJumpMax;
	public String name;

	private Element(String name, int nbJumpMax){
		this.name = name;
		this.nbJumpMax = nbJumpMax;
	}
}
