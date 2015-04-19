/**
 *  Copyright mcplissken.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.cradle.gateway.vertx.test;

import java.io.File;
import java.util.List;

import org.cradle.platform.httpgateway.HttpAdapter;
import org.cradle.platform.httpgateway.HttpMethod;
import org.cradle.platform.httpgateway.HttpMethod.Method;

/**
 * @author	Sherief Shawky
 * @email 	mcrakens@gmail.com
 * @date 	Apr 15, 2015
 */
public class TestHttpHandler {
	
	@org.cradle.platform.httpgateway.HttpMethod(method = Method.GET, path="/hello")
	public String sayHello(org.cradle.platform.httpgateway.HttpAdapter adapter){
		return "Hello, World!";
	}
	
	@HttpMethod(method = Method.PUT, path="/save")
	public TestDocument update(HttpAdapter adapter, TestDocument document){
		document.setId(15);
		return document;
	}
	
	@HttpMethod(method = Method.POST, path="/calc")
	public TestDocument multiply(HttpAdapter adapter, TestDocument document){
		
		document.calcResult();
		
		return document;
	}
	
	@HttpMethod(method = Method.MULTIPART_POST, path="/form")
	public TestDocument submitForm(HttpAdapter adapter, TestDocument form, List<File> files){
		return multiply(adapter, form);
	}
}
