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
        private Int32 id;
        [DataMember]
        public Int32 _id
        {
            get { return id; }
            set { id = value; }
        }

        private String sfirstname;
        [DataMember]
        public String sFirstname
        {
            get { return sfirstname; }
            set { sfirstname = value; }
        }

        private String slastname;
        [DataMember]
        public String sLastname
        {
            get { return slastname; }
            set { slastname = value; }
        }

        private short igender;
        [DataMember]
        public short iGender
        {
            get { return igender; }
            set { igender = value; }
        }

        private String snotes;
        [DataMember]
        public String sNotes
        {
            get { return snotes; }
            set { snotes = value; }
        }

        private String snickname;
        [DataMember]
        public String sNickname
        {
            get { return snickname; }
            set { snickname = value; }
        }

        private Byte[] bavatar;
        [DataMember]
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
