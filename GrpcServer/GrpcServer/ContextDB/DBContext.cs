using GrpcServer.Models;
using Microsoft.EntityFrameworkCore;
using System.Globalization;
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
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<AirQualityDataDbModel>()
             .Property(e => e.Date)
             .HasConversion(
                 v => v.ToString("yyyy-MM-dd"),
                 v => DateTime.ParseExact(v, "yyyy-MM-dd", CultureInfo.InvariantCulture));

        }
        public DbSet<AirQualityDataDbModel> airQualities { get; set; }
    }
}
