#include <google/protobuf/util/delimited_message_util.h>
#include <iiwa_com/communicator.hpp>

namespace util = google::protobuf::util;

namespace iiwa_com
{
Communicator::Communicator(const std::string &address, unsigned short port)
    : com_channel(address, port) {}

void Communicator::stream_pose(std::function<void(const CartesianPose &pose)> callback)
{
  // send the request
  CartesianPoseRequest pose_request;
  WrapperMsg request;
  request.set_allocated_cartesian_pose_request(&pose_request);
  util::SerializeDelimitedToZeroCopyStream(
      request, com_channel.get_output_stream().get());
  com_channel.flush_output();
  // keep parsing new pose messages
  WrapperMsg response;
  while (util::ParseDelimitedFromZeroCopyStream(
      &response, com_channel.get_input_stream().get(), nullptr))
  {
    if (response.msg_case() == iiwa_com::WrapperMsg::MsgCase::kCartesianPose)
    {
      callback(response.cartesian_pose());
    }
    else
    {
      std::cerr << "unexpected message type: " << response.msg_case() << "\n";
    }
  }
}
} // namespace iiwa_com