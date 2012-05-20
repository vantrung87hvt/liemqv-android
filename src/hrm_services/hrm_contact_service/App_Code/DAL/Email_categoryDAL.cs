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
    public class Email_categoryDAL : SqlProvider<Email_categoryEntity>
    {
        static Email_categoryDAL()
        {
            InitReader();
        }
        protected static void InitReader()
        {
            getFromReader=delegate(SqlDataReader dr)
            {
                Email_categoryEntity entity = new Email_categoryEntity();
				entity._id = short.Parse("0"+dr["_id"].ToString());
				entity.sCategoryname = dr["sCategoryname"].ToString();
                return entity;
            };
        }
        public static Email_categoryEntity GetOne(short PK_iEmail_categoryID)
        {
            string cmdName = "tblEmail_category_GetOne";
            SqlParameter p = new SqlParameter("@_id", PK_iEmail_categoryID);
            Email_categoryEntity entity = GetOne(cmdName, p);
            return entity;
        }
        public static List<Email_categoryEntity> GetAll()
        {
            string cmdName = "tblEmail_category_GetAll";
            return GetList(cmdName);
        }
        
        public static int Add(Email_categoryEntity entity)
        {
            string cmdName = "tblEmail_category_Insert";
            return Run(cmdName,true,initParams(entity));

        }
        public static bool Edit(Email_categoryEntity entity)
        {
            string cmdName = "tblEmail_category_Update";
            return Run(cmdName,false,initParams(entity))>0;
        }
        public static bool Remove(short _id)
        {
            string cmdName = "tblEmail_category_Delete";
            SqlParameter p = new SqlParameter("@_id", _id);
            return Run(cmdName,false,p)>0;
        }
        #region private
        private static SqlParameter[] initParams(Email_categoryEntity entity)
        {
            SqlParameter[] p = new SqlParameter[2];
			p[0] = new SqlParameter("@_id", entity._id);
			p[1] = new SqlParameter("@sCategoryname", entity.sCategoryname);
            return p;
        }
        #endregion
       
    }
}