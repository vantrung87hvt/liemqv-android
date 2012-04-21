using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.ServiceModel;
using System.Collections;

namespace JSONServices
{
    [ServiceContract(Namespace = "http://DotNetByExample/JSONDemoService")]
    public interface IJSONService
    {
        [OperationContract]
        SampleDataObject GetSingleObject(int id, string name);

        [OperationContract]
        ICollection GetMultipleObjects(int number);
    }
}