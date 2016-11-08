--------------------------------------------------------
--  File created - Sunday-October-23-2016
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table ATTACHED_TAGS
--------------------------------------------------------

  CREATE TABLE "ROOT"."ATTACHED_TAGS"
   (	"T_ID" NUMBER,
	"T_NAME" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index ATTACHED_TAGS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROOT"."ATTACHED_TAGS_PK" ON "ROOT"."ATTACHED_TAGS" ("T_ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index UNIQ_TAG_NAME
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROOT"."UNIQ_TAG_NAME" ON "ROOT"."ATTACHED_TAGS" ("T_NAME")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;


--------------------------------------------------------
--  DDL for Sequence TAG_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ROOT"."TAG_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 104 CACHE 100 NOORDER  NOCYCLE ;

--------------------------------------------------------
--  DDL for Trigger TAG_INC
--------------------------------------------------------



  CREATE OR REPLACE TRIGGER "ROOT"."TAG_INC"
BEFORE INSERT ON ATTACHED_TAGS
FOR EACH ROW
  BEGIN
    :new.T_ID := tag_seq.nextval;
  END;
/
ALTER TRIGGER "ROOT"."TAG_INC" ENABLE;
--------------------------------------------------------
--  Constraints for Table ATTACHED_TAGS
--------------------------------------------------------

  ALTER TABLE "ROOT"."ATTACHED_TAGS" ADD CONSTRAINT "UNIQ_TAG_NAME" UNIQUE ("T_NAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "ROOT"."ATTACHED_TAGS" ADD CONSTRAINT "ATTACHED_TAGS_PK" PRIMARY KEY ("T_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "ROOT"."ATTACHED_TAGS" MODIFY ("T_ID" NOT NULL ENABLE);



  --------------------------------------------------------
--  DDL for Table AUTHORS
--------------------------------------------------------

  CREATE TABLE "ROOT"."AUTHORS"
   (	"A_ID" NUMBER,
	"A_NAME" VARCHAR2(25 BYTE),
	"A_SURNAME" VARCHAR2(40 BYTE),
	"A_STATUS" CHAR(1 BYTE) DEFAULT 'N'
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index AUTHORS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROOT"."AUTHORS_PK" ON "ROOT"."AUTHORS" ("A_ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;


--------------------------------------------------------
--  DDL for Sequence AUTHORS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ROOT"."AUTHORS_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 25 CACHE 20 NOORDER  NOCYCLE ;


--------------------------------------------------------
--  DDL for Trigger AUTHORS_INC
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "ROOT"."AUTHORS_INC"
BEFORE INSERT ON AUTHORS
FOR EACH ROW
  BEGIN
    :new.A_ID := authors_seq.nextval;
  END;
/
ALTER TRIGGER "ROOT"."AUTHORS_INC" ENABLE;
--------------------------------------------------------
--  Constraints for Table AUTHORS
--------------------------------------------------------

  ALTER TABLE "ROOT"."AUTHORS" ADD CHECK (A_STATUS IN('Y','N')) ENABLE;
  ALTER TABLE "ROOT"."AUTHORS" ADD CONSTRAINT "AUTHORS_PK" PRIMARY KEY ("A_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "ROOT"."AUTHORS" MODIFY ("A_SURNAME" NOT NULL ENABLE);
  ALTER TABLE "ROOT"."AUTHORS" MODIFY ("A_ID" NOT NULL ENABLE);




--------------------------------------------------------
--  DDL for Table NEWS
--------------------------------------------------------

  CREATE TABLE "ROOT"."NEWS"
   (	"N_ID" NUMBER,
	"N_MAIN_TITLE" VARCHAR2(50 BYTE),
	"N_SHORT_TITLE" VARCHAR2(230 BYTE),
	"N_DATE_PUBLISHING" DATE,
	"N_MAIN_PHOTO" VARCHAR2(100 BYTE),
	"N_CONTENT" LONG
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index NEWS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROOT"."NEWS_PK" ON "ROOT"."NEWS" ("N_ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;


--------------------------------------------------------
--  DDL for Sequence NEWS_SEQUENCE
--------------------------------------------------------

   CREATE SEQUENCE  "ROOT"."NEWS_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE ;



--------------------------------------------------------
--  DDL for Trigger NEWS_ON_INSERT
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "ROOT"."NEWS_ON_INSERT"
  BEFORE INSERT ON NEWS
  FOR EACH ROW
BEGIN
  SELECT news_sequence.nextval
  INTO :new.N_ID
  FROM dual;
END;
/
ALTER TRIGGER "ROOT"."NEWS_ON_INSERT" ENABLE;
--------------------------------------------------------
--  Constraints for Table NEWS
--------------------------------------------------------

  ALTER TABLE "ROOT"."NEWS" ADD CONSTRAINT "NEWS_PK" PRIMARY KEY ("N_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "ROOT"."NEWS" MODIFY ("N_MAIN_PHOTO" NOT NULL ENABLE);
  ALTER TABLE "ROOT"."NEWS" MODIFY ("N_DATE_PUBLISHING" NOT NULL ENABLE);
  ALTER TABLE "ROOT"."NEWS" MODIFY ("N_SHORT_TITLE" NOT NULL ENABLE);
  ALTER TABLE "ROOT"."NEWS" MODIFY ("N_MAIN_TITLE" NOT NULL ENABLE);
  ALTER TABLE "ROOT"."NEWS" MODIFY ("N_ID" NOT NULL ENABLE);








  --------------------------------------------------------
--  DDL for Table NEWS_HAS_AUTHORS
--------------------------------------------------------

  CREATE TABLE "ROOT"."NEWS_HAS_AUTHORS"
   (	"AUTHORS_ID" NUMBER,
	"NEWS_ID" NUMBER
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index NEWS_HAS_AUTHORS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROOT"."NEWS_HAS_AUTHORS_PK" ON "ROOT"."NEWS_HAS_AUTHORS" ("AUTHORS_ID", "NEWS_ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table NEWS_HAS_AUTHORS
--------------------------------------------------------

  ALTER TABLE "ROOT"."NEWS_HAS_AUTHORS" ADD CONSTRAINT "NEWS_HAS_AUTHORS_PK" PRIMARY KEY ("AUTHORS_ID", "NEWS_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "ROOT"."NEWS_HAS_AUTHORS" MODIFY ("NEWS_ID" NOT NULL ENABLE);
  ALTER TABLE "ROOT"."NEWS_HAS_AUTHORS" MODIFY ("AUTHORS_ID" NOT NULL ENABLE);




--------------------------------------------------------
--  DDL for Table NEWS_HAS_TAGS
--------------------------------------------------------

  CREATE TABLE "ROOT"."NEWS_HAS_TAGS"
   (	"NEWS_ID" NUMBER,
	"ATTACHED_TAGS_ID" NUMBER
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index NEWS_HAS_TAGS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROOT"."NEWS_HAS_TAGS_PK" ON "ROOT"."NEWS_HAS_TAGS" ("NEWS_ID", "ATTACHED_TAGS_ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table NEWS_HAS_TAGS
--------------------------------------------------------

  ALTER TABLE "ROOT"."NEWS_HAS_TAGS" ADD CONSTRAINT "NEWS_HAS_TAGS_PK" PRIMARY KEY ("NEWS_ID", "ATTACHED_TAGS_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "ROOT"."NEWS_HAS_TAGS" MODIFY ("ATTACHED_TAGS_ID" NOT NULL ENABLE);
  ALTER TABLE "ROOT"."NEWS_HAS_TAGS" MODIFY ("NEWS_ID" NOT NULL ENABLE);



--------------------------------------------------------
--  DDL for Table ROLE
--------------------------------------------------------

  CREATE TABLE "ROOT"."ROLE"
   (	"R_ID" NUMBER,
	"R_NAME" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index ROLE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROOT"."ROLE_PK" ON "ROOT"."ROLE" ("R_ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index UNIQ_ROLE_NAME
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROOT"."UNIQ_ROLE_NAME" ON "ROOT"."ROLE" ("R_NAME")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;


--------------------------------------------------------
--  DDL for Sequence ROLE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ROOT"."ROLE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 101 CACHE 100 NOORDER  NOCYCLE ;


--------------------------------------------------------
--  DDL for Trigger ROLE_INC
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "ROOT"."ROLE_INC"
BEFORE INSERT ON ROLE
FOR EACH ROW
  BEGIN
    :new.R_ID := role_seq.nextval;
  END;
/
ALTER TRIGGER "ROOT"."ROLE_INC" ENABLE;
--------------------------------------------------------
--  Constraints for Table ROLE
--------------------------------------------------------

  ALTER TABLE "ROOT"."ROLE" ADD CONSTRAINT "UNIQ_ROLE_NAME" UNIQUE ("R_NAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "ROOT"."ROLE" ADD CONSTRAINT "ROLE_PK" PRIMARY KEY ("R_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "ROOT"."ROLE" MODIFY ("R_NAME" NOT NULL ENABLE);
  ALTER TABLE "ROOT"."ROLE" MODIFY ("R_ID" NOT NULL ENABLE);



--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "ROOT"."USERS"
   (	"U_ID" NUMBER,
	"U_LOGIN" VARCHAR2(50 BYTE),
	"U_PASSWORD" VARCHAR2(50 BYTE),
	"U_ROLE" NUMBER
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index USER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROOT"."USER_PK" ON "ROOT"."USERS" ("U_ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SYS_C007135
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROOT"."SYS_C007135" ON "ROOT"."USERS" ("U_LOGIN")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

--------------------------------------------------------
--  DDL for Sequence USER_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ROOT"."USER_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 100 NOORDER  NOCYCLE ;


--------------------------------------------------------
--  DDL for Trigger USER_INC
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "ROOT"."USER_INC"
BEFORE INSERT ON USERS
FOR EACH ROW
  BEGIN
    :new.U_ID := user_seq.nextval;
  END;
/
ALTER TRIGGER "ROOT"."USER_INC" ENABLE;
--------------------------------------------------------
--  Constraints for Table USERS
--------------------------------------------------------

  ALTER TABLE "ROOT"."USERS" ADD UNIQUE ("U_LOGIN")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "ROOT"."USERS" ADD CONSTRAINT "USER_PK" PRIMARY KEY ("U_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "ROOT"."USERS" MODIFY ("U_ROLE" NOT NULL ENABLE);
  ALTER TABLE "ROOT"."USERS" MODIFY ("U_PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "ROOT"."USERS" MODIFY ("U_ID" NOT NULL ENABLE);


  --------------------------------------------------------
--  DDL for Table COMMENT_NEWS
--------------------------------------------------------

  CREATE TABLE "ROOT"."COMMENT_NEWS"
   (	"USER_ID_USER" NUMBER,
	"C_ID" NUMBER,
	"C_DATE" DATE,
	"C_CONTENT" VARCHAR2(200 BYTE),
	"C_NEWS" NUMBER
   ) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index COMMENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "ROOT"."COMMENT_PK" ON "ROOT"."COMMENT_NEWS" ("C_ID")
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

--------------------------------------------------------
--  DDL for Sequence COMMENT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ROOT"."COMMENT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 100 NOORDER  NOCYCLE ;

--------------------------------------------------------
--  DDL for Trigger COMMENT_INC
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "ROOT"."COMMENT_INC"
BEFORE INSERT ON COMMENT_NEWS
FOR EACH ROW
  BEGIN
    :new.C_ID := comment_seq.nextval;
  END;
/
ALTER TRIGGER "ROOT"."COMMENT_INC" ENABLE;
--------------------------------------------------------
--  Constraints for Table COMMENT_NEWS
--------------------------------------------------------

  ALTER TABLE "ROOT"."COMMENT_NEWS" ADD CONSTRAINT "COMMENT_PK" PRIMARY KEY ("C_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "ROOT"."COMMENT_NEWS" MODIFY ("C_NEWS" NOT NULL ENABLE);
  ALTER TABLE "ROOT"."COMMENT_NEWS" MODIFY ("C_CONTENT" NOT NULL ENABLE);
  ALTER TABLE "ROOT"."COMMENT_NEWS" MODIFY ("C_DATE" NOT NULL ENABLE);
  ALTER TABLE "ROOT"."COMMENT_NEWS" MODIFY ("C_ID" NOT NULL ENABLE);
  ALTER TABLE "ROOT"."COMMENT_NEWS" MODIFY ("USER_ID_USER" NOT NULL ENABLE);

