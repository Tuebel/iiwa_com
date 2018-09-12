cmake_minimum_required(VERSION 3.5)
project(iiwa_com)

if(NOT CMAKE_CXX_STANDARD)
  set(CMAKE_CXX_STANDARD 11)
endif()
if(CMAKE_COMPILER_IS_GNUCXX OR CMAKE_CXX_COMPILER_ID MATCHES "Clang")
  add_compile_options(-Wall -Wextra)
endif()
set(CMAKE_POSITION_INDEPENDENT_CODE ON)

# find dependencies
find_package(catkin REQUIRED COMPONENTS
  sensor_msgs
  tf2_geometry_msgs
  tf2_ros)
find_package(Protobuf CONFIG REQUIRED)

# set aliases so catkin_package() does not complain
set(Protobuf_LIBRARIES protobuf::libprotobuf)

# packaging
catkin_package(
 INCLUDE_DIRS include
 LIBRARIES robo_guide_ros
 CATKIN_DEPENDS 
  sensor_msgs
  tf2_geometry_msgs
  tf2_ros
 DEPENDS Protobuf)

# create library
add_library(iiwa_com)
target_sources(iiwa_com PRIVATE
  src/generated/iiwa_msgs.pb.cc
  src/iiwa_com/com_channel.cpp
  src/iiwa_com/communicator.cpp
  src/iiwa_com/iiwa_geometry_msgs.cpp)
target_include_directories(iiwa_com PUBLIC
  ${catkin_INCLUDE_DIRS}
  include
  src/generated)
target_link_libraries(iiwa_com PUBLIC
    ${catkin_LIBRARIES}
    ${Protobuf_LIBRARIES})

# executables
add_executable(iiwa_pose_node)
target_sources(iiwa_pose_node PRIVATE
  src/iiwa_pose_node.cpp)
target_link_libraries(iiwa_pose_node PUBLIC
  iiwa_com)

# installation
install(TARGETS iiwa_com
  ARCHIVE DESTINATION ${CATKIN_PACKAGE_LIB_DESTINATION}
  LIBRARY DESTINATION ${CATKIN_PACKAGE_LIB_DESTINATION}
  RUNTIME DESTINATION ${CATKIN_PACKAGE_BIN_DESTINATION}
)
install(DIRECTORY include/iiwa_com/
  DESTINATION ${CATKIN_PACKAGE_INCLUDE_DESTINATION})
