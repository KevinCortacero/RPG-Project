import java.awt.Canvas;


public class Main extends Canvas implements Runnable {

	private boolean running;
	private Interface i;

	private Main(){
		this.setSize(600, 600);
		this.i = new Interface();
	}
	public static void main(String[] args) {
		BDD.getBDD();
		Main main = new Main();
		main.start();
	}

	@Override
	public void run() {
		long before = System.nanoTime();
		long now = 0L;
		double elapsed = 0.0;
		double tickTime = 1000000000.0 / 60.0;

		int frames = 0;
		int ticks = 0;
		int tickTimer = 0;
		while(this.running){
			now = System.nanoTime();
			elapsed = now - before;

			boolean tick = false;
			if (elapsed >= tickTime) {
				ticks ++;
				this.update();
				tickTimer ++;
				if (tickTimer % 60 == 0){
					tick = true;
					tickTimer = 0;
				}
				before += tickTime;
			}
			else{
				this.render();
				frames ++;
			}
			if(tick){
				System.out.println(ticks + " tps, " + frames + " fps");
				ticks = 0;
				frames = 0;
			}
		}
	}

	public void update(){

	}

	public void render(){
		this.i.render();
	}
	
	public void start(){
		this.running = true;
		new Thread(this).start();
	}

}