package test_sample.components;

import static aria.testframework.util.Step.addStep;
import static aria.testframework.util.Step.description;
import static aria.testframework.util.Step.result;
import aria.testframework.util.Log;


/**
 * 
 * C01_RicercaGoogle - classe per la pagina di Login, extende AComponent. Per ora gestisce solo la login
 * attraverso la username e password
 * 	
 * La classe implementa i metodi loadData e exec.
 * 
 */

//test
public class C01_RicercaGoogle extends AComponent2{

	/** propriet� *in* di tipo String che viene valorizzata fa "saltare" il contenuto del metodo exec, altrimenti esso viene eseguito.*/
	public String inSkip; 
	/** propriet� *in* di tipo String che contiene il tipo autenticazione; da compilare con una tra le seguenti 
	 * parole chiave: carta, codice, username  */

	public String inPulsante;
	/** propriet� *in* di tipo String che indica se il test � un test negativo; da valorizzare se il test � negativo */
	public String inTestNegativo;
	/** propriet� *in* di tipo String che indica il messaggio di errore che si desidera verificare se il test �
	 * indicato come negativo nella propriet� precedente; se valorizzato si verifica il messaggio di errore */
	public String inCerca;

	private String _component = this.getClass().getSimpleName();

	/** 
	 * 	exec - metodo implementato con la gestione della pagina di login, verifica l'atteraggio nella pagina e in base al
	 * valore della propriet� inAutenticazione - gestisce la pagina di login
	 * Se la propriet� inSkip!=null, allora tutto ci� non viene eseguito. 
	 * Per ora � gestito solo il login con user e password.
	 * In questo caso inserisce l'user e la password se valorizzati, verifica il messaggio di errore se valorizzato e il test � negativo,
	 * seleziona i pulsanti Accedi o Carta (l'immagine della carta).
	 * Da gestire ancora: 
	 * 	- gli altri 2 tipi di login
	 * Non ritorna nessun parametro.
	 * @see application.components.AComponent#exec(org.openqa.selenium.WebDriver)
	 */
	public void exec() {

		Log.info("avvio " + _component);

		if (inSkip==null) {
			String lblPagina ="Ricerca Google";
			String locatorPage = "//input[@role='combobox']";

			addStep(description("pagina.apertura", lblPagina), result("pagina.aperta", lblPagina));
			if (checkPoint(locatorPage)){
				Log.info(_component + " > " + lblPagina);
				if (inCerca!=null) 
					safeInput(locatorPage,inCerca);

				if (inPulsante!=null)
					this.submitElement(locatorPage);
			}
			else 
				invokeFail(_component, _component + " >> Pagina non trovata", 0);
		}
	}

}
