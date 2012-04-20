using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace hrm_contact_services
{
    [ServiceContract(Namespace = "http://services.example.com")]
    public interface IVehicleService
    {
        [OperationContract]
        [WebGet(
            UriTemplate = "GetPlates",
            BodyStyle = WebMessageBodyStyle.WrappedRequest,
            ResponseFormat = WebMessageFormat.Json,
            RequestFormat = WebMessageFormat.Json)]
        IList<string> GetPlates();

        [OperationContract]
        [WebGet(UriTemplate = "GetVehicle/{plate}",
            BodyStyle = WebMessageBodyStyle.WrappedRequest,
            ResponseFormat = WebMessageFormat.Json,
            RequestFormat = WebMessageFormat.Json)]
        Vehicle GetVehicle(string plate);

        [OperationContract]
        [WebInvoke(
            Method = "POST",
            UriTemplate = "SaveVehicle",
            BodyStyle = WebMessageBodyStyle.WrappedRequest,
            ResponseFormat = WebMessageFormat.Json,
            RequestFormat = WebMessageFormat.Json)]
        void SaveVehicle(Vehicle vehicle);
    }
}
