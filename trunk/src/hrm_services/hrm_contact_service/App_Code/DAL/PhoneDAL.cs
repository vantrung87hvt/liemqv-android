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
    public class PhoneDAL : SqlProvider<PhoneEntity>
    {
        static PhoneDAL()
        {
            InitReader();
        }
        protected static void InitReader()
        {
            getFromReader=delegate(SqlDataReader dr)
            {
                PhoneEntity entity = new PhoneEntity();
				entity._id = Int32.Parse("0"+dr["_id"].ToString());
				entity.sNumber = dr["sNumber"].ToString();
                entity.FK_iPhone_categoryID = short.Parse("0" + dr["FK_iPhone_categoryID"].ToString());
                entity.FK_iContactID = Int32.Parse("0" + dr["FK_iContactID"].ToString());
                return entity;
            };
        }
        public static PhoneEntity GetOne(Int32 PK_iPhoneID)
        {
            string cmdName = "spPhone_GetOne";
            SqlParameter p = new SqlParameter("@_id", PK_iPhoneID);
            PhoneEntity entity = GetOne(cmdName, p);
            return entity;
        }
        public static List<PhoneEntity> GetAll()
        {
            string cmdName = "spPhone_GetAll";
            return GetList(cmdName);
        }
        
        public static int Add(PhoneEntity entity)
        {
            string cmdName = "spPhone_Insert";
            return Run(cmdName,true,initParams(entity));

        }
        public static bool Edit(PhoneEntity entity)
        {
            string cmdName = "spPhone_Update";
            return Run(cmdName,false,initParams(entity))>0;
        }
        public static bool Remove(Int32 PK_iPhoneID)
        {
            string cmdName = "spPhone_Delete";
            SqlParameter p = new SqlParameter("@_id", PK_iPhoneID);
            return Run(cmdName,false,p)>0;
        }
        #region private
        private static SqlParameter[] initParams(PhoneEntity entity)
        {
            SqlParameter[] p = new SqlParameter[4];
			p[0] = new SqlParameter("@_id", entity._id);
			p[1] = new SqlParameter("@sNumber", entity.sNumber);
			p[2] = new SqlParameter("@FK_iPhone_category", entity.FK_iPhone_categoryID);
			p[3] = new SqlParameter("@FK_iContactID", entity.FK_iContactID);
            return p;
        }
        #endregion
       
    }
}