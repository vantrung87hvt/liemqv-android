using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace JSONWebApp
{
    [DataContract]
    public class Employee
    {
        [DataMember]
        public int EmployeeId { get; set; }
        [DataMember]
        public string FirstName { get; set; }
        [DataMember]
        public string LastName { get; set; }
        [DataMember]
        public string Address { get; set; }
        [DataMember]
        public string BloodGroup { get; set; }
    }
}