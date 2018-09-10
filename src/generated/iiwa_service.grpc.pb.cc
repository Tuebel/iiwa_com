// Generated by the gRPC C++ plugin.
// If you make any local change, they will be lost.
// source: iiwa_service.proto

#include "iiwa_service.pb.h"
#include "iiwa_service.grpc.pb.h"

#include <grpcpp/impl/codegen/async_stream.h>
#include <grpcpp/impl/codegen/async_unary_call.h>
#include <grpcpp/impl/codegen/channel_interface.h>
#include <grpcpp/impl/codegen/client_unary_call.h>
#include <grpcpp/impl/codegen/method_handler_impl.h>
#include <grpcpp/impl/codegen/rpc_service_method.h>
#include <grpcpp/impl/codegen/service_type.h>
#include <grpcpp/impl/codegen/sync_stream.h>
namespace iiwa_com {

static const char* IiwaService_method_names[] = {
  "/iiwa_com.IiwaService/StreamCartesianPose",
  "/iiwa_com.IiwaService/StreamCartesianState",
};

std::unique_ptr< IiwaService::Stub> IiwaService::NewStub(const std::shared_ptr< ::grpc::ChannelInterface>& channel, const ::grpc::StubOptions& options) {
  (void)options;
  std::unique_ptr< IiwaService::Stub> stub(new IiwaService::Stub(channel));
  return stub;
}

IiwaService::Stub::Stub(const std::shared_ptr< ::grpc::ChannelInterface>& channel)
  : channel_(channel), rpcmethod_StreamCartesianPose_(IiwaService_method_names[0], ::grpc::internal::RpcMethod::SERVER_STREAMING, channel)
  , rpcmethod_StreamCartesianState_(IiwaService_method_names[1], ::grpc::internal::RpcMethod::SERVER_STREAMING, channel)
  {}

::grpc::ClientReader< ::iiwa_com::CartesianPose>* IiwaService::Stub::StreamCartesianPoseRaw(::grpc::ClientContext* context, const ::iiwa_com::StreamCartesianPoseRequest& request) {
  return ::grpc::internal::ClientReaderFactory< ::iiwa_com::CartesianPose>::Create(channel_.get(), rpcmethod_StreamCartesianPose_, context, request);
}

::grpc::ClientAsyncReader< ::iiwa_com::CartesianPose>* IiwaService::Stub::AsyncStreamCartesianPoseRaw(::grpc::ClientContext* context, const ::iiwa_com::StreamCartesianPoseRequest& request, ::grpc::CompletionQueue* cq, void* tag) {
  return ::grpc::internal::ClientAsyncReaderFactory< ::iiwa_com::CartesianPose>::Create(channel_.get(), cq, rpcmethod_StreamCartesianPose_, context, request, true, tag);
}

::grpc::ClientAsyncReader< ::iiwa_com::CartesianPose>* IiwaService::Stub::PrepareAsyncStreamCartesianPoseRaw(::grpc::ClientContext* context, const ::iiwa_com::StreamCartesianPoseRequest& request, ::grpc::CompletionQueue* cq) {
  return ::grpc::internal::ClientAsyncReaderFactory< ::iiwa_com::CartesianPose>::Create(channel_.get(), cq, rpcmethod_StreamCartesianPose_, context, request, false, nullptr);
}

::grpc::ClientReader< ::iiwa_com::CartesianState>* IiwaService::Stub::StreamCartesianStateRaw(::grpc::ClientContext* context, const ::iiwa_com::StreamCartesianStateRequest& request) {
  return ::grpc::internal::ClientReaderFactory< ::iiwa_com::CartesianState>::Create(channel_.get(), rpcmethod_StreamCartesianState_, context, request);
}

::grpc::ClientAsyncReader< ::iiwa_com::CartesianState>* IiwaService::Stub::AsyncStreamCartesianStateRaw(::grpc::ClientContext* context, const ::iiwa_com::StreamCartesianStateRequest& request, ::grpc::CompletionQueue* cq, void* tag) {
  return ::grpc::internal::ClientAsyncReaderFactory< ::iiwa_com::CartesianState>::Create(channel_.get(), cq, rpcmethod_StreamCartesianState_, context, request, true, tag);
}

::grpc::ClientAsyncReader< ::iiwa_com::CartesianState>* IiwaService::Stub::PrepareAsyncStreamCartesianStateRaw(::grpc::ClientContext* context, const ::iiwa_com::StreamCartesianStateRequest& request, ::grpc::CompletionQueue* cq) {
  return ::grpc::internal::ClientAsyncReaderFactory< ::iiwa_com::CartesianState>::Create(channel_.get(), cq, rpcmethod_StreamCartesianState_, context, request, false, nullptr);
}

IiwaService::Service::Service() {
  AddMethod(new ::grpc::internal::RpcServiceMethod(
      IiwaService_method_names[0],
      ::grpc::internal::RpcMethod::SERVER_STREAMING,
      new ::grpc::internal::ServerStreamingHandler< IiwaService::Service, ::iiwa_com::StreamCartesianPoseRequest, ::iiwa_com::CartesianPose>(
          std::mem_fn(&IiwaService::Service::StreamCartesianPose), this)));
  AddMethod(new ::grpc::internal::RpcServiceMethod(
      IiwaService_method_names[1],
      ::grpc::internal::RpcMethod::SERVER_STREAMING,
      new ::grpc::internal::ServerStreamingHandler< IiwaService::Service, ::iiwa_com::StreamCartesianStateRequest, ::iiwa_com::CartesianState>(
          std::mem_fn(&IiwaService::Service::StreamCartesianState), this)));
}

IiwaService::Service::~Service() {
}

::grpc::Status IiwaService::Service::StreamCartesianPose(::grpc::ServerContext* context, const ::iiwa_com::StreamCartesianPoseRequest* request, ::grpc::ServerWriter< ::iiwa_com::CartesianPose>* writer) {
  (void) context;
  (void) request;
  (void) writer;
  return ::grpc::Status(::grpc::StatusCode::UNIMPLEMENTED, "");
}

::grpc::Status IiwaService::Service::StreamCartesianState(::grpc::ServerContext* context, const ::iiwa_com::StreamCartesianStateRequest* request, ::grpc::ServerWriter< ::iiwa_com::CartesianState>* writer) {
  (void) context;
  (void) request;
  (void) writer;
  return ::grpc::Status(::grpc::StatusCode::UNIMPLEMENTED, "");
}


}  // namespace iiwa_com

