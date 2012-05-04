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
    public class PhoneEntity
    {
        private Int32 id;
        [DataMember]
        public Int32 _id
        {
            get { return id; }
            set { id = value; }
        }

        private String snumber;
        [DataMember]
        public String sNumber
        {
            get { return snumber; }
            set { snumber = value; }
        }

        private short fk_iphone_categoryid;
        [DataMember]
        public short FK_iPhone_categoryID
        {
            get { return fk_iphone_categoryid; }
            set { fk_iphone_categoryid = value; }
        }

        private Int32 fk_icontactid;
        [DataMember]
        public Int32 FK_iContactID
        {
            get { return fk_icontactid; }
            set { fk_icontactid = value; }
        }

        #region Comparison
        public static List<PhoneEntity> Sort(List<PhoneEntity> list, String SortExpression, String SortDirection)
        {
            string strComparisonAttrb="COMPARISON_"+SortExpression;
            PropertyInfo propInfo = typeof(PhoneEntity).GetProperty(strComparisonAttrb);
            if (propInfo != null)
            {
                list.Sort(propInfo.GetGetMethod().Invoke(null, null) as Comparison<PhoneEntity>);
                if (SortDirection == "DESC")
                    list.Reverse();
            }
            return list;
        }
        #endregion
    }
}
