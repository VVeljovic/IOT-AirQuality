package client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


public class RestClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5259).usePlaintext().build();

    }
}
