drop table if exists project_values;
drop table if exists user_values;
drop table if exists user_roles;
drop table if exists roles;
drop table if exists projects;
drop table if exists "values";
drop table if exists users;

create table users (
   id serial primary key,
   username varchar(30) not null unique,
   password varchar(255) not null,
   first_name varchar(45) not null,
   last_name varchar(65) not null,
   email varchar(255) not null unique,
   phone varchar(12) not null unique
);

create table roles (
    id serial primary key,
    name varchar(30) not null unique
);

create table "values" (
    id serial primary key,
    value varchar(55) not null unique
);

create table projects (
  id serial primary key,
  title varchar(30) not null,
  description varchar(255),
  user_id int not null,
  foreign key(user_id) references users(id) on delete cascade on update cascade
);

create table user_roles(
    user_id int not null,
    role_id int not null,
    foreign key (user_id) references users(id) on delete cascade on update cascade,
    foreign key (role_id) references roles(id) on delete cascade on update cascade,
    primary key (user_id, role_id)
);

create table user_values(
    user_id int not null,
    value_id int not null,
    favorite boolean default false,
    text varchar(255) default null,
    foreign key(user_id) references users(id) on delete cascade on update cascade,
    foreign key (value_id) references "values"(id) on delete cascade on update cascade,
    primary key (user_id, value_id)
);

create table project_values (
    project_id int not null,
    value_id int not null,
    foreign key (project_id) references projects(id) on delete cascade on update cascade,
    foreign key (value_id) references "values"(id) on delete cascade on update cascade,
    primary key (project_id, value_id)
);

insert into users (username, password, first_name, last_name, email, phone) values ('enorth0', 'aw1Ntjfptb', 'Elnar', 'North', 'enorth0@cam.ac.uk', '762-183-2223');
insert into users (username, password, first_name, last_name, email, phone) values ('mpesselt1', '3S4rGplKWgB', 'Maighdiln', 'Pesselt', 'mpesselt1@slideshare.net', '256-452-6591');
insert into users (username, password, first_name, last_name, email, phone) values ('csuller2', 'VP1ZyDjOT', 'Cesya', 'Suller', 'csuller2@free.fr', '100-766-3266');
insert into users (username, password, first_name, last_name, email, phone) values ('cnuccii3', 'HiccEpL1c', 'Cindy', 'Nuccii', 'cnuccii3@harvard.edu', '222-852-4140');
insert into users (username, password, first_name, last_name, email, phone) values ('aguppy4', 'E8gCJMf', 'Ardelia', 'Guppy', 'aguppy4@state.tx.us', '685-177-7409');
insert into users (username, password, first_name, last_name, email, phone) values ('tyankeev5', 'BnogDETe', 'Tadeas', 'Yankeev', 'tyankeev5@reverbnation.com', '409-944-7693');
insert into users (username, password, first_name, last_name, email, phone) values ('rboyan6', 'jEK9jBaG2w', 'Raff', 'Boyan', 'rboyan6@exblog.jp', '744-422-2733');
insert into users (username, password, first_name, last_name, email, phone) values ('dredwood7', 'r5cHJBHNxCM9', 'Dara', 'Redwood', 'dredwood7@whitehouse.gov', '531-402-7908');
insert into users (username, password, first_name, last_name, email, phone) values ('bricarde8', 'DbWcPFWDEb', 'Balduin', 'Ricarde', 'bricarde8@tiny.cc', '994-450-3516');
insert into users (username, password, first_name, last_name, email, phone) values ('lsemechik9', 'SBDvdYQK', 'Leontyne', 'Semechik', 'lsemechik9@telegraph.co.uk', '647-410-7274');

insert into roles (name) values ('USER');
insert into roles (name) values ('ADMIN');

insert into "values" (value) values ('Acceptance Accomplishment');
insert into "values" (value) values ('Accountability');
insert into "values" (value) values ('Accuracy');
insert into "values" (value) values ('Achievement');
insert into "values" (value) values ('Adaptability');
insert into "values" (value) values ('Alertness');
insert into "values" (value) values ('Altruism');
insert into "values" (value) values ('Ambition');
insert into "values" (value) values ('Amusement');
insert into "values" (value) values ('Assertiveness');
insert into "values" (value) values ('Attentive');
insert into "values" (value) values ('Awareness');
insert into "values" (value) values ('Balance');
insert into "values" (value) values ('Beauty');
insert into "values" (value) values ('Boldness');
insert into "values" (value) values ('Bravery');
insert into "values" (value) values ('Brilliance');
insert into "values" (value) values ('Calm');
insert into "values" (value) values ('Candor');
insert into "values" (value) values ('Capable');
insert into "values" (value) values ('Careful');
insert into "values" (value) values ('Certainty');
insert into "values" (value) values ('Challenge');
insert into "values" (value) values ('Charity');
insert into "values" (value) values ('Cleanliness');
insert into "values" (value) values ('Clear');
insert into "values" (value) values ('Clever');
insert into "values" (value) values ('Comfort');
insert into "values" (value) values ('Commitment');
insert into "values" (value) values ('Common');
insert into "values" (value) values ('sense Communication');
insert into "values" (value) values ('Community');
insert into "values" (value) values ('Compassion');
insert into "values" (value) values ('Competence');
insert into "values" (value) values ('Concentration');
insert into "values" (value) values ('Confidence');
insert into "values" (value) values ('Connection');
insert into "values" (value) values ('Consciousness');
insert into "values" (value) values ('Consistency');
insert into "values" (value) values ('Contentment');
insert into "values" (value) values ('Contribution');
insert into "values" (value) values ('Control');
insert into "values" (value) values ('Conviction');
insert into "values" (value) values ('Cooperation');
insert into "values" (value) values ('Courage');
insert into "values" (value) values ('Courtesy');
insert into "values" (value) values ('Creation');
insert into "values" (value) values ('Creativity');
insert into "values" (value) values ('Credibility');
insert into "values" (value) values ('Curiosity');
insert into "values" (value) values ('Decisive');
insert into "values" (value) values ('Decisiveness');
insert into "values" (value) values ('Dedication');
insert into "values" (value) values ('Dependability');
insert into "values" (value) values ('Determination');
insert into "values" (value) values ('Development');
insert into "values" (value) values ('Devotion');
insert into "values" (value) values ('Dignity');
insert into "values" (value) values ('Discipline');
insert into "values" (value) values ('Discovery');
insert into "values" (value) values ('Drive');
insert into "values" (value) values ('Effectiveness');
insert into "values" (value) values ('Efficiency');
insert into "values" (value) values ('Empathy');
insert into "values" (value) values ('Empower');
insert into "values" (value) values ('Endurance');
insert into "values" (value) values ('Energy');
insert into "values" (value) values ('Enjoyment');
insert into "values" (value) values ('Enthusiasm');
insert into "values" (value) values ('Equality');
insert into "values" (value) values ('Ethical');
insert into "values" (value) values ('Excellence');
insert into "values" (value) values ('Experience');
insert into "values" (value) values ('Exploration');
insert into "values" (value) values ('Expressive');
insert into "values" (value) values ('Fairness');
insert into "values" (value) values ('Family');
insert into "values" (value) values ('Famous');
insert into "values" (value) values ('Fearless');
insert into "values" (value) values ('Feelings');
insert into "values" (value) values ('Ferocious');
insert into "values" (value) values ('Fidelity');
insert into "values" (value) values ('Focus');
insert into "values" (value) values ('Foresight');
insert into "values" (value) values ('Fortitude');
insert into "values" (value) values ('Freedom');
insert into "values" (value) values ('Friendship');
insert into "values" (value) values ('Fun');
insert into "values" (value) values ('Generosity');
insert into "values" (value) values ('Genius');
insert into "values" (value) values ('Giving');
insert into "values" (value) values ('Goodness');
insert into "values" (value) values ('Grace');
insert into "values" (value) values ('Gratitude');
insert into "values" (value) values ('Greatness');
insert into "values" (value) values ('Growth');
insert into "values" (value) values ('Happiness');
insert into "values" (value) values ('Hard');
insert into "values" (value) values ('work Harmony');
insert into "values" (value) values ('Health');

insert into projects (title, description , user_id ) values ('Rohan-Lowe', 'Phasellus in felis. Donec semper sapien a libero.', 9);
insert into projects (title, description , user_id ) values ('Boehm, O''Hara and Schimmel', 'Nunc rhoncus dui vel sem. Sed sagittis.', 7);
insert into projects (title, description , user_id ) values ('Franecki-O''Reilly', 'Suspendisse accumsan tortor quis turpis. Sed ante.', 3);
insert into projects (title, description , user_id ) values ('Keebler, Hartmann and Jerde', 'Mauris sit amet eros. Suspendisse accumsan tortor quis turpis.', 10);
insert into projects (title, description , user_id ) values ('Howell-Corwin', 'Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.', 7);
insert into projects (title, description , user_id ) values ('Auer-Glover', 'Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus. In sagittis dui vel nisl.', 10);
insert into projects (title, description , user_id ) values ('Reichel Group', 'Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio.', 4);
insert into projects (title, description , user_id ) values ('Heller, Weber and Bartell', 'Aliquam erat volutpat. In congue.', 2);
insert into projects (title, description , user_id ) values ('Brekke Inc', 'Proin eu mi. Nulla ac enim.', 9);
insert into projects (title, description , user_id ) values ('Swift-Mayer', 'Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst.', 5);
insert into projects (title, description , user_id ) values ('Hirthe, Beahan and Treutel', 'Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien.', 1);
insert into projects (title, description , user_id ) values ('McGlynn-Nader', 'Vestibulum ante  t. Donec odio justo sapien urna pretium nisl, ut volutpat sapien arcu sed augue.', 5);
insert into projects (title, description , user_id ) values ('Ankunding-Wolff', 'Morbi quis tortor uet. Maecenas leo odio, condimentum id,  justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui.', 1);
insert into projects (title, description , user_id ) values ('Streich, Hills and Paucek', 'Suspendisse accumsan tortor quis turpis. Sed ante. Vivamus tortor.', 2);
insert into projects (title, description , user_id ) values ('Hauck LLC', 'Vestibulum ante magna vestibulum  non mi. Integer ac neque. Duis bibendum. Morbi non quam nec dui luctus rutrum.', 7);

insert into user_roles (user_id, role_id) values (1, 1);
insert into user_roles (user_id, role_id) values (2, 1);
insert into user_roles (user_id, role_id) values (3, 1);
insert into user_roles (user_id, role_id) values (4, 1);
insert into user_roles (user_id, role_id) values (5, 1);
insert into user_roles (user_id, role_id) values (6, 1);
insert into user_roles (user_id, role_id) values (7 ,1);
insert into user_roles (user_id, role_id) values (8, 1);
insert into user_roles (user_id, role_id) values (9, 1);
insert into user_roles (user_id, role_id) values (10, 1);

insert into user_values (user_id, value_id) values (10, 47);
insert into user_values (user_id, value_id) values (8, 51);
insert into user_values (user_id, value_id) values (6, 76);
insert into user_values (user_id, value_id) values (10, 42);
insert into user_values (user_id, value_id) values (8, 55);
insert into user_values (user_id, value_id) values (5, 13);
insert into user_values (user_id, value_id) values (2, 69);
insert into user_values (user_id, value_id) values (1, 50);
insert into user_values (user_id, value_id) values (6, 77);
insert into user_values (user_id, value_id) values (4, 95);
insert into user_values (user_id, value_id) values (4, 40);
insert into user_values (user_id, value_id) values (3, 29);
insert into user_values (user_id, value_id) values (2, 9);
insert into user_values (user_id, value_id) values (7, 28);
insert into user_values (user_id, value_id) values (3, 13);
insert into user_values (user_id, value_id) values (7, 19);
insert into user_values (user_id, value_id) values (1, 6);
insert into user_values (user_id, value_id) values (2, 29);
insert into user_values (user_id, value_id) values (6, 79);
insert into user_values (user_id, value_id) values (2, 17);
insert into user_values (user_id, value_id) values (3, 15);
insert into user_values (user_id, value_id) values (9, 78);
insert into user_values (user_id, value_id) values (3, 85);
insert into user_values (user_id, value_id) values (9, 14);
insert into user_values (user_id, value_id) values (10, 15);
insert into user_values (user_id, value_id) values (3, 59);
insert into user_values (user_id, value_id) values (7, 85);
insert into user_values (user_id, value_id) values (4, 13);
insert into user_values (user_id, value_id) values (7, 98);
insert into user_values (user_id, value_id) values (5, 47);
insert into user_values (user_id, value_id) values (2, 61);
insert into user_values (user_id, value_id) values (1, 7);
insert into user_values (user_id, value_id) values (2, 78);
insert into user_values (user_id, value_id) values (5, 25);
insert into user_values (user_id, value_id) values (3, 16);
insert into user_values (user_id, value_id) values (2, 31);
insert into user_values (user_id, value_id) values (2, 96);
insert into user_values (user_id, value_id) values (9, 87);
insert into user_values (user_id, value_id) values (1, 37);
insert into user_values (user_id, value_id) values (7, 60);
insert into user_values (user_id, value_id) values (2, 20);
insert into user_values (user_id, value_id) values (2, 49);
insert into user_values (user_id, value_id) values (4, 5);
insert into user_values (user_id, value_id) values (10, 81);
insert into user_values (user_id, value_id) values (7, 80);
insert into user_values (user_id, value_id) values (2, 15);
insert into user_values (user_id, value_id) values (5, 19);
insert into user_values (user_id, value_id) values (5, 73);
insert into user_values (user_id, value_id) values (4, 94);
insert into user_values (user_id, value_id) values (5, 75);
insert into user_values (user_id, value_id) values (7, 56);
insert into user_values (user_id, value_id) values (8, 5);
insert into user_values (user_id, value_id) values (4, 38);
insert into user_values (user_id, value_id) values (4, 85);
insert into user_values (user_id, value_id) values (1, 62);
insert into user_values (user_id, value_id) values (5, 71);
insert into user_values (user_id, value_id) values (6, 87);
insert into user_values (user_id, value_id) values (7, 92);
insert into user_values (user_id, value_id) values (7, 31);
insert into user_values (user_id, value_id) values (7, 42);
insert into user_values (user_id, value_id) values (5, 99);
insert into user_values (user_id, value_id) values (7, 33);
insert into user_values (user_id, value_id) values (2, 24);
insert into user_values (user_id, value_id) values (1, 45);
insert into user_values (user_id, value_id) values (1, 93);
insert into user_values (user_id, value_id) values (8, 96);
insert into user_values (user_id, value_id) values (8, 16);
insert into user_values (user_id, value_id) values (5, 82);
insert into user_values (user_id, value_id) values (3, 55);
insert into user_values (user_id, value_id) values (6, 80);
insert into user_values (user_id, value_id) values (1, 12);
insert into user_values (user_id, value_id) values (2, 45);
insert into user_values (user_id, value_id) values (3, 70);
insert into user_values (user_id, value_id) values (10, 53);
insert into user_values (user_id, value_id) values (3, 14);
insert into user_values (user_id, value_id) values (6, 99);
insert into user_values (user_id, value_id) values (1, 30);
insert into user_values (user_id, value_id) values (7, 5);
insert into user_values (user_id, value_id) values (6, 71);
insert into user_values (user_id, value_id) values (5, 26);
insert into user_values (user_id, value_id) values (10, 40);
insert into user_values (user_id, value_id) values (8, 53);
insert into user_values (user_id, value_id) values (3, 80);
insert into user_values (user_id, value_id) values (4, 100);
insert into user_values (user_id, value_id) values (9, 32);
insert into user_values (user_id, value_id) values (7, 30);
insert into user_values (user_id, value_id) values (1, 80);
insert into user_values (user_id, value_id) values (5, 23);
insert into user_values (user_id, value_id) values (5, 62);
insert into user_values (user_id, value_id) values (9, 85);
insert into user_values (user_id, value_id) values (9, 57);
insert into user_values (user_id, value_id) values (3, 89);
insert into user_values (user_id, value_id) values (6, 23);

insert into project_values (project_id, value_id) values (11, 23);
insert into project_values (project_id, value_id) values (1, 22);
insert into project_values (project_id, value_id) values (13, 10);
insert into project_values (project_id, value_id) values (7, 41);
insert into project_values (project_id, value_id) values (11, 28);
insert into project_values (project_id, value_id) values (11, 99);
insert into project_values (project_id, value_id) values (3, 91);
insert into project_values (project_id, value_id) values (5, 27);
insert into project_values (project_id, value_id) values (3, 64);
insert into project_values (project_id, value_id) values (1, 19);
insert into project_values (project_id, value_id) values (11, 70);
insert into project_values (project_id, value_id) values (9, 50);
insert into project_values (project_id, value_id) values (14, 41);
insert into project_values (project_id, value_id) values (8, 63);
insert into project_values (project_id, value_id) values (10, 96);
insert into project_values (project_id, value_id) values (5, 64);
insert into project_values (project_id, value_id) values (15, 95);
insert into project_values (project_id, value_id) values (10, 55);
insert into project_values (project_id, value_id) values (15, 77);
insert into project_values (project_id, value_id) values (1, 43);
insert into project_values (project_id, value_id) values (7, 37);
insert into project_values (project_id, value_id) values (14, 4);
insert into project_values (project_id, value_id) values (9, 88);
insert into project_values (project_id, value_id) values (4, 91);
insert into project_values (project_id, value_id) values (1, 77);
insert into project_values (project_id, value_id) values (1, 71);
insert into project_values (project_id, value_id) values (10, 14);
insert into project_values (project_id, value_id) values (3, 6);
insert into project_values (project_id, value_id) values (2, 60);
insert into project_values (project_id, value_id) values (13, 94);
insert into project_values (project_id, value_id) values (11, 85);
insert into project_values (project_id, value_id) values (4, 90);
insert into project_values (project_id, value_id) values (2, 25);
insert into project_values (project_id, value_id) values (8, 98);
insert into project_values (project_id, value_id) values (10, 51);
insert into project_values (project_id, value_id) values (2, 27);
insert into project_values (project_id, value_id) values (4, 95);
insert into project_values (project_id, value_id) values (3, 18);
insert into project_values (project_id, value_id) values (11, 53);
insert into project_values (project_id, value_id) values (14, 59);
insert into project_values (project_id, value_id) values (10, 20);
insert into project_values (project_id, value_id) values (14, 13);
insert into project_values (project_id, value_id) values (14, 49);
insert into project_values (project_id, value_id) values (6, 6);
insert into project_values (project_id, value_id) values (15, 3);
insert into project_values (project_id, value_id) values (2, 33);
insert into project_values (project_id, value_id) values (15, 25);
insert into project_values (project_id, value_id) values (13, 33);
insert into project_values (project_id, value_id) values (7, 36);
insert into project_values (project_id, value_id) values (1, 78);
insert into project_values (project_id, value_id) values (3, 40);
insert into project_values (project_id, value_id) values (4, 23);
insert into project_values (project_id, value_id) values (3, 16);
insert into project_values (project_id, value_id) values (15, 82);
insert into project_values (project_id, value_id) values (7, 65);
insert into project_values (project_id, value_id) values (6, 50);
insert into project_values (project_id, value_id) values (3, 62);
insert into project_values (project_id, value_id) values (13, 49);
insert into project_values (project_id, value_id) values (15, 52);
insert into project_values (project_id, value_id) values (12, 68);
insert into project_values (project_id, value_id) values (5, 74);
insert into project_values (project_id, value_id) values (2, 100);
insert into project_values (project_id, value_id) values (13, 3);
insert into project_values (project_id, value_id) values (11, 56);
insert into project_values (project_id, value_id) values (12, 38);
insert into project_values (project_id, value_id) values (10, 30);
insert into project_values (project_id, value_id) values (15, 45);
insert into project_values (project_id, value_id) values (4, 48);
insert into project_values (project_id, value_id) values (7, 7);
insert into project_values (project_id, value_id) values (4, 78);
insert into project_values (project_id, value_id) values (7, 45);
insert into project_values (project_id, value_id) values (13, 29);
insert into project_values (project_id, value_id) values (1, 39);
insert into project_values (project_id, value_id) values (4, 46);
insert into project_values (project_id, value_id) values (7, 66);
insert into project_values (project_id, value_id) values (14, 37);
insert into project_values (project_id, value_id) values (8, 17);
insert into project_values (project_id, value_id) values (13, 35);
insert into project_values (project_id, value_id) values (9, 3);
insert into project_values (project_id, value_id) values (4, 56);
insert into project_values (project_id, value_id) values (13, 81);
insert into project_values (project_id, value_id) values (6, 34);
insert into project_values (project_id, value_id) values (8, 14);
insert into project_values (project_id, value_id) values (11, 80);
insert into project_values (project_id, value_id) values (4, 94);
insert into project_values (project_id, value_id) values (6, 27);
insert into project_values (project_id, value_id) values (7, 59);
insert into project_values (project_id, value_id) values (6, 86);
insert into project_values (project_id, value_id) values (6, 32);
insert into project_values (project_id, value_id) values (9, 60);
insert into project_values (project_id, value_id) values (12, 62);
insert into project_values (project_id, value_id) values (1, 60);
insert into project_values (project_id, value_id) values (14, 71);
insert into project_values (project_id, value_id) values (8, 69);
insert into project_values (project_id, value_id) values (11, 48);
insert into project_values (project_id, value_id) values (12, 36);
