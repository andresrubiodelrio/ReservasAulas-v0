package org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Tramo;
import org.junit.Test;

public class PermanenciaTest {
	
	private static final String ERROR_EXCEPCION = "Debería haber saltado la excepción.";
	private static final String ERROR_NO_EXCEPCION = "No debería haber saltado la excepción.";
	
	private LocalDate dia = LocalDate.of(2018, 12, 1); 
	Permanencia permanencia = new Permanencia(dia, Tramo.MANANA);

	@Test
	public void constructorValidoTest() {
		try {
			Permanencia permanencia = new Permanencia(dia, Tramo.MANANA);
			assertEquals(2018, permanencia.getDia().getYear());
			assertEquals(12, permanencia.getDia().getMonthValue());
			assertEquals(1, permanencia.getDia().getDayOfMonth());
			assertEquals(Tramo.MANANA, permanencia.getTramo());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructorNoValidoTest() {
		Permanencia permanencia = null;
		try {
			permanencia = new Permanencia(LocalDate.of(2018, 12, 1), null);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: El tramo de una permanencia no puede ser nulo.", e.getMessage());
			assertNull(permanencia);
		}
		try {
			permanencia = new Permanencia(null, Tramo.MANANA);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: El día de una permanencia no puede ser nulo.", e.getMessage());
			assertNull(permanencia);
		}
	}
	
	@Test
	public void constructorCopiaValidoTest() {
		Permanencia otraPermanencia;
		try {
			otraPermanencia = new Permanencia(permanencia);
			assertEquals(dia, otraPermanencia.getDia());
			assertEquals(Tramo.MANANA, otraPermanencia.getTramo());
		} catch (IllegalArgumentException e) {
			fail(ERROR_NO_EXCEPCION);
		}
	}
	
	@Test
	public void constructoCopiaNoValidoTest() {
		Permanencia otraPermanencia = null;
		try {
			otraPermanencia = new Permanencia(null);
			fail(ERROR_EXCEPCION);
		} catch (NullPointerException e) {
			assertEquals("ERROR: No se puede copiar una permanencia nula.", e.getMessage());
			assertNull(otraPermanencia);
		}
	}
	
	@Test
	public void getTest() {
		assertEquals(dia, permanencia.getDia());
		assertEquals(Tramo.MANANA, permanencia.getTramo());
	}
	
	@Test
	public void equalsTest() {
		Permanencia permanencia1 = new Permanencia(dia, Tramo.MANANA);
		Permanencia permanencia2 = new Permanencia(dia, Tramo.TARDE);
		assertNotEquals(permanencia1, null);
		assertNotEquals(permanencia1, "");
		assertEquals(permanencia1, permanencia1);
		assertEquals(permanencia, permanencia1);
		assertNotEquals(permanencia1, permanencia2);
	}
	
	@Test
	public void hashCodeTest() {
		Permanencia permanencia1 = new Permanencia(dia, Tramo.MANANA);
		Permanencia permanencia2 = new Permanencia(dia, Tramo.TARDE);
		assertEquals(permanencia.hashCode(), permanencia.hashCode());
		assertEquals(permanencia.hashCode(), permanencia1.hashCode());
		assertNotEquals(permanencia1.hashCode(), permanencia2.hashCode());
	}
	
	@Test
	public void toStringTest() {
		assertEquals("dia=01/12/2018, tramo=Mañana", permanencia.toString());
	}

}
