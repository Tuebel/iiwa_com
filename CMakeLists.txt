cmake_minimum_required(VERSION 3.5)
project(iiwa_com)

if(NOT CMAKE_CXX_STANDARD)
  set(CMAKE_CXX_STANDARD 11)
endif()
if(CMAKE_COMPILER_IS_GNUCXX OR CMAKE_CXX_COMPILER_ID MATCHES "Clang")
  add_compile_options(-Wall -Wextra -Wpedantic)
endif()
set(CMAKE_POSITION_INDEPENDENT_CODE ON)

# find dependencies
find_package(catkin REQUIRED COMPONENTS
  sensor_msgs
  tf2_ros)
find_package(Protobuf REQUIRED)

# packaging
catkin_package(
 INCLUDE_DIRS include
 LIBRARIES robo_guide_ros
 CATKIN_DEPENDS 
  sensor_msgs
  tf2_ros
 DEPENDS Protobuf)

# generate messages
protobuf_generate_cpp(PROTO_SRCS PROTO_HDRS iiwa_msgs.proto)

# create library
add_library(iiwa_com)
target_sources(iiwa_com PRIVATE
  ${PROTO_SRCS}
  ${PROTO_HDRS})
target_include_directories(iiwa_com
  PUBLIC
    include
    ${catkin_INCLUDE_DIRS}
  PRIVATE
    ${Protobuf_INCLUDE_DIRS})
target_link_libraries(iiwa_com PUBLIC
  ${catkin_LIBRARIES}
  ${Protobuf_LIBRARIES})

# installation
install(TARGETS iiwa_com
  ARCHIVE DESTINATION ${CATKIN_PACKAGE_LIB_DESTINATION}
  LIBRARY DESTINATION ${CATKIN_PACKAGE_LIB_DESTINATION}
  RUNTIME DESTINATION ${CATKIN_PACKAGE_BIN_DESTINATION}
)
install(DIRECTORY include/iiwa_com/
  DESTINATION ${CATKIN_PACKAGE_INCLUDE_DESTINATION})
