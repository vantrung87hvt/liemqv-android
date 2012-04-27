using System.Collections.Generic;
using System.ServiceModel;
using System.ServiceModel.Web;

using hrm_contact.Business;
using hrm_contact.Entity;

[ServiceContract]
public interface IRESTHRService
{
    [OperationContract]
    [WebGet(UriTemplate = "persons", ResponseFormat = WebMessageFormat.Json)]
    List<PersonData> GetAll();

    [OperationContract]
    [WebGet(UriTemplate = "contacts", ResponseFormat = WebMessageFormat.Json)]
    List<ContactEntity> GetAllContact();

    [OperationContract]
    [WebGet(UriTemplate = "person/{personId}", ResponseFormat = WebMessageFormat.Json)]
    PersonData GetPerson(string personId);

    [OperationContract]
    [WebGet(UriTemplate = "contact/{contactId}", ResponseFormat = WebMessageFormat.Json)]
    ContactEntity GetContact(string contactId);

    [OperationContract]
    [WebInvoke(Method = "PUT",
            UriTemplate = "person/{personId}",
            RequestFormat = WebMessageFormat.Json)]
    void UpdatePerson(string personId, PersonData personData);

    [OperationContract]
    [WebInvoke(Method = "DELETE", UriTemplate = "person/{personId}")]
    void DeletePerson(string personId);
}
