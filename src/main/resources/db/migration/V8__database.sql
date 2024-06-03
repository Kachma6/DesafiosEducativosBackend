CREATE TABLE "user_app" (
                        "id" integer PRIMARY KEY,
                        "first_name" varchar(150) NOT NULL,
                        "last_name" varchar(150) NOT NULL,
                        "username" varchar(150) UNIQUE NOT NULL,
                        "password" varchar(150) NOT NULL,
                        "email" varchar(150) UNIQUE NOT NULL,
                        "gender" integer not null,
                        "created_at" timestamp,
                        "rol" varchar
);
create sequence user_sequence as integer increment 1;
CREATE TABLE "desa_created" (
                                   "id" integer PRIMARY KEY,
                                   "created" timestamp,
                                   "finished_date" timestamp not null ,
                                   "name_desa" varchar(200) not null ,
                                   "description" varchar(200),
                                   "num_rep" integer not null ,
                                   "user_created" integer not null ,
                                   "code" varchar(100) not null ,
                                    "state" integer not null,
                                    "num_cards" integer not null,
                                    "num_members" integer not null

);
create sequence desa_created_sequence as integer increment 1;
CREATE TABLE "card" (
                        "id" integer PRIMARY KEY,
                        "question" varchar(200) NOT NULL,
                        "answer" varchar(100) NOT NULL,
                        "url" varchar(200),
                        "id_image" integer,
                        "id_desa_created" integer
);
create sequence card_sequence as integer increment 1;
CREATE TABLE "desa_join" (
                                "id" integer PRIMARY KEY,
                                "id_desa_created" integer NOT NULL ,
                                "id_user" integer,
                                "num_reps" integer,
                                "state" integer

);
create sequence desa_join_sequence as integer increment 1;
CREATE TABLE rep_desa (
                          "id" integer PRIMARY KEY,
                          "cards_correct" integer NOT NULL ,
                          "cards_incorrect" integer NOT NULL ,
                          "date_rep" timestamp NOT NULL ,
                          "id_desa" integer NOT NULL
);
create sequence rep_desa_sequence as integer increment 1;
CREATE TABLE refresh_token (
                          "id" integer PRIMARY KEY,
                          "id_user" integer,
                          "token" varchar(200),
                          "expiry_date" timestamp

);
alter table desa_created add constraint FK_desa_created_ref_user foreign key (user_created) references "user_app" (id);
alter table card add constraint FK_card_ref_desa_created foreign key (id_desa_created) references desa_created (id) ;
alter table desa_join add constraint FK_desa_join_ref_desa_created foreign key (id_desa_created) references desa_created (id);
alter table desa_join add constraint FK_desa_join_ref_user foreign key (id_user) references "user_app" (id);
alter table rep_desa add constraint FK_rep_desa_ref_desa_join foreign key  (id_desa) references desa_join (id);


alter table refresh_token add constraint FK_refresh_token_ref_user_app foreign key  ("id_user") references user_app (id);
