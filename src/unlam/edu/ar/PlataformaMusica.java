package unlam.edu.ar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;

import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;

public class PlataformaMusica {

	private HashSet<Usuario> usuarios;
	private ArrayList<Playlist> playlists;

	private List<Reproduccion> reproducciones;
	private ListIterator<Reproduccion> iterador;
	private final Double cuotaMensual;

	public PlataformaMusica() {
		this.usuarios = new HashSet<>();
		this.playlists = new ArrayList<>();
		this.reproducciones = new ArrayList<>();
		this.iterador = null;
		this.cuotaMensual = 1500.0d;
	}

	
	


	public Boolean registrarUsuario(Usuario usuario) {
		if (usuario.getNombre() != null && !usuario.getNombre().isBlank() && usuario.getContrasenia() != null
				&& !usuario.getContrasenia().isBlank()) {
			return this.usuarios.add(usuario);
		}
		return false;
	}

	public Boolean eliminarUsuario(Usuario usuario) {
		return this.usuarios.remove(usuario);
	}

	public Boolean crearPlaylist(Playlist nuevaPlaylist) {
		if (nuevaPlaylist.getNombre() == null || nuevaPlaylist.getNombre().isBlank()) {
			return false;
		}
		for (Usuario usuario : usuarios) {
			if (usuario.equals(nuevaPlaylist.getUsuario())) {
				this.playlists.add(nuevaPlaylist);
				return true;
			}
		}
		return false;
	}

	
    public Boolean reproducir(Cancion cancion, LocalDateTime fechaDeInicio) {
        if (fechaDeInicio == null) {
            fechaDeInicio = LocalDateTime.now();
        }

        Reproduccion reproduccion = new Reproduccion(cancion, fechaDeInicio, null);
        boolean agregada = this.reproducciones.add(reproduccion);

        
        iterador = reproducciones.listIterator(reproducciones.size());
        iterador.previous(); 
        return agregada;
    }

    
    public Boolean reproducirNuevaCancion(Cancion nuevaCancion) {
    	
        LocalDateTime fechaDeInicio;
        Reproduccion actual = obtenerCancionActual();

        if (actual != null) {
            
            actual.setFin(actual.getInicio().plus(actual.getCancion().getDuracion()));
            fechaDeInicio = actual.getFin();
            
        } else {
        	
            fechaDeInicio = LocalDateTime.now();
        }

        Boolean reproducido = reproducir(nuevaCancion, fechaDeInicio);
        
        return reproducido;
    }

   
    public Reproduccion obtenerCancionActual() {
    	
        if (iterador == null) {
        	return null;
        }
        	
        int posicion = iterador.nextIndex();

        if (posicion == reproducciones.size()) {
        	
        	posicion--; 
        }
        
        if (posicion >= 0 && posicion < reproducciones.size()) {
        	
            return reproducciones.get(posicion);
        }
        
        
        return null;
    }

   
    public Boolean cambiarALaSiguienteCancion() {
    	
    	Boolean cambiado = true;
    	
        if (iterador == null || !iterador.hasNext()) {
        	cambiado = false;
        }

        Reproduccion actual = obtenerCancionActual();
        
        if (actual != null) {
        	actual.setFin(LocalDateTime.now());
        }

        Reproduccion siguiente = iterador.next();
        siguiente.setFin(null); 
        
        return cambiado;
    }

    
    public Boolean cambiarALaCancionAnterior() {
    	
    	Boolean cambiado = true;
    	
        if (iterador == null || !iterador.hasPrevious()) {
        	cambiado = false;
        }

        Reproduccion actual = obtenerCancionActual();
        if (actual != null) {
        	
        	actual.setFin(LocalDateTime.now());
        }

        Reproduccion anterior = iterador.previous();
        anterior.setFin(null); 

        return cambiado;
    }

  
    
	
	public Boolean cambiarTipoDeUsuario(Usuario user) {
		for(Usuario u:usuarios) {
			if(u.equals(user)) {
				if(user instanceof UsuarioGratuito && user.getSaldo() >= cuotaMensual) {
					user = new UsuarioPago(user.getContrasenia(), user.getNombre());					
				}
				if(user instanceof UsuarioPago) {
					user = new UsuarioGratuito(user.getContrasenia(), user.getNombre());
				}
				
				eliminarUsuario(u);
				usuarios.add(user);
				return true;
			}
		}
		
		return false;
	}
	
	public Boolean cambiarTipoDeUsuario2(Usuario user) {
		Boolean resultado = false;
		
		for(Usuario u:usuarios) {
			if(!u.equals(user)) {
				return resultado;
			}
			else {
				if(user instanceof UsuarioGratuito && user.getSaldo() >= cuotaMensual) {
					user = new UsuarioPago(user.getContrasenia(), user.getNombre());					
					resultado = true;
				}
				if(user instanceof UsuarioPago && user.getSaldo() < cuotaMensual) {
					user = new UsuarioGratuito(user.getContrasenia(), user.getNombre());
					resultado = true;
				}
				
				eliminarUsuario(u);
				usuarios.add(user);
				
			}
		}
		
		return resultado;
	}
	
	public Boolean borrarPlaylist(Playlist playlist) {
        for (Usuario usuario : usuarios) {
            if (usuario.equals(playlist.getUsuario())) {
                this.playlists.remove(playlist);
                return true;
            }
        }
        return false;
    }
	



	public List<Cancion> obtenerCancionesReproducidasEntre(LocalDateTime desde, LocalDateTime hasta) {

		List<Cancion> canciones = new ArrayList<Cancion>();

		for (Reproduccion reproduccion : this.reproducciones) {

			LocalDateTime inicio = reproduccion.getInicio();
			LocalDateTime fin = reproduccion.getFin();

			if (fin == null) {
				fin = LocalDateTime.now();
			}

			if ((inicio.isBefore(hasta) || inicio.isEqual(hasta)) && (fin.isAfter(desde) || fin.isEqual(desde))) {
				canciones.add(reproduccion.getCancion());
			}
		}

		return canciones;

	}

	

	public HashSet<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(HashSet<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public ArrayList<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(ArrayList<Playlist> playlists) {
		this.playlists = playlists;
	}

	public List<Reproduccion> getReproducciones() {
		return reproducciones;
	}

	public void setReproducciones(List<Reproduccion> reproducciones) {
		this.reproducciones = reproducciones;
	}

	public ListIterator<Reproduccion> getIterador() {
		return iterador;
	}

	public void setIterador(ListIterator<Reproduccion> iterador) {
		this.iterador = iterador;
	}


	@Override
	public String toString() {
		return "PlataformaMusica [usuarios=" + usuarios + ", playlists=" + playlists + ", cuotaMensual=" + cuotaMensual
				+ "]";
	}
	

}
