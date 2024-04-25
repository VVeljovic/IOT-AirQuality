package iot.rest.RestServer.client;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import iot.rest.grpc.AirDataQuality;
import iot.rest.grpc.AirQualityGrpc;
import iot.rest.grpc.DataId;
import org.springframework.stereotype.Component;


@Component
public class RestClient {
    private ManagedChannel channel;
    private AirQualityGrpc.AirQualityBlockingStub blockingStub;
    public RestClient() {
        this.channel = ManagedChannelBuilder.forAddress("127.0.0.1", 5259).usePlaintext().build();
        this.blockingStub = AirQualityGrpc.newBlockingStub(channel);
    }
    public AirDataQuality getDataById(int id)
    {
        DataId dataId = DataId.newBuilder().setId(id).build();
        return blockingStub.getDataById(dataId);
    }
    public AirDataQuality createData(AirDataQuality data)
    {
        return blockingStub.createData(data);
    }
    public Empty deleteData(int id)
    {
        DataId dataId = DataId.newBuilder().setId(id).build();
        return blockingStub.deleteData(dataId);
    }
    public AirDataQuality updateData(AirDataQuality data)
    {
        return blockingStub.updateData(data);
    }


}
