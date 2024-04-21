using Google.Protobuf.WellKnownTypes;
using Grpc.Core;
using GrpcServer.ContextDB;
using GrpcServer.Models;
using Microsoft.EntityFrameworkCore;

namespace GrpcServer.Services
{
    public class AirQualityService : AirQuality.AirQualityBase
    {
        private readonly ILogger<AirQualityService> _logger;    
        public DBContext DBContext { get; private set; }
        public AirQualityService(DBContext context ) { this.DBContext = context; }
        public override Task<Hellow> fja(Hellow request, ServerCallContext context)
        {
            return Task.FromResult(new Hellow
            {
                Name = "Hello" + request.Name
            });
        }
        public override async Task<AirDataQuality> getDataById(DataId request, ServerCallContext context)
        {
            try
            {
                int id = request.Id;
                var airDataQuality = DBContext.airQualities.FirstOrDefault(aDQ=>aDQ.Id == id);
                if(airDataQuality != null ) {
                    DateTime utcDateTime = airDataQuality.Date.ToUniversalTime();

                    return await Task.FromResult(new AirDataQuality
                    {
                        Id = airDataQuality.Id,
                        Date = Timestamp.FromDateTime(utcDateTime),
                        Time = Duration.FromTimeSpan( airDataQuality.Time),
                        CoGt = airDataQuality.CO_GT,
                        Pt08S1Co = airDataQuality.PT08_S1_CO,
                        NmhcGt = airDataQuality.NMHC_GT,
                        C6H6Gt = airDataQuality.C6H6_GT,
                        Pt08S2Nmhc = airDataQuality.PT08_S2_NMHC,
                        NoxGt = airDataQuality.NOx_GT,
                        Pt08S3Nox = airDataQuality.PT08_S3_NOx,
                        No2Gt = airDataQuality.NO2_GT,
                        Pt08S4No2 = airDataQuality.PT08_S4_NO2,
                        Pt08S5O3=airDataQuality.PT08_S5_O3,
                        T=airDataQuality.T,
                        Rh=airDataQuality.RH,
                        Ah = airDataQuality.AH
                    }) ;
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
                var airDataQuality = new AirQualityData
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
                DBContext.airQualities.AddAsync(airDataQuality);
                DBContext.SaveChangesAsync();
                return await Task.FromResult(request);
            }
            catch (Exception ex)
            {

                throw;
            }
                   
        }
        public override async Task<Empty> deleteData(DataId request, ServerCallContext context)
        {
            
                var data = await DBContext.airQualities.FirstOrDefaultAsync(x => x.Id == request.Id);
                if(data== null)
                {
                throw new RpcException(new Status(StatusCode.NotFound, "Data not found"));
                }
            DBContext.airQualities.Remove(data);
            await DBContext.SaveChangesAsync();
            return await Task.FromResult(new Empty());

           
        }
        public override Task<AirDataQuality> updateData(AirDataQuality request, ServerCallContext context)
        {
            return base.updateData(request, context);
        }

    }
   
}
