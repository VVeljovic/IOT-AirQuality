using GrpcServer.Services;
using Microsoft.EntityFrameworkCore;
using GrpcServer.ContextDB;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddGrpc();
var Configuration = builder.Configuration;
builder.Services.AddDbContext<DBContext>(options =>
        options.UseNpgsql(Configuration.GetConnectionString("Database")));
var app = builder.Build();

// Configure the HTTP request pipeline.
app.MapGrpcService<GreeterService>();
app.MapGrpcService<AirQualityService>();

app.MapGet("/", () => "Communication with gRPC endpoints must be made through a gRPC client. To learn how to create a client, visit: https://go.microsoft.com/fwlink/?linkid=2086909");

app.Run();
