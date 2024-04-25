package iot.rest.RestServer;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import iot.rest.grpc.AirDataQuality;
import iot.rest.grpc.AirQualityGrpc;
import iot.rest.grpc.DataId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServerApplication.class, args);
		System.out.println('a');







	}

}
