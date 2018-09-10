package iiwa_com;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Control the KUKA iiwa robot via RPC calls.
 * Receive information from the KUKA iiwa robot via streaming calls.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.14.0)",
    comments = "Source: iiwa_service.proto")
public final class IiwaServiceGrpc {

  private IiwaServiceGrpc() {}

  public static final String SERVICE_NAME = "iiwa_com.IiwaService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<iiwa_com.IiwaServiceOuterClass.StreamCartesianPoseRequest,
      iiwa_com.IiwaServiceOuterClass.CartesianPose> getStreamCartesianPoseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamCartesianPose",
      requestType = iiwa_com.IiwaServiceOuterClass.StreamCartesianPoseRequest.class,
      responseType = iiwa_com.IiwaServiceOuterClass.CartesianPose.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<iiwa_com.IiwaServiceOuterClass.StreamCartesianPoseRequest,
      iiwa_com.IiwaServiceOuterClass.CartesianPose> getStreamCartesianPoseMethod() {
    io.grpc.MethodDescriptor<iiwa_com.IiwaServiceOuterClass.StreamCartesianPoseRequest, iiwa_com.IiwaServiceOuterClass.CartesianPose> getStreamCartesianPoseMethod;
    if ((getStreamCartesianPoseMethod = IiwaServiceGrpc.getStreamCartesianPoseMethod) == null) {
      synchronized (IiwaServiceGrpc.class) {
        if ((getStreamCartesianPoseMethod = IiwaServiceGrpc.getStreamCartesianPoseMethod) == null) {
          IiwaServiceGrpc.getStreamCartesianPoseMethod = getStreamCartesianPoseMethod = 
              io.grpc.MethodDescriptor.<iiwa_com.IiwaServiceOuterClass.StreamCartesianPoseRequest, iiwa_com.IiwaServiceOuterClass.CartesianPose>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "iiwa_com.IiwaService", "StreamCartesianPose"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  iiwa_com.IiwaServiceOuterClass.StreamCartesianPoseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  iiwa_com.IiwaServiceOuterClass.CartesianPose.getDefaultInstance()))
                  .setSchemaDescriptor(new IiwaServiceMethodDescriptorSupplier("StreamCartesianPose"))
                  .build();
          }
        }
     }
     return getStreamCartesianPoseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<iiwa_com.IiwaServiceOuterClass.StreamCartesianStateRequest,
      iiwa_com.IiwaServiceOuterClass.CartesianState> getStreamCartesianStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamCartesianState",
      requestType = iiwa_com.IiwaServiceOuterClass.StreamCartesianStateRequest.class,
      responseType = iiwa_com.IiwaServiceOuterClass.CartesianState.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<iiwa_com.IiwaServiceOuterClass.StreamCartesianStateRequest,
      iiwa_com.IiwaServiceOuterClass.CartesianState> getStreamCartesianStateMethod() {
    io.grpc.MethodDescriptor<iiwa_com.IiwaServiceOuterClass.StreamCartesianStateRequest, iiwa_com.IiwaServiceOuterClass.CartesianState> getStreamCartesianStateMethod;
    if ((getStreamCartesianStateMethod = IiwaServiceGrpc.getStreamCartesianStateMethod) == null) {
      synchronized (IiwaServiceGrpc.class) {
        if ((getStreamCartesianStateMethod = IiwaServiceGrpc.getStreamCartesianStateMethod) == null) {
          IiwaServiceGrpc.getStreamCartesianStateMethod = getStreamCartesianStateMethod = 
              io.grpc.MethodDescriptor.<iiwa_com.IiwaServiceOuterClass.StreamCartesianStateRequest, iiwa_com.IiwaServiceOuterClass.CartesianState>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "iiwa_com.IiwaService", "StreamCartesianState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  iiwa_com.IiwaServiceOuterClass.StreamCartesianStateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  iiwa_com.IiwaServiceOuterClass.CartesianState.getDefaultInstance()))
                  .setSchemaDescriptor(new IiwaServiceMethodDescriptorSupplier("StreamCartesianState"))
                  .build();
          }
        }
     }
     return getStreamCartesianStateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static IiwaServiceStub newStub(io.grpc.Channel channel) {
    return new IiwaServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static IiwaServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new IiwaServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static IiwaServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new IiwaServiceFutureStub(channel);
  }

  /**
   * <pre>
   * Control the KUKA iiwa robot via RPC calls.
   * Receive information from the KUKA iiwa robot via streaming calls.
   * </pre>
   */
  public static abstract class IiwaServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * stream the cartesian pose from the robot
     * </pre>
     */
    public void streamCartesianPose(iiwa_com.IiwaServiceOuterClass.StreamCartesianPoseRequest request,
        io.grpc.stub.StreamObserver<iiwa_com.IiwaServiceOuterClass.CartesianPose> responseObserver) {
      asyncUnimplementedUnaryCall(getStreamCartesianPoseMethod(), responseObserver);
    }

    /**
     * <pre>
     * stream the cartesian state from the robot
     * </pre>
     */
    public void streamCartesianState(iiwa_com.IiwaServiceOuterClass.StreamCartesianStateRequest request,
        io.grpc.stub.StreamObserver<iiwa_com.IiwaServiceOuterClass.CartesianState> responseObserver) {
      asyncUnimplementedUnaryCall(getStreamCartesianStateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getStreamCartesianPoseMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                iiwa_com.IiwaServiceOuterClass.StreamCartesianPoseRequest,
                iiwa_com.IiwaServiceOuterClass.CartesianPose>(
                  this, METHODID_STREAM_CARTESIAN_POSE)))
          .addMethod(
            getStreamCartesianStateMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                iiwa_com.IiwaServiceOuterClass.StreamCartesianStateRequest,
                iiwa_com.IiwaServiceOuterClass.CartesianState>(
                  this, METHODID_STREAM_CARTESIAN_STATE)))
          .build();
    }
  }

  /**
   * <pre>
   * Control the KUKA iiwa robot via RPC calls.
   * Receive information from the KUKA iiwa robot via streaming calls.
   * </pre>
   */
  public static final class IiwaServiceStub extends io.grpc.stub.AbstractStub<IiwaServiceStub> {
    private IiwaServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private IiwaServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IiwaServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new IiwaServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * stream the cartesian pose from the robot
     * </pre>
     */
    public void streamCartesianPose(iiwa_com.IiwaServiceOuterClass.StreamCartesianPoseRequest request,
        io.grpc.stub.StreamObserver<iiwa_com.IiwaServiceOuterClass.CartesianPose> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getStreamCartesianPoseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * stream the cartesian state from the robot
     * </pre>
     */
    public void streamCartesianState(iiwa_com.IiwaServiceOuterClass.StreamCartesianStateRequest request,
        io.grpc.stub.StreamObserver<iiwa_com.IiwaServiceOuterClass.CartesianState> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getStreamCartesianStateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Control the KUKA iiwa robot via RPC calls.
   * Receive information from the KUKA iiwa robot via streaming calls.
   * </pre>
   */
  public static final class IiwaServiceBlockingStub extends io.grpc.stub.AbstractStub<IiwaServiceBlockingStub> {
    private IiwaServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private IiwaServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IiwaServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new IiwaServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * stream the cartesian pose from the robot
     * </pre>
     */
    public java.util.Iterator<iiwa_com.IiwaServiceOuterClass.CartesianPose> streamCartesianPose(
        iiwa_com.IiwaServiceOuterClass.StreamCartesianPoseRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getStreamCartesianPoseMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * stream the cartesian state from the robot
     * </pre>
     */
    public java.util.Iterator<iiwa_com.IiwaServiceOuterClass.CartesianState> streamCartesianState(
        iiwa_com.IiwaServiceOuterClass.StreamCartesianStateRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getStreamCartesianStateMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Control the KUKA iiwa robot via RPC calls.
   * Receive information from the KUKA iiwa robot via streaming calls.
   * </pre>
   */
  public static final class IiwaServiceFutureStub extends io.grpc.stub.AbstractStub<IiwaServiceFutureStub> {
    private IiwaServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private IiwaServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IiwaServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new IiwaServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_STREAM_CARTESIAN_POSE = 0;
  private static final int METHODID_STREAM_CARTESIAN_STATE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final IiwaServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(IiwaServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STREAM_CARTESIAN_POSE:
          serviceImpl.streamCartesianPose((iiwa_com.IiwaServiceOuterClass.StreamCartesianPoseRequest) request,
              (io.grpc.stub.StreamObserver<iiwa_com.IiwaServiceOuterClass.CartesianPose>) responseObserver);
          break;
        case METHODID_STREAM_CARTESIAN_STATE:
          serviceImpl.streamCartesianState((iiwa_com.IiwaServiceOuterClass.StreamCartesianStateRequest) request,
              (io.grpc.stub.StreamObserver<iiwa_com.IiwaServiceOuterClass.CartesianState>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class IiwaServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    IiwaServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return iiwa_com.IiwaServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("IiwaService");
    }
  }

  private static final class IiwaServiceFileDescriptorSupplier
      extends IiwaServiceBaseDescriptorSupplier {
    IiwaServiceFileDescriptorSupplier() {}
  }

  private static final class IiwaServiceMethodDescriptorSupplier
      extends IiwaServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    IiwaServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (IiwaServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new IiwaServiceFileDescriptorSupplier())
              .addMethod(getStreamCartesianPoseMethod())
              .addMethod(getStreamCartesianStateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
