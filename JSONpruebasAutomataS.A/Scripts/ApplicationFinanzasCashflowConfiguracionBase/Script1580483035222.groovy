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
import groovy.json.JsonSlurper as JsonSlurper

WebUI.openBrowser('')

WebUI.navigateToUrl('https://dev.automata.com.gt/login')

WebUI.setText(findTestObject('login y verificacion de inicio de sesion/input_person_email'), 'sherrera')

WebUI.setEncryptedText(findTestObject('login y verificacion de inicio de sesion/input_lock_outline_password'), 'RigbBhfdqOBGNlJIWM1ClA==')

WebUI.click(findTestObject('login y verificacion de inicio de sesion/button_Ingresar'))

WebUI.delay(5)

		/////////////////////////////////////////////////////////////////////////////////////////////////////////
											//extraemos el json de la url
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
def jsonurl = WebUI.navigateToUrl('https://dev.automata.com.gt/application/finanzas/cashflow/configuracionBase?anio=2020&mes=1&moneda=1' // se define la url para obtener el Json
    // se define la url para obtener el Json
    )

texto = WebUI.getText(findTestObject('Page_/JsonBody'))

def parser = new JsonSlurper()

def json = parser.parseText(texto)

println(json)
//se definen las variables
MenuSeleccionado = json.menuSeleccionado
InitialYear=json.anioInicial
MesInicial=json.mesInicial
TipoDeCambio=json.tipoDeCambio
TipoDeCambioCashflow=json.tipoDeCambioCashflow
TdeCCId=json.tipoDeCambioMoneda.id
TdeCCAbreviacion=json.tipoDeCambioMoneda.abreviacion
TdeCCNombre=json.tipoDeCambioMoneda.nombre
TdeCCPais=json.tipoDeCambioMoneda.pais
TdeCCMonedaBancoGuatemalaID=json.tipoDeCambioMoneda.monedaBancoGuatemalaId
TdeCCSimbolo=json.tipoDeCambioMoneda.simbolo
TdeCCMTipoDeCambio=json.tipoDeCambioMoneda.tipoDeCambio
//array
DatosTipoDeCambio=json.datosTipoDeCambio
println(InitialYear)
//Pruebas
//Menu Seleccionado
	try{
		assert MenuSeleccionado.getClass()==String:"El Menu Seleccionado es de tipo String"
		assert !MenuSeleccionado.equals(""):"La cadena contiene texto"
		assert MenuSeleccionado.equals("cashflow"):"el dato Menu Seleccionado es correcto"
		
	}catch(AssertionError e){
		e.getMessage()
		MenuSeleccionado = e
	}
	
	//Initial Year
	try{
		assert InitialYear.getClass()==String:"El Menu Seleccionado es de tipo String"
		assert !InitialYear.equals(""):"La cadena contiene texto"
		assert InitialYear.equals("2020"):"el dato Año Inicial es correcto"
	}catch(AssertionError e){
		e.getMessage()
		InitialYear = e
	}
	//Mes Inicial
	try{
		assert MesInicial.getClass()==String:"El Menu Seleccionado es de tipo String"
		assert !MesInicial.equals(""):"La cadena contiene texto"
		assert MesInicial.equals("02"):"el dato Mes Inicial es correcto"
	}catch(AssertionError e){
		e.getMessage()
		MesInicial = e
	}
	//Tipo de Dato
	try{
		assert TipoDeCambio.getClass().equals(Integer):"El Tipo de Cambio es de tipo Integer"
		assert TipoDeCambio > 0:"El numero ingresado es correcto"
	}catch(AssertionError e){
		 e.getMessage()
		TipoDeCambio = e
	}
	//tipo De Cambio Cashflow
	try{
		assert TipoDeCambioCashflow.getClass().equals(Integer):"El Tipo de Cambio es de tipo Integer"
		assert TipoDeCambioCashflow > 0:"El numero ingresado es correcto"
	}catch(AssertionError e){
		 e.getMessage()
		 TipoDeCambioCashflow = e
	}
	//Tipo de Cambio Moneda .Id
	try{
		assert TdeCCId.getClass().equals(Integer):"El Tipo de Cambio es de tipo Integer"
		assert TdeCCId > 0:"El numero ingresado es correcto"
	}catch(AssertionError e){
		 e.getMessage()
		 TdeCCId = e
	}
	//Tipo de Cambio Moneda .Abreviacion
	try{
		assert TdeCCAbreviacion.getClass().equals(String):"El Tipo de Cambio Moneda .Abreviacion  es de tipo String"
		assert !MesInicial.equals(""):"La cadena contiene texto"
	}catch(AssertionError e){
		 e.getMessage()
		TdeCCAbreviacion = e
	}
	//Tipo de Cambio Moneda .Abreviacion
	try{
		assert TdeCCNombre.getClass().equals(String):"El Tipo de Cambio Moneda .Abreviacion  es de tipo String"
		assert !TdeCCNombre.equals(""):"La cadena contiene texto"
		
	}catch(AssertionError e){
		 e.getMessage()
		TdeCCAbreviacion = e
	}
	//Tipo de Cambio Moneda .Abreviacion
	try{
		assert TdeCCPais.getClass().equals(String):"El PAis  es de tipo String"
		assert !TdeCCPais.equals(""):"La cadena contiene texto"
	}catch(AssertionError e){
		 e.getMessage()
		TdeCCAbreviacion = e
	}
	//validacion de los campos .Abreviacion .Nombre . Pais
	
	if(TdeCCAbreviacion.equals("Q") || TdeCCAbreviacion.equals("USD")){
		if(TdeCCAbreviacion.equals("Q")){
			assert TdeCCNombre.equals("Quetzal")
			if(TdeCCNombre.equals("Quetzal")){
				assert TdeCCPais.equals("Guatemala")
			}
		}else{
			assert false
		}
		if(TdeCCAbreviacion.equals("USD")){
			assert TdeCCNombre.equals("D\u00f3lar")
			if(TdeCCNombre.equals("D\u00f3lar")){
				assert TdeCCPais.equals("Estados Unidos de Am\u00e9rica")
			}
		}else{
			assert false
		}
		
	}
	//validacion de los campos .Abreviacion .Nombre . Pais con USD
	
	//lista de las pruebas que pasaron las pruebas y las que no
	if(InitialYear==true){
		def lista = [InitialYear]
		print lista
	}
	
	
	
//







