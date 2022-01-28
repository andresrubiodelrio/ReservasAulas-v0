package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.junit.Test;

public class ReservaTest {
	
	private static final String ERROR_EXCEPCION = "Debería haber saltado la excepción.";
	private static final String ERROR_NO_EXCEPCION = "No debería haber saltado la excepción.";
	private static final String nombreProfesor = "José Ramón";
	private static final String correo = "joseramon.jimenez@iesalandalus.org";
	private static final String nombreAula = "Salón de actos";
	private static final LocalDate dia = LocalDate.of(2018, 12, 1);
	
	Profesor profesor = new Profesor(nombreProfesor, correo);
	Aula aula = new Aula(nombreAula);
	Permanencia permanencia = new Permanencia(dia, Tramo.MANANA);

	@Test
	public void constructorTresParametrosValidoTest() {
		Reserva reserva = null;
		try {
			reserva = new Reserva(profesor, aula, permanencia);
			assertEquals(profesor, reserva.getProfesor());
			assertEquals(aula, reserva.getAula());
			assertEquals(permanencia, reserva.getPermanencia());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructorTresParametrosNoValidoTest() {
		Reserva reserva = null;
		try {
			reserva = new Reserva(null, aula, permanencia);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: La reserva debe estar a nombre de un profesor.", e.getMessage());
			assertNull(reserva);
		}
		try {
			reserva = new Reserva(profesor, null, permanencia);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: La reserva debe ser para un aula concreta.", e.getMessage());
			assertNull(reserva);
		}
		try {
			reserva = new Reserva(profesor, aula, null);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: La reserva se debe hacer para una permanencia concreta.", e.getMessage());
			assertNull(reserva);
		}
	}
	
	@Test
	public void constructorCopiaValidoTest() {
		Reserva reserva = new Reserva(profesor, aula, permanencia);
		Reserva reserva1;
		try {
			reserva1 = new Reserva(reserva);
			assertEquals(profesor, reserva1.getProfesor());
			assertEquals(aula, reserva1.getAula());
			assertEquals(permanencia, reserva1.getPermanencia());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructorCopiaNoValidoTest() {
		Reserva reserva = null;
		try {
			reserva = new Reserva(null);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: No se puede copiar una reserva nula.", e.getMessage());
			assertNull(reserva);
		}
	}
	
	@Test
	public void getTest() {
		Reserva reserva = new Reserva(profesor, aula, permanencia);
		assertEquals(profesor, reserva.getProfesor());
		assertEquals(aula, reserva.getAula());
		assertEquals(permanencia, reserva.getPermanencia());
	}
	
	@Test
	public void equalTest() {
		Reserva reserva = new Reserva(profesor, aula, permanencia);
		Reserva reserva1 = new Reserva(reserva);
		Reserva reserva2 = new Reserva(profesor, new Aula("Aula"), permanencia);
		assertNotEquals(reserva, null);
		assertNotEquals(reserva, "");
		assertEquals(reserva, reserva);
		assertEquals(reserva1, reserva);
		assertNotEquals(reserva, reserva2);
	}
	
	@Test
	public void hashCodeTest() {
		Reserva reserva = new Reserva(profesor, aula, permanencia);
		Reserva reserva1 = new Reserva(reserva);
		Reserva reserva2 = new Reserva(profesor, new Aula("Aula"), permanencia);
		assertEquals(reserva.hashCode(), reserva.hashCode());
		assertEquals(reserva1.hashCode(), reserva.hashCode());
		assertNotEquals(reserva.hashCode(), reserva2.hashCode());
	}
	
	@Test
	public void toStringTest() {
		Reserva reserva = new Reserva(profesor, aula, permanencia);
		String cadenaEsperada = "Profesor=nombre=José Ramón, correo=joseramon.jimenez@iesalandalus.org"
				+ ", aula=nombre Aula=Salón de actos, permanencia=dia=01/12/2018, tramo=Mañana";
		assertEquals(cadenaEsperada, reserva.toString());
	}

}
