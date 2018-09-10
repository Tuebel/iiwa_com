rmdir /q /s cpp_out
mkdir cpp_out
protoc iiwa_service.proto --cpp_out=cpp_out --grpc_out=cpp_out --plugin=protoc-gen-grpc=grpc_cpp_plugin.exe
rmdir /q /s java_out
mkdir java_out
protoc iiwa_service.proto --java_out=java_out --grpc_out=java_out --plugin=protoc-gen-grpc=grpc_java_plugin.exe
