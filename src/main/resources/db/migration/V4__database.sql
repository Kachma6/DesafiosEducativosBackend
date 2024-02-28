CREATE TABLE "user_app" (
                        "id" integer PRIMARY KEY,
                        "first_name" varchar(150) NOT NULL,
                        "last_name" varchar(150) NOT NULL,
                        "username" varchar(150) NOT NULL,
                        "password" varchar(150) NOT NULL,
                        "email" varchar(150) UNIQUE NOT NULL,
                        "created_at" timestamp
);
create sequence user_sequence as integer increment 1;
CREATE TABLE "desa_created" (
                                   "id" integer PRIMARY KEY,
                                   "created" timestamp,
                                   "finished_date" timestamp,
                                   "name_desa" varchar(200),
                                   "description" varchar(200),
                                   "num_rep" integer,
                                   "user_created" integer,
                                   "code" varchar(10)

);
create sequence desa_created_sequence as integer increment 1;
CREATE TABLE "card" (
                        "id" integer PRIMARY KEY,
                        "question" varchar(200) NOT NULL,
                        "answer" varchar(100) NOT NULL,
                        "id_desa_created" integer
);
create sequence card_sequence as integer increment 1;
CREATE TABLE "desa_join" (
                                "id" integer PRIMARY KEY,
                                "id_desa_created" integer NOT NULL ,
                                "id_user" integer
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

alter table desa_created add constraint FK_desa_created_ref_user foreign key (user_created) references "user_app" (id);
alter table card add constraint FK_card_ref_desa_created foreign key (id_desa_created) references desa_created (id) ;
alter table desa_join add constraint FK_desa_join_ref_desa_created foreign key (id_desa_created) references desa_created (id);
alter table desa_join add constraint FK_desa_join_ref_user foreign key (id_user) references "user_app" (id);
alter table rep_desa add constraint FK_rep_desa_ref_desa_join foreign key  (id_desa) references desa_join (id);


