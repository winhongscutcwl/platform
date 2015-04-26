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
package org.cradle.platform.vertx.websocketgateway;

import org.cradle.platform.document.DocumentWriter;
import org.cradle.platform.websocketgateway.spi.WebsokcetAgent;
import org.cradle.platform.websocketgateway.spi.WebsocketAgentFactory;
import org.vertx.java.core.Handler;
import org.vertx.java.core.sockjs.SockJSSocket;
import org.vertx.java.core.streams.Pump;

/**
 * @author 	Sherief Shawky
 * @email 	mcrakens@gmail.com
 * @date 	Aug 14, 2014
 */
public  class VertxWebsocketApplication  implements Handler<SockJSSocket> {
	
	private WebsocketAgentFactory agentFactory;
	private DocumentWriter writer;
	
	/**
	 * @param agentFactory
	 * @param writer
	 */
	public VertxWebsocketApplication(WebsocketAgentFactory agentFactory,
			DocumentWriter writer) {
		
		this.agentFactory = agentFactory;
		this.writer = writer;
	}

	/* (non-Javadoc)
	 * @see org.vertx.java.core.Handler#handle(java.lang.Object)
	 */
	@Override
	public void handle(SockJSSocket socket) {
		
		initConnection(socket);
		
		WebsokcetAgent agent = agentFactory.createAgent();
		
		agent.setWriter(writer);
		
		new VertxWebsocketAgent(socket, agent);
		
	}

	private void initConnection(SockJSSocket socket){

		Pump.createPump(socket, socket).start();

		socket.exceptionHandler(new Handler<Throwable>() {

			@Override
			public void handle(Throwable event) {
				
				event.printStackTrace();
			}
		});
	}
	
}