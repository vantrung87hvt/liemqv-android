-- =============================================
-- hrm_contact Database
-- Create date: 20/04/2012
-- Create by: liemqv@gmail.com
-- =============================================
USE master
GO

IF  EXISTS (
	SELECT name 
		FROM sys.databases 
		WHERE name = N'hrm_contact'
)
DROP DATABASE hrm_contact
GO

CREATE DATABASE hrm_contact
GO

use hrm_contact
go
-- =============================================
-- 
-- =============================================

---Bảng tblContact
IF(OBJECT_ID('tblContact','U') is not null)
BEGIN 
	DROP TABLE tblContact
END
CREATE TABLE tblContact
(
	_id Integer Identity Primary key,
	sFirstname NVarchar(50),
	sLastname NVarchar(50) NOT NULL,
	iGender Smallint,
	sNotes Nvarchar(200),
	sNickname Varchar(50),
	bAvartar Varbinary(max)
)
GO

---Bảng tblPhone_category
IF(OBJECT_ID('tblPhone_category','U') is not null)
BEGIN 
	DROP TABLE tblPhone_category
END
CREATE TABLE tblPhone_category
(
	_id Smallint Identity Primary key,
	sCategoryname Varchar(100)
)
GO

---Bảng tblEmail_category
IF(OBJECT_ID('tblEmail_category','U') is not null)
BEGIN 
	DROP TABLE tblEmail_category
END
CREATE TABLE tblEmail_category
(
	_id Smallint Identity Primary key,
	sCategoryname Varchar(100)
)
GO

---Bảng tblAddress_category
IF(OBJECT_ID('tblAddress_category','U') is not null)
BEGIN 
	DROP TABLE tblAddress_category
END
CREATE TABLE tblAddress_category
(
	_id Smallint Identity Primary key,
	sCategoryname Varchar(100)
)
GO

---Bảng tblIm_category
IF(OBJECT_ID('tblIm_category','U') is not null)
BEGIN 
	DROP TABLE tblIm_category
END
CREATE TABLE tblIm_category
(
	_id Smallint Identity Primary key,
	sCategoryname Varchar(100)
)
GO

---Bảng tblPhone
IF(OBJECT_ID('tblPhone','U') is not null)
BEGIN 
	DROP TABLE tblPhone
END
CREATE TABLE tblPhone
(
	_id Integer Identity Primary key,
	sNumber Varchar(15) NOT NULL,
	FK_iPhone_categoryID smallint NOT NULL References tblPhone_category(_id),
	FK_iContactID Integer NOT NULL References tblContact(_id)
)
GO

---Bảng tblEmail
IF(OBJECT_ID('tblEmail','U') is not null)
BEGIN 
	DROP TABLE tblEmail
END
CREATE TABLE tblEmail
(
	_id Integer Identity Primary key,
	sEmail Varchar(50) NOT NULL,
	FK_iEmail_categoryID smallint NOT NULL References tblEmail_category(_id),
	FK_iContactID Integer NOT NULL References tblContact(_id)
)
GO

---Bảng tblAddress
IF(OBJECT_ID('tblAddress','U') is not null)
BEGIN 
	DROP TABLE tblAddress
END
CREATE TABLE tblAddress
(
	_id Integer Identity Primary key,
	sStreet NVarchar(100),
	sCity NVarchar(100),
	sState NVarchar(100),
	sZipcode NVarchar(100),
	FK_iAddress_categoryID smallint NOT NULL References tblAddress_category(_id),
	FK_iContactID Integer NOT NULL References tblContact(_id)
)
GO

---Bảng tblIm
IF(OBJECT_ID('tblIm','U') is not null)
BEGIN 
	DROP TABLE tblIm
END
CREATE TABLE tblIm
(
	_id Integer Identity Primary key,
	sIm Varchar(50) NOT NULL,
	FK_iIm_categoryID smallint NOT NULL References tblIm_category(_id),
	FK_iContactID Integer NOT NULL References tblContact(_id)
)
GO

---Bảng tblWebsite
IF(OBJECT_ID('tblWebsite','U') is not null)
BEGIN 
	DROP TABLE tblWebsite
END
CREATE TABLE tblWebsite
(
	_id Integer Identity Primary key,
	sWebsite Varchar(100) NOT NULL,
	FK_iContactID Integer NOT NULL References tblContact(_id)
)
GO

---Bảng tblOrganization
IF(OBJECT_ID('tblOrganization','U') is not null)
BEGIN 
	DROP TABLE tblOrganization
END
CREATE TABLE tblOrganization
(
	_id Integer Identity Primary key,
	sCompany NVarchar(100),
	sTitle NVarchar(50),
	FK_iContactID Integer NOT NULL References tblContact(_id)
)
GO

-- =============================================
-- Insert sample data
-- =============================================

INSERT INTO tblPhone_category(sCategoryname) VALUES('Home')
INSERT INTO tblPhone_category(sCategoryname) VALUES('Phone')

INSERT INTO tblEmail_category(sCategoryname) VALUES('Home')
INSERT INTO tblEmail_category(sCategoryname) VALUES('Work')

INSERT INTO tblAddress_category(sCategoryname) VALUES('Home')
INSERT INTO tblAddress_category(sCategoryname) VALUES('Work')

INSERT INTO tblIm_category(sCategoryname) VALUES('Skype')
INSERT INTO tblIm_category(sCategoryname) VALUES('Yahoo')

INSERT INTO tblContact(sFirstname, sLastname, iGender, sNotes)
VALUES(N'Quang', N'Liem', 1, N'...')
INSERT INTO tblContact(sFirstname, sLastname, iGender, sNotes)
VALUES(N'Bui Thanh', N'Minh', 1, N'...')