package unlam.edu.ar;


import java.util.ArrayList;

public class Reproductor {

    private Playlist playlist;
    private ArrayList<Cancion> listaDeCanciones;
    private int indiceActual;
    private Cancion cancionActual;
    private boolean reproduciendo;

    public Reproductor(Playlist playlist) {
        this.playlist = playlist;
        this.listaDeCanciones = playlist.getCanciones();
        this.indiceActual = -1;  
        this.cancionActual = null;
        this.reproduciendo = false;
    }

    public Cancion reproducir() {
    	
        if (!reproduciendo) {
        	
            if (this.cancionActual == null && !listaDeCanciones.isEmpty()) {
                this.indiceActual = 0;
                this.cancionActual = listaDeCanciones.get(indiceActual);
            }
            
            this.reproduciendo = true;
        }
        
        return this.cancionActual;
    }

    public void pausarCancion() {
        this.reproduciendo = false;
    }

    public void reanudarCancion() {
    	
        if (this.cancionActual != null) {
        	
        	this.reproduciendo = true;
        }
        	
    }

    public Cancion pasarALaSiguienteCancion() {
    	
        if (this.indiceActual + 1 < listaDeCanciones.size()) {
        	
            this.indiceActual++;
            
            this.cancionActual = listaDeCanciones.get(indiceActual);
            this.reproduciendo = true;
            
            return cancionActual;
        }
        
        return null;
    }
    
    

    public Cancion volverALaCancionAnterior() {
    	
        if (indiceActual - 1 >= 0) {
     
            indiceActual--;
            
            cancionActual = listaDeCanciones.get(indiceActual);
            reproduciendo = true;
            
            return cancionActual;
        }
        
        return null;
    }

    
    public Cancion getCancionActual() {
        return cancionActual;
    }

    public boolean getReproduciendo() {
        return reproduciendo;
    }
}