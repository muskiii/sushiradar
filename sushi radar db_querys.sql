USE [sushi_radar_db]
GO

/****** Object:  Table [dbo].[forecast]    Script Date: 5/16/2018 7:59:22 PM ******/
DROP TABLE [dbo].[fcday]
GO


CREATE TABLE [dbo].[fcday](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fk_forecast] [int] Not null,
	[day][int] NULL,
    [month][int] NULL,
    [year] [int] NULL,
    [yday] [int] NULL,
    [hour] [int] NULL,
    [monthname][varchar](255) NULL,
    [weekday][varchar](255) NULL,
    [ampm][varchar](255) NULL,
    [tzShort][varchar](255) NULL,
    [tzLong][varchar](255) NULL,
	[highT][float]null,
	[lowT][float]null,
	[aveWindHPH][float]null,
	[aveWindDir][varchar](255) NULL,
	[aveWinddegrees][float]null,
	[precipAllDay][varchar](255) NULL,
	[aveHumidity][float]null,
	[conditions][varchar](255) NULL,
	[iconURL][varchar](255) NULL)
	

	alter table fcday add constraint pk_forecast primary key (fk_forecast)
	alter table fcday add constraint fk_forecast_fcday foreign key (fk_forecast) references forecast (id)




USE [sushi_radar_db]
GO

DROP TABLE [dbo].[forecast]
GO

CREATE TABLE [dbo].[forecast](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[target][varchar](255) NULL,
	[country] [varchar](255) NULL,
	[city] [varchar](255) NULL,
	[latitude] [varchar](255) NULL,
	[longitude] [varchar](255) NULL)


