package popsim

import org.springframework.web.socket.{CloseStatus, TextMessage, WebSocketSession}
import org.springframework.web.socket.handler.AbstractWebSocketHandler

class WebSockHandler extends AbstractWebSocketHandler{

  override def handleTextMessage(session: WebSocketSession, message: TextMessage): Unit = {
    val payload = message.getPayload
    println("Recieved Message from client: " + payload)

    val tokens = payload.split(" ", 2)
    if(tokens.length == 2) {
      val command = tokens(0)
      val args = tokens(1)
      command match {
        case "greet" => session.sendMessage(new TextMessage(s"Hello $args"))
        case "echo" => session.sendMessage(new TextMessage(args))
        case _ => session.sendMessage(new TextMessage(s"I don't understand: $payload"))
      }
    }
  }


  override def afterConnectionEstablished(session: WebSocketSession): Unit = super.afterConnectionEstablished(session)
  override def afterConnectionClosed(session: WebSocketSession, status: CloseStatus): Unit = super.afterConnectionClosed(session, status)
  override def handleTransportError(session: WebSocketSession, exception: Throwable): Unit = super.handleTransportError(session, exception)
  override def supportsPartialMessages(): Boolean = false
}
