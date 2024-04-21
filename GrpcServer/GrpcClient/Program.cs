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
            var reply = await client.deleteDataAsync(clientRequest);
            Console.WriteLine(reply);
            Console.ReadLine();
        }
    }
}
