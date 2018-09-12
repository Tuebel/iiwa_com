#pragma once
#include <functional>
#include <iiwa_com/com_channel.hpp>
#include <iiwa_msgs.pb.h>
#include <string>

namespace iiwa_com
{
/*!
Bundles the iiwa communication methods.
*/
class Communicator
{
public:
  /*!
  Create a communicator for the given endpoint.
  */
  Communicator(const std::string& address, unsigned short port);

  /*!
  Start streaming the current pose of the robot.
  \param callback is called when a new pose has been received.
  */
  void stream_pose(std::function<void(const CartesianPose& pose)> callback);

private:
  ComChannel com_channel;
};
} // namespace iiwa_com