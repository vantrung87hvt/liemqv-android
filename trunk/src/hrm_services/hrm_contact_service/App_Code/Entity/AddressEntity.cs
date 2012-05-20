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
    public class AddressEntity
    {
        private Int32 id;
        [DataMember]
        public Int32 _id
        {
            get { return id; }
            set { id = value; }
        }

        private String sstreet;
        [DataMember]
        public String sStreet
        {
            get { return sstreet; }
            set { sstreet = value; }
        }

        private String scity;
        [DataMember]
        public String sCity
        {
            get { return scity; }
            set { scity = value; }
        }

        private String state;
        [DataMember]
        public String sState
        {
            get { return state; }
            set { state = value; }
        }

        private String szipcode;
        [DataMember]
        public String sZipcode
        {
            get { return szipcode; }
            set { szipcode = value; }
        }

        private short fk_iaddress_category;
        [DataMember]
        public short FK_iAddress_categoryID
        {
            get { return fk_iaddress_category; }
            set { fk_iaddress_category = value; }
        }

        private Int32 fk_icontactid;
        [DataMember]
        public Int32 FK_iContactID
        {
            get { return fk_icontactid; }
            set { fk_icontactid = value; }
        }

        #region Comparison
        public static List<AddressEntity> Sort(List<AddressEntity> list, String SortExpression, String SortDirection)
        {
            string strComparisonAttrb="COMPARISON_"+SortExpression;
            PropertyInfo propInfo = typeof(AddressEntity).GetProperty(strComparisonAttrb);
            if (propInfo != null)
            {
                list.Sort(propInfo.GetGetMethod().Invoke(null, null) as Comparison<AddressEntity>);
                if (SortDirection == "DESC")
                    list.Reverse();
            }
            return list;
        }
        #endregion
    }
}
