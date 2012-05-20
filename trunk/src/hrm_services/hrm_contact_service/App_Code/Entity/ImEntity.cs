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
    public class ImEntity
    {
        private Int32 id;
        [DataMember]
        public Int32 _id
        {
            get { return id; }
            set { id = value; }
        }

        private String sim;
        [DataMember]
        public String sIm
        {
            get { return sim; }
            set { sim = value; }
        }

        private short fk_iim_categoryid;
        [DataMember]
        public short FK_iIm_categoryID
        {
            get { return fk_iim_categoryid; }
            set { fk_iim_categoryid = value; }
        }

        private Int32 fk_icontactid;
        [DataMember]
        public Int32 FK_iContactID
        {
            get { return fk_icontactid; }
            set { fk_icontactid = value; }
        }

        #region Comparison
        public static List<ImEntity> Sort(List<ImEntity> list, String SortExpression, String SortDirection)
        {
            string strComparisonAttrb="COMPARISON_"+SortExpression;
            PropertyInfo propInfo = typeof(ImEntity).GetProperty(strComparisonAttrb);
            if (propInfo != null)
            {
                list.Sort(propInfo.GetGetMethod().Invoke(null, null) as Comparison<ImEntity>);
                if (SortDirection == "DESC")
                    list.Reverse();
            }
            return list;
        }
        #endregion
    }
}
