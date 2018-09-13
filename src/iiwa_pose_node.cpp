#include <iiwa_com/communicator.hpp>
#include <iiwa_com/iiwa_geometry_msgs.hpp>
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
    // connect to the iiwa
    communicator = std::unique_ptr<Communicator>(
        new Communicator(iiwa_address, iiwa_port));
    ROS_INFO("initialized communicator");
  }

  /*!
  Start reading the iiwa state messages and republishing them as ROS messages.
  */
  void start()
  {
    ROS_INFO("start streaming poses");
    communicator->stream_pose([&](const CartesianPose &pose) {
      tf_broadcast.sendTransform(from_iiwa_pose(pose));
    });
    ROS_INFO("finished streaming poses");
  }

private:
  ros::NodeHandle node_handle;
  std::unique_ptr<Communicator> communicator;
  // republishing the transformation
  tf2_ros::TransformBroadcaster tf_broadcast;
};
} // namespace iiwa_com

int main(int argc, char **argv)
{
  // init node
  ros::init(argc, argv, "iiwa_pose_node");
  iiwa_com::IiwaPoseNode pose_node;
  pose_node.start();
  return EXIT_SUCCESS;
}