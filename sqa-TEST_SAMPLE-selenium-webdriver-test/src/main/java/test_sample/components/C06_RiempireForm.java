package test_sample.components;
import static aria.testframework.util.Step.addStep;
import static aria.testframework.util.Step.description;
import static aria.testframework.util.Step.result;

//RICORDARE DI CAMBIARE URL SU test_sample.properties
import org.openqa.selenium.By;

import aria.testframework.util.Driver;
import aria.testframework.util.Log;
import aria.testframework.util.PropertiesRw;
import aria.testframework.util.Util;

public class C06_RiempireForm extends AComponent2{
	public String inSkip; 
	public String inButton;
	public String place;
	public String bottone;
	public String inRiempiForm;
	public String riempiCampo; 

	private String _component = this.getClass().getSimpleName();

	public void exec() {

		if (inSkip==null) {
			Log.info("avvio " + _component);

			String lblPagina="Sign up";
			addStep(description("pagina.apertura", lblPagina), result("pagina.aperta", lblPagina));

			String locatorPage = "//form/p[contains (., 'Sign up')]";

			String newUrl= Util.getAbsolutePath(PropertiesRw.readProjectProp("url.form"));
			Driver.getDriver().get(newUrl);

			if (checkPoint(locatorPage)){

				if (inRiempiForm!=null) {
					String [] riempire = inRiempiForm.split("\\|");
					for (int i=0; i<riempire.length; i++) {
						String split1 = riempire[i];
						String [] split2 = split1.split("#");
						place = split2[0];
						riempiCampo = split2[1];
						String locatorForm = "//form//input [@placeholder='"+place+"']";
						Driver.getDriver().findElement(By.xpath(locatorForm)).sendKeys(riempiCampo);
						//java.util.Objects.toString(null, "stringa nulla");
					}
				}

				if (inButton!=null) {
					String [] pulsanti = inButton.split("\\|");
					for (int i=0; i< pulsanti.length ; i++) {
						bottone = pulsanti[i];
						String locatorButton = "//form//button[contains (. , '"+bottone+"')]";
						Driver.getDriver().findElement(By.xpath(locatorButton)).click();

					}
				}
			}
		}
		else {
			invokeFail(_component, _component + " >> Pagina non trovata", 0);

		}
	}
}
