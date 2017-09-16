# Development

For updates to the service, drop the [graphrespose.proto](https://raw.githubusercontent.com/dgraph-io/dgraph/master/protos/graphresponse.proto),
 [facets.proto](https://raw.githubusercontent.com/dgraph-io/dgraph/master/protos/facets.proto),
 and [schema.proto](https://raw.githubusercontent.com/dgraph-io/dgraph/master/protos/schema.proto) file here and add:

* graphresponse.proto
```
...
syntax="proto3";
// update facets proto file path
import "facets.proto";

package protos;

//java configurations
option java_multiple_files = true;
option java_package = "io.dgraph.proto";
option java_outer_classname = "GraphResponse";

service Dgraph {
    rpc Run (Request) returns (Response) {};
}
...
```

* facets.proto
```
...
syntax = "proto3";

package protos;

option java_multiple_files = true;
option java_package = "io.dgraph.proto.facets";
option java_outer_classname = "FacetsProto";

message Facet {
	enum ValType {
...
```

Once the java package has been specified, execute:
```
./gradlew build
```
in the root of the project to regenerate the service Java implementations of the Grpc calls.
