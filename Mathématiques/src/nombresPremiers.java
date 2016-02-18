
public class nombresPremiers {

	public static void main(String[] args) {
		int nombres[] = new int[1000000];
		int nombre = 3;
		nombres[0] = 2;
		int compteurNBPremier = 1;
		
		while(compteurNBPremier < nombres.length){
			if (testerNombre(nombres, nombre, compteurNBPremier)){
				nombres[compteurNBPremier] = nombre;
				compteurNBPremier ++;
				System.out.println(nombre);
			}
			nombre +=2;
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
