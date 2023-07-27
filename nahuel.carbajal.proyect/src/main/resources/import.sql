create table statistics (
       id bigint auto_increment,
       country varchar(255),
       distance int,
       invocations int,
       primary key (id)
    );

INSERT INTO statistics (country, distance, invocations) VALUES ('Brasil', 2862, 10);
INSERT INTO statistics (country, distance, invocations) VALUES ('España', 10040, 5);