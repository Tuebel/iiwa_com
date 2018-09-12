#include <arpa/inet.h>
#include <google/protobuf/io/zero_copy_stream_impl.h>
#include <iiwa_com/com_channel.hpp>
#include <netinet/in.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>

namespace iiwa_com
{
ComChannel::ComChannel(const std::string &address, unsigned short port)
{
  // create socket file descriptor
  socket_fd = socket(AF_INET, SOCK_STREAM, 0);
  if (socket_fd < 0)
  {
    throw std::runtime_error("failed to create the socket");
  }
  // fill the address
  struct sockaddr_in sockaddr = {};
  inet_aton(address.c_str(), &sockaddr.sin_addr);
  sockaddr.sin_family = AF_INET;
  sockaddr.sin_port = htons(port);
  // connect the socket
  if (connect(socket_fd, (struct sockaddr *)&sockaddr, sizeof(sockaddr)) < 0)
  {
    throw std::runtime_error("failed to connect the socket");
  }
  // create the streams from the socket
  input_stream = std::make_shared<io::FileInputStream>(socket_fd);
  output_stream = std::make_shared<io::FileOutputStream>(socket_fd);
}

ComChannel::~ComChannel()
{
  // close a valid connection
  if (socket_fd >= 0)
  {
    close(socket_fd);
  }
}

std::shared_ptr<io::ZeroCopyInputStream> ComChannel::get_input_stream() const
{
  return input_stream;
}

std::shared_ptr<io::ZeroCopyOutputStream> ComChannel::get_output_stream() const
{
  return output_stream;
}
} // namespace iiwa_com
