using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Activation;

namespace hrm_contact.Entity
{
    [DataContract]
    public class WebsiteEntity
    {
        private Int32 id;
        [DataMember]
        public Int32 _id
        {
            get { return id; }
            set { id = value; }
        }

        private String swebsite;
        [DataMember]
        public String sWebsite
        {
            get { return swebsite; }
            set { swebsite = value; }
        }

        private Int32 fk_icontactid;
        [DataMember]
        public Int32 FK_iContactID
        {
            get { return fk_icontactid; }
            set { fk_icontactid = value; }
        }

        #region Comparison
        public static List<WebsiteEntity> Sort(List<WebsiteEntity> list, String SortExpression, String SortDirection)
        {
            string strComparisonAttrb="COMPARISON_"+SortExpression;
            PropertyInfo propInfo = typeof(WebsiteEntity).GetProperty(strComparisonAttrb);
            if (propInfo != null)
            {
                list.Sort(propInfo.GetGetMethod().Invoke(null, null) as Comparison<WebsiteEntity>);
                if (SortDirection == "DESC")
                    list.Reverse();
            }
            return list;
        }
        #endregion
    }
}
