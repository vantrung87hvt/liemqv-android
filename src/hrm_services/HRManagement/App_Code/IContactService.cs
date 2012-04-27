using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ServiceModel;
using System.ServiceModel.Web;

using hrm_contact.Entity;
using System.ServiceModel.Activation;

/// <summary>
/// Khai bao cacs interface cho Contact
/// </summary>

[ServiceContract(Namespace = "urn:hr")]
public interface IContactService
{
    [OperationContract]
    [WebGet(UriTemplate = "contact_all", ResponseFormat = WebMessageFormat.Json)]
    List<ContactEntity> GetAll();

    [OperationContract]
    [WebGet(UriTemplate = "contact_pk/{_id}", ResponseFormat = WebMessageFormat.Json)]
    ContactEntity GetByPK(string _id);

    //[OperationContract]
    //[WebInvoke(Method = "PUT",
    //        UriTemplate = "person/{personId}",
    //        RequestFormat = WebMessageFormat.Json)]
    //void UpdatePerson(string personId, PersonData personData);

    //[OperationContract]
    //[WebInvoke(Method = "DELETE", UriTemplate = "person/{personId}")]
    //void DeletePerson(string personId);
}