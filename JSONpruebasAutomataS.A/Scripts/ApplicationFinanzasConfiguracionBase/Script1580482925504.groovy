import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import groovy.json.JsonSlurper as JsonSlurper

WebUI.openBrowser('')

WebUI.navigateToUrl('https://dev.automata.com.gt/login')

WebUI.setText(findTestObject('login y verificacion de inicio de sesion/input_person_email'), 'sherrera')

WebUI.setEncryptedText(findTestObject('login y verificacion de inicio de sesion/input_lock_outline_password'), 'RigbBhfdqOBGNlJIWM1ClA==')

WebUI.click(findTestObject('login y verificacion de inicio de sesion/button_Ingresar'))

WebUI.verifyElementInViewport(findTestObject('login y verificacion de inicio de sesion/div_Log out_row justify-content-center'), 
    0)

/////////////////////////////////////////////////////////////////////////////////////////////////////////
//extraemos el json de la url
//////////////////////////////////////////////////////////////////////////////////////////////////////////
def jsonurl = WebUI.navigateToUrl('https://dev.automata.com.gt/application/finanzas/configuracionBase' // se define la url para obtener el Json  
    )

WebUI.getText(findTestObject('obtencion de json/Page_/body_menuSeleccionadoinicioanioInicial2020mesInicial01tipoDeCambio76779tituloAUTOMATA SAhtml1body1'))

texto = WebUI.getText(findTestObject('obtencion de json/Page_/body_menuSeleccionadoinicioanioInicial2020mesInicial01tipoDeCambio76779tituloAUTOMATA SAhtml1body1'))

def parser = new JsonSlurper()

def json = parser.parseText(texto)

/////////////////////////////////////////////////////////////////////////////////////////////////////////
//validamos que el paso 5 siempre de como resultado AUTOMATA.S.A.
//////////////////////////////////////////////////////////////////////////////////////////////////////////
String InitialYear = json.anioInicial

String MenuSeleccionado = json.menuSeleccionado

String MesInicial = json.mesInicial

String TipoDeCambio = json.tipoDeCambio

String Titulo = json.titulo

//Initial Year
try {
    assert InitialYear.equals('2020') : 'el dato que se encontro en el Json coincide con el dato esperado'
}
catch (AssertionError e) {
    e.getMessage()

    println('los datos que se ingresaron no son validos')

    assert false
} 

//Menu Seleccionado
try {
    assert MenuSeleccionado.equals('inicio') : 'el dato que se encontro en el Json coincide con el dato esperado'
}
catch (AssertionError e) {
    e.getMessage()

    println('los datos que se ingresaron no son validos')

    assert false
} 

//Mes Inicial
try {
    assert MesInicial.equals('01') : 'el dato que se encontro en el Json coincide con el dato esperado'
}
catch (AssertionError e) {
    e.getMessage()

    println('los datos que se ingresaron no son validos')

    assert false
} 

//Titulo
try {
    assert Titulo.equals('AUTOMATA S.A.') : 'el dato que se encontro en el Json coincide con el dato esperado'
}
catch (AssertionError e) {
    e.getMessage()

    println('los datos que se ingresaron no son validos')

    assert false
} 

//se valida cada uno de los datos
/////////////////////////////////////////////////////////////////////////////////////////////////////////
//imprimimos el json 
//////////////////////////////////////////////////////////////////////////////////////////////////////////
/*println(json)
for (def item in json){
		for(def a in item ){
			if (a.equals("")){
				println(a)
				println ("el dato es incorrecto por que esta vacio",FailureHandling.CONTINUE_ON_FAILURE)
				return a
			}else{
				println("el dato es correcto")
				println(a)
			}
		}
		
	}*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////
//se crean las variables de cada dato dentro del json
//////////////////////////////////////////////////////////////////////////////////////////////////////////
/*


if (dato1 instanceof String){
	println ("el dato es un cadena string")
}else{
	println ("el dato es: "+dato1,FailureHandling.CONTINUE_ON_FAILURE)
	println ("el dato es un numero apesar que la entrada es un String cadena:",FailureHandling.CONTINUE_ON_FAILURE)
	
	
	//marcado como falla
}
if (dato1 instanceof String){
	println ("el dato es un cadena string")
}else{
	println ("el dato es: "+dato2,FailureHandling.CONTINUE_ON_FAILURE)
	println ("el dato es un numero apesar que la entrada es un String cadena:",FailureHandling.CONTINUE_ON_FAILURE)
	
	
	//marcado como falla
}
if (dato1 instanceof String & dato1 != dato1.isNumber()){
	println ("el dato es un cadena string")
}else{
	println ("el dato es: "+dato5,FailureHandling.CONTINUE_ON_FAILURE)
	println ("el dato es un numero apesar que la entrada es un String cadena:",FailureHandling.CONTINUE_ON_FAILURE)
	
	
	//marcado como falla
}
*/
/*
boolean Menu= MenuSeleccionado.contains("inicio");
if (Menu == true){
	println ("el dato que se encontro en el Json $MenuSeleccionado} coincide con el dato esperado")
	}else{
	println ("se encontro un error al leer el resultado")
	return true
	}

boolean MesInitial= MesInicial.contains("01");
if (MesInitial == true){
	println ("el dato que se encontro en el Json ${MesInicial} coincide con el dato esperado")
	}else{
	println ("se encontro un error al leer el resultado")
	return true
	}

boolean TituloH1= Titulo.contains("AUTOMATA S.A.");
if (TituloH1 == true){
	println ("el dato que se encontro en el Json ${Titulo} coincide con el dato esperado")
	}else{
	println ("se encontro un error al leer el resultado")
	return true
	}
	
	try {
		def name = "John"
		assert name == "Peter" : "Name should be John"
	} catch (AssertionError e) {
		println "Something bad happened: " + e.getMessage()
	}
*/
/////////////////////////////////////////////////////////////////////////////////////////////////////////
//ciclo for para leer cada paso de el json
//////////////////////////////////////////////////////////////////////////////////////////////////////////
/*for (index = 1; index < 5; index++){
		 
			if ("dato${index}".getClass()==GString & "dato${index}".getClass()!=Integer & "dato${index}".getClass()!=Float & "dato${index}".getClass()!=Boolean ){
			println ("dato${index}")
			}
			else
			{
			println ("el dato no es correcto")
			println("dato${index}")
			println("dato${index}".getClass())
			}

}*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////
//datos sin ciclo for solamente ciclo upto
//////////////////////////////////////////////////////////////////////////////////////////////////////////
/*if (dato1.getClass()==GString & dato1.getClass()!=Integer & dato1.getClass()!=Float & dato1.getClass()!=Boolean ){
	println (dato1)
	}
	else
	{
	println ("el dato no es correcto")
	println(dato1)
	println(dato1.getClass())
	}
*/
WebUI.closeBrowser()

