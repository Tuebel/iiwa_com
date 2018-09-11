#include <grpc/grpc.h>
#include <grpcpp/channel.h>
#include <grpcpp/client_context.h>
#include <grpcpp/create_channel.h>
#include <grpcpp/security/credentials.h>
#include <iiwa_com/iiwa_geometry_msgs.hpp>
#include <iiwa_service.grpc.pb.h>
#include <ros/ros.h>
#include <tf2_ros/transform_broadcaster.h>

namespace iiwa_com
{
/*!
Receives pose messages from the iiwa robot, translates them to Transform 
messages and sends publishes them via tf2_ros.
*/
class IiwaPoseNode
{
public:
  IiwaPoseNode()
  {
    // load params
    ros::NodeHandle private_nh("~");
    // ip of the iiwa
    std::string iiwa_address;
    private_nh.param<std::string>("iiwa_address", iiwa_address, "172.31.1.147");
    // port of the tcp server
    int iiwa_port;
    private_nh.param<int>("iiwa_port", iiwa_port, 30005);
    // connect to the iiwa and create stub
    auto channel = grpc::CreateChannel(
        iiwa_address + ":" + std::to_string(iiwa_port),
        grpc::InsecureChannelCredentials());
    stub = std::unique_ptr<IiwaService::Stub>(new IiwaService::Stub(channel));
  }

  /*!
  Start reading the iiwa state messages and republishing them as ROS messages.
  */
  void start()
  {
    // init the stream call
    grpc::ClientContext context;
    StreamCartesianPoseRequest request;
    CartesianPose pose;
    auto reader = stub->StreamCartesianPose(&context, request);
    // keep reading while ros is alive and the stream is not finished
    while (ros::ok() && reader->Read(&pose))
    {
      // republish the pose as tf2
      tf_broadcast.sendTransform(from_iiwa_pose(pose));
    }
    auto status = reader->Finish();
    if (!status.ok())
    {
      ROS_ERROR("StreamCartesianPose rpc failed");
      ROS_ERROR(status.error_message().c_str());
      ROS_ERROR(status.error_details().c_str());
    }
  }

private:
  ros::NodeHandle node_handle;
  std::unique_ptr<IiwaService::Stub> stub;
  // republishing the transformation
  tf2_ros::TransformBroadcaster tf_broadcast;
};
} // namespace iiwa_com

int main(int argc, char **argv)
{
  // init node
  ros::init(argc, argv, "iiwa_pose_node");
  iiwa_com::IiwaPoseNode pose_node;
  return EXIT_SUCCESS;
}