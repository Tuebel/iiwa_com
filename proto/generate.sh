rm cpp_out -r
mkdir cpp_out
rm java_out -r
mkdir java_out
protoc iiwa_msgs.proto --cpp_out=cpp_out --java_out=java_out
