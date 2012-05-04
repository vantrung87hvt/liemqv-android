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
    public class EmailDAL : SqlProvider<EmailEntity>
    {
        static EmailDAL()
        {
            InitReader();
        }
        protected static void InitReader()
        {
            getFromReader=delegate(SqlDataReader dr)
            {
                EmailEntity entity = new EmailEntity();
				entity._id = Int32.Parse("0"+dr["_id"].ToString());
				entity.sEmail = dr["sEmail"].ToString();
                entity.FK_iEmail_categoryID = short.Parse("0" + dr["FK_iEmail_categoryID"].ToString());
                entity.FK_iContactID = Int32.Parse("0" + dr["FK_iContactID"].ToString());
                return entity;
            };
        }
        public static EmailEntity GetOne(Int32 PK_iEmailID)
        {
            string cmdName = "spEmail_GetOne";
            SqlParameter p = new SqlParameter("@_id", PK_iEmailID);
            EmailEntity entity = GetOne(cmdName, p);
            return entity;
        }
        public static List<EmailEntity> GetAll()
        {
            string cmdName = "spEmail_GetAll";
            return GetList(cmdName);
        }
        
        public static int Add(EmailEntity entity)
        {
            string cmdName = "spEmail_Insert";
            return Run(cmdName,true,initParams(entity));

        }
        public static bool Edit(EmailEntity entity)
        {
            string cmdName = "spEmail_Update";
            return Run(cmdName,false,initParams(entity))>0;
        }
        public static bool Remove(Int32 PK_iEmailID)
        {
            string cmdName = "spEmail_Delete";
            SqlParameter p = new SqlParameter("@_id", PK_iEmailID);
            return Run(cmdName,false,p)>0;
        }
        #region private
        private static SqlParameter[] initParams(EmailEntity entity)
        {
            SqlParameter[] p = new SqlParameter[4];
			p[0] = new SqlParameter("@_id", entity._id);
			p[1] = new SqlParameter("@sEmail", entity.sEmail);
			p[2] = new SqlParameter("@FK_iEmail_category", entity.FK_iEmail_categoryID);
			p[3] = new SqlParameter("@FK_iContactID", entity.FK_iContactID);
            return p;
        }
        #endregion
       
    }
}