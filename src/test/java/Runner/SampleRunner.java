package Runner;



import com.intuit.karate.junit5.Karate;


public class SampleRunner {
	
	@Karate.Test
	Karate apichain() {
		return Karate.run("classpath:Features/Dyanamic.feature").relativeTo(getClass());
	}
	
	
	@Karate.Test
	Karate json() {
		return Karate.run("classpath:Features/DataDriven.feature").relativeTo(getClass());
	}
	
	@Karate.Test
	Karate query() {
		return Karate.run("classpath:Features/DataDriven.feature").relativeTo(getClass());
	}
	
	@Karate.Test
	Karate datacsv() {
		return Karate.run("classpath:Features/DataDriven.feature").relativeTo(getClass());
	}

}

