package unlam.edu.ar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class GestionMusicaTest {
	private PlataformaMusica spotify;
	
	@Before
	public void init() {
		this.spotify = new PlataformaMusica();
	}
	
	@Test
	public void dadoQueExisteUnaPlataformaDeMusicaPuedoRegistrarUnUsuarioExitosamenteVerificandoQueSuNombreNoSeaNulo() {
		String contrasenia = "juli123";
		String nombre = "Julieta";
		Usuario usuario = new UsuarioPago(contrasenia, nombre);
		
		String contrasenia2 = "hola345";
		String nombre2 = "";
		Usuario otroUsuario = new UsuarioPago(contrasenia2, nombre2);
		
		Boolean registrado = spotify.registrarUsuario(usuario);
		Boolean registrado2 = spotify.registrarUsuario(otroUsuario);
		
		assertTrue(registrado);
		assertFalse(registrado2);
	}
	
	@Test
	public void dadoQueExisteUnaPlataformaDeMusicaPuedoCrearUnaPlaylistExitosamente() {
		String contrasenia = "juli123";
		String nombre = "Julieta";
		Usuario usuario = new UsuarioPago(contrasenia, nombre);
		
		spotify.registrarUsuario(usuario);
		
		Playlist nuevaPlaylist = new Playlist(usuario, "MI NUEVA PLAYLIST");
		
		Boolean creada = spotify.crearPlaylist(nuevaPlaylist);
		
		assertTrue(creada);
	}
	@Test
	public void dadoQueSeCreaUnaPlaylistDeUnUsuarioNoRegistradoObtengoUnResultadoNegativo() {
		String contrasenia = "juli123";
		String nombre = "Julieta";
		Usuario usuario = new UsuarioPago(contrasenia, nombre);
		
		Playlist nuevaPlaylist = new Playlist(usuario, "MI NUEVA PLAYLIST");
		
		Boolean creada = spotify.crearPlaylist(nuevaPlaylist);
		
		assertFalse(creada);
	}
	@Test
	public void dadoQueExisteUnaPlaylistPuedoAgregarUnaCancion() {
		String contrasenia = "juli123";
		String nombre = "Julieta";
		Usuario usuario = new UsuarioPago(contrasenia, nombre);
		
		spotify.registrarUsuario(usuario);
		
		Playlist nuevaPlaylist = new Playlist(usuario, "MI NUEVA PLAYLIST");
		
		spotify.crearPlaylist(nuevaPlaylist);
		
		Cancion cancion = new Cancion(1, "Exclusive", "Emilia Mernes", Duration.ofMinutes(2).plusSeconds(1));
		
		LocalDateTime fechaAgregado = LocalDateTime.of(2025, 10, 3, 15, 00);
		assertTrue(nuevaPlaylist.agregarCancion(cancion, fechaAgregado));
	}
	@Test
	public void dadoQueExisteUnaPlaylistYQuieroAgregarUnaCancionRepetidaObtengoUnResultadoNegativo() {
		String contrasenia = "juli123";
		String nombre = "Julieta";
		Usuario usuario = new UsuarioPago(contrasenia, nombre);
		
		spotify.registrarUsuario(usuario);
		
		Playlist nuevaPlaylist = new Playlist(usuario, "MI NUEVA PLAYLIST");
		
		spotify.crearPlaylist(nuevaPlaylist);
		
		Cancion cancion = new Cancion(1, "Exclusive", "Emilia Mernes", Duration.ofMinutes(2).plusSeconds(1));
		
		LocalDateTime fechaAgregado = LocalDateTime.of(2025, 10, 3, 11, 30);
		assertTrue(nuevaPlaylist.agregarCancion(cancion, fechaAgregado));
		assertFalse(nuevaPlaylist.agregarCancion(cancion, fechaAgregado));
	}
	@Test
	public void dadoQueExisteUnaPlaylistConCancionesPuedoCalcularSuDuracionTotal() {
		String contrasenia = "juli123";
		String nombre = "Julieta";
		Usuario usuario = new UsuarioPago(contrasenia, nombre);
		
		spotify.registrarUsuario(usuario);
		
		Playlist nuevaPlaylist = new Playlist(usuario, "MI NUEVA PLAYLIST");
		
		spotify.crearPlaylist(nuevaPlaylist);
		
		LocalDateTime fechaAgregado = LocalDateTime.of(2025, 10, 3, 11, 30);
		Cancion cancion1 = new Cancion(1, "Exclusive", "Emilia Mernes", Duration.ofMinutes(2).plusSeconds(1));
		Cancion cancion2 = new Cancion(2, "Dos mil 16", "Bad Bunny", Duration.ofMinutes(3).plusSeconds(29));
		Cancion cancion3 = new Cancion(3, "No se ve", "Emilia Mernes", Duration.ofMinutes(3).plusSeconds(27));
		
		assertTrue(nuevaPlaylist.agregarCancion(cancion1, fechaAgregado));
		assertTrue(nuevaPlaylist.agregarCancion(cancion2, fechaAgregado));
		assertTrue(nuevaPlaylist.agregarCancion(cancion3, fechaAgregado));
		
		Duration valorEsperado = nuevaPlaylist.calcularDuracionTotal();
		Duration valorObtenido = Duration.ofMinutes(8).plusSeconds(57);
		
		assertEquals(valorEsperado, valorObtenido);
	}
	

}
