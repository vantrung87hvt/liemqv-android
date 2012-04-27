using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Activation;

[DataContract]
public class PersonData
{
    [DataMember]
    public int PersonId;
    [DataMember]
    public string FirstName;
    [DataMember]
    public string LastName;
    [DataMember]
    public string EmailAddress;
    [DataMember]
    public string Department;
}

[ServiceContract(Namespace = "urn:hr")]
[AspNetCompatibilityRequirements(RequirementsMode = AspNetCompatibilityRequirementsMode.Allowed)]
public class HRService
{
	// Add [WebGet] attribute to use HTTP GET
	[OperationContract]
    public IList<PersonData> GetAll()
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

            return persons.ToList();
        }
    }

    [OperationContract]
    public PersonData GetPerson(int personId)
    {
        using (var personCtx = new DataClassesDataContext())
        {
            // Set up the query
            var person = (from p in personCtx.Persons
                          where p.PersonId == personId
                          select
                                  new PersonData
                                      {
                                              PersonId = p.PersonId,
                                              FirstName = p.FirstName,
                                              LastName = p.LastName,
                                              EmailAddress = p.EmailAddress,
                                              Department = p.Department
                                      }).Single();

            return person;
        }
    }

    [OperationContract]
    public void UpdatePerson(int personId, PersonData newData)
    {
        using (var personCtx = new DataClassesDataContext())
        {
            // Set up the query
            var person = personCtx.Persons.Single(p => p.PersonId == personId);
            if (person != null)
            {
                person.FirstName = newData.FirstName;
                person.LastName = newData.LastName;
                person.EmailAddress = newData.EmailAddress;
                person.Department = newData.Department;
                personCtx.SubmitChanges();
            }
        }
    }

    [OperationContract]
    public void AddPerson(PersonData p)
    {
        using (var personCtx = new DataClassesDataContext())
        {
            var person = new Person
            {
                PersonId = p.PersonId,
                FirstName = p.FirstName,
                LastName = p.LastName,
                EmailAddress = p.EmailAddress,
                Department = p.Department
            };

            personCtx.Persons.InsertOnSubmit(person);
            personCtx.SubmitChanges();
        }
    }

    [OperationContract]
    public void DeletePerson(int personId)
    {
        using (var personCtx = new DataClassesDataContext())
        {
            var person = personCtx.Persons.Single(p => p.PersonId == personId);
            if (person != null)
            {
                personCtx.Persons.DeleteOnSubmit(person);
                personCtx.SubmitChanges();
            }
        }
    }
}
