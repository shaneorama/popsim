package server;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class PopSimWebSocketHandler extends AbstractWebSocketHandler {

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
  }


  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    System.out.println("Received message: " + message);

    String[] payload = message.getPayload().split(" ", 2);
    String command = payload[0];
    String args = payload[1];
    switch(command) {
      case "greet":
        session.sendMessage(new TextMessage("Hello "+ args));
        break;
      case "echo":
        session.sendMessage(new TextMessage(args));
        break;
      case "slam":
        for(int i = 0; i < 1000001; i++) {
          session.sendMessage(new TextMessage("SLAM\n"));
        }
    }
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
  }

  @Override
  public boolean supportsPartialMessages() {
    return false;
  }
}