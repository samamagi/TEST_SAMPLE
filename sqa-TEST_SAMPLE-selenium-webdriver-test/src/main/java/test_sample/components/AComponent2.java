package test_sample.components;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import aria.testframework.components.AComponent;
import aria.testframework.util.*;

/**
 * AComponent classe astratta per le componenti delle pagine; contiene i seguenti metodi:
 * loadData - da implementare nella classe della pagine
 * unloadData - da implementare nella classe della pagine
 * attachBrowser - non sviluppata
 * exec - da implementare nella classe della pagine

 */

public abstract class AComponent2 extends AComponent{
	/**
	 * loaderSiage: metodo di attesa per SIAGE
	 */
	public void loaderSiage() {
		loaderGenerico("//div[@id='global-wait']");
	}
	
	/**
	 * verifyPage - metodo per verificare il titolo della pagina che si trova nel campo "bara_istituzionale", specifico per PCASA
	 *
	 * @param driver        parametro in input con il driver aperto
	 * @param expectedTitle parametro in input con la stringa del titolo
	 * @param flashing      parametro in input per indicare se si desira il flash sull'elemento (true) oppure no (false)
	 * @return parametro in output di tipo boolean, torna true se il titolo viene trovato nella pagina, altrimenti false
	 */
	public boolean verifyPage(String expectedTitle, boolean flashing) {
		Log.info("Func: verifyPage");
		boolean vb = false;

		String actualTitle = safeLabel("barra_istituzionale", flashing);
		if ((actualTitle != null) && (actualTitle.contains(expectedTitle))) {
			Log.info("\tTitolo in barra istituzionale: " + expectedTitle + " trovato.");
			vb = true;
		} else
			invokeFail("verifyPage", "\tTitolo in barra istituzionale: " + expectedTitle + " non trovato!", 0);

		return vb;
	}	 
	
	public void safeInput(String elementLocator, boolean check, String... attribute) {
		Log.info("safeInput: " + elementLocator);
		WebElement element = Driver.getDriver().findElement(By.xpath(elementLocator));
		String attr = "#";
		if (attribute.length > 0) {
			attr = attribute[0];
			Log.info("attr: " + attr);
		}

		if (element.isEnabled()) {
			String typeElement = element.getTagName();
			switch(typeElement) {
			case "h1":
			case "h2":
			case "h3":
			case "h4":
				if (!check) {
					jsClick(elementLocator);
					//element.click();
				} else {
					String elementText = getTextOrValue(elementLocator);
					boolean isTextRight = false;
					if (attr.toLowerCase().equals("notnull"))
						isTextRight = elementText.equals(attr);
					else
						isTextRight = !elementText.isEmpty();

					if (isTextRight)
						Log.info("Elemento: " + elementLocator + " ha testo: " + attr);
					else
						Log.info("Elemento: " + elementLocator + " non ha testo: " + attr + " ma testo: " + elementText);
				}
				break;
			
			default:
				super.safeInput( elementLocator,  check, attribute);
				break;
			}


		}
	}
}
