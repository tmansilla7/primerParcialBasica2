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

	@Test
	public void cuandoQuieroReprucirUnaPlaylistSinCancionesObtengoUnResultadoNegativo(){
		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuario = new UsuarioGratuito (contrasenia,nombre);
		
		spotify.registrarUsuario(usuario);
		
		Playlist playlistVacia = new Playlist (usuario, "Playlist Vacia");
		
		spotify.crearPlaylist(playlistVacia);
		
		boolean resultado = playlistVacia.reproducir();
		
		assertFalse(resultado);
	}
	
	@Test
	public void cuandoQuieroReprucirUnaPlaylistConCancionesObtengoUnResultadoPositivo(){
		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuario = new UsuarioGratuito(contrasenia,nombre);
		
		spotify.registrarUsuario(usuario);
		
		Playlist playlistConCanciones = new Playlist (usuario, "Playlist Con Canciones");
		
		spotify.crearPlaylist(playlistConCanciones);
		
		LocalDateTime fechaAgregado = LocalDateTime.of(2025, 10, 3, 11, 30);
		Cancion cancion1 = new Cancion(1, "Heartless", "The Weeknd", Duration.ofMinutes(3).plusSeconds(18));
		Cancion cancion2 = new Cancion(2, "Moth To A Flame", "The Weeknd", Duration.ofMinutes(3).plusSeconds(54));
		Cancion cancion3 = new Cancion(3, "After Hours", "The Weeknd", Duration.ofMinutes(6).plusSeconds(01));
		
		assertTrue(playlistConCanciones.agregarCancion(cancion1, fechaAgregado));
		assertTrue(playlistConCanciones.agregarCancion(cancion2, fechaAgregado));
		assertTrue(playlistConCanciones.agregarCancion(cancion3, fechaAgregado));
		
		boolean resultado = playlistConCanciones.reproducir();
		
		assertTrue(resultado);
	}
	
	@Test
	public void dadoQueQuieroEliminarUnUsuarioExistenteObtengoUnResultadoExitoso() {
		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuario = new UsuarioGratuito(contrasenia,nombre);
		
		//el usuario esta registrado
		Boolean registrado = spotify.registrarUsuario(usuario);
		assertTrue(registrado);
		
		//eliminacion del usurio
		Boolean eliminado = spotify.eliminarUsuario(usuario);
		assertTrue(eliminado);
	}
	
	@Test
	public void dadoQueExisteUnaPlaylistPuedoRenombrarla(){
		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuario = new UsuarioGratuito (contrasenia,nombre);
		
		spotify.registrarUsuario(usuario);
		
		Playlist playlistARenombrar = new Playlist (usuario, "Playlist HOLA");
		
		spotify.crearPlaylist(playlistARenombrar);
		
		String nuevoNombre = "Playlist CHAU";
		playlistARenombrar.setNombre(nuevoNombre);
		
		assertEquals(nuevoNombre,playlistARenombrar.getNombre());
		
	}
	
	@Test
	public void dadoQueExisteUnaPlataformaDeMusicaPuedoCrearUnaPlaylistExitosamenteVerificandoQueSuNombreNoSeaNulo() {
		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuario = new UsuarioGratuito (contrasenia,nombre);
		
		spotify.registrarUsuario(usuario);
		
		Playlist playlistConNombre = new Playlist(usuario, "The Weeknd Playlist");
		Playlist playlistSinNombre = new Playlist(usuario, null);
		
		Boolean playlistCreada = spotify.crearPlaylist(playlistConNombre);
		Boolean playlistNoPudoSerCreada = spotify.crearPlaylist(playlistSinNombre);
		
		assertTrue(playlistCreada);
		assertFalse(playlistNoPudoSerCreada);
}
	@Test
	public void dadoQueElUsuarioNoEsPremiumNoPuedeCrearUnaPlaylistConMasDe10Canciones() {
		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuario = new UsuarioGratuito (contrasenia,nombre);
		
		spotify.registrarUsuario(usuario);
		
		Playlist playlistUsuarioComun = new Playlist (usuario, "Playlist De 11 canciones");
		
		spotify.crearPlaylist(playlistUsuarioComun );
		
		LocalDateTime fechaAgregado = LocalDateTime.of(2025, 10, 3, 11, 30);
		Cancion cancion1 = new Cancion(1, "Heartless", "The Weeknd", Duration.ofMinutes(3).plusSeconds(18));
		Cancion cancion2 = new Cancion(2, "Moth To A Flame", "The Weeknd", Duration.ofMinutes(3).plusSeconds(54));
		Cancion cancion3 = new Cancion(3, "After Hours", "The Weeknd", Duration.ofMinutes(6).plusSeconds(01));
		Cancion cancion4 = new Cancion(4, "Is There Someone Else", "The Weeknd", Duration.ofMinutes(3).plusSeconds(19));
		Cancion cancion5 = new Cancion(5, "Call Out My Name", "The Weeknd", Duration.ofMinutes(3).plusSeconds(48));
		Cancion cancion6 = new Cancion(6, "Less Than Zero", "The Weeknd", Duration.ofMinutes(3).plusSeconds(31));
		Cancion cancion7 = new Cancion(7, "Reminder", "The Weeknd", Duration.ofMinutes(3).plusSeconds(38));
		Cancion cancion8 = new Cancion(8, "Die For You", "The Weeknd", Duration.ofMinutes(4).plusSeconds(20));
		Cancion cancion9 = new Cancion(9, "Starboy", "The Weeknd", Duration.ofMinutes(3).plusSeconds(50));
		Cancion cancion10 = new Cancion(10, "Often", "The Weeknd", Duration.ofMinutes(4).plusSeconds(9));
		Cancion cancion11 = new Cancion(11, "Party Monster", "The Weeknd", Duration.ofMinutes(4).plusSeconds(9));
		
		assertTrue(playlistUsuarioComun.agregarCancion(cancion1, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion2, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion3, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion4, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion5, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion6, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion7, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion8, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion9, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion10, fechaAgregado));
		assertFalse(playlistUsuarioComun.agregarCancion(cancion11, fechaAgregado));
	}
	@Test
	public void dadoQueElUsuarioEsPremiumPuedeCrearUnaPlaylistConMasDe10Canciones() {
		String contrasenia = "martina456";
		String nombre = "Martina";
		Usuario usuarioPremium = new UsuarioPago (contrasenia,nombre);
		
		spotify.registrarUsuario(usuarioPremium);
		
		Playlist playlistUsuarioComun = new Playlist (usuarioPremium, "Playlist De 11 canciones");
		
		spotify.crearPlaylist(playlistUsuarioComun );
		
		LocalDateTime fechaAgregado = LocalDateTime.of(2025, 10, 3, 11, 30);
		Cancion cancion1 = new Cancion(1, "Heartless", "The Weeknd", Duration.ofMinutes(3).plusSeconds(18));
		Cancion cancion2 = new Cancion(2, "Moth To A Flame", "The Weeknd", Duration.ofMinutes(3).plusSeconds(54));
		Cancion cancion3 = new Cancion(3, "After Hours", "The Weeknd", Duration.ofMinutes(6).plusSeconds(01));
		Cancion cancion4 = new Cancion(4, "Is There Someone Else", "The Weeknd", Duration.ofMinutes(3).plusSeconds(19));
		Cancion cancion5 = new Cancion(5, "Call Out My Name", "The Weeknd", Duration.ofMinutes(3).plusSeconds(48));
		Cancion cancion6 = new Cancion(6, "Less Than Zero", "The Weeknd", Duration.ofMinutes(3).plusSeconds(31));
		Cancion cancion7 = new Cancion(7, "Reminder", "The Weeknd", Duration.ofMinutes(3).plusSeconds(38));
		Cancion cancion8 = new Cancion(8, "Die For You", "The Weeknd", Duration.ofMinutes(4).plusSeconds(20));
		Cancion cancion9 = new Cancion(9, "Starboy", "The Weeknd", Duration.ofMinutes(3).plusSeconds(50));
		Cancion cancion10 = new Cancion(10, "Often", "The Weeknd", Duration.ofMinutes(4).plusSeconds(9));
		Cancion cancion11 = new Cancion(11, "Party Monster", "The Weeknd", Duration.ofMinutes(4).plusSeconds(9));
		
		assertTrue(playlistUsuarioComun.agregarCancion(cancion1, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion2, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion3, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion4, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion5, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion6, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion7, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion8, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion9, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion10, fechaAgregado));
		assertTrue(playlistUsuarioComun.agregarCancion(cancion11, fechaAgregado));
	}
	
	@Test
	public void convertirUsuarioGratuitoAPagoExitoso() {
		Usuario nuevo = new UsuarioGratuito("12345", "Tomas Mansilla");
		nuevo.setSaldo(1500.0d);
		spotify.registrarUsuario(nuevo);
		boolean cambio = spotify.cambiarTipoDeUsuario2(nuevo);
		
		assertEquals(true, cambio);
	}
	
	@Test
	public void convertirUsuarioGratuitoAPagoInvalido() {
		Usuario nuevo = new UsuarioGratuito("12345", "Tomas Mansilla");
		nuevo.setSaldo(1000.0d);
		spotify.registrarUsuario(nuevo);
		boolean cambio = spotify.cambiarTipoDeUsuario2(nuevo);
		
		assertEquals(false, cambio);
	}
	
	@Test
	public void convertirUsuarioPagoAGratuitoExitoso() {
		Usuario nuevo = new UsuarioPago("12345", "Tomas Mansilla");
		nuevo.setSaldo(1000.0d);
		spotify.registrarUsuario(nuevo);
		boolean cambio = spotify.cambiarTipoDeUsuario2(nuevo);
		
		assertEquals(true, cambio);
	}
	
	@Test
	public void convertirUsuarioPagoAGratuitoInvalido() {
		Usuario nuevo = new UsuarioPago("12345", "Tomas Mansilla");
		nuevo.setSaldo(1500.0d);
		spotify.registrarUsuario(nuevo);
		boolean cambio = spotify.cambiarTipoDeUsuario2(nuevo);
		
		assertEquals(false, cambio);
	}
}