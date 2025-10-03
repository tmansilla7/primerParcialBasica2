package unlam.edu.ar;

import java.util.ArrayList;
import java.util.HashSet;

public class PlataformaMusica {
	
	private HashSet<Usuario> usuarios;
	private ArrayList<Playlist> playlists;
	
	public PlataformaMusica() {
		this.usuarios = new HashSet<>();
		this.playlists = new ArrayList<>();
	}

	public Boolean registrarUsuario(Usuario usuario) {
		if(usuario.getNombre() != null && !usuario.getNombre().isBlank() && usuario.getContrasenia() != null && !usuario.getContrasenia().isBlank()) {
		return this.usuarios.add(usuario);
		}
		return false;
	}

	public Boolean crearPlaylist(Playlist nuevaPlaylist) {
		for (Usuario usuario : usuarios) {
			if(usuario.equals(nuevaPlaylist.getUsuario())) {
				this.playlists.add(nuevaPlaylist);
				return true;
			}
		}
		return false;
	}
	

}
