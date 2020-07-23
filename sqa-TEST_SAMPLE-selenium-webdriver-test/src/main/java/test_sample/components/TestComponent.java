package test_sample.components;

import java.util.HashMap;
import aria.testframework.components.TComponent;

public class TestComponent extends AComponent2{


	public static void main(String[] args){

		// dati da modificare in base alla classe da testare - come nel file xml della suite		
		String reportName="report_generic.xls";

		String browser = "chrome";
		String iteration = "1";
		String xlsDataProvider="BP10_Qualifica";
		String bpName="BP10_Qualifica";
		String filetest_sample="CE18.properties";

		//la classse che si testa
		String componentName="CE18.components.C10_Qualifica";
		String loadAlias="C10";
		String unloadAlias=null;

		//valorizzazione parametri manualmente, senza riga iterazione in excel
		HashMap<String,Object> parameter1= new HashMap<String,Object>();
		parameter1.put("C10.compilacampi", "Denominazione o Ragione sociale#pippo");
		parameter1.put("C10.opzione", "Socio");

		TComponent.setValues(reportName, browser, iteration, xlsDataProvider , bpName,  filetest_sample, componentName, loadAlias, unloadAlias);

		//con null prende i valori dei parametri dal file excel per l'iterazione indicata;
		//		TComponent.testComponent(null);

		//altrimenti si possono valorizzare manualmente i parametri e usare parameter1
		TComponent.testComponent(parameter1);
	}

}
