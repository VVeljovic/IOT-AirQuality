using GrpcServer.Models;
using Microsoft.EntityFrameworkCore;
namespace GrpcServer.ContextDB
{
    public class DBContext : DbContext
    {
        protected readonly IConfiguration _configuration;
        public DBContext  (IConfiguration configuration) { this._configuration = configuration; }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseNpgsql(_configuration.GetConnectionString("Database"));
        }
        public DbSet<AirQualityData> airQualities { get; set; }
    }
}
