package unlam.edu.ar;

import java.time.LocalDateTime;



public class Reproduccion {
	

	private LocalDateTime inicio;
	private LocalDateTime fin;
	

	private Cancion cancion;
	
	
	public Reproduccion(Cancion cancion, LocalDateTime inicio, LocalDateTime fin) {
	
		this.inicio = inicio;
		
		this.cancion = cancion;
		
		this.fin = fin;
		
	}


	public LocalDateTime getInicio() {
		return inicio;
	}


	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}


	public LocalDateTime getFin() {
		return fin;
	}


	public void setFin(LocalDateTime fin) {
		this.fin = fin;
	}





	public Cancion getCancion() {
		return cancion;
	}


	public void setCancion(Cancion cancion) {
		this.cancion = cancion;
	}



	
	
	
	
	
	
	
	
	
}
