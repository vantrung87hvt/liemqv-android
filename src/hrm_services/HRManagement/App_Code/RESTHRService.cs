using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel.Web;

using hrm_contact.Business;
using System.ServiceModel.Activation;

[AspNetCompatibilityRequirements(RequirementsMode = AspNetCompatibilityRequirementsMode.Allowed)]
public class RESTHRService : IRESTHRService
{
    public List<PersonData> GetAll()
    {
        var ctx = WebOperationContext.Current;
        try
        {
            using (var personCtx = new DataClassesDataContext())
            {
                // Set up the query
                var persons = from p in personCtx.Persons
                              orderby p.FirstName
                              select new PersonData
                              {
                                  PersonId = p.PersonId,
                                  FirstName = p.FirstName,
                                  LastName = p.LastName,
                                  EmailAddress = p.EmailAddress,
                                  Department = p.Department
                              };
                ctx.OutgoingResponse.StatusCode = System.Net.HttpStatusCode.OK;
                return persons.ToList();
            }
        }
        catch
        {
            ctx.OutgoingResponse.StatusCode = System.Net.HttpStatusCode.BadRequest;
            return null;
        }
    }

    public PersonData GetPerson(string personId)
    {
        var ctx = WebOperationContext.Current;
        try
        {
            using (var personCtx = new DataClassesDataContext())
            {
                // Set up the query
                var person = personCtx.Persons.SingleOrDefault(p => p.PersonId == Convert.ToInt32(personId));

                if (person == null)
                {
                    ctx.OutgoingResponse.SetStatusAsNotFound();
                    return null;
                }
                var personData = new PersonData
                                          {
                                              PersonId = person.PersonId,
                                              FirstName = person.FirstName,
                                              LastName = person.LastName,
                                              EmailAddress = person.EmailAddress,
                                              Department = person.Department
                                          };

                ctx.OutgoingResponse.StatusCode = System.Net.HttpStatusCode.OK;
                return personData;
            }
        }
        catch
        {
            ctx.OutgoingResponse.StatusCode = System.Net.HttpStatusCode.BadRequest;
            return null;
        }
    }

    public void UpdatePerson(string personId, PersonData personData)
    {
        WebOperationContext ctx = WebOperationContext.Current;
        System.Net.HttpStatusCode status = System.Net.HttpStatusCode.OK;

        try
        {
            using (var dataContext = new DataClassesDataContext())
            {
                Person person = dataContext.Persons.SingleOrDefault(
                    prod => prod.PersonId == Convert.ToInt32(personId));

                if (person == null)
                {
                    person = new Person();
                    dataContext.Persons.InsertOnSubmit(person);
                    status = System.Net.HttpStatusCode.Created;
                }

                person.FirstName = personData.FirstName;
                person.LastName = personData.LastName;
                person.Department = personData.Department;
                person.EmailAddress = personData.EmailAddress;

                dataContext.SubmitChanges();
                ctx.OutgoingResponse.StatusCode = status;
                return;
            }
        }
        catch
        {
            ctx.OutgoingResponse.StatusCode = System.Net.HttpStatusCode.BadRequest;
            return;
        }
    }

    public void DeletePerson(string personId)
    {
        WebOperationContext ctx = WebOperationContext.Current;
 
        try
        {
            using (var dataContext = new DataClassesDataContext())
            {
                Person person = dataContext.Persons.SingleOrDefault(p => p.PersonId == Convert.ToInt32(personId));
                if (person == null)
                {
                    ctx.OutgoingResponse.StatusCode = System.Net.HttpStatusCode.NotFound;
                    return;
                }
 
                dataContext.Persons.DeleteOnSubmit(person);
                dataContext.SubmitChanges();
 
                ctx.OutgoingResponse.StatusCode = System.Net.HttpStatusCode.OK;
                return;
            }
        }
        catch
        {
            ctx.OutgoingResponse.StatusCode = System.Net.HttpStatusCode.BadRequest;
            return;
        }
    }


    List<hrm_contact.Entity.ContactEntity> IRESTHRService.GetAllContact()
    {
        return ContactBRL.GetAll();
    }

    public hrm_contact.Entity.ContactEntity GetContact(string contactId)
    {
        return ContactBRL.GetOne(Int32.Parse(contactId));
    }
}
