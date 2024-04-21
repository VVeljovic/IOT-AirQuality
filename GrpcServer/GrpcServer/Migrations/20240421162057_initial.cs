using System;
using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

#nullable disable

namespace GrpcServer.Migrations
{
    /// <inheritdoc />
    public partial class initial : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "air_quality",
                columns: table => new
                {
                    Id = table.Column<int>(type: "integer", nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    Date = table.Column<DateTime>(type: "timestamp with time zone", nullable: false),
                    Time = table.Column<TimeSpan>(type: "interval", nullable: false),
                    CO_GT = table.Column<float>(type: "real", nullable: false),
                    PT08_S1_CO = table.Column<int>(type: "integer", nullable: false),
                    NMHC_GT = table.Column<int>(type: "integer", nullable: false),
                    C6H6_GT = table.Column<float>(type: "real", nullable: false),
                    PT08_S2_NMHC = table.Column<int>(type: "integer", nullable: false),
                    NOx_GT = table.Column<int>(type: "integer", nullable: false),
                    PT08_S3_NOx = table.Column<int>(type: "integer", nullable: false),
                    NO2_GT = table.Column<int>(type: "integer", nullable: false),
                    PT08_S4_NO2 = table.Column<int>(type: "integer", nullable: false),
                    PT08_S5_O3 = table.Column<int>(type: "integer", nullable: false),
                    T = table.Column<float>(type: "real", nullable: false),
                    RH = table.Column<float>(type: "real", nullable: false),
                    AH = table.Column<float>(type: "real", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_air_quality", x => x.Id);
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "air_quality");
        }
    }
}
