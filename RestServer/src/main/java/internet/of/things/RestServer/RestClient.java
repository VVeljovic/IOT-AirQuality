package internet.of.things.RestServer;

import GrpcServer.*;
import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Timestamps;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RestClient {
private ManagedChannel channel;
private AirQualityGrpc.AirQualityBlockingStub blockingStub;
public RestClient()
{
    this.channel = ManagedChannelBuilder.forAddress("dotnetapp", 8080).usePlaintext().build();
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
    public AirDataQuality getMinDataInRange(String startDatestr, String endDatestr, String propertyName) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date startDate = dateFormat.parse(startDatestr);
            Date endDate = dateFormat.parse(endDatestr);
            Timestamp startTimestamp = Timestamps.fromMillis(startDate.getTime());
            Timestamp endTimestamp = Timestamps.fromMillis(endDate.getTime());
            DateRange dateRange = DateRange.newBuilder().setStartDate(startTimestamp).setEndDate(endTimestamp).setPropertyName(propertyName).build();
            return blockingStub.minDataValueInRange(dateRange);


    }
    public AirDataQuality getMaxDataInRange(String startDatestr, String endDatestr, String propertyName) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = dateFormat.parse(startDatestr);
        Date endDate = dateFormat.parse(endDatestr);
        Timestamp startTimestamp = Timestamps.fromMillis(startDate.getTime());
        Timestamp endTimestamp = Timestamps.fromMillis(endDate.getTime());
        DateRange dateRange = DateRange.newBuilder().setStartDate(startTimestamp).setEndDate(endTimestamp).setPropertyName(propertyName).build();
        return blockingStub.maxDataValueInRange(dateRange);


    }
    public AverageData getAvgValueOfPropertyInRange(String startDatestr, String endDatestr, String propertyName) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = dateFormat.parse(startDatestr);
        Date endDate = dateFormat.parse(endDatestr);
        Timestamp startTimestamp = Timestamps.fromMillis(startDate.getTime());
        Timestamp endTimestamp = Timestamps.fromMillis(endDate.getTime());
        DateRange dateRange = DateRange.newBuilder().setStartDate(startTimestamp).setEndDate(endTimestamp).setPropertyName(propertyName).build();
        return blockingStub.averageDataValueInRange(dateRange);


    }
    public SumData getSumValueOfPropertyInRange(String startDatestr, String endDatestr, String propertyName) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = dateFormat.parse(startDatestr);
        Date endDate = dateFormat.parse(endDatestr);
        Timestamp startTimestamp = Timestamps.fromMillis(startDate.getTime());
        Timestamp endTimestamp = Timestamps.fromMillis(endDate.getTime());
        DateRange dateRange = DateRange.newBuilder().setStartDate(startTimestamp).setEndDate(endTimestamp).setPropertyName(propertyName).build();
        return blockingStub.sumDataValueInRange(dateRange);


    }
}
