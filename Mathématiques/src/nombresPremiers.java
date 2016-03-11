import java.util.HashMap;
import java.util.Map;


public class nombresPremiers {

	public static void main(String[] args) {
		int taille = 1000;
		int nombres[] = new int[taille];
		int nombre = 3;
		nombres[0] = 2;
		int compteurNBPremier = 1;
		Map<Integer, Integer> tabDernierChiffre = new HashMap<Integer, Integer>();
		while(compteurNBPremier < nombres.length){
			if (testerNombre(nombres, nombre, compteurNBPremier)){
				nombres[compteurNBPremier] = nombre;
				compteurNBPremier ++;
			}
			nombre +=2;
		}
		int save = 0;
		for (int i = 0; i < nombres.length; i ++ ){
			String nbs = String.valueOf(nombres[i]);
			String cs = String.valueOf(nbs.charAt(nbs.length()-1));
			int c = Integer.valueOf(cs);
		//	System.out.println("[" + (i+1) + "] --> " + nbs + " | " + c + " | " + (nombres[i]%c) + " | ");
			tabDernierChiffre.put(c,(tabDernierChiffre.get(c) == null ? 1 : tabDernierChiffre.get(c)+1));
		}
		
		for ( int indice : tabDernierChiffre.keySet()){
			System.out.println(indice +" : "+ tabDernierChiffre.get(indice));
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
