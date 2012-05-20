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
    public class Address_categoryDAL : SqlProvider<Address_categoryEntity>
    {
        static Address_categoryDAL()
        {
            InitReader();
        }
        protected static void InitReader()
        {
            getFromReader=delegate(SqlDataReader dr)
            {
                Address_categoryEntity entity = new Address_categoryEntity();
				entity._id = short.Parse("0"+dr["_id"].ToString());
				entity.sCategoryname = dr["sCategoryname"].ToString();
                return entity;
            };
        }
        public static Address_categoryEntity GetOne(short PK_iAddress_categoryID)
        {
            string cmdName = "tblEmail_category_GetOne";
            SqlParameter p = new SqlParameter("@_id", PK_iAddress_categoryID);
            Address_categoryEntity entity = GetOne(cmdName, p);
            return entity;
        }
        public static List<Address_categoryEntity> GetAll()
        {
            string cmdName = "tblEmail_category_GetAll";
            return GetList(cmdName);
        }
        
        public static int Add(Address_categoryEntity entity)
        {
            string cmdName = "tblEmail_category_Insert";
            return Run(cmdName,true,initParams(entity));

        }
        public static bool Edit(Address_categoryEntity entity)
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
        private static SqlParameter[] initParams(Address_categoryEntity entity)
        {
            SqlParameter[] p = new SqlParameter[2];
			p[0] = new SqlParameter("@_id", entity._id);
			p[1] = new SqlParameter("@sCategoryname", entity.sCategoryname);
            return p;
        }
        #endregion
       
    }
}