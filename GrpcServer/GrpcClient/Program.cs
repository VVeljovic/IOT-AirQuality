using Google.Protobuf.WellKnownTypes;
using Grpc.Net.Client;
using GrpcServer;
using System;
using System.Threading.Tasks;
namespace GrpcClient
{
    class Program
    {
        static async Task Main(string[] args)
        {

            var channel = GrpcChannel.ForAddress(" http://localhost:5259");
            var client = new AirQuality.AirQualityClient(channel);
            var clientRequested = new Hellow { Name = "ana" };
            var clientRequest = new DataId { Id = 9359 };
            var request = new AirDataQuality
            {
                Id = 9358,
                Date = Timestamp.FromDateTime(DateTime.UtcNow),
                Time = Duration.FromTimeSpan(TimeSpan.FromMinutes(30)),
                CoGt = 0.5f,
                Pt08S1Co = 100,
                NmhcGt = 50,
                C6H6Gt = 5.5f,
                Pt08S2Nmhc = 150,
                NoxGt = 200,
                Pt08S3Nox = 250,
                No2Gt = 300,
                Pt08S4No2 = 350,
                Pt08S5O3 = 100,
                T = 25.5f,
                Rh = 50.5f,
                Ah = 0.3f
            };

            var reply = await client.updateDataAsync(request);
            Console.WriteLine(reply);
            Console.ReadLine();
        }
    }
}
