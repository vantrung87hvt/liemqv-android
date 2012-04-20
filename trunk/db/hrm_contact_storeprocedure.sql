USE hrm_contact
GO
--tblContact-----------------------
--sp GetOne by ID
CREATE proc spContact_GetOne
@_id Integer
as
begin
	select * FROM tblContact where _id = @_id
end
--EXEC spContact_GetOne 1

--sp Get all
CREATE proc spContact_GetAll
as
begin
	select * FROM tblContact
	order BY sLastname ASC
end
--EXEC spContact_GetAll