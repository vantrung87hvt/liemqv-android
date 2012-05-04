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
    public class Phone_categoryDAL : SqlProvider<Phone_categoryEntity>
    {
        static Phone_categoryDAL()
        {
            InitReader();
        }
        protected static void InitReader()
        {
            getFromReader=delegate(SqlDataReader dr)
            {
                Phone_categoryEntity entity = new Phone_categoryEntity();
				entity._id = short.Parse("0"+dr["_id"].ToString());
				entity.sCategoryname = dr["sCategoryname"].ToString();
                return entity;
            };
        }
        public static Phone_categoryEntity GetOne(short PK_iPhone_categoryID)
        {
            string cmdName = "tblPhone_category_GetOne";
            SqlParameter p = new SqlParameter("@_id", PK_iPhone_categoryID);
            Phone_categoryEntity entity = GetOne(cmdName, p);
            return entity;
        }
        public static List<Phone_categoryEntity> GetAll()
        {
            string cmdName = "tblPhone_category_GetAll";
            return GetList(cmdName);
        }
        
        public static int Add(Phone_categoryEntity entity)
        {
            string cmdName = "tblPhone_category_Insert";
            return Run(cmdName,true,initParams(entity));

        }
        public static bool Edit(Phone_categoryEntity entity)
        {
            string cmdName = "tblPhone_category_Update";
            return Run(cmdName,false,initParams(entity))>0;
        }
        public static bool Remove(short _id)
        {
            string cmdName = "tblPhone_category_Delete";
            SqlParameter p = new SqlParameter("@_id", _id);
            return Run(cmdName,false,p)>0;
        }
        #region private
        private static SqlParameter[] initParams(Phone_categoryEntity entity)
        {
            SqlParameter[] p = new SqlParameter[2];
			p[0] = new SqlParameter("@_id", entity._id);
			p[1] = new SqlParameter("@sCategoryname", entity.sCategoryname);
            return p;
        }
        #endregion
       
    }
}