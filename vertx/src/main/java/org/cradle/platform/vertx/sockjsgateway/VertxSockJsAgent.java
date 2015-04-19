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
package org.cradle.platform.vertx.sockjsgateway;

import org.cradle.platform.sockjsgateway.SockJsAgent;
import org.cradle.platform.sockjsgateway.SockJsMessageWriter;
import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.sockjs.SockJSSocket;
/**
 * @author 	Sherief Shawky
 * @email 	mcrakens@gmail.com
 * @date 	Aug 14, 2014
 */
public class VertxSockJsAgent implements SockJsMessageWriter  {

	private SockJSSocket socket;
	private SockJsAgent sockJsAgent;

	/**
	 * @param socket
	 * @param sockJsAgent
	 */
	public VertxSockJsAgent(SockJSSocket socket, SockJsAgent sockJsAgent) {
		this.socket = socket;
		this.sockJsAgent = sockJsAgent;
		
		sockJsAgent.setSockJsMessageWriter(this);
		
		initSocket(socket);
	}

	public void writeMessage(byte[] bytesBuff) {
		
		socket.write(new Buffer(bytesBuff));
	}

	/**
	 * @param socket the socket to set
	 */
	private void initSocket(SockJSSocket socket) {
		
		socket.dataHandler(new Handler<Buffer>() {
			
			@Override
			public void handle(Buffer event) {
				
				byte[] inputArr = event.getByteBuf().array();
				
				sockJsAgent.socketMessageReceived(inputArr);
			}
		});
		
	}

}
