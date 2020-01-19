package samochody;

import org.junit.Test;
import org.junit.Before;

//import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class SamochodTest {

		Samochod samochod;
	
		@Before
		public void inincjujTesty() {
			samochod = new Samochod("KR1345",20000,10);
			
			
		}
		
		@Test
		public void testGettery() {
			assertEquals(samochod.getRejestracja(), "KR1345");
			assertEquals(samochod.getWartosc(), 20000.0f, 0.0f);
		}
		
		@Test
		public void testAmortyzacja10() {
			Samochod s = new Samochod("KR1345",20000,10);
			s.amortyzuj();
			assertEquals(s.getWartosc(), 18000.0f, 0.0f);
		
		}
		
		@Test
		public void testAmortyzacja0() {
			Samochod s = new Samochod("KR1345",20000,0);
			s.amortyzuj();
			assertEquals(s.getWartosc(), 20000.0f, 0.0f);
		
		}
		
		@Test
		public void testAmortyzacja10_5() {
			Samochod s = new Samochod("KR1345",20000,10.5f);
			s.amortyzuj();
			assertEquals(s.getWartosc(), 17900.0f, 0.0f);
		
		}
}
