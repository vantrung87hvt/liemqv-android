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
    public class ContactEntity
    {
        public ContactEntity()
        {
			
        }
        [DataMember]
        public Int32 id;

        public Int32 _id
        {
            get { return id; }
            set { id = value; }
        }
        [DataMember]
        public String sfirstname;

        public String sFirstname
        {
            get { return sfirstname; }
            set { sfirstname = value; }
        }
        [DataMember]
        public String slastname;

        public String sLastname
        {
            get { return slastname; }
            set { slastname = value; }
        }
        [DataMember]
        public short igender;

        public short iGender
        {
            get { return igender; }
            set { igender = value; }
        }
        [DataMember]
        public String snotes;

        public String sNotes
        {
            get { return snotes; }
            set { snotes = value; }
        }
        [DataMember]
        public String snickname;

        public String sNickname
        {
            get { return snickname; }
            set { snickname = value; }
        }
        [DataMember]
        public Byte[] bavatar;

        public Byte[] bAvatar
        {
            get { return bavatar; }
            set { bavatar = value; }
        }

        #region Comparison
        public static List<ContactEntity> Sort(List<ContactEntity> list, String SortExpression, String SortDirection)
        {
            string strComparisonAttrb="COMPARISON_"+SortExpression;
            PropertyInfo propInfo = typeof(ContactEntity).GetProperty(strComparisonAttrb);
            if (propInfo != null)
            {
                list.Sort(propInfo.GetGetMethod().Invoke(null, null) as Comparison<ContactEntity>);
                if (SortDirection == "DESC")
                    list.Reverse();
            }
            return list;
        }
        #endregion
    }
}
