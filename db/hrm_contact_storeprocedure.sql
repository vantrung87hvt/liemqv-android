USE hrm_contact
GO
-------------------------------------------------
----------------tblContact-----------------------
-------------------------------------------------
IF(OBJECT_ID('spContact_GetOne','p') is not null)
begin
	drop proc spContact_GetOne
end
GO
CREATE proc spContact_GetOne
	@_id Integer
as
begin
	select * FROM tblContact where _id = @_id
end
GO
--EXEC spContact_GetOne 1

--sp Get all
IF(OBJECT_ID('spContact_GetAll','p') is not null)
begin
	drop proc spContact_GetAll
end
GO
CREATE proc spContact_GetAll
as
begin
	select * FROM tblContact
	order BY sLastname ASC
end
GO
--EXEC spContact_GetAll
--sp Contact Insert
IF(OBJECT_ID('spContact_Insert','p') is not null)
begin
	drop proc spContact_Insert
end
GO
CREATE proc spContact_Insert
	@_id Integer Output,
	@sFirstname NVarchar(50),
	@sLastname NVarchar(50),
	@iGender smallint,
	@sNotes NVarchar(200),
	@sNickname NVarchar,
	@bAvatar Varbinary(max)
as
begin
	Insert INTO tblContact(sFirstname, sLastname, iGender, sNotes, sNickname, bAvartar)
	VALUES(@sFirstname, @sLastname, @iGender, @sNotes, @sNickname, @bAvatar)
	SELECT @_id = scope_identity()
end
GO
--sp Contact Update
IF(OBJECT_ID('spContact_Update','p') is not null)
begin
	drop proc spContact_Update
end
GO
CREATE proc spContact_Update
	@_id Integer,
	@sFirstname NVarchar(50),
	@sLastname NVarchar(50),
	@iGender smallint,
	@sNotes NVarchar(200),
	@sNickname NVarchar,
	@bAvatar Varbinary(max)
as
begin
	update tblContact
	SET sFirstname=@sFirstname, sLastname=@sLastname, iGender=@iGender, sNotes=@sNotes, sNickname=@sNickname, bAvartar=@bAvatar
	WHERE _id = @_id
end
GO
--sp Contact Delete
IF(OBJECT_ID('spContact_Delete','p') is not null)
begin
	drop proc spContact_Delete
end
GO
CREATE proc spContact_Delete
	@_id Integer
as
begin
	delete tblContact
	WHERE _id = @_id
end
GO
-------------------------------------------------
----------------tblAddress_category--------------
-------------------------------------------------
--tblAddress_category_GetOne
IF(OBJECT_ID('tblAddress_category_GetOne','p') is not null)
begin
	drop proc tblAddress_category_GetOne
end
GO
CREATE proc tblAddress_category_GetOne
	@_id Integer
as
begin
	select * FROM tblAddress_category
	WHERE _id = @_id
end
GO

--tblAddress_category_GetAll
IF(OBJECT_ID('tblAddress_category_GetAll','p') is not null)
begin
	drop proc tblAddress_category_GetAll
end
GO
CREATE proc tblAddress_category_GetAll
as
begin
	select * FROM tblAddress_category
end
GO

--tblAddress_category_Insert
IF(OBJECT_ID('tblAddress_category_Insert','p') is not null)
begin
	drop proc tblAddress_category_Insert
end
GO
CREATE proc tblAddress_category_Insert
	@_id smallint Output,
	@sCategoryname NVarchar(100)
as
begin
	Insert into tblAddress_category(sCategoryname)
	values(@sCategoryname)
	SELECT @_id = scope_identity()
end
GO

--tblAddress_category_Update
IF(OBJECT_ID('tblAddress_category_Update','p') is not null)
begin
	drop proc tblAddress_category_Update
end
GO
CREATE proc tblAddress_category_Update
	@_id smallint,
	@sCategoryname NVarchar(100)
as
begin
	update tblAddress_category
	SET sCategoryname = @sCategoryname
	WHERE _id = @_id
end
GO

--tblAddress_category_Delete
IF(OBJECT_ID('tblAddress_category_Delete','p') is not null)
begin
	drop proc tblAddress_category_Delete
end
GO
CREATE proc tblAddress_category_Delete
	@_id smallint
as
begin
	delete FROM tblAddress_category
	WHERE _id = @_id
end
GO

-------------------------------------------------
----------------tblAddress-----------------------
-------------------------------------------------
--tblAddress_GetOne
IF(OBJECT_ID('tblAddress_GetOne','p') is not null)
begin
	drop proc tblAddress_GetOne
end
GO
CREATE proc tblAddress_GetOne
	@_id Integer
as
begin
	select * FROM tblAddress
	WHERE _id = @_id
end
GO
--tblAddress_GetAll
IF(OBJECT_ID('tblAddress_GetAll','p') is not null)
begin
	drop proc tblAddress_GetAll
end
GO
CREATE proc tblAddress_GetAll
as
begin
	select * FROM tblAddress
end
GO
--tblAddress_Insert
IF(OBJECT_ID('tblAddress_Insert','p') is not null)
begin
	drop proc tblAddress_Insert
end
GO
CREATE proc tblAddress_Insert
	@_id Integer Output,
	@sStreet Nvarchar(100),
	@sCity Nvarchar(100),
	@sState Nvarchar(100),
	@sZipcode Nvarchar(100),
	@FK_iAddress_categoryID smallint,
	@FK_iContactID Integer
as
begin
	Insert INTO tblAddress(sStreet, sCity, sState, sZipcode, FK_iAddress_categoryID, FK_iContactID)
	VALUES(@sStreet, @sCity, @sState, @sZipcode, @FK_iAddress_categoryID, @FK_iContactID)
	SELECT @_id = scope_identity()
end
GO
--tblAddress_Update
IF(OBJECT_ID('tblAddress_Update','p') is not null)
begin
	drop proc tblAddress_Update
end
GO
CREATE proc tblAddress_Update
	@_id Integer,
	@sStreet Nvarchar(100),
	@sCity Nvarchar(100),
	@sState Nvarchar(100),
	@sZipcode Nvarchar(100),
	@FK_iAddress_categoryID smallint,
	@FK_iContactID Integer
as
begin
	update tblAddress
	SET sStreet=@sStreet, sCity=@sCity, sState=@sState, sZipcode=@sZipcode, FK_iAddress_categoryID=@FK_iAddress_categoryID, FK_iContactID=@FK_iContactID
	WHERE _id = @_id
end
GO
--tblAddress_Delete
IF(OBJECT_ID('tblAddress_Delete','p') is not null)
begin
	drop proc tblAddress_Delete
end
GO
CREATE proc tblAddress_Delete
	@_id Integer
as
begin
	Delete FROM tblAddress
	WHERE _id = @_id
end
GO
-------------------------------------------------
----------------tblEmail_category--------------
-------------------------------------------------
--tblEmail_category_GetOne
IF(OBJECT_ID('tblEmail_category_GetOne','p') is not null)
begin
	drop proc tblEmail_category_GetOne
end
GO
CREATE proc tblEmail_category_GetOne
	@_id Integer
as
begin
	select * FROM tblEmail_category
	WHERE _id = @_id
end
GO

--tblEmail_category_GetAll
IF(OBJECT_ID('tblEmail_category_GetAll','p') is not null)
begin
	drop proc tblEmail_category_GetAll
end
GO
CREATE proc tblEmail_category_GetAll
as
begin
	select * FROM tblEmail_category
end
GO

--tblEmail_category_Insert
IF(OBJECT_ID('tblEmail_category_Insert','p') is not null)
begin
	drop proc tblEmail_category_Insert
end
GO
CREATE proc tblEmail_category_Insert
	@_id smallint Output,
	@sCategoryname NVarchar(100)
as
begin
	Insert into tblEmail_category(sCategoryname)
	values(@sCategoryname)
	SELECT @_id = scope_identity()
end
GO

--tblEmail_category_Update
IF(OBJECT_ID('tblEmail_category_Update','p') is not null)
begin
	drop proc tblEmail_category_Update
end
GO
CREATE proc tblEmail_category_Update
	@_id smallint,
	@sCategoryname NVarchar(100)
as
begin
	update tblEmail_category
	SET sCategoryname = @sCategoryname
	WHERE _id = @_id
end
GO

--tblAddress_category_Delete
IF(OBJECT_ID('tblEmail_category_Delete','p') is not null)
begin
	drop proc tblEmail_category_Delete
end
GO
CREATE proc tblEmail_category_Delete
	@_id smallint
as
begin
	delete FROM tblEmail_category
	WHERE _id = @_id
end
GO
-------------------------------------------------
----------------tblIm_category--------------
-------------------------------------------------
--tblIm_category_GetOne
IF(OBJECT_ID('tblIm_category_GetOne','p') is not null)
begin
	drop proc tblIm_category_GetOne
end
GO
CREATE proc tblIm_category_GetOne
	@_id Integer
as
begin
	select * FROM tblIm_category
	WHERE _id = @_id
end
GO

--tblIm_category_GetAll
IF(OBJECT_ID('tblIm_category_GetAll','p') is not null)
begin
	drop proc tblIm_category_GetAll
end
GO
CREATE proc tblIm_category_GetAll
as
begin
	select * FROM tblIm_category
end
GO

--tblIm_category_Insert
IF(OBJECT_ID('tblIm_category_Insert','p') is not null)
begin
	drop proc tblIm_category_Insert
end
GO
CREATE proc tblIm_category_Insert
	@_id smallint Output,
	@sCategoryname NVarchar(100)
as
begin
	Insert into tblIm_category(sCategoryname)
	values(@sCategoryname)
	SELECT @_id = scope_identity()
end
GO

--tblIm_category_Update
IF(OBJECT_ID('tblIm_category_Update','p') is not null)
begin
	drop proc tblIm_category_Update
end
GO
CREATE proc tblIm_category_Update
	@_id smallint,
	@sCategoryname NVarchar(100)
as
begin
	update tblIm_category
	SET sCategoryname = @sCategoryname
	WHERE _id = @_id
end
GO

--tblAddress_category_Delete
IF(OBJECT_ID('tblIm_category_Delete','p') is not null)
begin
	drop proc tblIm_category_Delete
end
GO
CREATE proc tblIm_category_Delete
	@_id smallint
as
begin
	delete FROM tblIm_category
	WHERE _id = @_id
end
GO

-------------------------------------------------
----------------tblPhone_category--------------
-------------------------------------------------
--tblPhone_category_GetOne
IF(OBJECT_ID('tblPhone_category_GetOne','p') is not null)
begin
	drop proc tblPhone_category_GetOne
end
GO
CREATE proc tblPhone_category_GetOne
	@_id Integer
as
begin
	select * FROM tblPhone_category
	WHERE _id = @_id
end
GO

--tblPhone_category_GetAll
IF(OBJECT_ID('tblPhone_category_GetAll','p') is not null)
begin
	drop proc tblPhone_category_GetAll
end
GO
CREATE proc tblPhone_category_GetAll
as
begin
	select * FROM tblPhone_category
end
GO

--tblPhone_category_Insert
IF(OBJECT_ID('tblPhone_category_Insert','p') is not null)
begin
	drop proc tblPhone_category_Insert
end
GO
CREATE proc tblPhone_category_Insert
	@_id smallint Output,
	@sCategoryname NVarchar(100)
as
begin
	Insert into tblPhone_category(sCategoryname)
	values(@sCategoryname)
	SELECT @_id = scope_identity()
end
GO

--tblPhone_category_Update
IF(OBJECT_ID('tblPhone_category_Update','p') is not null)
begin
	drop proc tblPhone_category_Update
end
GO
CREATE proc tblPhone_category_Update
	@_id smallint,
	@sCategoryname NVarchar(100)
as
begin
	update tblPhone_category
	SET sCategoryname = @sCategoryname
	WHERE _id = @_id
end
GO

--tblAddress_category_Delete
IF(OBJECT_ID('tblPhone_category_Delete','p') is not null)
begin
	drop proc tblPhone_category_Delete
end
GO
CREATE proc tblPhone_category_Delete
	@_id smallint
as
begin
	delete FROM tblPhone_category
	WHERE _id = @_id
end
GO

-------------------------------------------------
----------------tblEmail-----------------------
-------------------------------------------------
--tblEmail_GetOne
IF(OBJECT_ID('tblEmail_GetOne','p') is not null)
begin
	drop proc tblEmail_GetOne
end
GO
CREATE proc tblEmail_GetOne
	@_id Integer
as
begin
	select * FROM tblEmail
	WHERE _id = @_id
end
GO
--tblEmail_GetAll
IF(OBJECT_ID('tblEmail_GetAll','p') is not null)
begin
	drop proc tblEmail_GetAll
end
GO
CREATE proc tblEmail_GetAll
as
begin
	select * FROM tblEmail
end
GO
--tblEmail_Insert
IF(OBJECT_ID('tblEmail_Insert','p') is not null)
begin
	drop proc tblEmail_Insert
end
GO
CREATE proc tblEmail_Insert
	@_id Integer Output,
	@sEmail NVarchar(50),
	@FK_iEmail_categoryID smallint,
	@FK_iContactID Integer
as
begin
	Insert INTO tblEmail(sEmail, FK_iEmail_categoryID, FK_iContactID)
	VALUES(@sEmail,@FK_iEmail_categoryID,@FK_iContactID)
	SELECT @_id = scope_identity()
end
GO
--tblEmail_Update
IF(OBJECT_ID('tblEmail_Update','p') is not null)
begin
	drop proc tblEmail_Update
end
GO
CREATE proc tblEmail_Update
	@_id Integer Output,
	@sEmail NVarchar(50),
	@FK_iEmail_categoryID smallint,
	@FK_iContactID Integer
as
begin
	update tblEmail
	set sEmail=@sEmail, FK_iEmail_categoryID=@FK_iEmail_categoryID, FK_iContactID=@FK_iContactID
	WHERE _id = @_id
end
GO
--tblEmail_Delete
IF(OBJECT_ID('tblEmail_Delete','p') is not null)
begin
	drop proc tblEmail_Delete
end
GO
CREATE proc tblEmail_Delete
	@_id Integer
as
begin
	Delete FROM tblEmail
	WHERE _id = @_id
end
GO

-------------------------------------------------
----------------tblIm-----------------------
-------------------------------------------------
--tblIm_GetOne
IF(OBJECT_ID('tblIm_GetOne','p') is not null)
begin
	drop proc tblIm_GetOne
end
GO
CREATE proc tblIm_GetOne
	@_id Integer
as
begin
	select * FROM tblIm
	WHERE _id = @_id
end
GO
--tblIm_GetAll
IF(OBJECT_ID('tblIm_GetAll','p') is not null)
begin
	drop proc tblIm_GetAll
end
GO
CREATE proc tblIm_GetAll
as
begin
	select * FROM tblIm
end
GO
--tblIm_Insert
IF(OBJECT_ID('tblIm_Insert','p') is not null)
begin
	drop proc tblIm_Insert
end
GO
CREATE proc tblIm_Insert
	@_id Integer Output,
	@sIm NVarchar(50),
	@FK_iIm_categoryID smallint,
	@FK_iContactID Integer
as
begin
	Insert INTO tblIm(sIm, FK_iIm_categoryID, FK_iContactID)
	VALUES(@sIm,@FK_iIm_categoryID,@FK_iContactID)
	SELECT @_id = scope_identity()
end
GO
--tblIm_Update
IF(OBJECT_ID('tblIm_Update','p') is not null)
begin
	drop proc tblIm_Update
end
GO
CREATE proc tblIm_Update
	@_id Integer Output,
	@sIm NVarchar(50),
	@FK_iIm_categoryID smallint,
	@FK_iContactID Integer
as
begin
	update tblIm
	set sIm=@sIm, FK_iIm_categoryID=@FK_iIm_categoryID, FK_iContactID=@FK_iContactID
	WHERE _id = @_id
end
GO
--tblIm_Delete
IF(OBJECT_ID('tblIm_Delete','p') is not null)
begin
	drop proc tblIm_Delete
end
GO
CREATE proc tblIm_Delete
	@_id Integer
as
begin
	Delete FROM tblIm
	WHERE _id = @_id
end
GO

-------------------------------------------------
----------------tblPhone-----------------------
-------------------------------------------------
--tblPhone_GetOne
IF(OBJECT_ID('tblPhone_GetOne','p') is not null)
begin
	drop proc tblPhone_GetOne
end
GO
CREATE proc tblPhone_GetOne
	@_id Integer
as
begin
	select * FROM tblPhone
	WHERE _id = @_id
end
GO
--tblPhone_GetAll
IF(OBJECT_ID('tblPhone_GetAll','p') is not null)
begin
	drop proc tblPhone_GetAll
end
GO
CREATE proc tblPhone_GetAll
as
begin
	select * FROM tblPhone
end
GO
--tblPhone_Insert
IF(OBJECT_ID('tblPhone_Insert','p') is not null)
begin
	drop proc tblPhone_Insert
end
GO
CREATE proc tblPhone_Insert
	@_id Integer Output,
	@sNumber NVarchar(50),
	@FK_iPhone_categoryID smallint,
	@FK_iContactID Integer
as
begin
	Insert INTO tblPhone(sNumber, FK_iPhone_categoryID, FK_iContactID)
	VALUES(@sNumber,@FK_iPhone_categoryID,@FK_iContactID)
	SELECT @_id = scope_identity()
end
GO
--tblPhone_Update
IF(OBJECT_ID('tblPhone_Update','p') is not null)
begin
	drop proc tblPhone_Update
end
GO
CREATE proc tblPhone_Update
	@_id Integer Output,
	@sNumber NVarchar(50),
	@FK_iPhone_categoryID smallint,
	@FK_iContactID Integer
as
begin
	update tblPhone
	set sNumber=@sNumber, FK_iPhone_categoryID=@FK_iPhone_categoryID, FK_iContactID=@FK_iContactID
	WHERE _id = @_id
end
GO
--tblPhone_Delete
IF(OBJECT_ID('tblPhone_Delete','p') is not null)
begin
	drop proc tblPhone_Delete
end
GO
CREATE proc tblPhone_Delete
	@_id Integer
as
begin
	Delete FROM tblPhone
	WHERE _id = @_id
end
GO

-------------------------------------------------
----------------tblOrganization------------------
-------------------------------------------------
IF(OBJECT_ID('spOrganization_GetOne','p') is not null)
begin
	drop proc spOrganization_GetOne
end
GO
CREATE proc spOrganization_GetOne
	@_id Integer
as
begin
	select * FROM tblOrganization where _id = @_id
end
GO

--sp Get all
IF(OBJECT_ID('spOrganization_GetAll','p') is not null)
begin
	drop proc spOrganization_GetAll
end
GO
CREATE proc spOrganization_GetAll
as
begin
	select * FROM tblOrganization
end
GO
--sp Organization Insert
IF(OBJECT_ID('spOrganization_Insert','p') is not null)
begin
	drop proc spOrganization_Insert
end
GO
CREATE proc spOrganization_Insert
	@_id Integer Output,
	@sCompany Nvarchar(100),
	@sTitle Nvarchar(50),
	@FK_iContactID int
as
begin
	Insert INTO tblOrganization(sCompany, sTitle, FK_iContactID)
	VALUES(@sCompany,@sTitle,@FK_iContactID)
	SELECT @_id = scope_identity()
end
GO
--sp Organization Update
IF(OBJECT_ID('spOrganization_Update','p') is not null)
begin
	drop proc spOrganization_Update
end
GO
CREATE proc spOrganization_Update
	@_id Integer Output,
	@sCompany Nvarchar(100),
	@sTitle Nvarchar(50),
	@FK_iContactID int
as
begin
	update tblOrganization
	SET sCompany=@sCompany, sTitle=@sTitle, FK_iContactID=@FK_iContactID
	WHERE _id = @_id
end
GO
--sp Organization Delete
IF(OBJECT_ID('spOrganization_Delete','p') is not null)
begin
	drop proc spOrganization_Delete
end
GO
CREATE proc spOrganization_Delete
	@_id Integer
as
begin
	delete tblOrganization
	WHERE _id = @_id
end
GO
-------------------------------------------------
----------------tblWebsite------------------
-------------------------------------------------
IF(OBJECT_ID('spWebsite_GetOne','p') is not null)
begin
	drop proc spWebsite_GetOne
end
GO
CREATE proc spWebsite_GetOne
	@_id Integer
as
begin
	select * FROM tbltblWebsite where _id = @_id
end
GO

--sp Get all
IF(OBJECT_ID('spWebsite_GetAll','p') is not null)
begin
	drop proc spWebsite_GetAll
end
GO
CREATE proc spWebsite_GetAll
as
begin
	select * FROM tblWebsite
end
GO
--sp tblWebsite Insert
IF(OBJECT_ID('spWebsite_Insert','p') is not null)
begin
	drop proc spWebsite_Insert
end
GO
CREATE proc spWebsite_Insert
	@_id Integer Output,
	@sWebsite Nvarchar(100),
	@FK_iContactID int
as
begin
	Insert INTO tbltblWebsite(sWebsite, FK_iContactID)
	VALUES(@sWebsite,@FK_iContactID)
	SELECT @_id = scope_identity()
end

--sp tblWebsite Update
IF(OBJECT_ID('spWebsite_Update','p') is not null)
begin
	drop proc spWebsite_Update
end
GO
CREATE proc spWebsite_Update
	@_id Integer Output,
	@sWebsite Nvarchar(100),
	@FK_iContactID int
as
begin
	update tblWebsite
	SET sWebsite=@sWebsite, FK_iContactID=@FK_iContactID
	WHERE _id = @_id
END
GO

--sp tblWebsite Delete
IF(OBJECT_ID('spWebsite_Delete','p') is not null)
begin
	drop proc spWebsite_Delete
end
GO
CREATE proc spWebsite_Delete
	@_id Integer
as
begin
	delete tbltblWebsite
	WHERE _id = @_id
end