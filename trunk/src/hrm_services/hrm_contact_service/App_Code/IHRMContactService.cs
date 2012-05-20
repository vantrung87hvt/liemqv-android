using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ServiceModel;
using System.ServiceModel.Web;

using hrm_contact.Entity;
using System.ServiceModel.Activation;

/// <summary>
/// Khai bao cac interface cho Contact
/// </summary>

[ServiceContract(Namespace = "urn:hr")]
public interface IHRMContactService
{
    [OperationContract]
    [WebGet(UriTemplate = "contact_getall", ResponseFormat = WebMessageFormat.Json)]
    List<ContactEntity> ContactGetAll();

    [OperationContract]
    [WebGet(UriTemplate = "contact_getbypk/{_id}", ResponseFormat = WebMessageFormat.Json)]
    ContactEntity ContactGetByPK(String _id);

    [OperationContract]
    [WebInvoke(Method = "PUT",
            UriTemplate = "contact_insert/{strEntity}",
            RequestFormat = WebMessageFormat.Json)]
    Int32 ContactInsert(String strEntity);

    [OperationContract]
    [WebInvoke(Method = "PUT",
            UriTemplate = "contact_update/{strEntity}",
            RequestFormat = WebMessageFormat.Json)]
    bool ContactUpdate(String strEntity);

    [OperationContract]
    [WebInvoke(Method = "DELETE", UriTemplate = "contact_delete/{_id}")]
    bool ContactDelete(String _id);
}