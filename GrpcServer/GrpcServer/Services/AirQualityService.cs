using CsvHelper;
using CsvHelper.Configuration;
using Google.Protobuf.WellKnownTypes;
using Grpc.Core;
using GrpcServer.ContextDB;
using GrpcServer.Models;
using Microsoft.EntityFrameworkCore;
using System.Globalization;
using static System.Runtime.InteropServices.JavaScript.JSType;

namespace GrpcServer.Services
{
    public class AirQualityService : AirQuality.AirQualityBase
    {
        private readonly ILogger<AirQualityService> _logger;
        List<AirQualityData> airQualityarrayFromCsv = new List<AirQualityData>();
        private DBContext dbContext { get;  set; }
        private void loadFromCsv()
        {
            var configuration = new CsvConfiguration(CultureInfo.InvariantCulture)
            { HasHeaderRecord = false };
            string csvFilePath = "/grpcServer/config/AirQuality.csv";
            using (var reader = new StreamReader(csvFilePath))
            using (var csv = new CsvReader(reader, CultureInfo.InvariantCulture))
            {
                while (csv.Read())
                {
                    var record = csv.GetRecord<AirQualityData>();
                    airQualityarrayFromCsv.Add(record);
                    
                }

            }
        }
        public AirQualityService(DBContext context) { 
            dbContext = context; 
            if(dbContext.Database.EnsureCreated())
            {
                loadFromCsv();
                
               
                    foreach (var airQuality in airQualityarrayFromCsv)
                    {
                        var date = DateTime.ParseExact(airQuality.Date, "dd/MM/yyyy", CultureInfo.InvariantCulture);
                        var time = TimeSpan.ParseExact(airQuality.Time, "hh\\:mm\\:ss", CultureInfo.InvariantCulture);


                        var airQualityDataDbModel = new AirQualityDataDbModel
                        {
                            Date = date,
                            Time = time,
                            CO_GT = airQuality.CO_GT,
                            PT08_S1_CO = airQuality.PT08_S1_CO,
                            NMHC_GT = airQuality.NMHC_GT,
                            C6H6_GT = airQuality.C6H6_GT,
                            PT08_S2_NMHC = airQuality.PT08_S2_NMHC,
                            NOx_GT = airQuality.NOx_GT,
                            PT08_S3_NOx = airQuality.PT08_S3_NOx,
                            NO2_GT = airQuality.NO2_GT,
                            PT08_S4_NO2 = airQuality.PT08_S4_NO2,
                            PT08_S5_O3 = airQuality.PT08_S5_O3,
                            T = airQuality.T,
                            RH = airQuality.RH,
                            AH = airQuality.AH
                        };

                        context.airQualities.Add(airQualityDataDbModel);
                    

                    context.SaveChanges();
                }
            }
        }

        public override async Task<AirDataQuality> getDataById(DataId request, ServerCallContext context)
        {
            try
            {
                int id = request.Id;
                var airDataQuality = dbContext.airQualities.FirstOrDefault(aDQ => aDQ.Id == id);
                if (airDataQuality != null)
                {
                    DateTime utcDateTime = airDataQuality.Date.ToUniversalTime();

                    return await Task.FromResult(new AirDataQuality
                    {
                        Id = airDataQuality.Id,
                        Date = Timestamp.FromDateTime(utcDateTime),
                        Time = Duration.FromTimeSpan(airDataQuality.Time),
                        CoGt = airDataQuality.CO_GT,
                        Pt08S1Co = airDataQuality.PT08_S1_CO,
                        NmhcGt = airDataQuality.NMHC_GT,
                        C6H6Gt = airDataQuality.C6H6_GT,
                        Pt08S2Nmhc = airDataQuality.PT08_S2_NMHC,
                        NoxGt = airDataQuality.NOx_GT,
                        Pt08S3Nox = airDataQuality.PT08_S3_NOx,
                        No2Gt = airDataQuality.NO2_GT,
                        Pt08S4No2 = airDataQuality.PT08_S4_NO2,
                        Pt08S5O3 = airDataQuality.PT08_S5_O3,
                        T = airDataQuality.T,
                        Rh = airDataQuality.RH,
                        Ah = airDataQuality.AH
                    });
                }
                else
                {
                    return null;
                }
            }
            catch (Exception ex)
            {

                throw new RpcException(new Status(StatusCode.Internal, ex.Message));
            }
        }
        public override async Task<AirDataQuality> createData(AirDataQuality request, ServerCallContext context)
        {
            try
            {
                var airDataQuality = new AirQualityDataDbModel
                {
                    Date = request.Date.ToDateTime(),
                    Time = request.Time.ToTimeSpan(),
                    CO_GT = request.CoGt,
                    PT08_S1_CO = request.Pt08S1Co,
                    NMHC_GT = request.NmhcGt,
                    C6H6_GT = request.C6H6Gt,
                    PT08_S2_NMHC = request.Pt08S2Nmhc,
                    NOx_GT = request.NoxGt,
                    PT08_S3_NOx = request.Pt08S3Nox,
                    NO2_GT = request.No2Gt,
                    PT08_S4_NO2 = request.Pt08S4No2,
                    PT08_S5_O3 = request.Pt08S5O3,
                    T = request.T,
                    RH = request.Rh,
                    AH = request.Ah
                };
                await dbContext.airQualities.AddAsync(airDataQuality);
                await dbContext.SaveChangesAsync();
                request.Id = airDataQuality.Id;
                return await Task.FromResult(request);
            }
            catch (Exception ex)
            {

                throw;
            }

        }
        public override async Task<Empty> deleteData(DataId request, ServerCallContext context)
        {

            var data = await dbContext.airQualities.FirstOrDefaultAsync(x => x.Id == request.Id);
            if (data == null)
            {
                throw new RpcException(new Status(StatusCode.NotFound, "Data not found"));
            }
            dbContext.airQualities.Remove(data);
            await dbContext.SaveChangesAsync();
            return await Task.FromResult(new Empty());


        }
        public override async Task<AirDataQuality> updateData(AirDataQuality request, ServerCallContext context)
        {
            var data = await dbContext.airQualities.FindAsync(request.Id);
            if (data == null)
            {
                throw new RpcException(new Status(StatusCode.NotFound, "Data not found"));
            }
            else
            {
                data.Date = request.Date.ToDateTime();
                data.Time = request.Time.ToTimeSpan();
                data.CO_GT = request.CoGt;
                data.PT08_S1_CO = request.Pt08S1Co;
                data.NMHC_GT = request.NmhcGt;
                data.C6H6_GT = request.C6H6Gt;
                data.PT08_S2_NMHC = request.Pt08S2Nmhc;
                data.NOx_GT = request.NoxGt;
                data.PT08_S3_NOx = request.Pt08S3Nox;
                data.NO2_GT = request.No2Gt;
                data.PT08_S4_NO2 = request.Pt08S4No2;
                data.PT08_S5_O3 = request.Pt08S5O3;
                data.T = request.T;
                data.RH = request.Rh;
                data.AH = request.Ah;
                await dbContext.SaveChangesAsync();
                return await Task.FromResult(request);

            }
        }
        public override async Task<AirDataQuality> MinDataValueInRange(DateRange request, ServerCallContext context)
        {
            var startDate = request.StartDate.ToDateTime();
            var endDate = request.EndDate.ToDateTime();
            var propertyName = request.PropertyName;

            var airData = await dbContext.airQualities.Where(
                x => x.Date >= startDate && x.Date <= endDate).ToListAsync();

            if (airData.Count == 0)
            {
                return null;
            }

            var propertyInfo = typeof(AirQualityDataDbModel).GetProperty(propertyName);
            if (propertyInfo == null)
            {
                throw new ArgumentException($"Property '{propertyName}' does not exist on type 'AirQuality'.");
            }

            var sortedAirData = airData.OrderBy(x => propertyInfo.GetValue(x)).ToList();

            var airDataQuality = sortedAirData.FirstOrDefault();

            DateTime utcDateTime = airDataQuality.Date.ToUniversalTime();
            return await Task.FromResult(new AirDataQuality
            {
                Id = airDataQuality.Id,
                Date = Timestamp.FromDateTime(utcDateTime),
                Time = Duration.FromTimeSpan(airDataQuality.Time),
                CoGt = airDataQuality.CO_GT,
                Pt08S1Co = airDataQuality.PT08_S1_CO,
                NmhcGt = airDataQuality.NMHC_GT,
                C6H6Gt = airDataQuality.C6H6_GT,
                Pt08S2Nmhc = airDataQuality.PT08_S2_NMHC,
                NoxGt = airDataQuality.NOx_GT,
                Pt08S3Nox = airDataQuality.PT08_S3_NOx,
                No2Gt = airDataQuality.NO2_GT,
                Pt08S4No2 = airDataQuality.PT08_S4_NO2,
                Pt08S5O3 = airDataQuality.PT08_S5_O3,
                T = airDataQuality.T,
                Rh = airDataQuality.RH,
                Ah = airDataQuality.AH
            });
        }
        public override async Task<AirDataQuality> MaxDataValueInRange(DateRange request, ServerCallContext context)
        {
            var startDate = request.StartDate.ToDateTime();
            var endDate = request.EndDate.ToDateTime();
            var propertyName = request.PropertyName;

            var airData = await dbContext.airQualities.Where(
                x => x.Date >= startDate && x.Date <= endDate).ToListAsync();

            if (airData.Count == 0)
            {
                return null;
            }

            var propertyInfo = typeof(AirQualityDataDbModel).GetProperty(propertyName);
            if (propertyInfo == null)
            {
                throw new ArgumentException($"Property '{propertyName}' does not exist on type 'AirQuality'.");
            }

            var sortedAirData = airData.OrderBy(x => propertyInfo.GetValue(x)).ToList();

            var airDataQuality = sortedAirData.LastOrDefault();

            DateTime utcDateTime = airDataQuality.Date.ToUniversalTime();
            return await Task.FromResult(new AirDataQuality
            {
                Id = airDataQuality.Id,
                Date = Timestamp.FromDateTime(utcDateTime),
                Time = Duration.FromTimeSpan(airDataQuality.Time),
                CoGt = airDataQuality.CO_GT,
                Pt08S1Co = airDataQuality.PT08_S1_CO,
                NmhcGt = airDataQuality.NMHC_GT,
                C6H6Gt = airDataQuality.C6H6_GT,
                Pt08S2Nmhc = airDataQuality.PT08_S2_NMHC,
                NoxGt = airDataQuality.NOx_GT,
                Pt08S3Nox = airDataQuality.PT08_S3_NOx,
                No2Gt = airDataQuality.NO2_GT,
                Pt08S4No2 = airDataQuality.PT08_S4_NO2,
                Pt08S5O3 = airDataQuality.PT08_S5_O3,
                T = airDataQuality.T,
                Rh = airDataQuality.RH,
                Ah = airDataQuality.AH
            });
        }
        public override async Task<AverageData> AverageDataValueInRange(DateRange request, ServerCallContext context)
        {
            var startDate = request.StartDate.ToDateTime();
            var endDate = request.EndDate.ToDateTime();
            var propertyName = request.PropertyName;
            var averageValue = await dbContext.airQualities
                .Where(x => x.Date >= startDate && x.Date <= endDate)
                .AverageAsync(x => EF.Property<double>(x, propertyName));
            return await Task.FromResult(new AverageData
            {
                PropertyName = propertyName,
                AverageValue = (float)averageValue
            });
        }
        public override async Task<SumData> SumDataValueInRange(DateRange request, ServerCallContext context)
        {
            var startDate = request.StartDate.ToDateTime();
            var endDate = request.EndDate.ToDateTime();
            var propertyName = request.PropertyName;
            var averageValue = await dbContext.airQualities
                .Where(x => x.Date >= startDate && x.Date <= endDate)
                .SumAsync(x => EF.Property<double>(x, propertyName));
            return await Task.FromResult(new SumData
            {
                PropertyName = propertyName,
                SumValue = (float)averageValue
            });
        }
    }
    
}
