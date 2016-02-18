
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
		for (int i = 0; i < nombres.length; i ++ ){
			String nbs = String.valueOf(nombres[i]);
			String cs = String.valueOf(nbs.charAt(nbs.length()-1));
			int c = Integer.valueOf(cs);
			System.out.println("[" + (i+1) + "] --> " + nbs + " | " + c + " | " + (nombres[i]%c) + " | ");
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
