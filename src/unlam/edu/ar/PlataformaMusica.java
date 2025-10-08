package unlam.edu.ar;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.plaf.basic.BasicCheckBoxMenuItemUI;

public class PlataformaMusica {
	
	private HashSet<Usuario> usuarios;
	private ArrayList<Playlist> playlists;
	private final Double cuotaMensual;
	
	public PlataformaMusica() {
		this.usuarios = new HashSet<>();
		this.playlists = new ArrayList<>();
		this.cuotaMensual = 1500.0d;
	}

	public Boolean registrarUsuario(Usuario usuario) {
		if(usuario.getNombre() != null && !usuario.getNombre().isBlank() && usuario.getContrasenia() != null && !usuario.getContrasenia().isBlank()) {
		return this.usuarios.add(usuario);
		}
		return false;
	}

	public Boolean eliminarUsuario(Usuario usuario) {
		return this.usuarios.remove(usuario);
	}
	public Boolean crearPlaylist(Playlist nuevaPlaylist) {
		if(nuevaPlaylist.getNombre() == null || nuevaPlaylist.getNombre().isBlank()) {
			return false;
		}
		for (Usuario usuario : usuarios) {
			if(usuario.equals(nuevaPlaylist.getUsuario())) {
				this.playlists.add(nuevaPlaylist);
				return true;
			}
		}
		return false;
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
	
	public HashSet<Usuario> getUsuarios(){
		return this.usuarios;
	}

	@Override
	public String toString() {
		return "PlataformaMusica [usuarios=" + usuarios + ", playlists=" + playlists + ", cuotaMensual=" + cuotaMensual
				+ "]";
	}
	
}
