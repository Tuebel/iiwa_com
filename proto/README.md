# Protocol Buffers version
Since protobuf dropped Java 6 support with release 3.6.0 and Sunrise is limited
to Java 6 we are stuck at version 
[3.5.1](https://github.com/protocolbuffers/protobuf/releases/tag/v3.5.1).

# gRPC version
The grpc version is [1.14.0](https://github.com/grpc/grpc/releases/tag/v1.14.0)

# Generation
The easiest way to generate the files is using the supplied scripts and compiler
binaries. Use the generate_cpp.sh script for generation of the Linux cpp files;
it is expected that all dependencies (grpc and protobuf) are installed.
Under windows use generate.bat to create the cpp and Java files.