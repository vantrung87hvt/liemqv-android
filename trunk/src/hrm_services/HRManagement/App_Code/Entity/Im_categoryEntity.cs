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
    public class Im_categoryEntity
    {
        private short id;
        [DataMember]
        public short _id
        {
            get { return id; }
            set { id = value; }
        }

        private String scategoryname;
        [DataMember]
        public String sCategoryname
        {
            get { return scategoryname; }
            set { scategoryname = value; }
        }
        #region Comparison
        public static List<Im_categoryEntity> Sort(List<Im_categoryEntity> list, String SortExpression, String SortDirection)
        {
            string strComparisonAttrb="COMPARISON_"+SortExpression;
            PropertyInfo propInfo = typeof(Im_categoryEntity).GetProperty(strComparisonAttrb);
            if (propInfo != null)
            {
                list.Sort(propInfo.GetGetMethod().Invoke(null, null) as Comparison<Im_categoryEntity>);
                if (SortDirection == "DESC")
                    list.Reverse();
            }
            return list;
        }
        #endregion
    }
}
