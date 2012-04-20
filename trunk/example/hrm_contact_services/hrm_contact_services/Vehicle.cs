using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace hrm_contact_services
{
    [DataContract]
    public class Vehicle
    {
        [DataMember(Name = "year")]
        public int Year
        {
            get;
            set;
        }

        [DataMember(Name = "plate")]
        public string Plate
        {
            get;
            set;
        }

        [DataMember(Name = "make")]
        public string Make
        {
            get;
            set;
        }

        [DataMember(Name = "model")]
        public string Model
        {
            get;
            set;
        }
    }
}