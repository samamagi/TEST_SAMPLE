package test_sample.components;
import static aria.testframework.util.Step.addStep;
import static aria.testframework.util.Step.description;
import static aria.testframework.util.Step.result;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.python.jline.internal.Log;

import aria.testframework.util.RestClient;

public class C07_ExampleWS extends AComponent2{
	
	public String inTestNegativo;

	public String inArguments;
	public String inCheckList;
	
	private String _component = this.getClass().getSimpleName();

	public void exec() {
		Log.info("avvio " + _component);
		
		//PREPARAZIONE DEL MESSAGGIO DA INVIARE//
		String response = "";
		String url = "http://scooterlabs.com/echo.json";
		byte[] body = "".getBytes();
		if (inArguments!=null)
			body=inArguments.getBytes();
		Map<String,String> prop = new HashMap<String,String>();
		prop.put("http.agent", "Mozilla/5.0");
		prop.put("Content-Type", "application/x-www-form-urlencoded");
		//PREPARAZIONE DEL MESSAGGIO DA INVIARE//
		
		addStep( description("rest.send","HTTP/POST",url), result("rest.OK"));
		
		//INVIO DEL MESSAGGIO E LETTURA DELLA RISPOSTA//
		try {
			response = RestClient.requestHTTP("POST",url,prop,body);
		}catch(IOException e) {
			invokeFail(_component, _component + " >> Richiesta fallita "+e.toString(),	0);
		}
		Log.info("Response: "+response);
		//INVIO DEL MESSAGGIO E LETTURA DELLA RISPOSTA//
		
		//CONTROLLO DELLA RISPOSTA//
		//scooterlab ritorna un json
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(response);
		} catch (ParseException e) {
			invokeFail(_component, _component + " >> Formato della risposta non corretto: " + response,	0);
		}
		
		JSONObject form = (JSONObject) json.get("request");
		boolean trovato;
		for (String value : inCheckList.split("\\|")) {
			trovato = false;
			
			for(Object key : form.keySet()) {
				if (((String) form.get(key)).equals(value)) {
					trovato = true;
					break;
				}
			}
			
			if(inTestNegativo!=null && trovato)
				invokeFail(_component, _component + " >> campo inatteso trovato: " + value,	0);
		}
		//CONTROLLO DELLA RISPOSTA//
		
	}
}
