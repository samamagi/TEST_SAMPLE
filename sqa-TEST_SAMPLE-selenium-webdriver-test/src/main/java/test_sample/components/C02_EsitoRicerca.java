package test_sample.components;

import static aria.testframework.util.Step.addStep;
import static aria.testframework.util.Step.description;
import static aria.testframework.util.Step.result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import aria.testframework.util.Log;

/**
 * 
 * C02_EsitoRicerca - classe per la pagina di Login, extende AComponent. Per ora gestisce solo la login
 * attraverso la username e password
 * 	
 * La classe implementa i metodi loadData e exec.
 * 
 */
public class C02_EsitoRicerca extends AComponent2{

	/** proprietà *in* di tipo String che viene valorizzata fa "saltare" il contenuto del metodo exec, altrimenti esso viene eseguito.*/
	public String inSkip; 
	/** proprietà *in* di tipo String che contiene il tipo autenticazione; da compilare con una tra le seguenti 
	 * parole chiave: carta, codice, username  */

	public String inChiaveRicerca;
	/** proprietà *in* di tipo String che indica se il test è un test negativo; da valorizzare se il test è negativo */
	public String inTestNegativo;
	/** proprietà *in* di tipo String che indica il messaggio di errore che si desidera verificare se il test è
	 * indicato come negativo nella proprietà precedente; se valorizzato si verifica il messaggio di errore */
	public String outRisultati;

	private String _component = this.getClass().getSimpleName();


	/** 
	 * 	exec - metodo implementato con la gestione della pagina di login, verifica l'atteraggio nella pagina e in base al
	 * valore della proprietà inAutenticazione - gestisce la pagina di login
	 * Se la proprietà inSkip!=null, allora tutto ciò non viene eseguito. 
	 * Per ora è gestito solo il login con user e password.
	 * In questo caso inserisce l'user e la password se valorizzati, verifica il messaggio di errore se valorizzato e il test è negativo,
	 * seleziona i pulsanti Accedi o Carta (l'immagine della carta).
	 * Da gestire ancora: 
	 * 	- gli altri 2 tipi di login
	 * Non ritorna nessun parametro.
	 * @see application.components.AComponent#exec(org.openqa.selenium.WebDriver)
	 */
	public void exec() {

		Log.info("avvio " + _component);

		if (inSkip==null) {
			String lblPagina ="Esito Ricerca";
			String locatorPage = "//div[@id='top_nav']"; 

			addStep(description("pagina.apertura", lblPagina), result("pagina.aperta", lblPagina));

			if (checkPoint(locatorPage)){
				Log.info(_component + " > " + lblPagina);
				if (inTestNegativo==null){
					String esito=getElementAttribute(locatorPage, "textContent");
					Log.info(esito);

					Pattern pattern = Pattern.compile("[^\\d]+([\\d\\.]+)");
					Matcher matcher = pattern.matcher(esito);
					if (matcher.find())
						outRisultati = matcher.group(1);
					else
						invokeFail(_component, _component + " >> Stringa con i risultati non trovata", 0);
					Log.info(outRisultati);

					if (inChiaveRicerca!=null) 
						safeInput("//h3[contains(.,'"+inChiaveRicerca+"')]");
				}
				else {
					locatorPage="//p[contains(.,'non ha prodotto risultati')]";
					if (checkPoint(locatorPage)) {
						outRisultati= "0";
						Log.info(outRisultati);
					}
					else
						invokeFail(_component, _component + " >> Pagina con la ricerca senza risultati non trovata", 0);
				}


			}
			else 
				invokeFail(_component, _component + " >> Pagina con i risultati della ricerca non trovata", 0);
		}
		addUnloadData("risultati", outRisultati);
	}


}
