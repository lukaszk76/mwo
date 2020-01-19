package samochody;
import org.junit.Test;
import org.junit.Before;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class KierowcaTest {
	
	Kierowca kierowca;
	
	@Before
	public void inicjujTesty()
	{
		kierowca = new Kierowca(12345678945l, "John", "Doe");
	}
	
	@Test
	public void testDaneKierowcy()
	{
		
		assertEquals(kierowca.getImieINazwisko(),"John Doe");
		assertEquals(kierowca.getPesel(), 12345678945l);
	}
	
	@Test
	public void testAmortyzacjaSamochodu()
	{
		Samochod mockSamochod = mock(Samochod.class);
		
		//zakladamy, ze dodawany samochod ma wartosc 10000 i amortyzacje 10 %
		
		mockSamochod.amortyzuj();
		expect(mockSamochod.getWartosc()).andReturn(9000.0f);
		replay(mockSamochod);
		
		kierowca.dodajSamochod(mockSamochod);
		kierowca.amortyzujSamochody();

		assertEquals(mockSamochod.getWartosc(),9000.0f, 0.0f);
		verify(mockSamochod);


	}
	
	@Test
	public void testAmortyzacjaSamochoduNull()
	{
		kierowca.amortyzujSamochody();

	}
	
	@Test
	public void testDrukujSamochody()
	{
		Samochod mockSamochod1 = mock(Samochod.class);
		Samochod mockSamochod2 = mock(Samochod.class);
		
		expect(mockSamochod1.getRejestracja()).andReturn("WE032WP");
		expect(mockSamochod1.getWartosc()).andReturn(20000.0f);
		
		expect(mockSamochod2.getRejestracja()).andReturn("SM07359");
		expect(mockSamochod2.getWartosc()).andReturn(22000.0f);
		
		replay(mockSamochod1);
		replay(mockSamochod2);
		
		kierowca.dodajSamochod(mockSamochod1);
		kierowca.dodajSamochod(mockSamochod2);
	
		kierowca.drukujSamochody();

		verify(mockSamochod1);
		verify(mockSamochod2);
	}
	
	@Test
	public void testZnajdzSamochod() {
		
		Samochod mockSamochod1 = mock(Samochod.class);
		Samochod mockSamochod2 = mock(Samochod.class);
		
		expect(mockSamochod1.getRejestracja()).andReturn("KR032WP");	
		expect(mockSamochod2.getRejestracja()).andReturn("WE07350");

		replay(mockSamochod1);
		replay(mockSamochod2);
		
		
		kierowca.dodajSamochod(mockSamochod1);
		kierowca.dodajSamochod(mockSamochod2);
		
		assertSame(kierowca.znajdzSamochod("WE07350"), mockSamochod2);
		verify(mockSamochod1);
		verify(mockSamochod2);
		
		//assertEquals(kierowca.znajdzSamochod("WE032WP"),null);
		
	}
	
	@Test
	public void testZnajdzSamochodNull() {
		Samochod s = kierowca.znajdzSamochod("123456");
		assertSame(s, null);
	}
	
	@Test
	public void testObliczSredniaWartoscSamochodu() {
		Samochod mockSamochod1 = mock(Samochod.class);
		Samochod mockSamochod2 = mock(Samochod.class);
		
		expect(mockSamochod1.getWartosc()).andReturn(20000.0f);	
		expect(mockSamochod2.getWartosc()).andReturn(10000.0f);

		replay(mockSamochod1);
		replay(mockSamochod2);
		
		kierowca.dodajSamochod(mockSamochod1);
		kierowca.dodajSamochod(mockSamochod2);

		assertEquals(kierowca.obliczSredniaWartoscSamochodu(), 15000.0f, 0.0f);
		verify(mockSamochod1);
		verify(mockSamochod2);
	}

	@Test
	public void testObliczSredniaWartoscSamochoduNull() {

		assertEquals(kierowca.obliczSredniaWartoscSamochodu(), 0.0f, 0.0f);

	}
	
	@Test
	public void testPrzekazSamochod() {
		Kierowca kierowca2 = new Kierowca(12345678785l, "Jack", "Daniels");
		Samochod mockSamochod1 = mock(Samochod.class);
		expect(mockSamochod1.getRejestracja()).andReturn("WE032WP").andReturn("WE032WP").andReturn("WE032WP");
		
		replay(mockSamochod1);
		
		kierowca.dodajSamochod(mockSamochod1);
		kierowca.przekazSamochod("WE032WP", kierowca2);
		
		assertSame(kierowca2.znajdzSamochod("WE032WP"), mockSamochod1);
		
		verify(mockSamochod1);
		
	}
	
	@Test
	public void testPrzekazSamochodNegative() {
		Kierowca kierowca2 = new Kierowca(12345678785l, "Jack", "Daniels");
		Samochod mockSamochod1 = mock(Samochod.class);
		expect(mockSamochod1.getRejestracja()).andReturn("WE032WP");
		
		replay(mockSamochod1);
		
		kierowca.dodajSamochod(mockSamochod1);
		kierowca.przekazSamochod("WE000WP", kierowca2);
		
		assertSame(kierowca2.znajdzSamochod("WE000WP"), null);
		
		verify(mockSamochod1);
		
	}

}