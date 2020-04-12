INSERT INTO customer
(id, first_name, last_name, email, phone, active) values
(1, 'Gustavo','Ponce','test@test.com','1234567890',true),
(2,'Bob','Marley','one@love.com','6483748590',false),
(3,'David','Gilmour','high@hopes.com','7648909831',true),
(4,'John','Lennon','standby@me.com','7689485620',true),
(5,'Ozzy','Osbourne','children@grave.com','6483748590',false),
(6,'Jimmy','Page','stairway@heaven.com','7648909831',true),
(7,'Jimi','Hendrix','purple@haze.com','8754091236',false),
(8,'Sex','Pistols','save@queen.com','6729098761',true),
(9,'Jim','Morrison','riders@storm.com','8754091236',false),
(10,'Richard','Blackmore','highway@star.com','8754091236',true),
(11,'Jay','Kay','cosmic@girl.com','0926389871',true),
(12,'David','Bowie','heroes@oneday.com','4338490981',true),
(13,'Bob','Dylan','knocking@doors.com','4338490981',false),
(14,'Manu','Chao','mala@vida.com','8923098753',true),
(15,'The','Specials','ghost@thown.com','7590498573',true),
(16,'Jymmy','Cliff','see@clearly.com','4338490981',false),
(17,'The','Temptations','my@girl.com','7639864096',true),
(18,'Simon','Garfunkel','mr@robinson.com','8750987531',true),
(19,'catch','22','takes@sometime.com','7098653427',true),
(20,'Janis','Joplin','cry@baby.com','6739087641',false),
(21,'Lou','Red','wild@side.com','6789045678',true),
(22,'Iggy','Pop','the@passenger.com','6934980751',true),
(23,'Dead','Kennedys','holiday@cambodia.com','2389096457',false),
(24,'The','Cure','dont@cry.com','8749340987',false)
;

INSERT INTO insurer_detail
(id,provider_name,start_year,current_rating,turnover_million,customer_size,claim_days) VALUES
(1,'AIA',1991,7,1200,100000,0),
(2,'AVIVA',2005,8,180,10000,0),
(3,'AXA',2002,7,880,17000,1),
(4,'Great Eastern',1999,7,120,7000,0),
(5,'Income',1999,8,160,7300,0),
(6,'Prudential',1994,8,160,8300,0),
(7,'Raffles Health Insurance',1990,8,160,7570,0)
;

INSERT INTO policy
(id,insurer,policy_name) VALUES
(1,'AIA','AIA HealthShield Gold Max Standard Plan'),
(2,'AIA','AIA HealthShield Gold Max C*'),
(3,'AIA','AIA HealthShield Gold Max B'),
(4,'AIA','AIA HealthShield Gold Max A'),
(5,'AVIVA','Aviva MyShield Standard Plan'),
(6,'AVIVA','Aviva MyShield Plan 3'),
(7,'AVIVA','Aviva MyShield Plan 2'),
(8,'AVIVA','Aviva MyShield Plan 1'),
(9,'AVIVA','AXA Shield Standard Plan'),
(10,'AXA','AXA Shield Plan C'),
(11,'AXA','AXA Shield Plan B'),
(12,'AXA','AXA Shield Plan A'),
(13,'Great Eastern','Great Eastern Supreme Health Standard Plan'),
(14,'Great Eastern','Great Eastern Supreme Health B Plus'),
(15,'Great Eastern','Great Eastern Supreme Health A Plus'),
(16,'Great Eastern','Great Eastern Supreme Health P Plus'),
(17,'Income','Income Enhanced IncomeShield C'),
(18,'Income','Income IncomeShield Standard Plan'),
(19,'Income','Income Enhanced IncomeShield Basic'),
(20,'Income','Income Enhanced IncomeShield Advantage'),
(21,'Income','Income Enhanced IncomeShield Preferred'),
(22,'Prudential','Prudential PruShield Standard Plan'),
(23,'Prudential','Prudential PruShield B'),
(24,'Prudential','Prudential PruShield A Plus'),
(25,'Prudential','Prudential PruShield A Premier'),
(26,'Raffles Health Insurance','Raffles Shield Standard'),
(27,'Raffles Health Insurance','Raffles Shield B'),
(28,'Raffles Health Insurance','Raffles Shield A'),
(29,'Raffles Health Insurance','Raffles Shield Private')
;

INSERT INTO ward_type
(id,ward_category,ward_name,description) VALUES
(1,'Basic','B2C','equivalent or just slightly better than MediShield Life'),
(2,'Standard','B1','coverage falls somewhere in between Basic and Class B1'),
(3,'Class','B1','coverage for treatment in public hospital B1 wards'),
(4,'Class','A','coverage for treatment in public hospital A wards'),
(5,'Private','Private','coverage for treatment in private hospitals')
;

INSERT INTO comp_policy
(id,insurer_id,policy_id) VALUES
(1,1,1),
(2,1,2),
(3,1,3),
(4,1,4),
(5,2,5),
(6,2,6),
(7,2,7),
(8,2,8),
(9,3,9),
(10,3,10),
(11,3,11),
(12,3,12),
(13,4,13),
(14,4,14),
(15,4,15),
(16,4,16),
(17,5,17),
(18,5,18),
(19,5,19),
(20,5,20),
(21,5,21),
(22,6,22),
(23,6,23),
(24,6,24),
(25,6,25),
(26,7,26),
(27,7,27),
(28,7,28),
(29,7,29);


INSERT INTO policy_feature
(id,name,description) values
(1,'POLICY_PREM','Price of premiums'),
(2,'PRE_HOSP_COVG','Pre-hospitalisation coverage'),
(3,'POST_HOSP_COVG','Post-hospitalisation coverage'),
(4,'ANNUAL_COVERAGE_LIMIT','Annual coverage limit'),
(5,'PRE_HOSP_DAYS','Pre-hospitalisation coverage days'),
(6,'POST_HOSP_DAYS','Post-hospitalisation coverage days');


INSERT INTO comp_policy_feature
(id,policy_id,policy_feature_id,benefit_value,benefit_desc) VALUES
(1,4,2,1,'As Charged'),
(2,8,2,1,'As Charged'),
(3,12,2,1,'As Charged'),
(4,16,2,1,'As Charged'),
(5,21,2,1,'As Charged'),
(6,25,2,1,'As Charged'),
(7,29,2,1,'As Charged'),
(8,4,5,100,'Number of Days'),
(9,8,5,100,'Number of Days'),
(10,12,5,120,'Number of Days'),
(11,16,5,180,'Number of Days'),
(12,21,5,90,'Number of Days'),
(13,25,5,180,'Number of Days'),
(14,29,5,180,'Number of Days'),
(15,4,3,1,'As Charged'),
(16,8,3,1,'As Charged'),
(17,12,3,1,'As Charged'),
(18,16,3,1,'As Charged'),
(19,21,3,1,'As Charged'),
(20,25,3,1,'As Charged'),
(21,29,3,1,'As Charged'),
(22,4,6,100,'Number of Days'),
(23,8,6,100,'Number of Days'),
(24,12,6,120,'Number of Days'),
(25,16,6,365,'Number of Days'),
(26,21,6,90,'Number of Days'),
(27,25,6,365,'Number of Days'),
(28,29,6,365,'Number of Days'),
(29,4,4,1500000,'Annual Coverage Limit'),
(30,8,4,2000000,''),
(31,12,4,1500000,''),
(32,16,4,1200000,''),
(33,21,4,1000000,''),
(34,25,4,1000000,''),
(35,29,4,600000,'');

INSERT INTO question
(id,name,value,stage,extra_data) VALUES
(0,'question0','Invalid Request',0,''),
(1,'question1','Age',1,''),
(2,'question2','SPR/Citizen ',1,''),
(3,'question3','Type of Ward?',2,'/api/wardTypes'),
(4,'question4','Max Premium?',2,''),
(5,'question5','Min Pre-Hospitalization days?',2,''),
(6,'question6','Min Post-Hospitalization days?',2,''),
(7,'question7','Annual coverage limt?',2,''),
(8,'question8','Preferred Insurers?',2,'/api/insurerDetails'),
(9,'question9','Overall Pref/Weightage/Adding Limit?',2,'/api/userPreference')
;