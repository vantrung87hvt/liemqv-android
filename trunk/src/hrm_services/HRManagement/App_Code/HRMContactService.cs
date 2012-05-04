using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Activation;
using System.ServiceModel.Web;
using System.Text;

using hrm_contact.Entity;
using hrm_contact.Business;
using System.Web.Script.Serialization;

[AspNetCompatibilityRequirements(RequirementsMode = AspNetCompatibilityRequirementsMode.Allowed)]
public class HRMContactService : IHRMContactService
{
    public List<ContactEntity> ContactGetAll()
    {
        return ContactBRL.GetAll();
    }

    public ContactEntity ContactGetByPK(String _id)
    {
        return ContactBRL.GetOne(Int32.Parse(_id));
    }

    public Int32 ContactInsert(String strEntity)
    {
        ContactEntity entity = new JavaScriptSerializer().Deserialize<ContactEntity>(strEntity);
        return ContactBRL.Add(entity);
    }

    public bool ContactUpdate(String strEntity)
    {
        ContactEntity entity = new JavaScriptSerializer().Deserialize<ContactEntity>(strEntity);
        return ContactBRL.Edit(entity);
    }

    public bool ContactDelete(String _id)
    {
        Int32 iID = Int32.Parse(_id);
        return ContactBRL.Remove(iID);
    }
}
