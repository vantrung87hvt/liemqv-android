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

[AspNetCompatibilityRequirements(RequirementsMode = AspNetCompatibilityRequirementsMode.Allowed)]
public class ContactService : IContactService
{
    List<ContactEntity> IContactService.GetAll()
    {
        return ContactBRL.GetAll();
    }

    public ContactEntity GetByPK(string _id)
    {
        return ContactBRL.GetOne(Int32.Parse(_id));
    }

    
}
