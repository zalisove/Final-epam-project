-- insert roles
insert into final_project_bd.roles values(default,'admin');
insert into final_project_bd.roles values(default,'client');

-- insert subject
insert into final_project_bd.subject values(default,'mathematics');
insert into final_project_bd.subject values(default,'physics');

-- insert complexity
insert into final_project_bd.complexity values(default,'easy');
insert into final_project_bd.complexity values(default,'normal');
insert into final_project_bd.complexity values(default,'hard');

-- insert question_type
insert into final_project_bd.question_type values(default,'one answer');
insert into final_project_bd.question_type values(default,'many answers');

-- insert test
insert into final_project_bd.test values(default,'mathematics',120,default,1,1);
insert into final_project_bd.test values(default,'mathematics',120,default,2,1);
insert into final_project_bd.test values(default,'physics',120,default,3,2);
insert into final_project_bd.test values(default,'physics',120,default,1,2);
insert into final_project_bd.test values(default,'mathematics',120,default,1,1);
insert into final_project_bd.test values(default,'mathematics',120,default,2,1);
insert into final_project_bd.test values(default,'physics',120,default,3,2);
insert into final_project_bd.test values(default,'physics',120,default,1,2);
insert into final_project_bd.test values(default,'mathematics',120,default,1,1);
insert into final_project_bd.test values(default,'mathematics',120,default,2,1);
insert into final_project_bd.test values(default,'physics',120,default,3,2);
insert into final_project_bd.test values(default,'physics',120,default,1,2);
insert into final_project_bd.test values(default,'mathematics',120,default,1,1);
insert into final_project_bd.test values(default,'mathematics',120,default,2,1);
insert into final_project_bd.test values(default,'physics',120,default,3,2);
insert into final_project_bd.test values(default,'physics',120,default,1,2);
insert into final_project_bd.test values(default,'mathematics',120,default,1,1);
insert into final_project_bd.test values(default,'mathematics',120,default,2,1);
insert into final_project_bd.test values(default,'physics',120,default,3,2);
insert into final_project_bd.test values(default,'physics',120,default,1,2);
insert into final_project_bd.test values(default,'mathematics',120,default,1,1);
insert into final_project_bd.test values(default,'mathematics',120,default,2,1);
insert into final_project_bd.test values(default,'physics',120,default,3,2);
insert into final_project_bd.test values(default,'physics',120,default,1,2);
insert into final_project_bd.test values(default,'mathematics',120,default,1,1);
insert into final_project_bd.test values(default,'mathematics',120,default,2,1);
insert into final_project_bd.test values(default,'physics',120,default,3,2);
insert into final_project_bd.test values(default,'physics',120,default,1,2);


-- insert question
insert into final_project_bd.question values(default,'2+2',1,1);
insert into final_project_bd.question values(default,'5+2',1,1);

-- insert answer
insert into final_project_bd.answer values(default,'4',true,1);
insert into final_project_bd.answer values(default,'3',false,1);
insert into final_project_bd.answer values(default,'2',false,1);

insert into final_project_bd.answer values(default,'4',false,2);
insert into final_project_bd.answer values(default,'3',false,2);
insert into final_project_bd.answer values(default,'7',true,2);

-- insert user
insert into final_project_bd.user values(default,'admin@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,1,default);
insert into final_project_bd.user values(default,'client1@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client2@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client3@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client4@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client5@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client6@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client7@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client8@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client9@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client10@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client11@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client12@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client13@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client14@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client15@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client16@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client17@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client18@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client19@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client20@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client21@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client22@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client23@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client24@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client25@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client26@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client27@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);
insert into final_project_bd.user values(default,'client28@gmail.com','5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8',null,null,2,default);

-- insert user_has_test
insert into final_project_bd.user_has_test values(1,1,99.5);
insert into final_project_bd.user_has_test values(2,1,100.0);


-- SELECT * from  answer LIMIT 2 OFFSET 4