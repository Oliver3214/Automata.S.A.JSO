import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable

/*
 * Copyright 2003-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import gls.CompilableTestSupport

class TryCatchTest extends CompilableTestSupport {

	def exceptionCalled
	def finallyCalled

	void testTryCatch() {
		try {
			failingMethod()
		}
		catch (AssertionError e) {
			onException(e)
		}
		finally {
			onFinally()
		}
		afterTryCatch()
		assert exceptionCalled, "should have invoked the catch clause"
		assert finallyCalled, "should have invoked the finally clause"
		println("After try/catch")
	}

	void testStandaloneTryBlockShouldNotCompile() {
		shouldNotCompile """
            try {
                assert true
            }
        """
	}

	void testTryFinally() {
		Boolean touched = false;

		try {
		}
		finally {
			touched = true;
		}

		assert touched, "finally not called with empty try"
	}

	void testWorkingMethod() {
		try {
			workingMethod()
		}
		catch (AssertionError e) {
			onException(e)
		}
		finally {
			onFinally()
		}
		assert !exceptionCalled, "should not invoked the catch clause"
		assert finallyCalled, "should have invoked the finally clause"
		println "After try/catch"
	}

	void failingMethod() {
		assert false, "Failing on purpose"
	}

	void workingMethod() {
		assert true, "Should never fail"
	}

	void onException(e) {
		assert e != null
		exceptionCalled = true
	}

	void onFinally() {
		finallyCalled = true
	}

	void afterTryCatch() {
		assert exceptionCalled, "should have invoked the catch clause"
		assert finallyCalled, "should have invoked the finally clause"
		println("After try/catch")
	}

	protected void setUp() {
		exceptionCalled = false
		finallyCalled = false
	}

	void testTryWithReturnWithPrimitiveTypes() {
		assert intTry() == 1
		assert longTry() == 2
		assert byteTry() == 3
		assert shortTry() == 4
		assert charTry() == "c"
		assert floatTry() == 1.0
		assert doubleTry() == 2.0
	}

	int intTry() {
		try {
			return 1
		} finally {}
	}

	long longTry() {
		try {
			return 2
		} finally {}
	}

	byte byteTry() {
		try {
			return 3
		} finally {}
	}

	short shortTry() {
		try {
			return 4
		} finally {}
	}
}
  