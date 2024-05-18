package internet.of.things.RestServer;

import GrpcServer.AirDataQuality;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.util.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        System.out.print("cao");
        AirDataQuality data = restClient.getDataById(id);
        System.out.print("2222222222");

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
}
