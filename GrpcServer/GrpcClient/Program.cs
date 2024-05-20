// See https://aka.ms/new-console-template for more information
using Google.Protobuf.WellKnownTypes;
using Grpc.Core;
using Grpc.Net.Client;
using GrpcServer;
using System.Threading.Channels;

var channel = GrpcChannel.ForAddress(" http://localhost:5259");
var client = new AirQuality.AirQualityClient(channel);
var request = new DateRange
{
    PropertyName = "CO_GT",
    StartDate = Timestamp.FromDateTime(DateTime.SpecifyKind(new DateTime(2004, 3, 10), DateTimeKind.Utc)),
    EndDate = Timestamp.FromDateTime(DateTime.SpecifyKind(new DateTime(2004, 3, 13), DateTimeKind.Utc))
};
try
{
    var response = await client.SumDataValueInRangeAsync(request);

    if (response != null)
    {
        Console.WriteLine($"Min Data Value ID: {response.PropertyName}");
        Console.WriteLine($"Date: {response.SumValue}");
        
        Console.Read();
    }
    else
    {
        Console.WriteLine("No data found for the specified range.");
    }
}
catch (RpcException e)
{
    Console.WriteLine($"gRPC Error: {e.Status.Detail}");
}
catch (Exception e)
{
    Console.WriteLine($"An error occurred: {e.Message}");
}