package modules

import javax.inject.{Inject, Provider, Singleton}

import play.engineio.EngineIOController
import play.socketio.scaladsl.SocketIO
import it.gov.daf.realtime.ChatEngine

@Singleton
class MySocketIOEngineProvider @Inject() (chatEngine: ChatEngine)
  extends Provider[EngineIOController] {

  override lazy val get = chatEngine.controller
}
