# DGraph4S: Scala API for DGraph database

DGraph4S is a Scala API for the [DGraph graph database](https://dgraph.io/), it uses [GRPC](https://grpc.io/) to communicate with the DGraph database.

## Windows build

Requires Python 2.
PB.pythonExe := "C:\\Python27\\Python.exe"

## Test that it works

Ensure that DGraph is running and it exposes port 9080 which is the default port for GRPC communication. Run com.dgraph4s.TestConnection

## Running DGraph from Docker

https://hub.docker.com/r/dgraph/dgraph/

~~~~
docker pull dgraph/dgraph
mkdir -p dgraph
docker run -it -p 8080:8080 -p 9080:9080 -v dgraph:/dgraph --name dgraph dgraph/dgraph dgraph --bindall=true --memory_mb 2048
~~~~

## Keep protobuf files up-to-date

The files in src/main/protobuf/ contain the definition of the Protocol Buffers for DGraph GRPC communications, you may need to manually update these files if they change, refer to the README.md in that directory.

## Publish local to use

~~~~
sbt publishLocal
~~~~

In your build.sbt

~~~~
libraryDependencies += "dgraph4s" %% "dgraph4s" % "0.1"
~~~~

