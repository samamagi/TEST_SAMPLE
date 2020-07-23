package test_sample.components;

import static aria.testframework.util.Step.addStep;
import static aria.testframework.util.Step.description;
import static aria.testframework.util.Step.result;
import org.openqa.selenium.By;
import aria.testframework.util.Driver;
import aria.testframework.util.Log;

public class C03_GestioneMenu extends AComponent2{
	public String inSkip; 
	public String inSceltaMenu;


	private String _component = this.getClass().getSimpleName();

	public void exec() {
		Log.info("avvio " + _component);
		String lblPagina="Menu Google";

		addStep(description("menu.navigazione", lblPagina), result("menu.navigazioneOK", lblPagina));
		String locatorPage = "//*[@id='top_nav']";

		if (checkPoint(locatorPage)){
			if (inSceltaMenu!=null) {
				String locatorMenu = "//*[@id='top_nav']//* [(self::div or self::g-header-menu) and not (contains (@style,'display:none'))]/a[normalize-space(.)='"+inSceltaMenu+"']";
				Driver.getDriver().findElement(By.xpath(locatorMenu)).click();
				//EQUIVALE A CLICK DI LOCATORMENU 
			}
		}
		else 
			invokeFail(_component, _component + " >> Pagina non trovata", 0);

	}






}
