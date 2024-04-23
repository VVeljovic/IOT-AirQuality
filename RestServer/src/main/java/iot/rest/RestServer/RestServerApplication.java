package iot.rest.RestServer;

import GrpcServer.AirDataQuality;
import GrpcServer.AirQualityGrpc;
import GrpcServer.DataId;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServerApplication.class, args);
		System.out.println('a');
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5259).usePlaintext().build();


		AirQualityGrpc.AirQualityBlockingStub airQualityBlockingStub = AirQualityGrpc.newBlockingStub(channel);

		int id = 1;
		DataId dataId = DataId.newBuilder().setId(id).build();
		AirDataQuality response = airQualityBlockingStub.getDataById(dataId);


		System.out.println("Response from server: " + response);
	}

}
