package iot.rest.grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: airquality.proto")
public final class AirQualityGrpc {

  private AirQualityGrpc() {}

  public static final String SERVICE_NAME = "AirQuality";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<DataId,
      AirDataQuality> METHOD_GET_DATA_BY_ID =
      io.grpc.MethodDescriptor.<DataId, AirDataQuality>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "AirQuality", "getDataById"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              DataId.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              AirDataQuality.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<AirDataQuality,
      AirDataQuality> METHOD_CREATE_DATA =
      io.grpc.MethodDescriptor.<AirDataQuality, AirDataQuality>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "AirQuality", "createData"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              AirDataQuality.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              AirDataQuality.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<DataId,
      com.google.protobuf.Empty> METHOD_DELETE_DATA =
      io.grpc.MethodDescriptor.<DataId, com.google.protobuf.Empty>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "AirQuality", "deleteData"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              DataId.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.google.protobuf.Empty.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<AirDataQuality,
      AirDataQuality> METHOD_UPDATE_DATA =
      io.grpc.MethodDescriptor.<AirDataQuality, AirDataQuality>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "AirQuality", "updateData"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              AirDataQuality.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              AirDataQuality.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AirQualityStub newStub(io.grpc.Channel channel) {
    return new AirQualityStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AirQualityBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AirQualityBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AirQualityFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AirQualityFutureStub(channel);
  }

  /**
   */
  public static abstract class AirQualityImplBase implements io.grpc.BindableService {

    /**
     */
    public void getDataById(DataId request,
                            io.grpc.stub.StreamObserver<AirDataQuality> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_DATA_BY_ID, responseObserver);
    }

    /**
     */
    public void createData(AirDataQuality request,
                           io.grpc.stub.StreamObserver<AirDataQuality> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CREATE_DATA, responseObserver);
    }

    /**
     */
    public void deleteData(DataId request,
                           io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_DELETE_DATA, responseObserver);
    }

    /**
     */
    public void updateData(AirDataQuality request,
                           io.grpc.stub.StreamObserver<AirDataQuality> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UPDATE_DATA, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_DATA_BY_ID,
            asyncUnaryCall(
              new MethodHandlers<
                DataId,
                AirDataQuality>(
                  this, METHODID_GET_DATA_BY_ID)))
          .addMethod(
            METHOD_CREATE_DATA,
            asyncUnaryCall(
              new MethodHandlers<
                AirDataQuality,
                AirDataQuality>(
                  this, METHODID_CREATE_DATA)))
          .addMethod(
            METHOD_DELETE_DATA,
            asyncUnaryCall(
              new MethodHandlers<
                DataId,
                com.google.protobuf.Empty>(
                  this, METHODID_DELETE_DATA)))
          .addMethod(
            METHOD_UPDATE_DATA,
            asyncUnaryCall(
              new MethodHandlers<
                AirDataQuality,
                AirDataQuality>(
                  this, METHODID_UPDATE_DATA)))
          .build();
    }
  }

  /**
   */
  public static final class AirQualityStub extends io.grpc.stub.AbstractStub<AirQualityStub> {
    private AirQualityStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AirQualityStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AirQualityStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AirQualityStub(channel, callOptions);
    }

    /**
     */
    public void getDataById(DataId request,
                            io.grpc.stub.StreamObserver<AirDataQuality> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_DATA_BY_ID, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createData(AirDataQuality request,
                           io.grpc.stub.StreamObserver<AirDataQuality> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CREATE_DATA, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteData(DataId request,
                           io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_DELETE_DATA, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateData(AirDataQuality request,
                           io.grpc.stub.StreamObserver<AirDataQuality> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UPDATE_DATA, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AirQualityBlockingStub extends io.grpc.stub.AbstractStub<AirQualityBlockingStub> {
    private AirQualityBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AirQualityBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AirQualityBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AirQualityBlockingStub(channel, callOptions);
    }

    /**
     */
    public AirDataQuality getDataById(DataId request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_DATA_BY_ID, getCallOptions(), request);
    }

    /**
     */
    public AirDataQuality createData(AirDataQuality request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CREATE_DATA, getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty deleteData(DataId request) {
      return blockingUnaryCall(
          getChannel(), METHOD_DELETE_DATA, getCallOptions(), request);
    }

    /**
     */
    public AirDataQuality updateData(AirDataQuality request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UPDATE_DATA, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AirQualityFutureStub extends io.grpc.stub.AbstractStub<AirQualityFutureStub> {
    private AirQualityFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AirQualityFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AirQualityFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AirQualityFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<AirDataQuality> getDataById(
        DataId request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_DATA_BY_ID, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<AirDataQuality> createData(
        AirDataQuality request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CREATE_DATA, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> deleteData(
        DataId request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_DELETE_DATA, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<AirDataQuality> updateData(
        AirDataQuality request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UPDATE_DATA, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_DATA_BY_ID = 0;
  private static final int METHODID_CREATE_DATA = 1;
  private static final int METHODID_DELETE_DATA = 2;
  private static final int METHODID_UPDATE_DATA = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AirQualityImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AirQualityImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_DATA_BY_ID:
          serviceImpl.getDataById((DataId) request,
              (io.grpc.stub.StreamObserver<AirDataQuality>) responseObserver);
          break;
        case METHODID_CREATE_DATA:
          serviceImpl.createData((AirDataQuality) request,
              (io.grpc.stub.StreamObserver<AirDataQuality>) responseObserver);
          break;
        case METHODID_DELETE_DATA:
          serviceImpl.deleteData((DataId) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_UPDATE_DATA:
          serviceImpl.updateData((AirDataQuality) request,
              (io.grpc.stub.StreamObserver<AirDataQuality>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class AirQualityDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Airquality.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AirQualityGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AirQualityDescriptorSupplier())
              .addMethod(METHOD_GET_DATA_BY_ID)
              .addMethod(METHOD_CREATE_DATA)
              .addMethod(METHOD_DELETE_DATA)
              .addMethod(METHOD_UPDATE_DATA)
              .build();
        }
      }
    }
    return result;
  }
}
