-- Accounts
INSERT INTO `artrunners`.`Account` VALUES
  (1, 'ArtRunners Customer', 'ArtRunners customer account for test', null, null,null,'+972-3-641-5211', '+972 3-641-5200', 'artrunners.com', 'UNDEFINED', 'GBP', 'ACTIVE', null, null);
INSERT INTO `artrunners`.`Account` VALUES
  (2, 'MWDN Customer', 'MWDN customer account for test', null, null,null,'+972-3-641-5222', '+972 3-641-5100', 'mwdn.com', 'UNDEFINED', 'USD', 'ACTIVE', null, null);
INSERT INTO `artrunners`.`Account` VALUES
  (3, 'Museum of Israeli history', 'Big indoor/outdoor museum with Israeli culture & history exhibits, a planetarium & a garden',
   '{"countryCode":"IL","city":"Tel Aviv-Yafo","street":"Chaim Levanon 2","state":null,"zip":"69975","geo":"32.1019546,34.8035182"}', null,null,'+972-3-641-5244', '+972 3-641-5300', 'eretzmuseum.org.il', 'MUSEUM', 'ILS', 'ACTIVE', null, null);
INSERT INTO `artrunners`.`Account` VALUES
  (4, 'Museum of modern & contemporary art', 'Museum with an international collection of modern & contemporary art, plus a sculpture garden',
   '{"countryCode":"IL","city":"Tel Aviv-Yafo","street":"Sderot Shaul HaMelech 27","state":null,"zip":"61332012","geo":"32.0776555,34.7845603"}', null,null,'+972-3-607-7020', '+972 3-607-7022', 'tamuseum.org.il', 'MUSEUM', 'ILS', 'ACTIVE', null, null);
INSERT INTO `artrunners`.`Account` VALUES
  (5, 'Rubin Museum', 'Museum dedicated to the life and art of pioneering Israeli artist Reuben Rubin in his former home',
   '{"countryCode":"IL","city":"Tel Aviv-Yafo","street":"Bialik 14","state":null,"zip":"","geo":"32.0724872,34.7715998"}',
   null,null,'+972-3-525-5961', '+972-3-525-5960', 'rubinmuseum.org.il', 'MUSEUM', 'ILS', 'ACTIVE', null, null);
INSERT INTO `artrunners`.`Account` VALUES
  (6, 'Gallery 32 Tel Aviv', 'Art Gallery',
   '{"countryCode":"IL","city":"Tel Aviv-Yafo","street":"Ahad HaAm 32","state":null,"zip":"","geo":"32.0637869,34.7696461"}',
   null,null,'+972-3-509-1492', '+972-3-509-1490', null, 'GALLERY', 'ILS', 'ACTIVE', null, null);

-- Account Contacts
INSERT INTO `artrunners`.`AccountContact` VALUES
  (null, 1, 'Motty Cohen', 'CTO', 'Testing application', 'motty@artrunners.com', '+972-77-500-9101', '+972-508825110', '+972-77-500-9100', null, 'ADMIN', 'ACTIVE');
INSERT INTO `artrunners`.`AccountContact` VALUES
  (null, 1, 'Lilian Sylvia Buss', 'CMO', 'Testing application', 'lilian@artrunners.com', '+972-77-500-9202', '+972-508829999', '+972-77-500-9200', null, 'ADMIN', 'ACTIVE');
INSERT INTO `artrunners`.`AccountContact` VALUES
  (null, 1, 'Daniel Lev-er', 'COO', 'Testing application', 'daniel@artrunners.com', '+972-77-500-9303', '+972-508828888', '+972-77-500-9300', null, 'USER', 'ACTIVE');

INSERT INTO `artrunners`.`AccountContact` VALUES
  (null, 2, 'Andrey Klimenko', 'Developer', 'Testing application', 'andrey.iemail@gmail.com', '+972-77-500-9303', '+972-508828888', '+972-77-500-9300', null, 'USER', 'ACTIVE');

INSERT INTO `artrunners`.`AccountContact` VALUES
  (null, 2, 'Tatiana Litvinova', 'Developer', 'Testing application', 'litvinova.tatiana11@gmail.com', '+972-77-500-9303', '+972-508828888', '+972-77-500-9300', null, 'USER', 'ACTIVE');

INSERT INTO `artrunners`.`AccountContact` VALUES
  (null, 3, 'Arie Yossef', 'Manager', 'Testing application', 'arie.yosef@fakemail.com', '+972-8-433-9999', '+972-50-433-9999', '+972-8-433-9990', '{"countryCode":"IL","city":"Tel Aviv-Yafo","street":"Sderot Shaul HaMelech 27","state":null,"zip":"61332012","geo":"32.0776555,34.7845603"}', 'USER', 'ACTIVE');
INSERT INTO `artrunners`.`AccountContact` VALUES
  (null, 4, 'Avi Katz', 'Manager', 'Testing application', 'avi.katz@fakemail.com', '+972-8-433-8888', '+972-50-433-8888', '+972-8-433-8880', '{"countryCode":"IL","city":"Tel Aviv-Yafo","street":"Bialik 14","state":null,"zip":"","geo":"32.0724872,34.7715998"}', 'USER', 'ACTIVE');
INSERT INTO `artrunners`.`AccountContact` VALUES
  (null, 5, 'Eli Morad', 'Gallery Manager', 'Testing application', 'eli.morad@fakemail.com', '+972-8-433-7777', '+972-50-433-7777', '+972-8-433-7770', '{"countryCode":"IL","city":"Tel Aviv-Yafo","street":"Ahad HaAm 32","state":null,"zip":"","geo":"32.0637869,34.7696461"}', 'USER', 'ACTIVE');

-- Providers
INSERT INTO `artrunners`.`Provider` VALUES
  (1, 'ArtRunners Provider', 'ArtRunners provider account for test', null, null,null,'+972-3-641-5211', '+972 3-641-5200', 'artrunners.com', 'UNDEFINED', 'GBP', 'ACTIVE', null, null);
INSERT INTO `artrunners`.`Provider` VALUES
  (2, 'MWDN Provider', 'MWDN provider account for test', null, null,null,'+972-3-641-5222', '+972 3-641-5100', 'mwdn.com', 'UNDEFINED', 'USD', 'ACTIVE', null, null);
INSERT INTO `artrunners`.`Provider` VALUES
  (3, 'DHL Israel', 'DHL Israel shipping services',
   '{"countryCode":"IL","city":"Airport City","street":"Hermon","state":null,"zip":"","geo":"31.9953026,34.9126886"}',
   null,null,'+972-3-557-3557', '+972-3-557-3555', 'dhl-israel.co.il', 'SHIPPER', 'ILS', 'ACTIVE', null, null);
INSERT INTO `artrunners`.`Provider` VALUES
  (4, 'UPS Israel', 'UPS services',
   '{"countryCode":"IL","city":"Ben Gurion Airport","street":"Sayfun 1","state":null,"zip":"","geo":"31.9891069,34.8983583"}',
   null,null,'+972-1-700-700-877', null, 'ups.com', 'SHIPPER', 'ILS', 'ACTIVE', null, null);
INSERT INTO `artrunners`.`Provider` VALUES
  (5, 'A.Universe Transit', 'International Shipping',
   '{"countryCode":"IL","city":"Ashdod","street":"Hadarim","state":null,"zip":"77613","geo":""}',
   null,null,'+972-8-856-3145', '+972-8-856-3387', 'www.univers.com', 'SHIPPER', 'ILS', 'ACTIVE', null, null);

-- Provider Contacts
INSERT INTO `artrunners`.`ProviderContact` VALUES
  (null, 1, 'Motty Cohen', 'CTO', 'Testing application', 'motty@artrunners.com', '+972-77-500-9101', '+972-508825110', '+972-77-500-9100', null, 'ADMIN', 'ACTIVE');
INSERT INTO `artrunners`.`ProviderContact` VALUES
  (null, 1, 'Lilian Sylvia Buss', 'CMO', 'Testing application', 'lilian@artrunners.com', '+972-77-500-9202', '+972-508829999', '+972-77-500-9200', null, 'ADMIN', 'ACTIVE');
INSERT INTO `artrunners`.`ProviderContact` VALUES
  (null, 1, 'Daniel Lev-er', 'COO', 'Testing application', 'daniel@artrunners.com', '+972-77-500-9303', '+972-508828888', '+972-77-500-9300', null, 'USER', 'ACTIVE');

INSERT INTO `artrunners`.`ProviderContact` VALUES
  (null, 2, 'Andrey Klimenko', 'Developer', 'Testing application', 'andrey.iemail@gmail.com', '+972-77-500-9303', '+972-508828888', '+972-77-500-9300', null, 'USER', 'ACTIVE');
INSERT INTO `artrunners`.`ProviderContact` VALUES
  (null, 2, 'Tatiana Litvinova', 'Developer', 'Testing application', 'litvinova.tatiana11@gmail.com', '+972-77-500-9303', '+972-508828888', '+972-77-500-9300', null, 'USER', 'ACTIVE');

INSERT INTO `artrunners`.`ProviderContact` VALUES
  (null, 3, 'Yossi Gold', 'Gallery Manager', 'Testing application', 'yossi.gold@fakemail.com', '+972-8-433-6666', '+972-50-433-6666', '+972-8-433-6660', '{"countryCode":"IL","city":"Airport City","street":"Hermon","state":null,"zip":"","geo":"31.9953026,34.9126886"}', 'USER', 'ACTIVE');
INSERT INTO `artrunners`.`ProviderContact` VALUES
  (null, 4, 'Arik Bar', 'Head of delivery', 'Testing application', 'arik.bar@fakemail.com', '+972-8-433-5555', '+972-50-433-5555', '+972-8-433-5550', '{"countryCode":"IL","city":"Ben Gurion Airport","street":"Sayfun 1","state":null,"zip":"","geo":"31.9891069,34.8983583"}', 'USER', 'ACTIVE');
INSERT INTO `artrunners`.`ProviderContact` VALUES
  (null, 5, 'Ami Gur', 'Head of delivery', 'Testing application', 'ami.gur@fakemail.com', '+972-8-433-4444', '+972-50-433-4444', '+972-8-433-4440', '{"countryCode":"IL","city":"Ashdod","street":"Hadarim","state":null,"zip":"77613","geo":""}', 'USER', 'ACTIVE');

-- Address
INSERT INTO `artrunners`.`Address` VALUES
  (null, 1, 'Museum of Israeli history',
   '{"countryCode":"IL","city":"Tel Aviv-Yafo","street":"Chaim Levanon 2","state":null,"zip":"69975","geo":"32.1019546,34.8035182"}', 'MUSEUM', '0', 'DOCK', 'Parking', 'Notes');
INSERT INTO `artrunners`.`Address` VALUES
  (null, 1, 'Museum of modern & contemporary art',
   '{"countryCode":"IL","city":"Tel Aviv-Yafo","street":"Sderot Shaul HaMelech 27","state":null,"zip":"61332012","geo":"32.0776555,34.7845603"}', 'MUSEUM', '0', 'DOCK', 'Parking', 'Notes');
INSERT INTO `artrunners`.`Address` VALUES
  (null, 1, 'Rubin Museum',
   '{"countryCode":"IL","city":"Tel Aviv-Yafo","street":"Bialik 14","state":null,"zip":"","geo":"32.0724872,34.7715998"}', 'MUSEUM', '0', 'DOCK', 'Parking', 'Notes');
INSERT INTO `artrunners`.`Address` VALUES
  (null, 1, 'Gallery 32 Tel Aviv',
   '{"countryCode":"IL","city":"Tel Aviv-Yafo","street":"Ahad HaAm 32","state":null,"zip":"","geo":"32.0637869,34.7696461"}', 'GALLERY', '3', 'DOCK', 'Parking', 'Notes');

INSERT INTO `artrunners`.`Address` VALUES
  (null, 2, 'Museum of Israeli history',
   '{"countryCode":"IL","city":"Tel Aviv-Yafo","street":"Chaim Levanon 2","state":null,"zip":"69975","geo":"32.1019546,34.8035182"}', 'MUSEUM', '0', 'DOCK', 'Parking', 'Notes');
INSERT INTO `artrunners`.`Address` VALUES
  (null, 2, 'Museum of modern & contemporary art',
   '{"countryCode":"IL","city":"Tel Aviv-Yafo","street":"Sderot Shaul HaMelech 27","state":null,"zip":"61332012","geo":"32.0776555,34.7845603"}', 'MUSEUM', '0', 'DOCK', 'Parking', 'Notes');
INSERT INTO `artrunners`.`Address` VALUES
  (null, 2, 'Rubin Museum',
   '{"countryCode":"IL","city":"Tel Aviv-Yafo","street":"Bialik 14","state":null,"zip":"","geo":"32.0724872,34.7715998"}', 'MUSEUM', '0', 'DOCK', 'Parking', 'Notes');
INSERT INTO `artrunners`.`Address` VALUES
  (null, 2, 'Gallery 32 Tel Aviv',
   '{"countryCode":"IL","city":"Tel Aviv-Yafo","street":"Ahad HaAm 32","state":null,"zip":"","geo":"32.0637869,34.7696461"}', 'GALLERY', '3', 'DOCK', 'Parking', 'Notes');
