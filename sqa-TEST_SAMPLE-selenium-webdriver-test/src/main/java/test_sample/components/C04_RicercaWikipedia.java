package test_sample.components;

import static aria.testframework.util.Step.addStep;
import static aria.testframework.util.Step.description;
import static aria.testframework.util.Step.result;

import org.openqa.selenium.By;

import aria.testframework.util.Driver;
import aria.testframework.util.Log;

public class C04_RicercaWikipedia extends AComponent2{
	public String inSkip; 
	public String inTestoRicerca;
	public String inCerca;

	private String _component = this.getClass().getSimpleName();

	public void exec() {
		Log.info("avvio " + _component);

		if (inSkip==null) {
			String lblPagina="Ricerca Wikipedia";
			addStep(description("pagina.apertura", lblPagina), result("pagina.aperta", lblPagina));

			Driver.openPage("http://wikipedia.org");
			String locatorPage = "//button [normalize-space (.)='Search']";

			if (checkPoint(locatorPage)){
				if (inTestoRicerca!=null) {
					String locatorTestoRicerca = "//input [@id='searchInput']";
					Driver.getDriver().findElement(By.xpath(locatorTestoRicerca)).sendKeys(inTestoRicerca);

				}
				if (inCerca!=null) {
					String locatorCerca = "//button [normalize-space (.)='Search']";
					Driver.getDriver().findElement(By.xpath(locatorCerca)).click();

				}
			}
			else 
				invokeFail(_component, _component + " >> Pagina non trovata", 0);

		}

	}


}
