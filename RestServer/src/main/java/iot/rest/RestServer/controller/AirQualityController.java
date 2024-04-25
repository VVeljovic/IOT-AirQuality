package iot.rest.RestServer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.Duration;
import com.google.protobuf.Struct;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.JsonFormat;
import iot.rest.RestServer.client.RestClient;
import iot.rest.RestServer.model.AirDataQualityDTO;
import iot.rest.grpc.AirDataQuality;
import iot.rest.grpc.AirDataQualityOrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="airquality")
public class AirQualityController {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;
    @Autowired
    public AirQualityController(RestClient restClient, ObjectMapper objectMapper) {
        this.restClient = restClient;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/getData/{id}")
    public ResponseEntity<String> getDataById(@PathVariable Integer id) {
        AirDataQuality data = restClient.getDataById(id);
        try{
            String jsonData = JsonFormat.printer().print(data);
            return ResponseEntity.ok(jsonData);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greška prilikom konverzije objekta u json.");
        }
    }

    @PostMapping("/createData")
    public ResponseEntity<String> createData(@RequestBody AirDataQualityDTO data) {
        try {
            System.out.println(data);
            long miliseconds = data.getDate().getTime();
            int nanos = (int)((miliseconds%1000)*1000000);
            Timestamp timestamp = Timestamp.newBuilder().setSeconds(miliseconds/1000).setNanos(nanos).build();
            miliseconds = data.getTime().getTime()+3600000;
            nanos = (int)((miliseconds%1000)*1000000);
            Duration duration = Duration.newBuilder().setSeconds(miliseconds/1000).setNanos(nanos).build();
            AirDataQuality.Builder builder = AirDataQuality.newBuilder();
            builder.setId(data.getId());
            builder.setTime(duration);
            builder.setDate(timestamp);
            builder.setCoGt(data.getCo_gt());
            builder.setPt08S1Co(data.getPt08_s1_co());
            builder.setNmhcGt(data.getNmhc_gt());
            builder.setC6H6Gt(data.getC6h6_gt());
            builder.setPt08S2Nmhc(data.getPt08_s2_nmhc());
            builder.setNoxGt(data.getNox_gt());
            builder.setPt08S3Nox(data.getPt08_s3_nox());
            builder.setNo2Gt(data.getNo2_gt());
            builder.setPt08S4No2(data.getPt08_s4_no2());
            builder.setPt08S5O3(data.getPt08_s5_o3());
            builder.setT(data.getT());
            builder.setRh(data.getRh());
            builder.setAh(data.getAh());
            AirDataQuality airDataQuality = builder.build();


            AirDataQuality result = restClient.createData(airDataQuality);
            String jsonData = JsonFormat.printer().print(result);
            return ResponseEntity.ok(jsonData);



        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greška prilikom konverzije objekta u json ili u proto.");
        }

    }

    @DeleteMapping("/deleteData/{id}")
    public ResponseEntity<String> deleteData(@PathVariable int id) {
        restClient.deleteData(id);
        return ResponseEntity.ok("Data with ID " + id + " deleted successfully.");
    }

    @PutMapping("/updateData")
    public ResponseEntity<String> updateData(@RequestBody AirDataQualityDTO data) {

        try {
            System.out.println(data);
            long miliseconds = data.getDate().getTime();
            int nanos = (int)((miliseconds%1000)*1000000);
            Timestamp timestamp = Timestamp.newBuilder().setSeconds(miliseconds/1000).setNanos(nanos).build();
            miliseconds = data.getTime().getTime()+3600000;
            nanos = (int)((miliseconds%1000)*1000000);
            Duration duration = Duration.newBuilder().setSeconds(miliseconds/1000).setNanos(nanos).build();
            AirDataQuality.Builder builder = AirDataQuality.newBuilder();
            builder.setId(data.getId());
            builder.setTime(duration);
            builder.setDate(timestamp);
            builder.setCoGt(data.getCo_gt());
            builder.setPt08S1Co(data.getPt08_s1_co());
            builder.setNmhcGt(data.getNmhc_gt());
            builder.setC6H6Gt(data.getC6h6_gt());
            builder.setPt08S2Nmhc(data.getPt08_s2_nmhc());
            builder.setNoxGt(data.getNox_gt());
            builder.setPt08S3Nox(data.getPt08_s3_nox());
            builder.setNo2Gt(data.getNo2_gt());
            builder.setPt08S4No2(data.getPt08_s4_no2());
            builder.setPt08S5O3(data.getPt08_s5_o3());
            builder.setT(data.getT());
            builder.setRh(data.getRh());
            builder.setAh(data.getAh());
            AirDataQuality airDataQuality = builder.build();


            AirDataQuality result = restClient.updateData(airDataQuality);
            String jsonData = JsonFormat.printer().print(result);
            return ResponseEntity.ok(jsonData);



        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greška prilikom konverzije objekta u json ili u proto.");
        }
    }
}