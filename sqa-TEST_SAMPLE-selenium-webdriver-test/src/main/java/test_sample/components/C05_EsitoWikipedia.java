package test_sample.components;

import static aria.testframework.util.Step.addStep;
import static aria.testframework.util.Step.description;
import static aria.testframework.util.Step.result;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import aria.testframework.util.Driver;
import aria.testframework.util.Log;

public class C05_EsitoWikipedia  extends AComponent2{
	public String inSkip; 
	public String outEsito;
	public String inLink;
	public String inInizioLink;


	private String _component = this.getClass().getSimpleName();

	public void exec() {

		List<WebElement> risultati;	
		Integer esito;


		if (inSkip==null) {
			Log.info("avvio " + _component);
			String lblPagina ="Esito Ricerca Wikipedia";
			String locatorPage = "//*[@id=\"bodyContent\"]"; 

			addStep(description("pagina.apertura", lblPagina), result("pagina.aperta", lblPagina));


			if (checkPoint(locatorPage)){
				if (inInizioLink!=null) {
					String locatorLink = "//div [@id='bodyContent']//a[starts-with(.,'"+inInizioLink+"')]"; 
					risultati = Driver.getDriver().findElements(By.xpath(locatorLink));
					esito = risultati.size();
					outEsito = esito.toString();
				}

			}
			else 
				invokeFail(_component, _component + " >> Pagina con i risultati della ricerca non trovata", 0);
		}
		addUnloadData("esito", outEsito);
		Log.info(outEsito);

	}



}
