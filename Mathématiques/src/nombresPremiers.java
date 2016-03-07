
public class nombresPremiers {

	public static void main(String[] args) {
		int nombres[] = new int[100];
		int nombre = 3;
		nombres[0] = 2;
		int compteurNBPremier = 1;
		
		while(compteurNBPremier < nombres.length){
			if (testerNombre(nombres, nombre, compteurNBPremier)){
				nombres[compteurNBPremier] = nombre;
				compteurNBPremier ++;
			}
			nombre +=2;
		}
		int save = 0;
		for (int i = 0; i < nombres.length; i ++ ){
			int nb = 0;
			if (i < nombres.length-1){
				nb = nombres[i] + (nombres[i+1] - nombres[i])/2;
				System.out.println("[" + (i+2) + "] --> " + nombres[i] + " | BLEU : "  +  nb + " | ECART : "  +  (nb-save)) ;
			}
			save = nb;
		}
	}
	
	private static boolean testerNombre(int nombres[], int nombre, int max){
		for (int i = 0; i < max; i ++ ){
			if (nombres[i] > (int)Math.sqrt(nombre))
				break;
			if (nombre % nombres[i] == 0)
				return false;
		}
		return true;
	}
}
