rmdir /q /s cpp_out
mkdir cpp_out
rmdir /q /s java_out
mkdir java_out
protoc iiwa_msgs.proto --cpp_out=cpp_out --java_out=java_out
