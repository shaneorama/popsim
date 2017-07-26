package popsim

import org.scalajs.dom.raw._

import scala.scalajs.js.JSApp
import org.scalajs.dom

import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("client")
object Client extends JSApp {

  @JSExport
  def main(): Unit = {
    val socket = new WebSocket(websocketUri)
    socket.onopen = { (event: Event) =>
      event
    }
    socket.onerror = { (event: ErrorEvent) =>
      event
    }
    socket.onmessage = { (event: MessageEvent) =>
      val msg = event.data
      event
    }
    socket.onclose = { (event: Event) =>
      event
    }
  }


  lazy val websocketUri: String = {
    val wsProtocol = if (dom.document.location.protocol == "https:") "wss" else "ws"
    s"$wsProtocol://${dom.document.location.host}/popsim"
  }
}