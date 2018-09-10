# iiwa_com
  Enables basic communication with a KUKA LBR iiwa robot by utilizing a Sunrise
  Project and ROS. For now the package focuses on receiving the robots state.
  
# dependencies
Depends on:

- grpc (install via cmake inside the grpc folder:
```bash
mkdir build
cd build
cmake .. -DgRPC_INSTALL=ON -DgRPC_BUILD_TESTS=OFF -DgRPC_PROTOBUF_PROVIDER=package -DgRPC_ZLIB_PROVIDER=package -DgRPC_CARES_PROVIDER=package -DgRPC_SSL_PROVIDER=package -DCMAKE_BUILD_TYPE=Release
make
sudo make install
```
)
- Protobuf
- sensor_msgs
- tf2_ros

Build erverything with cmake.
