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
    public class OrganizationEntity
    {
        private Int32 id;
        [DataMember]
        public Int32 _id
        {
            get { return id; }
            set { id = value; }
        }

        private String scompany;
        [DataMember]
        public String sCompany
        {
            get { return scompany; }
            set { scompany = value; }
        }

        private String stitle;
        [DataMember]
        public String sTitle
        {
            get { return stitle; }
            set { stitle = value; }
        }

        private Int32 fk_icontactid;
        [DataMember]
        public Int32 FK_iContactID
        {
            get { return fk_icontactid; }
            set { fk_icontactid = value; }
        }

        #region Comparison
        public static List<OrganizationEntity> Sort(List<OrganizationEntity> list, String SortExpression, String SortDirection)
        {
            string strComparisonAttrb="COMPARISON_"+SortExpression;
            PropertyInfo propInfo = typeof(OrganizationEntity).GetProperty(strComparisonAttrb);
            if (propInfo != null)
            {
                list.Sort(propInfo.GetGetMethod().Invoke(null, null) as Comparison<OrganizationEntity>);
                if (SortDirection == "DESC")
                    list.Reverse();
            }
            return list;
        }
        #endregion
    }
}
