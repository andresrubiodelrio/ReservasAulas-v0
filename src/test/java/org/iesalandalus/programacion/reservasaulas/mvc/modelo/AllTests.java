package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.*;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.negocio.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AulaTest.class, PermanenciaTest.class, ProfesorTest.class, ReservaTest.class, TramoTest.class,
	AulasTest.class, ProfesoresTest.class, ReservasTest.class })
public class AllTests {

}
