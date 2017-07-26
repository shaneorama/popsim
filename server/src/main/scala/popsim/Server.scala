package popsim

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.support.SpringBootServletInitializer
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.web.socket.config.annotation.{EnableWebSocket, WebSocketConfigurer, WebSocketHandlerRegistry}
import org.springframework.web.socket.handler.PerConnectionWebSocketHandler
import org.springframework.web.socket.server.standard.ServerEndpointExporter

@Configuration
@SpringBootApplication
@EnableWebSocket
class Server extends SpringBootServletInitializer with WebSocketConfigurer {
  override def configure(builder: SpringApplicationBuilder): SpringApplicationBuilder =
    builder.sources(classOf[Server])

  override def registerWebSocketHandlers(registry: WebSocketHandlerRegistry): Unit =
    registry.addHandler(socketHandler, "/popsim")

  @Bean def socketHandler = new PerConnectionWebSocketHandler(classOf[WebSockHandler])
  @Bean def serverEndpointExporter = new ServerEndpointExporter()
}

object Server {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Server])
  }
}
