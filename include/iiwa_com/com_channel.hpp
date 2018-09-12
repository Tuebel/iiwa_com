#pragma once
#include <google/protobuf/io/zero_copy_stream.h>
#include <memory>

namespace io = google::protobuf::io;
namespace iiwa_com
{
/*!
Communication channel for the delimited protocol buffers communication.
*/
class ComChannel
{
public:
  /*!
  Create a channel that communicates with the given endpoint via TCP.
  Throws an exception if the communication failed.
  */
  ComChannel(const std::string &address, unsigned short port);
  ~ComChannel();

  /*!
  Stream to read from for incoming calls
  */
  std::shared_ptr<io::ZeroCopyInputStream> get_input_stream() const;

  /*!
  Stream to write to for outgoing messages
  */
  std::shared_ptr<io::ZeroCopyOutputStream> get_output_stream() const;

private:
  // using raw POSIX sockets for the FileInputStream and FileOutputStream
  int socket_fd;
  // streams with the socket as file descriptor
  std::shared_ptr<io::ZeroCopyInputStream> input_stream;
  std::shared_ptr<io::ZeroCopyOutputStream> output_stream;
};
} // namespace iiwa_com
