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
package org.cradle.platform.websocketgateway.spi;

import java.nio.ByteBuffer;

import org.cradle.platform.document.DocumentWriter;

/**
 * @author	Sherief Shawky
 * @email 	mcrakens@gmail.com
 * @date 	Apr 19, 2015
 */
public abstract class WebsokcetAgent {

	private DocumentWriter writer;
	
	private WebsocketMessageWriter sockJsMessageWriter;
	
	
	/**
	 * @param writer the writer to set
	 */
	public void setWriter(DocumentWriter writer) {
		this.writer = writer;
	}
	
	/**
	 * @param sockJsMessageWriter the sockJsMessageWriter to set
	 */
	public void setSockJsMessageWriter(WebsocketMessageWriter sockJsMessageWriter) {
		this.sockJsMessageWriter = sockJsMessageWriter;
	}
	
	public void writeDocument(Object document) {
		
		byte[] bytesBuff = bufferDocument(document);
		
		sockJsMessageWriter.writeMessage(bytesBuff);
		
	}
	
	public void writeMessage(byte[] message){
		
		sockJsMessageWriter.writeMessage(message);
	}
	
	protected byte[] bufferDocument(Object document) {
		
		ByteBuffer output = ByteBuffer.allocate(1024 * 1024);
		
		writer.write(document, output);
		
		byte[] bytesBuff = unpackBuffer(output);
		
		return bytesBuff;
	}

	private byte[] unpackBuffer(ByteBuffer output) {
		
		output.flip();
		
		int length = output.limit();
	
		byte[] bytesBuff = new byte[length];
	
		output.get(bytesBuff);
		
		output.clear();
		
		return bytesBuff;
	}
	
	public abstract void socketMessageReceived(byte[] message);

}