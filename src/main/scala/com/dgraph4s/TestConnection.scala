package com.dgraph4s

import io.grpc.netty.{NegotiationType, NettyChannelBuilder}
import protos.graphresponse.DgraphGrpc.DgraphStub
import protos.graphresponse.{DgraphGrpc, Request, Response}
import io.grpc.{CallOptions, ManagedChannelBuilder}

import scala.concurrent.Future

object TestConnection extends App {
  import scala.concurrent.ExecutionContext.Implicits.global
  val channel = NettyChannelBuilder.forAddress("127.0.0.1", 9080).usePlaintext(true).build()
  val request = Request(query = """mutation {
                                  |
                                  |  schema {
                                  |    name: string @index(exact, term) .
                                  |    age: int @index(int) .
                                  |    friend: uid @count .
                                  |  }
                                  |
                                  |  set {
                                  |    _:michael <name> "Michael" .
                                  |    _:michael <age> "39" .
                                  |    _:michael <friend> _:amit .
                                  |    _:michael <friend> _:sarah .
                                  |    _:michael <friend> _:sang .
                                  |    _:michael <friend> _:catalina .
                                  |    _:michael <friend> _:artyom .
                                  |    _:michael <owns_pet> _:rammy .
                                  |
                                  |    _:amit <name> "अमित"@hi .
                                  |    _:amit <name> "অমিত"@bn .
                                  |    _:amit <name> "Amit"@en .
                                  |    _:amit <age> "35" .
                                  |    _:amit <friend> _:michael .
                                  |    _:amit <friend> _:sang .
                                  |    _:amit <friend> _:artyom .
                                  |
                                  |    _:luke <name> "Luke"@en .
                                  |    _:luke <name> "Łukasz"@pl .
                                  |    _:luke <age> "77" .
                                  |
                                  |    _:artyom <name> "Артём"@ru .
                                  |    _:artyom <name> "Artyom"@en .
                                  |    _:artyom <age> "35" .
                                  |
                                  |    _:sarah <name> "Sarah" .
                                  |    _:sarah <age> "55" .
                                  |
                                  |    _:sang <name> "상현"@ko .
                                  |    _:sang <name> "Sang Hyun"@en .
                                  |    _:sang <age> "24" .
                                  |    _:sang <friend> _:amit .
                                  |    _:sang <friend> _:catalina .
                                  |    _:sang <friend> _:hyung .
                                  |    _:sang <owns_pet> _:goldie .
                                  |
                                  |    _:hyung <name> "형신"@ko .
                                  |    _:hyung <name> "Hyung Sin"@en .
                                  |    _:hyung <friend> _:sang .
                                  |
                                  |    _:catalina <name> "Catalina" .
                                  |    _:catalina <age> "19" .
                                  |    _:catalina <friend> _:sang .
                                  |    _:catalina <owns_pet> _:perro .
                                  |
                                  |    _:rammy <name> "Rammy the sheep" .
                                  |
                                  |    _:goldie <name> "Goldie" .
                                  |
                                  |    _:perro <name> "Perro" .
                                  |
                                  |  }
                                  |}
                                  |""".stripMargin)
  val stub: DgraphStub = DgraphGrpc.stub(channel)
  val f: Future[Response] = stub.run(request)
  f onComplete println

  Thread.sleep(10000)
}
