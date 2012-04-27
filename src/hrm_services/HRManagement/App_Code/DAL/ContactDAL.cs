using hrm_contact.DataAccess.Base;
using hrm_contact.Entity;
using System;
using System.Data;
using System.Data.SqlClient;
using System.Configuration;
using System.Collections.Generic;
using System.Collections;
using System.Web;

namespace hrm_contact.DataAccess
{    
    public class ContactDAL : SqlProvider<ContactEntity>
    {
        static ContactDAL()
        {
            InitReader();
        }
        protected static void InitReader()
        {
            getFromReader=delegate(SqlDataReader dr)
            {
                ContactEntity entity = new ContactEntity();
				entity._id = Int32.Parse("0"+dr["_id"].ToString());
				entity.sFirstname = dr["sFirstname"].ToString();
                entity.sLastname = dr["sLastname"].ToString();
                entity.iGender = short.Parse("0" + dr["iGender"].ToString());
                entity.sNotes = dr["sNotes"].ToString();
                entity.sNickname = dr["sNickname"].ToString();
                try
                {
                    entity.bAvatar = (byte[])(dr["bAnhthe"]);
                }
                catch { }
                return entity;
            };
        }
        public static ContactEntity GetOne(Int32 PK_iContactID)
        {
            string cmdName = "spContact_GetOne";
            SqlParameter p = new SqlParameter("@_id", PK_iContactID);
            ContactEntity entity = GetOne(cmdName, p);
            return entity;
        }
        public static List<ContactEntity> GetAll()
        {
            string cmdName = "spContact_GetAll";
            return GetList(cmdName);
        }
        
        public static int Add(ContactEntity entity)
        {
            string cmdName = "spContact_Insert";
            return Run(cmdName,true,initParams(entity));

        }
        public static bool Edit(ContactEntity entity)
        {
            string cmdName = "spContact_Update";
            return Run(cmdName,false,initParams(entity))>0;
        }
        public static bool Remove(Int32 PK_iContactID)
        {
            string cmdName = "spContact_Delete";
            SqlParameter p = new SqlParameter("@PK_iContactID", PK_iContactID);
            return Run(cmdName,false,p)>0;
        }
        #region private
        private static SqlParameter[] initParams(ContactEntity entity)
        {
            SqlParameter[] p = new SqlParameter[7];
			p[0] = new SqlParameter("@_id", entity._id);
			p[1] = new SqlParameter("@sFirstname", entity.sFirstname);
			p[2] = new SqlParameter("@sLastname", entity.sLastname);
			p[3] = new SqlParameter("@iGender", entity.iGender);
            p[4] = new SqlParameter("@sNotes", entity.iGender);
            p[5] = new SqlParameter("@sNickname", entity.iGender);
            p[6] = new SqlParameter("@bAvatar", entity.iGender);
            return p;
        }
        #endregion
       
    }
}