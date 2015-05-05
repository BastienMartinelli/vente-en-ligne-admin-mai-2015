package selenium;

import org.junit.Test;

import com.thoughtworks.selenium.SeleneseTestCase;

public class AddClientSeleniumTest extends SeleneseTestCase {
	/** {@inheritDoc} */
	@Override
	public void setUp() throws Exception {
		setUp("http://www.netapsys.fr","*firefox"); // d�l�gation de la configuration � la classe parente
	}

	/** Ouvre la page et v�rifie que le texte est bien pr�sent. */
	public void test() {
		selenium.open("/"); // ouverture de la page
		assertTrue(selenium.isTextPresent("Netapsys"));
	}
}
