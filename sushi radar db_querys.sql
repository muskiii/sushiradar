USE [sushi_radar_db]
GO

/****** Object:  Table [dbo].[forecast]    Script Date: 5/16/2018 7:59:22 PM ******/
DROP TABLE [dbo].[fcday]
GO


CREATE TABLE [dbo].[fcday](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fk_forecast] [int] NOT null,
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
	[aveWindKPH][float]null,
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
	
	USE [sushi_radar_db]
GO

DROP TABLE [dbo].[tempfilter]
GO

CREATE TABLE [dbo].[tempfilter](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fk_forecast][int] NOT NULL,
	[maxTempC] [float] NULL,
	[minTempC] [float] NULL)

	
	alter table tempfilter add constraint pk_tempfilter primary key (pk_tempfilter)
	alter table tempfilter add constraint fk_forecast_tempfilter foreign key (fk_forecast) references forecast (id)

	
	POSGRESQL
	

CREATE TABLE public.forecast(
	id SERIAL PRIMARY KEY,
	target varchar(40) NOT NULL,
	country varchar(40) NOT NULL,
	city varchar(40) NOT NULL,
	latitude varchar(40) NOT NULL,
	longitude varchar(40) NOT NULL
    );
	
    CREATE UNIQUE INDEX country_city ON public.forecast
  USING btree (country COLLATE pg_catalog."default", city COLLATE pg_catalog."default");

CREATE TABLE public.fcday(	
	id SERIAL,
	fk_forecast integer references forecast(id),
	day integer  NULL,
    month integer NULL,
    year integer NULL,
    yday integer NULL,
    hour integer NULL,
    monthname varchar(40) NULL,
    weekday varchar(40) NULL,
    ampm varchar(40) NULL,
    tzShort varchar(40) NULL,
    tzLong varchar(40) NULL,
	highT real null,
	lowT real null,
	aveWindKPH integer null,
	aveWindDir varchar(40) NULL,
	aveWinddegrees real null,
	precipAllDay varchar(40) NULL,
	aveHumidity real null,
	conditions varchar(40) NULL,
	iconURL varchar(40) NULL);


CREATE TABLE public.tempfilter (
	id SERIAL,
	fk_forecast integer references forecast(id),
	maxTempC real null,
	minTempC real null);
/* Result : "Query OK, 0 rows affected (execution time: 171 ms; total time: 1.593 sec)" */


