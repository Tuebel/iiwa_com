
rm generated_cpp -r
mkdir generated_cpp
protoc iiwa_service.proto --cpp_out=generated_cpp --grpc_out=generated_cpp --plugin=protoc-gen-grpc=/usr/local/bin/grpc_cpp_plugin
